package dev.dietermai.compare.core.error;

public interface ICompareErrorHandler {
	void handleError(Throwable e);

	void handleError(String string);
}
