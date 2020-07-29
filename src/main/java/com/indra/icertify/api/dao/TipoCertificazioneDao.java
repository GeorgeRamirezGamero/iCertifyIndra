package com.indra.icertify.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.icertify.api.entity.TipoCertificazione;
import com.indra.icertify.api.entity.servicebean.CertificazioneByTipoCertificazione;

@Repository
public interface TipoCertificazioneDao extends CrudRepository<TipoCertificazione, Integer> {
	
	TipoCertificazione findByDescrizione(String descrizione) throws Exception;
	
	@Query(value = "CALL GET_ALL_CERTIFICAZIONE_BY_ALL_TIPOCERTIFICAZIONE;", nativeQuery = true) //call storeProcedure, with parameters
	List<Object> getAllCertificazioneByAllTipoCertificazione();

}
