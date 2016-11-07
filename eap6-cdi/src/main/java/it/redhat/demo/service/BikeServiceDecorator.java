package it.redhat.demo.service;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class BikeServiceDecorator implements BikeService {

	@Inject
	@Delegate
	@Any
	public BikeService bikeService;

	@Override
	public void startService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub
		
	}
	
}
