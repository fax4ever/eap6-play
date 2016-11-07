package it.redhat.demo.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

@Alternative
@Stateless
public class BikeServiceAlternative implements BikeService {
	
	@Inject
	private Logger log;

	@Override
	public void startService() {
		log.info("Alternative");
	}

	@Override
	public void stopService() {
		log.info("Alternative");
	}

}
