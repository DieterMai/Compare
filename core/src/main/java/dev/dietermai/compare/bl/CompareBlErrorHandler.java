package dev.dietermai.compare.bl;

public class CompareBlErrorHandler {

	public static void handleError(Throwable e) {
		new CompareBlException(e).printStackTrace();
	}

	public static void handleError(String string) {
		new CompareBlException(string).printStackTrace();
	}

}
