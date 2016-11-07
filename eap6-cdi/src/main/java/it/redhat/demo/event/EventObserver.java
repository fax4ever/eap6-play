package it.redhat.demo.event;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import it.redhat.demo.qualifier.Bike;

import static javax.enterprise.event.TransactionPhase.AFTER_SUCCESS;

@Singleton
public class EventObserver {
	
	@Inject
	private Logger log;
	
	public void bikeConversation(@Observes(during=AFTER_SUCCESS) @Bike EventContent event) {
		log.info(event.toString());
	}

}
