package it.redhat.demo.service;

import java.util.logging.Logger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class BikeServiceDecorator implements BikeService {
	
	@Inject
	private Logger log;

	@Inject
	@Delegate
	@Any
	public BikeService bikeService;

	@Override
	public void startService() {
		log.info("Decore");
		bikeService.startService();
	}

	@Override
	public void stopService() {
		log.info("Decore");
		bikeService.stopService();
	}
	
}
