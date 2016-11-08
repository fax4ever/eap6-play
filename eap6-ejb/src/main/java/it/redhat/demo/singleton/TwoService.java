package it.redhat.demo.singleton;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@DependsOn("OneSerivce")
public class TwoService {
	
	@Inject
	private Logger log;
	
	@PostConstruct
	public void start() {
		log.info("start");
	}
	
	@PreDestroy
	public void end() {
		log.info("end");
	}

}
