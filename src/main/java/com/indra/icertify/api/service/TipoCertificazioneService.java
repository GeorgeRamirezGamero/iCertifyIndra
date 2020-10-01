package com.indra.icertify.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.icertify.api.dao.TipoCertificazioneDao;
import com.indra.icertify.api.entity.TipoCertificazione;
import com.indra.icertify.api.entity.servicebean.CertificazioneByTipoCertificazione;
import com.indra.icertify.api.entity.servicebean.Esito;
import com.indra.icertify.api.entity.servicebean.GetAllCertByAllTipoCer;
import com.indra.icertify.api.entity.servicebean.GetAllCertByAllTipoCertResponse;

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
	
	public GetAllCertByAllTipoCertResponse getAllCertificazioneByAllTipoCertificazione () {
		
		Esito esito = new Esito(0, "");
		GetAllCertByAllTipoCertResponse response = new GetAllCertByAllTipoCertResponse();
		Map<Integer, List<String>> map = new HashMap<>();

		
		try {
			log.info("**Microservice /getAllCertificazioneByAllTipoCertificazione Start**");
			List<Object> procedureCertifiTypeCertificazione = tipoCertificazioneDao.getAllCertificazioneByAllTipoCertificazione();
			List<CertificazioneByTipoCertificazione> listBe = new ArrayList<>();
		
			Iterator<Object> it = procedureCertifiTypeCertificazione.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				int idTipoCertificazione = (int)line[0];
				String DescrizioneCertificazione = (String)line[1];
				String DescrizioneTipoCertificazione = (String)line[2]; 
				CertificazioneByTipoCertificazione cert = new CertificazioneByTipoCertificazione (idTipoCertificazione, DescrizioneCertificazione, DescrizioneTipoCertificazione);
				listBe.add(cert);
				
			}
			
			map = datiOnMap(listBe);
			response = createGetAllCertificazioneByAllTipoCertificazione (map);
			esito.setDescrizione("Chiamata effettuata correttamente");
			
		} catch (Exception e) {
			esito.setCodErrore(-1);
			esito.setDescrizione("Errore a interfacciarsi con il DB --> " + e.getMessage());
			log.info("****Exception getAllCertificazioneByAllTipoCertificazione**** --> "+ e.getMessage());
			log.info("****Exception getAllCertificazioneByAllTipoCertificazione**** --> "+ e.getLocalizedMessage());
		}
		log.info("***Microservice /getAllCertificazioneByAllTipoCertificazione End***");
		
		response.setEsito(esito);
		return response;
		
	}

	
	public GetAllCertByAllTipoCertResponse createGetAllCertificazioneByAllTipoCertificazione(Map<Integer, List<String>> map) {
		
		GetAllCertByAllTipoCertResponse response = new GetAllCertByAllTipoCertResponse();
		GetAllCertByAllTipoCer object = new GetAllCertByAllTipoCer();
		
		List<GetAllCertByAllTipoCer> list = new ArrayList<>();
		
		for (Integer key : map.keySet()) {
			object = new GetAllCertByAllTipoCer();
			List<String> listFromKey = new ArrayList<>();
			listFromKey = map.get(key);
			
			object.setCodiceTipoCertificazione(key);
			object.getCertificazioni().addAll(listFromKey);
			list.add(object);
		}
		
		response.getGetAllCertByAllTipoCer().addAll(list);
		return response;		
	}

	public Map<Integer, List<String>> datiOnMap (List<CertificazioneByTipoCertificazione> listBe){
		
		Map<Integer, List<String>> map = new HashMap<>();
		
		for (CertificazioneByTipoCertificazione cert : listBe) {
			List<String> listCertificazione = new ArrayList<String>();
			listCertificazione = map.get(cert.getIdTipoCertificazione());
			
			if (listCertificazione!=null) {
				if (!listCertificazione.contains(cert.getDescrizioneCertificazione())) {
					listCertificazione.add(cert.getDescrizioneCertificazione());
					map.put(cert.getIdTipoCertificazione(), listCertificazione);
				}
			}else {
				listCertificazione = new ArrayList<String>();
				listCertificazione.add(cert.getDescrizioneCertificazione());
				map.put(cert.getIdTipoCertificazione(), listCertificazione);
			}			
		}
		return map;
	}
	
}
