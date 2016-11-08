package it.redhat.demo.singleton;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

@Singleton
@Startup
@Lock(LockType.READ)
public class OneSerivce {
	
	@Inject
	private Logger log;
	
	private Integer value;
	
	@PostConstruct
	public void start() {
		value = 0;
		log.info("start");
	}
	
	@PreDestroy
	public void end() {
		log.info("end");
	}

	public Integer getValue() {
		return value;
	}
	
	@Lock(LockType.WRITE)
	public void increment() {
		value++;
	}
	
}
