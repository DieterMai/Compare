package dev.dietermai.compare.error;

public class CompareException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompareException(String message) {
		super(message);
	}

	public CompareException(Throwable newsted) {
		super(newsted);
	}

	public CompareException(String message, Throwable nested) {
		super(message, nested);
	}
}
