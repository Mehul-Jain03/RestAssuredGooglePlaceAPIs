package com.restassured.googlepalcesapi;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.pojo.classes.AddPlacePojo;
import com.pojo.classes.LocationPojo;

public class AddPlaceAPIRequestViaPOJO {
	
	@Test
	public void getList() {

		AddPlacePojo ap = new AddPlacePojo();
		List<String> types = new ArrayList<String>();
		types.add("Peak");
		types.add("Peek");
		LocationPojo lp = new LocationPojo();
		lp.setLat(-18.0001);
		lp.setLng(32.33333);
		ap.setAccuracy(12);
		ap.setAddress("Near Pratap Circle");
		ap.setLanguage("English");
		ap.setLocation(lp);
		ap.setName("Karan Kumar");
		ap.setPhone_number("+(91) 8696821212");
		ap.setTypes(types);
		ap.setWebsite("www.linkedin.com");
		
		baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").
				header("Content-Type","application/json").
				body(ap).
				when().
				post("/maps/api/place/add/json").
				then().
				assertThat().
				statusCode(200).body("scope", equalTo("APP")).
				header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		
		System.out.println(response);
	}
}

