package it.redhat.demo.integration;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.redhat.demo.stateless.HelloService;

@RunWith(Arquillian.class)
public class StatelessIT extends BaseIT {
	
	@Inject
	private HelloService service;
	
	@Test
	public void go() {
		
		System.out.println(service.createHelloMessage("Fabio"));
		
	}

}
