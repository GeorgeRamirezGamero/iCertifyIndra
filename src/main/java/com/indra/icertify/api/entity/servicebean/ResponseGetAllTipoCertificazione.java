package com.indra.icertify.api.entity.servicebean;

import java.util.List;

import com.indra.icertify.api.entity.TipoCertificazione;

public class ResponseGetAllTipoCertificazione {

	private List<TipoCertificazione> utenti;
	private Esito esito;
	
	public ResponseGetAllTipoCertificazione(List<TipoCertificazione> utenti, Esito esito) {
		super();
		this.utenti = utenti;
		this.esito = esito;
	}
	public List<TipoCertificazione> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<TipoCertificazione> utenti) {
		this.utenti = utenti;
	}
	public Esito getEsito() {
		return esito;
	}
	public void setEsito(Esito esito) {
		this.esito = esito;
	}
	

//UTENTEUTENTEUTENTEUTENTEUTENTE
	
}
