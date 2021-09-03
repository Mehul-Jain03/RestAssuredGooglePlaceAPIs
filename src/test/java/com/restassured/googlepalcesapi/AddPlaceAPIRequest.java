package com.restassured.googlepalcesapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.restassured.utilities.RawToJson;
import com.testdata.GoogleAddPlaceJson;

import io.restassured.path.json.JsonPath;

public class AddPlaceAPIRequest {
	
	
	// Test Case is: Adding a place in Google Maps, Updating the Address again and Calling Get API 
	//to validate the updated address.
	
	//given : all input details required to hit the api
	//when : Submit the api
	//then : after hitting the api validate the response
	
	static String placeId;
	static JsonPath path;
	
	@Test(priority = 1)
	public void addPlaceInGoogleMaps() {
		
	baseURI = "https://rahulshettyacademy.com";
		
	String response = given().log().all().queryParam("key", "qaclick123").
	header("Content-Type","application/json").
	body(GoogleAddPlaceJson.addPlaceNormal()).
	when().
	post("/maps/api/place/add/json").
	then().
	assertThat().
	statusCode(200).body("scope", equalTo("APP")).
	header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
	
	path= RawToJson.rawToJson(response); // For Parsing the Json Object
	placeId = path.getString("place_id");
	System.out.println(placeId);
	
	}
	
	@Test(priority = 2)
	public void updateAddress() {
		
		baseURI = "https://rahulshettyacademy.com";
		
		 given().log().all().queryParam("key", "qaclick123").
			header("Content-Type","application/json").
			body("{\n"
					+ "\"place_id\":\""+placeId+"\",\n"
					+ "\"address\":\"Near Jain Temple Shewtamber, Aspur\",\n"
					+ "\"key\":\"qaclick123\"\n"
					+ "}").
			when().
			put("/maps/api/place/update/json").
			then().log().all().assertThat().body("msg", equalTo("Address successfully updated")).statusCode(200);
		
	}
	
	@Test(priority = 3)
	public void getPlace() {
		System.out.println("In Get Place API");
		baseURI = "https://rahulshettyacademy.com";
		
		 given().log().all().
		 queryParam("key", "qaclick123").
		 queryParam("place_id",placeId).
		 when().
		 get("/maps/api/place/get/json").
		 then().
		 assertThat().body("address", equalTo("Near Jain Temple Shewtamber, Aspur")).statusCode(200);
		
	}
}