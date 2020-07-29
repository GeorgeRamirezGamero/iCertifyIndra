package com.indra.icertify.api.entity.servicebean;

import java.util.ArrayList;
import java.util.List;

public class GetAllCertByAllTipoCertResponse {
	
	private Esito esito;
	private List<GetAllCertByAllTipoCer> getAllCertByAllTipoCer = new ArrayList<>() ;
	
	public Esito getEsito() {
		return esito;
	}
	public void setEsito(Esito esito) {
		this.esito = esito;
	}
	public List<GetAllCertByAllTipoCer> getGetAllCertByAllTipoCer() {
		return getAllCertByAllTipoCer;
	}
	public void setGetAllCertByAllTipoCer(List<GetAllCertByAllTipoCer> getAllCertByAllTipoCer) {
		this.getAllCertByAllTipoCer = getAllCertByAllTipoCer;
	}
	
	
}
