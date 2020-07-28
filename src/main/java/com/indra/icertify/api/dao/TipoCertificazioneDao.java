package com.indra.icertify.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.icertify.api.entity.TipoCertificazione;

@Repository
public interface TipoCertificazioneDao extends CrudRepository<TipoCertificazione, Integer> {
	
	TipoCertificazione findByDescrizione(String descrizione) throws Exception;
	

}
