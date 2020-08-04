package com.indra.icertify.api.entity.servicebean;

import java.util.ArrayList;
import java.util.List;

import com.indra.icertify.api.entity.Utente;

public class ResponseGetAllUtenti {

	private List<Utente> utenti = new ArrayList<>();
	private Esito esito;
	
//	public ResponseGetAllUtenti(List<Utente> utenti, Esito esito) {
//		super();
//		this.utenti = utenti;
//		this.esito = esito;
//	}
	public List<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	public Esito getEsito() {
		return esito;
	}
	public void setEsito(Esito esito) {
		this.esito = esito;
	}
	
	
	
}
