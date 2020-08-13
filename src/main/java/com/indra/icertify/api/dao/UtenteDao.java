package com.indra.icertify.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indra.icertify.api.entity.Utente;

@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {
	
	Utente findByMatricola(String matricola) throws Exception;
	
	Utente findByEmail(String mail) throws Exception;
	
	
	

}
