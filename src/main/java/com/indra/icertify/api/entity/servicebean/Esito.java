package com.indra.icertify.api.entity.servicebean;

public class Esito {

	private int codErrore;
	private String descrizione;
	public int getCodErrore() {
		return codErrore;
	}
		
	public Esito(int codErrore, String descrizione) {
		super();
		this.codErrore = codErrore;
		this.descrizione = descrizione;
	}

	public void setCodErrore(int codErrore) {
		this.codErrore = codErrore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	
	
}
