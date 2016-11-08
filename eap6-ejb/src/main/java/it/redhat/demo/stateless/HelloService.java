package it.redhat.demo.stateless;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HelloService {
	
	@Inject
	private Logger log;
	
	public String createHelloMessage(String name) {
	      return "Hello " + name + "!";
	}
	
	@Schedule(second="*/1", minute="*",hour="*", persistent=false)
	public void repeat() {
		log.info("repeat");
	}
	
	@Asynchronous
	public Future<String> asyncInvoke() {
		
		log.info("start async invoke");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			log.warning(e.getMessage());
		}
		
		log.info("end async invoke");
		
		return new AsyncResult<String>("end");
		
	}

}
