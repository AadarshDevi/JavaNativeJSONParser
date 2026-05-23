public class Launcher {
	static void main() {
//		JSON json = new JSON();
//		json.append("bool", "Hola Mr. Car");
//		IO.println(json);

		String testJSON = """
			{
			  "string": "trident",
			  "bool": null,
			  "int": 12,
			  "double": 134.25,
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

	}
}