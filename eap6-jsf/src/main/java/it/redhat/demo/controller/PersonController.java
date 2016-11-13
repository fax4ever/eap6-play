package it.redhat.demo.controller;

import it.redhat.demo.model.Person;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class PersonController {
	
	@Inject
	private Logger log;
	
	public void log(Person person) {
		log.info(person.toString());
	}

	public String page(Person person) {
		log.info(person.toString());
		return "page";
	}

	public String person(Person person) {
		log.info(person.toString());
		return "person";
	}

	public void validateNonZero(FacesContext context, UIComponent component, Object componentValue) {
		if (componentValue != null && ((Integer) componentValue != 0)) {
			return;
		}

		UIInput inputComponent = (UIInput) component;
		inputComponent.setValid(false);
		context.addMessage(component.getClientId(context), new FacesMessage("This number cannot be zero"));

	}

	public void ajaxLog() {

	    log.info("using ajax cool (old stuff)");

    }

}
