public class ValueNullException extends RuntimeException {
	public ValueNullException() {
		super("JSON Key is null");
	}
}