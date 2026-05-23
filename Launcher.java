public class Launcher {
	static void main() {
		String testJSON = """
			{
			  "string_arr": [
			        "item 1",
			        "item 2",
			        "item 3"
			      ],
			      "int_arr": [
			        18,
			        23,
			        34
			      ],
			      "double_arr": [
			        12.45,
			        7256.78,
			        25867.70
			      ],
			      "bool_arr": [
			        true,
			        true,
			        false,
			        true
			      ]
			}
			""";

		JSON fromString = new JSON(testJSON);
		IO.println(fromString);

	}
}