public class BracketNotFoundException extends RuntimeException {
	public BracketNotFoundException(final char bracket) {
		super("Bracket \"" + bracket + "\" not found");
	}
}