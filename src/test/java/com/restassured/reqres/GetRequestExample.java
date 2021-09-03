package com.restassured.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class GetRequestExample {

	@Test
	public void methodOne() {
//		RestAssured.baseURI = "https://reqres.in";
//		RestAssured.basePath = "/api/users";
//		Response resp = RestAssured.given().queryParam("page", 2).header("Content-Type", "application/json")
//				.request(Method.GET).prettyPeek();
//		JsonPath path = resp.jsonPath();
//		Object obj = path.get("data[0].first_name");
//		Assert.assertEquals(obj.toString(), "Michael");

		Response res = get("https://reqres.in/api/users?page=2");
		System.out.println(res.getStatusCode());
		System.out.println(res.getHeader("content-type"));
		System.out.println(res.getBody().prettyPeek());
		System.out.println(res.getTime());

		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test
	public void methodTwo() {

		baseURI = "https://reqres.in";
		given().
		get("/api/users?page=2").
		then().
		statusCode(200).
		body("data[1].email",equalTo("lindsay.ferguson@reqres.in")).
		log().all();

	}
}
