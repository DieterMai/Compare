package dev.dietermai.compare.bl;

public class CompareBlException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompareBlException(String message) {
		super(message);
	}

	public CompareBlException(Throwable newsted) {
		super(newsted);
	}

	public CompareBlException(String message, Throwable nested) {
		super(message, nested);
	}
}
