package com.indra.icertify.api.entity.servicebean;

import java.util.ArrayList;
import java.util.List;

public class GetAllCertByAllTipoCer {

	private int codiceTipoCertificazione;
	private List<String> certificazioni = new ArrayList<>();
	
	
	public int getCodiceTipoCertificazione() {
		return codiceTipoCertificazione;
	}
	public void setCodiceTipoCertificazione(int codiceTipoCertificazione) {
		this.codiceTipoCertificazione = codiceTipoCertificazione;
	}
	public List<String> getCertificazioni() {
		return certificazioni;
	}
	public void setCertificazioni(List<String> certificazioni) {
		this.certificazioni = certificazioni;
	}
	
	
}
