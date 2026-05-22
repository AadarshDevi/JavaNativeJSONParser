public class Launcher {
	static void main() {
		JSON json = new JSON();
		json.append("bool", (String) null);
		IO.println(json);
	}
}