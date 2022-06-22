package dev.dietermai.compare.error;

import dev.dietermai.compare.bl.ICompareErrorHandler;

public class CompareErrorHandler implements ICompareErrorHandler {

	@Override
	public void handleError(Throwable e) {
		new CompareException(e).printStackTrace();
	}

	@Override
	public void handleError(String string) {
		new CompareException(string).printStackTrace();
	}

}
