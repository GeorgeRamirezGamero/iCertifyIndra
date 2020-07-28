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
import com.indra.icertify.api.service.TipoCertificazioneService;

@RequestMapping("tipoCertificazione")
@RestController
public class TipoCertificazioneController { 
	
//    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

	
	@Autowired
	private TipoCertificazioneService tipoCertificazioneService;
	
    @RequestMapping(value = "/insertTipoCertificazione", method = RequestMethod.POST, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito insertUtente(@RequestBody TipoCertificazione tipoCertificazione, HttpServletRequest request, HttpServletResponse response) {
		return tipoCertificazioneService.insertTipoCertificazione(tipoCertificazione);
	}
}
