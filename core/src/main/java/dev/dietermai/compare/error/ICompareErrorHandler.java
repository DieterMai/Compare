package dev.dietermai.compare.error;

public interface ICompareErrorHandler {
	void handleError(Throwable e);

	void handleError(String string);
}
