package com.indra.icertify.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TipoCertificazione")
public class TipoCertificazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671417246199538663L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int idTipoCertificazione;
	
	private String descrizione;

	public int getIdTipoCertificazione() {
		return idTipoCertificazione;
	}

	public void setIdTipoCertificazione(int idTipoCertificazione) {
		this.idTipoCertificazione = idTipoCertificazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

////TESTTESTTESTTESTTEST
////TESTTESTTESTTESTTEST
////TESTTESTTESTTESTTEST

////TESTTESTTESTTESTTEST
}
