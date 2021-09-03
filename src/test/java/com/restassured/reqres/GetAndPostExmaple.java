package com.restassured.reqres;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GetAndPostExmaple {
	
	
	@Test
	public void getId() {
		
		baseURI = "https://reqres.in";
		given().get("/api/users?page=2").
		then().
		statusCode(200).
		body("data[3].first_name",equalTo("Byron")).
		body("data.first_name", hasItems("Tobias","Lindsay","Michael"));
		
	}
	
	@Test
	public void createUser() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Mehul");
		map.put("job", "Engineer");
		
		//Via Gson
//		Gson gson = new Gson();
//		String str  = gson.toJson(map);
//		System.out.println(str);
		
		// Via Json Simple
		JSONObject req = new JSONObject(map);
		System.out.println(req);
		
		baseURI = "https://reqres.in";
		
		given().
			header("Content-Type", "application/json").
			body(req.toJSONString()).
			post("/api/users").
			then().
			statusCode(201).
			log().
			all();
	}

}