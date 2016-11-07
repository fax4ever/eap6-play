package it.redhat.demo.stateless;

import javax.ejb.Stateless;

@Stateless
public class HelloService {
	
	public String createHelloMessage(String name) {
	      return "Hello " + name + "!";
	}

}
