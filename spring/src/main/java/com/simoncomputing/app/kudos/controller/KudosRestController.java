package com.simoncomputing.app.kudos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simoncomputing.app.kudos.entity.Kudos;
import com.simoncomputing.app.kudos.service.KudosService;

@RestController
@RequestMapping("/kudos")
public class KudosRestController {

	@Autowired
	private KudosService kudosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Kudos>> getAllKudos() {		
		List<Kudos> kudos = kudosService.findAllByOrderByEntryDateTimeDesc();
		
		return new ResponseEntity<List<Kudos>>(kudos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{kudosId}")
	public ResponseEntity<Kudos> getKudos(@PathVariable Long kudosId) {

		Kudos kudos = kudosService.findOne(kudosId);

		return kudos == null ? new ResponseEntity<Kudos>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<Kudos>(kudos, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Kudos> saveKudos(@RequestBody Kudos kudos) {

		Kudos updatedKudos = kudosService.save(kudos);

		return new ResponseEntity<Kudos>(updatedKudos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{kudosId}")
	public ResponseEntity<Kudos> deleteKudos(@PathVariable Long kudosId) {

		Kudos u = kudosService.findOne(kudosId);
		
		if (u == null) {
			return new ResponseEntity<Kudos>(HttpStatus.NOT_FOUND);
		} else {
			kudosService.delete(kudosId);
			return new ResponseEntity<Kudos>(u, HttpStatus.OK);
		}
	}

}