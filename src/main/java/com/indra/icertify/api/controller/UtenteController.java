package com.indra.icertify.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.indra.icertify.api.entity.Utente;
import com.indra.icertify.api.entity.servicebean.Esito;
import com.indra.icertify.api.entity.servicebean.ResponseGetAllUtenti;
import com.indra.icertify.api.entity.servicebean.ResponseGetUtente;
import com.indra.icertify.api.service.UtenteService;

@RequestMapping("utente")
@RestController
public class UtenteController { 
	
//    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

	
	@Autowired
	private UtenteService utenteService;
	
    
    @RequestMapping(value = "/getUtenti", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseGetAllUtenti getUtenti(HttpServletRequest request, HttpServletResponse response) {
		return utenteService.getAllUtenti();
	}

    @RequestMapping(value = "/getUtenteByMatricola", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseGetUtente getUtenteByMatricola(@RequestParam (name = "matricola") String matricola, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.getUtenteByMatricola(matricola);
	}

    @RequestMapping(value = "/insertUtente", method = RequestMethod.POST, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito insertUtente(@RequestBody Utente utente, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.insertUtente(utente);
	}
}
