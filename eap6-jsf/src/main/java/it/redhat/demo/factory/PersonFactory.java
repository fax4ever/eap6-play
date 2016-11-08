package it.redhat.demo.factory;

import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

import it.redhat.demo.model.Person;

@Singleton
public class PersonFactory {
	
	@Produces
	@Named
	@SessionScoped
	private Person person = new Person("Fabio", "Ercoli");
	
	@Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
