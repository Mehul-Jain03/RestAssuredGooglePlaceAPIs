package com.restassured.reqres;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
//import java.util.HashMap;
//import java.util.Map;

public class PutPatchAndDeleteExample {
	
	@Test
	public void putRequest() {
		JSONObject putObject = new JSONObject();
	//	putObject.put("name", "Mehul");
	//	putObject.put("job", "zion resident");
		baseURI = "https://reqres.in/api";
		given().
		header("Content-Type", "application/json").
		when().
		body(putObject.toJSONString()).
		put("/users/2").
		then().
		statusCode(200).
		log().
		all();
	}
	
	@Test
	public void patchRequest() {
		JSONObject putObject = new JSONObject();
	//	putObject.put("name", "Mehul");
	//	putObject.put("job", "zion resident");
		baseURI = "https://reqres.in/api";
		given().
		header("Content-Type", "application/json").
		when().
		body(putObject.toJSONString()).
		patch("/users/2").
		then().
		statusCode(200).
		log().
		all();
		
	}
	
	@Test
	public void deleteRequest() {
		baseURI = "https://reqres.in/api";
		given().
		when().
		delete("/users/2").
		then().
		statusCode(204).
		log().
		all();	
	}
}