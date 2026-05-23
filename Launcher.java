public class Launcher {
	static void main() {
		String testJSON = """
			{
			  "double": 134.25,
			   "string_arr": [
				 "item 1",
				 "item 2",
				 "item 3"
			   ]
			}
			""";

		JSON fromString = new JSON(testJSON);
		IO.println(fromString);

	}
}