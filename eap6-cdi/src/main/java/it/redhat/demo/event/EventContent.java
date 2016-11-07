package it.redhat.demo.event;

public class EventContent {
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "EventContent [content=" + content + "]";
	}

}
