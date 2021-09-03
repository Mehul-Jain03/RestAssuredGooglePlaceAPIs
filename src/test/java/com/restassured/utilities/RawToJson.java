package com.restassured.utilities;

import io.restassured.path.json.JsonPath;

public class RawToJson {

	public static JsonPath rawToJson(String response) {

		JsonPath path = new JsonPath(response);
		return path;

	}

}