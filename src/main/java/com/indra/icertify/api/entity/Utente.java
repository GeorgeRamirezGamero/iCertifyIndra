package com.indra.icertify.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table(name = "utente")
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int idUtente;
	
	private String cognome;
	
	private String email;
	
	private Boolean mailVerificata = false;
	
	private String matricola;
	
	private String nome;
	
	private String password;		

	private String ruolo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDocIdent")
	private DocumentoIdentita documentoIdentita;
	
	@OneToMany(targetEntity = Certificazione.class, /*mappedBy = "utente",*/ cascade = CascadeType.ALL)
	private List<Certificazione> certificazioni = null;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			  name = "utente_progetto", 
			  joinColumns = @JoinColumn(name = "utente_id"), 
			  inverseJoinColumns = @JoinColumn(name = "progetto_id"))
	private List<Progetto> progetti;

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public Boolean getMailVerificata() {
		return mailVerificata;
	}

	public void setMailVerificata(Boolean mailVerificata) {
		this.mailVerificata = mailVerificata;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public DocumentoIdentita getDocumentoIdentita() {
		return documentoIdentita;
	}

	public void setDocumentoIdentita(DocumentoIdentita documentoIdentita) {
		this.documentoIdentita = documentoIdentita;
	}

	public List<Certificazione> getCertificazioni() {
		return certificazioni;
	}

	public void setCertificazioni(List<Certificazione> certificazioni) {
		this.certificazioni = certificazioni;
	}

	public List<Progetto> getProgetti() {
		return progetti;
	}

	public void setProgetti(List<Progetto> progetti) {
		this.progetti = progetti;
	}
	
	
	

}
