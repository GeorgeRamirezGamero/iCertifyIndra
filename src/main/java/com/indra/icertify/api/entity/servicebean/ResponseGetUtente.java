package com.indra.icertify.api.entity.servicebean;

import com.indra.icertify.api.entity.Utente;

public class ResponseGetUtente {

	private Utente utente;
	private Esito esito;
	
//	public ResponseGetUtente(Utente utente, Esito esito) {
//		super();
//		this.utente = utente;
//		this.esito = esito;
//	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}
	
	
}
