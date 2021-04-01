package com.indra.icertify.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DocumentoIdentita")
public class DocumentoIdentita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671417246199538663L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long idDocumentoIdentita;
	
	private String descrizione;
	
	private String numeroCarta;

	public Long getIdDocumentoIdentita() {
		return idDocumentoIdentita;
	}

	public void setIdDocumentoIdentita(Long idDocumentoIdentita) {
		this.idDocumentoIdentita = idDocumentoIdentita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
}
