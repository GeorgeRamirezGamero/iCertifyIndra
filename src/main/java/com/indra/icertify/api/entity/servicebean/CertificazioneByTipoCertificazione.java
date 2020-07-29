package com.indra.icertify.api.entity.servicebean;

public class CertificazioneByTipoCertificazione {

	private int idTipoCertificazione;
	private String descrizioneTipoCertificazione;
	private String descrizioneCertificazione;
	public CertificazioneByTipoCertificazione(int idTipoCertificazione, String descrizioneTipoCertificazione,
			String descrizioneCertificazione) {
		super();
		this.idTipoCertificazione = idTipoCertificazione;
		this.descrizioneTipoCertificazione = descrizioneTipoCertificazione;
		this.descrizioneCertificazione = descrizioneCertificazione;
	}
	public int getIdTipoCertificazione() {
		return idTipoCertificazione;
	}
	public void setIdTipoCertificazione(int idTipoCertificazione) {
		this.idTipoCertificazione = idTipoCertificazione;
	}
	public String getDescrizioneTipoCertificazione() {
		return descrizioneTipoCertificazione;
	}
	public void setDescrizioneTipoCertificazione(String descrizioneTipoCertificazione) {
		this.descrizioneTipoCertificazione = descrizioneTipoCertificazione;
	}
	public String getDescrizioneCertificazione() {
		return descrizioneCertificazione;
	}
	public void setDescrizioneCertificazione(String descrizioneCertificazione) {
		this.descrizioneCertificazione = descrizioneCertificazione;
	}
		
}
