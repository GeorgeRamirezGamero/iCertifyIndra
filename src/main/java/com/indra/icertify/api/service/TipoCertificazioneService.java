package com.indra.icertify.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.icertify.api.dao.TipoCertificazioneDao;
import com.indra.icertify.api.entity.TipoCertificazione;
import com.indra.icertify.api.entity.servicebean.Esito;

@Service
public class TipoCertificazioneService {

    private static final Logger log = LoggerFactory.getLogger(TipoCertificazioneService.class);
	
	@Autowired
	private TipoCertificazioneDao tipoCertificazioneDao;

	public TipoCertificazioneService() {
	}

	public Esito insertTipoCertificazione (TipoCertificazione tipoCertificazione) {
		
		Esito esito = new Esito(0, "");
		
		try {
			log.info("**Microservice /insertTipoCertificazione Start**");
			TipoCertificazione tipoCertificazioneBe = tipoCertificazioneDao.findByDescrizione(tipoCertificazione.getDescrizione());
			
			if (tipoCertificazioneBe==null) {
				tipoCertificazioneDao.save(tipoCertificazione);
				esito.setDescrizione("Chiamata effettuata correttamente");
			}else {
				esito.setDescrizione("Esiste già il tipo di certificazione inserito");
			}
			
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception insertTipoCertificazione**** --> "+ e.getMessage());
			log.info("****Exception insertTipoCertificazione**** --> "+ e.getLocalizedMessage());
		}
		log.info("***Microservice /insertTipoCertificazione End***");
		return esito;
		
	}

}
