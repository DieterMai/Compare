package dev.dietermai.compare.bl;

public interface ICompareErrorHandler {
	void handleError(Throwable e);

	void handleError(String string);
}
