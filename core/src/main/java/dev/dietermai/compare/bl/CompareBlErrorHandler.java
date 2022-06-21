package dev.dietermai.compare.bl;

public class CompareBlErrorHandler implements ICompareErrorHandler {

	@Override
	public void handleError(Throwable e) {
		new CompareBlException(e).printStackTrace();
	}

	@Override
	public void handleError(String string) {
		new CompareBlException(string).printStackTrace();
	}

}
