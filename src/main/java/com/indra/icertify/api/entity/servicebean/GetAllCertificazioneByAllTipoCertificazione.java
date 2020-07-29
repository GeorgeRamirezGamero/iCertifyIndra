package com.indra.icertify.api.entity.servicebean;

import java.util.List;

public class GetAllCertificazioneByAllTipoCertificazione {

	private int idTipoCertificazione;
	private List<String> descrizioniCertificazioni;
	
	public GetAllCertificazioneByAllTipoCertificazione(int idTipoCertificazione, List<String> descrizioniCertificazioni) {
		super();
		this.idTipoCertificazione = idTipoCertificazione;
		this.descrizioniCertificazioni = descrizioniCertificazioni;
	}
	
	public int getIdTipoCertificazione() {
		return idTipoCertificazione;
	}
	
	public void setIdTipoCertificazione(int idTipoCertificazione) {
		this.idTipoCertificazione = idTipoCertificazione;
	}
	
	public List<String> getDescrizioniCertificazioni() {
		return descrizioniCertificazioni;
	}
	
	public void setDescrizioniCertificazioni(List<String> descrizioniCertificazioni) {
		this.descrizioniCertificazioni = descrizioniCertificazioni;
	}
	
}
