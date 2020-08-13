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
	
    
    @RequestMapping(value = "/getUtenti", method = RequestMethod.GET, produces = {"application/json"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseGetAllUtenti getUtenti(HttpServletRequest request, HttpServletResponse response) {
		return utenteService.getAllUtenti();
	}

    @RequestMapping(value = "/getUtenteByMatricola", method = RequestMethod.GET, produces = {"application/json"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseGetUtente getUtenteByMatricola(@RequestParam (name = "matricola") String matricola, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.getUtenteByMatricola(matricola);
	}

    @RequestMapping(value = "/insertUtente", method = RequestMethod.POST, produces = {"application/json"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito insertUtente(@RequestBody Utente utente, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.insertUtente(utente);
	}
    
    @RequestMapping(value = "/verificaMail", method = RequestMethod.GET, produces = {"application/json"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito registraUtente (@RequestParam String mail, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.verificaMail(mail, request);
	}
    
    @RequestMapping(value = "/confirmMail", method = RequestMethod.GET, produces = {"application/json"}) 
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Esito confirmMail (@RequestParam String token, HttpServletRequest request, HttpServletResponse response) {
		return utenteService.confirmMail(token, request);
	}
    
    
//  @RequestMapping(value = "/getUtenteByTipoCertificazione", method = RequestMethod.GET, produces = {"application/json"}) 
//  @ResponseStatus(HttpStatus.OK)
//	public @ResponseBody ResponseGetAllUtenti getUtenteByTipoCertificazione(@RequestParam String descCertificazione, HttpServletRequest request, HttpServletResponse response) {
//		return utenteService.getUtenteByTipoCertificazione(descCertificazione);
//	}
    
//    //MICROSERVIZIO FASULLO
//    @RequestMapping(value = "/getUtenteByMatricolaW2SO", method = RequestMethod.GET, produces = {"application/json"}) 
//    @ResponseStatus(HttpStatus.OK)
//	public @ResponseBody ResponseEntity<String>  getUtenteByMatricolaPost(HttpServletRequest request, HttpServletResponse response) {
//		return utenteService.getUtenteByMatricolaWSO2();
//	}
//    //MICROSERVIZIO FASULLO
    
}
