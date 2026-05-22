public class KeyAlreadyExistsException extends RuntimeException {
	public KeyAlreadyExistsException(String key) {
		super("Key \"" + key + "\" already exists");
	}
}