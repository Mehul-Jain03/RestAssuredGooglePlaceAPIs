package com.restassured.googlepalcesapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.*;

import com.restassured.utilities.RawToJson;
import com.testdata.GoogleAddPlaceJson;

import io.restassured.path.json.JsonPath;

public class AddPlaceRequestWithParametrization {

	static String placeId;
	static JsonPath path;

	@Test(dataProvider = "AddPlaceData")
	public void addPlaceWithParametrization(String nameOfShop, String address) {

		baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(GoogleAddPlaceJson.addPlaceParamatrization(nameOfShop, address)).when()
				.post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();

		path = RawToJson.rawToJson(response); // For Parsing the Json Object
		placeId = path.getString("place_id");
		System.out.println(placeId);

	}

	@DataProvider(name = "AddPlaceData")
	public Object[][] getData() {

		return new Object[][] { { "Mehul Shoe Shop1", "Near Jain Temple 1" },
				{ "Mehul Shoe Shop2", "Near Jain Temple 2" }, { "Mehul Shoe Shop3", "Near Jain Temple 3" } };

	}

}