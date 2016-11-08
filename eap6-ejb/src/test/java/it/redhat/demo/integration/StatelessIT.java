package it.redhat.demo.integration;

import java.util.concurrent.Future;

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
	
	@Test
	public void repeat() throws InterruptedException {
		
		Thread.sleep(3000);
		
	}
	
	@Test
	public void async() throws Exception {
		
		Future<String> asyncInvoke = service.asyncInvoke();
		
		System.out.println("invoke");
		
		String string = asyncInvoke.get();
		
		System.out.println("returning invoke " + string);
		
	}

}
