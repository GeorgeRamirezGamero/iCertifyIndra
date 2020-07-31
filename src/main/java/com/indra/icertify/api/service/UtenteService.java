package com.indra.icertify.api.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

@Service
public class UtenteService {

    private static final Logger log = LoggerFactory.getLogger(UtenteService.class);
	
	@Autowired
	private UtenteDao utenteDao;

	public UtenteService() {
	}

	public Esito insertUtente (Utente utente) {
		
		Esito esito = new Esito(0, "");
		
		try {
			log.info("**Microservice /insertUtente Start**");
			Utente utenteDb = utenteDao.findByMatricola(utente.getMatricola());
			
			if (utenteDb==null) {

				if (utente.getCertificazioni() != null) {					
					convertCertificazioniToBase64 (utente);
				}else {
					esito.setDescrizione("Devi inserire almeno una certificazione");
					return esito;
				}
				
				String password = utente.getPassword();
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
				utente.setPassword(bCryptPasswordEncoder.encode(password));
				utenteDao.save(utente);
				
				esito.setDescrizione("Chiamata effettuata correttamente");
			}else {
				esito.setDescrizione("Esiste utente con la matricola inserita");
			}
			
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore --> " + e.getMessage());
			log.info("****Exception insertUtente**** --> "+ e.getMessage());
			log.info("****Exception insertUtente**** --> "+ e.getLocalizedMessage());
		}
		log.info("***Microservice /insertUtente End***");
		return esito;
		
	}
	
	public ResponseGetAllUtenti getAllUtenti() {

		Esito esito = new Esito(0, "");
		ResponseGetAllUtenti response = new ResponseGetAllUtenti(new ArrayList<Utente>(), esito);
		
		try {
			log.info("**Microservice /getUtenti Start**");
			
			List<Utente> utenteDb = (List<Utente>)utenteDao.findAll();
			response.getUtenti().addAll(utenteDb);
			if (utenteDb.size()>0)			
				esito.setDescrizione("Chiamata effettuata correttamente");
			else 
				esito.setDescrizione("Non ci sono utenti registrati");
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception ResponseGetAllUtente**** -->"+ e.getMessage());
			log.info("****Exception ResponseGetAllUtente**** -->"+ e.getLocalizedMessage());
		}
		log.info("***Microservice /getUtenti End***");
		return response;
	}

	public ResponseGetUtente getUtenteByMatricola(String matricola) {

		Esito esito = new Esito(0, "");
		ResponseGetUtente response = new ResponseGetUtente();
		
		try {
			if (!matricola.isEmpty() && matricola!=null) {
				log.info("**Microservice /getUtenteByMatricola Start**");
				response.setUtente(utenteDao.findByMatricola(matricola));
				
				if (response.getUtente() == null) {	
					esito.setDescrizione("Non ci sono Utenti con la matricola inserita");
				}else {
					esito.setDescrizione("Chiamata effettuata correttamente");	
				}
				response.setEsito(esito);
			}
			else {
				esito.setCodErrore(-1);
				esito.setDescrizione("Matricola non valida");
				response.setEsito(esito);
			}
			
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception getUtenteByMatricola**** -->"+ e.getMessage());
			log.info("****Exception getUtenteByMatricola**** -->"+ e.getLocalizedMessage());
		}
		log.info("***Microservice /getUtenteByMatricola End***");
		return response;
	}

	public void convertCertificazioniToBase64(Utente utente) throws Exception {
		for (Certificazione certificazione : utente.getCertificazioni()) {

			String filePath = certificazione.getPath();
			byte[] input_file = Files.readAllBytes(Paths.get(filePath));

			byte[] encodedBytes = Base64.getEncoder().encode(input_file);
			String encodedString = new String(encodedBytes);
			
			certificazione.setPayload(encodedString);

//			        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
//
//			        FileOutputStream fos = new FileOutputStream(filePath+newFileName);
//			        fos.write(decodedBytes);
//			        fos.flush();
//			        fos.close();
		}

	}
	
}
