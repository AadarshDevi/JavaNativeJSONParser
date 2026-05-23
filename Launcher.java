public class Launcher {
	static void main() {
		String testJSON = """
			{
			  "string": "trident",
			  "bool": null,
			  "int": 12,
			  "double": 134.25,
			}
			""";

		JSON fromString = new JSON(testJSON);
		IO.println(fromString);

	}
}