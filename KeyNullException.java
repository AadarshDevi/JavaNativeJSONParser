public class KeyNullException extends RuntimeException {
	public KeyNullException() {
		super("JSON Key is null");
	}
}