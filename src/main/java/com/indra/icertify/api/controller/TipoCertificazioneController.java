package com.indra.icertify.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indra.icertify.api.entity.TipoCertificazione;
import com.indra.icertify.api.entity.servicebean.Esito;
import com.indra.icertify.api.entity.servicebean.GetAllCertByAllTipoCertResponse;
import com.indra.icertify.api.service.TipoCertificazioneService;

@RequestMapping("tipoCertificazione")
@RestController
public class TipoCertificazioneController { 
	
	@Autowired
	private TipoCertificazioneService tipoCertificazioneService;
	
    @RequestMapping(value = "/insertTipoCertificazione", method = RequestMethod.POST, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito insertUtente(@RequestBody TipoCertificazione tipoCertificazione, HttpServletRequest request, HttpServletResponse response) {
		return tipoCertificazioneService.insertTipoCertificazione(tipoCertificazione);
	}
    
    @RequestMapping(value = "/getAllcertificazioneByAllTipoCertificazione", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody GetAllCertByAllTipoCertResponse getAllTipoCertificazione(HttpServletRequest request, HttpServletResponse response) {
		return tipoCertificazioneService.getAllCertificazioneByAllTipoCertificazione();
	}
}
