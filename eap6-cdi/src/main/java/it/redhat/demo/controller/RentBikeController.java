package it.redhat.demo.controller;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import it.redhat.demo.event.EventContent;
import it.redhat.demo.interceptor.Logged;
import it.redhat.demo.qualifier.Bike;
import it.redhat.demo.qualifier.Veichle;

@ConversationScoped
@Logged
public class RentBikeController implements Serializable {

	@Inject
	@Bike
	private Veichle veichle;

	@Inject
	private Conversation conversation;

	@Inject
	@Bike
	private Event<EventContent> event;

	public void go() {
		if (!conversation.isTransient()) {
			return;
		}

		conversation.begin();

		EventContent eventContent = new EventContent();
		eventContent.setContent("start conversation");
		event.fire(eventContent);
	}

	public void stop() {
		conversation.end();

		EventContent eventContent = new EventContent();
		eventContent.setContent("end conversation");
		event.fire(eventContent);
	}

}
