package dev.dietermai.compare.bl;

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
