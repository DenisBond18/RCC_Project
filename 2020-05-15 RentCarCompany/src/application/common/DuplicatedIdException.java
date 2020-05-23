package application.common;

@SuppressWarnings("serial")
public class DuplicatedIdException extends RuntimeException {

	public DuplicatedIdException(String msg) {
		super(msg);
	}
}
