package it.redhat.demo.integration;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.redhat.demo.singleton.OneSerivce;

@RunWith(Arquillian.class)
public class SingletonIT extends BaseIT {
	
	@Inject
	private OneSerivce one;

	@Test
	public void go() {
		
		one.increment();
		one.increment();
		one.increment();
		
		Integer value = one.getValue();
		System.out.println(value + "");
		
		
	}
	
}
