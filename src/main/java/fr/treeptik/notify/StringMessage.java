package fr.treeptik.notify;

public class StringMessage implements Message {
	
	private String message;

	public StringMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		return message;
	}
}
