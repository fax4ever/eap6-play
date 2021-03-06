package it.redhat.demo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BikeServiceImpl implements BikeService {
	
	@Inject
	private Logger log;

	@Override
	public void startService() {
		log.info("Basic!!");
	}

	@Override
	public void stopService() {
		log.info("Basic!!");
	}

}
