package it.redhat.demo.controller;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.redhat.demo.model.Person;

@Named
@RequestScoped
public class PersonController {
	
	@Inject
	private Logger log;
	
	public void log(Person person) {
		log.info(person.toString());
	}

}
