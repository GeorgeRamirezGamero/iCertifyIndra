package com.indra.icertify.api.service;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.indra.icertify.api.dao.UtenteDao;
import com.indra.icertify.api.entity.Certificazione;
import com.indra.icertify.api.entity.Utente;
import com.indra.icertify.api.entity.servicebean.Esito;
import com.indra.icertify.api.entity.servicebean.ResponseGetAllUtenti;
import com.indra.icertify.api.entity.servicebean.ResponseGetUtente;
import com.indra.icertify.api.util.SendMail;

@Service
public class UtenteService {

	private static final Logger log = LoggerFactory.getLogger(UtenteService.class);

	@Autowired
	private UtenteDao utenteDao;

	@Autowired
	private SendMail sendMail;

	public UtenteService() {
	}

	public Esito insertUtente(Utente utente) {

		Esito esito = new Esito(0, "");

		try {
			log.info("**Microservice /insertUtente Start**");
			Utente utenteDb = utenteDao.findByMatricola(utente.getMatricola());

			if (utenteDb == null) {

				if (utente.getCertificazioni() != null) {
					convertCertificazioniToBase64(utente);
				} else {
					esito.setDescrizione("Devi inserire almeno una certificazione");
					return esito;
				}

				String password = utente.getPassword();
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
				utente.setPassword(bCryptPasswordEncoder.encode(password));
				utenteDao.save(utente);

				esito.setDescrizione("Chiamata effettuata correttamente");
			} else {
				esito.setDescrizione("Esiste utente con la matricola inserita");
			}

		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore --> " + e.getMessage());
			log.info("****Exception insertUtente**** --> " + e.getMessage());
			log.info("****Exception insertUtente**** --> " + e.getLocalizedMessage());
		}
		log.info("***Microservice /insertUtente End***");
		return esito;

	}

	public ResponseGetAllUtenti getAllUtenti() {

		Esito esito = new Esito(0, "");
		ResponseGetAllUtenti response = new ResponseGetAllUtenti();
		response.setEsito(esito);
		List<Utente> utenteDb = new ArrayList<>();

		try {
			log.info("**Microservice /getUtenti Start**");

			utenteDb = (List<Utente>) utenteDao.findAll();
			response.getUtenti().addAll(utenteDb);
			if (utenteDb.size() > 0)
				esito.setDescrizione("Chiamata effettuata correttamente");
			else
				esito.setDescrizione("Non ci sono utenti registrati");
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception ResponseGetAllUtente**** -->" + e.getMessage());
			log.info("****Exception ResponseGetAllUtente**** -->" + e.getLocalizedMessage());
		}
		log.info("***Microservice /getUtenti End***");
		return response;
	}

	public ResponseGetUtente getUtenteByMatricola(String matricola) {

		Esito esito = new Esito(0, "");
		ResponseGetUtente response = new ResponseGetUtente();

		try {
			if (!matricola.isEmpty() && matricola != null) {
				log.info("**Microservice /getUtenteByMatricola Start**");
				response.setUtente(utenteDao.findByMatricola(matricola));

				if (response.getUtente() == null) {
					esito.setDescrizione("Non ci sono Utenti con la matricola inserita");
				} else {
					esito.setDescrizione("Chiamata effettuata correttamente");
					convertBase64ToCertificazione(response.getUtente());
				}
				response.setEsito(esito);
			} else {
				esito.setCodErrore(-1);
				esito.setDescrizione("Matricola non valida");
				response.setEsito(esito);
			}

		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception getUtenteByMatricola**** -->" + e.getMessage());
			log.info("****Exception getUtenteByMatricola**** -->" + e.getLocalizedMessage());
		}
		log.info("***Microservice /getUtenteByMatricola End***");
		return response;
	}

	public Esito verificaMail(String email, HttpServletRequest request) {

		Esito esito = new Esito(0, "");
		Utente UtenteFromDB = new Utente();

		try {
			log.info("**Microservice /verificaMail Start**");
			UtenteFromDB = utenteDao.findByEmail(email);

			if (UtenteFromDB != null) {
				// manda la mail alla mail indicata,
				if (UtenteFromDB.getMailVerificata() == null || !UtenteFromDB.getMailVerificata()) {

					String token = generateNewToken();
					sendMail.invioMail(email, token);

					// METTO IN SESSIONE IL TOKEN
					HttpSession session = request.getSession();
					session.setAttribute("token", token);
					session.setAttribute("matricola", UtenteFromDB.getMatricola());

				} else {
					esito.setCodErrore(2);
					esito.setDescrizione("La mail per questa utenza è stata già verificata");
				}

			} else {
				esito.setCodErrore(-1);
				esito.setDescrizione("Mail non corrisponde ad alcuna utenza registrata");
			}

		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore --> " + e.getMessage());
			log.info("****Exception verificaMail**** --> " + e.getMessage());
			log.info("****Exception verificaMail**** --> " + e.getLocalizedMessage());
		}
		log.info("***Microservice /verificaMail End***");
		return esito;

	}

	public Esito confirmMail(String token, HttpServletRequest request) {

		Esito esito = new Esito(0, "");
		String tokenSession = "";
		Utente utenteFromDB = new Utente();

		try {
			log.info("**Microservice /verificaMail Start**");

			HttpSession session = request.getSession();
			tokenSession = (String) session.getAttribute("token");

			if (tokenSession.equals(token)) {

				utenteFromDB = utenteDao.findByMatricola((String) session.getAttribute("matricola"));

				if (utenteFromDB != null) {

					utenteFromDB.setMailVerificata(true);

					utenteDao.save(utenteFromDB);

				} else {
					esito.setCodErrore(2);
					esito.setDescrizione("Non è stato trovata alcuna utenza con i dati inseriti");
				}

			} else {
				esito.setCodErrore(-1);
				esito.setDescrizione("Token non valido");
			}

		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore --> " + e.getMessage());
			log.info("****Exception verificaMail**** --> " + e.getMessage());
			log.info("****Exception verificaMail**** --> " + e.getLocalizedMessage());
		}
		log.info("***Microservice /verificaMail End***");
		return esito;

	}

	public void convertCertificazioniToBase64(Utente utente) throws Exception {
		for (Certificazione certificazione : utente.getCertificazioni()) {

			String filePath = certificazione.getPath();
			byte[] input_file = Files.readAllBytes(Paths.get(filePath));

			byte[] encodedBytes = Base64.getEncoder().encode(input_file);
			String encodedString = new String(encodedBytes);

			certificazione.setPayload(encodedString);

		}

	}

	public void convertBase64ToCertificazione(Utente utente) throws Exception {
		for (Certificazione certificazione : utente.getCertificazioni()) {

			byte[] decodedBytes = Base64.getDecoder().decode(certificazione.getPayload().getBytes());

			FileOutputStream fos = new FileOutputStream(
					"C:\\\\Users\\\\garamirez\\\\Documents\\\\" + utente.getCognome() + ".pdf");
			fos.write(decodedBytes);
			fos.flush();
			fos.close();
		}

	}

	public static String generateNewToken() {

		SecureRandom secureRandom = new SecureRandom();
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();

		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);

	}

//	public ResponseGetAllUtenti getUtenteByTipoCertificazione (String descCertificazione) {
//
//		//TO DO 
//		return null;
//	}

//	public ResponseEntity<String> getUtenteByMatricolaWSO2() {
//		return new ResponseEntity<>("GET UTENTE BY MATRICOLA WSO2", HttpStatus.OK);
//
//	}
}
