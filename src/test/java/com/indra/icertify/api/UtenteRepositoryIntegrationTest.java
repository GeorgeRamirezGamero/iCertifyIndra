package com.indra.icertify.api;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.indra.icertify.api.entity.Certificazione;
import com.indra.icertify.api.entity.DocumentoIdentita;
import com.indra.icertify.api.entity.Utente;
import com.indra.icertify.api.entity.servicebean.Esito;
import com.indra.icertify.api.entity.servicebean.ResponseGetAllUtenti;
import com.indra.icertify.api.service.UtenteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtenteRepositoryIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(UtenteRepositoryIntegrationTest.class);
	
	
	@Autowired
	private UtenteService utenteService;
	
	@Test
	public void testGetAllUtente() {
		
		ResponseGetAllUtenti response= utenteService.getAllUtenti();
		assertTrue(response != null);
	}
	
//	@Test
//	public void testInsertUtente() {
//		Utente utente = new Utente();
//		
//		DocumentoIdentita doc = new DocumentoIdentita();
//		doc.setDescrizione("descrizioneDocumentoIdentita");
//		doc.setNumeroCarta("AA12345566");
//		
//		Certificazione certificazione1 = new Certificazione();
//		certificazione1.setCodEsame("4444");
//		certificazione1.setDescrizione("descrizioneTipoCertificazione4444");
//		
//		Certificazione certificazione2 = new Certificazione();
//		certificazione2.setCodEsame("5555");
//		certificazione2.setDescrizione("descrizioneTipoCertificazione5555");
//		
//		List<Certificazione> listCertificazione = new ArrayList<>();
//		listCertificazione.add(certificazione1);
//		listCertificazione.add(certificazione2);
//		
//		utente.setCognome("Molinari");
//		utente.setEmail("marty@marty.com");
//		utente.setMatricola("11111");
//		utente.setNome("Martina");
//		utente.setPassword("123123123");
//		utente.setRuolo("Ruolo1");
//		utente.getCertificazioni().addAll(listCertificazione);
//		utente.get
//		
//		Esito response= utenteService.insertUtente(utente);
//		assertTrue(response != null);
//	}
	
}
