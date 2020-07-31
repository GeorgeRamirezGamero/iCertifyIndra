package com.indra.icertify.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Certificazione")
public class Certificazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671417246199538663L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int idCertificazione;

	private String codEsame;
	
	private String descrizioneCertificazione;
	
	private String path;
	
	private String payload;

	private int tipoCertificazione;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "idUtente")
//	private Utente utente;
	
	public int getIdCertificazione() {
		return idCertificazione;
	}

	public void setIdCertificazione(int idCertificazione) {
		this.idCertificazione = idCertificazione;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public int getTipoCertificazione() {
		return tipoCertificazione;
	}

	public void setTipoCertificazione(int tipoCertificazione) {
		this.tipoCertificazione = tipoCertificazione;
	}

	public String getDescrizioneCertificazione() {
		return descrizioneCertificazione;
	}

	public int getIdcertificazione() {
		return idCertificazione;
	}

	public void setIdcertificazione(int idCertificazione) {
		this.idCertificazione = idCertificazione;
	}

//	public Utente getUtente() {
//		return utente;
//	}
//
//	public void setUtente(Utente utente) {
//		this.utente = utente;
//	}

	public void setDescrizioneCertificazione(String descrizioneCertificazione) {
		this.descrizioneCertificazione = descrizioneCertificazione;
	}

	public String getCodEsame() {
		return codEsame;
	}

	public void setCodEsame(String codEsame) {
		this.codEsame = codEsame;
	}
}
