package exception;

public class ParkServiceException extends Exception {

	public ParkServiceException() {
		super();
	}

	public ParkServiceException(String message) {
		super(message);
	}

	public ParkServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParkServiceException(Throwable cause) {
		super(cause);
	}

	public ParkServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
