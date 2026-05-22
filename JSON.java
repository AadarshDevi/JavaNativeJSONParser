public class JSON {
	private final StringBuilder jsonBuilder;
	private boolean firstLine = true;

	public JSON() {
		jsonBuilder = new StringBuilder();
		jsonBuilder.append("{");
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

	public void removeAll() {
		jsonBuilder.setLength(0);
		jsonBuilder.append("{");
	}

	public void remove(String key) {}

	public void removeAll(String... keys) {}

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

	@Override
	public String toString() {
		return getJSON();
	}

	public String getJSON() {
		int i = jsonBuilder.lastIndexOf("}");
		if (i != -1)
			return jsonBuilder.toString();

		return jsonBuilder + "}";
	}

	class Appender {

	}

}