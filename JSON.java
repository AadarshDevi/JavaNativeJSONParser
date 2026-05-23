import java.util.ArrayList;

/**
 * J2Parser: A Basic Java JSON Parser. It can do int, double, String, boolean, int[], double[], String[], and boolean[]. Nested arrays or values is not supported.
 */
public class JSON {

	private final StringBuilder jsonBuilder;
	private boolean firstLine = true;

	public JSON(String jsonString) {
		this();
		parser(jsonString);
	}

	public JSON() {
		jsonBuilder = new StringBuilder();
		jsonBuilder.append("{");
	}

	private void parser(String jsonString) {

		final char CURLY_BRACKET_OPEN = '{';
		final char CURLY_BRACKET_CLOSE = '}';

		final char SQUARE_BRACKET_OPEN = '[';
		final char SQUARE_BRACKET_CLOSE = ']';

		final char DOUBLE_QUOTES = '"';
		final char COLON = ':';
		final char COMMA = ',';
		final char NEW_LINE = '\n';

		ArrayList<Character> brackets = new ArrayList<>();

		boolean isArray = false;
		boolean isKey = false;
		boolean isValue = false;
		boolean isBracket;
		boolean nextValue = false;

		StringBuilder key = new StringBuilder();
		StringBuilder value = new StringBuilder();

		for (char letter : jsonString.toCharArray()) {

			switch (letter) {
				case CURLY_BRACKET_OPEN:
					brackets.add(CURLY_BRACKET_OPEN);
					isBracket = true;
					break;
				case SQUARE_BRACKET_OPEN:
					brackets.add(SQUARE_BRACKET_OPEN);
					isBracket = true;
					break;
				case CURLY_BRACKET_CLOSE:
					if (isInvalidBracket(CURLY_BRACKET_OPEN, brackets.getLast()))
						throw new BracketNotFoundException(CURLY_BRACKET_OPEN);
					brackets.removeLast();
					isBracket = true;
					isArray = true;
					break;
				case SQUARE_BRACKET_CLOSE:
					if (isInvalidBracket(SQUARE_BRACKET_OPEN, brackets.getLast()))
						throw new BracketNotFoundException(SQUARE_BRACKET_OPEN);
					brackets.removeLast();
					isBracket = true;
					isArray = false;
					break;
				case COMMA:
					isKey = false;
					isValue = false;
					// todo: use correct append for the data type
					append(key.toString(), value.toString());
					IO.println(key + ": " + value);
					key.setLength(0);
					value.setLength(0);

				default:
					isBracket = false;
			}

			if (isBracket) {
				continue;
			}

			if (letter == DOUBLE_QUOTES && !isKey && !isValue) {
				isKey = true;
			} else if (letter == DOUBLE_QUOTES && isKey) {
				isKey = false;
			} else if (letter == COLON && !isKey && !isValue) {
				isKey = false;
				isValue = true;
			} else if (letter == COLON && isValue) {
				isValue = false;
			}

			if (letter == DOUBLE_QUOTES || letter == COLON || letter == NEW_LINE || letter == COMMA) {
				continue;
			}

			IO.println("\"" + letter + "\"\t " + ((isKey) ? "isKey" : "") + ((isValue) ? "isValue" : ""));

			//noinspection PointlessBooleanExpression
			if (isKey == true && isValue == true)
				throw new RuntimeException("KeyValueSameException");

			if (isKey) {
				key.append(letter);
			} else if (isValue) {
				// need better logic
				value.append(letter);
			}
		}

		IO.println("List length: " + brackets.size());
	}

	private boolean isInvalidBracket(char input, final char checker) {
		return input != checker;
	}

	public void append(String key, String val) {
		addKey(key);
		colon();
		if (val == null)
			jsonBuilder.append("null");
		else
			jsonBuilder.append(string(val));
	}

	/**
	 * check if the key is valid and then adds the key to the JSON and
	 *
	 * @param key the key to be added to the JSON
	 * @throws KeyNullException          when key is null
	 * @throws KeyAlreadyExistsException if key already exists in JSON
	 */
	private void addKey(String key) {

		if (key == null) {
			throw new KeyNullException();
		} else if (key.isEmpty()) {
			throw new KeyEmptyException();
		} else if (jsonBuilder.indexOf(key) != -1) {
			throw new KeyAlreadyExistsException(key);
		}
		comma();
		jsonBuilder.append(string(key));
	}

	private void colon() {
		jsonBuilder.append(":");
	}

	private String string(String string) {
		return String.format("\"%s\"", string);
	}

	private void comma() {
		if (firstLine) {
			firstLine = false;
			return;
		}
		jsonBuilder.append(",");
	}

	public void append(String key, int val) {
		addKey(key);
		colon();
		jsonBuilder.append(val);
	}

	public void append(String key, float val) {
		addKey(key);
		colon();
		jsonBuilder.append(val);
	}

	public void append(String key, float val, int decimals) {
		addKey(key);
		colon();
		String decimalPlaced = "%.#f".replace("#", Integer.toString(decimals));
		String formatted = String.format(decimalPlaced, val);
		jsonBuilder.append(formatted);
	}

	public void append(String key, double val) {
		addKey(key);
		colon();
		jsonBuilder.append(val);
	}

	public void append(String key, double val, int decimals) {
		addKey(key);
		colon();
		String decimalPlaced = "%.#f".replace("#", Integer.toString(decimals));
		String formatted = String.format(decimalPlaced, val);
		jsonBuilder.append(formatted);
	}

	public void append(String key, boolean val) {
		addKey(key);
		colon();
		jsonBuilder.append(val);
	}

	public void append(String key, String[] vals) {
		addKey(key);
		colon();

		jsonBuilder.append("[");
		for (int i = 0; i < vals.length; i++) {
			jsonBuilder.append(string(vals[i]));

			if (i < vals.length - 1) {
				jsonBuilder.append(",");
			}

		}
		jsonBuilder.append("]");
	}

	public void append(String key, int[] vals) {
		addKey(key);
		colon();
		jsonBuilder.append("[");
		for (int i = 0; i < vals.length; i++) {
			jsonBuilder.append(vals[i]);
			if (i < vals.length - 1) {
				jsonBuilder.append(",");
			}
		}
		jsonBuilder.append("]");
	}

	public void append(String key, double[] vals) {
		addKey(key);
		colon();
		jsonBuilder.append("[");
		for (int i = 0; i < vals.length; i++) {
			jsonBuilder.append(vals[i]);
			if (i < vals.length - 1) {
				jsonBuilder.append(",");
			}
		}
		jsonBuilder.append("]");
	}

	public void append(String key, boolean[] vals) {
		addKey(key);
		colon();
		jsonBuilder.append("[");
		for (int i = 0; i < vals.length; i++) {
			jsonBuilder.append(vals[i]);
			if (i < vals.length - 1) {
				jsonBuilder.append(",");
			}
		}
		jsonBuilder.append("]");
	}

	public void append(String key, int decimals, double[] val) {
		addKey(key);
		colon();
		String decimalPlaced = "%.#f".replace("#", Integer.toString(decimals));

		jsonBuilder.append("[");
		for (int i = 0; i < val.length; i++) {
			jsonBuilder.append(String.format(decimalPlaced, val[i]));
			if (i < val.length - 1) {
				jsonBuilder.append(",");
			}
		}
		jsonBuilder.append("]");
	}

	public void removeAll() {
		jsonBuilder.setLength(0);
		jsonBuilder.append("{");
	}

	public void remove(String key) {}

	public void removeAll(String... keys) {}


	@Override
	public String toString() {
		return getJSON();
	}

	public String getJSON() {
		String json = jsonBuilder.toString().trim();
		if (!json.endsWith("}"))
			return json + "}";
		return jsonBuilder.toString();
	}

}