package com.restassured.googlepalcesapi;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import com.restassured.utilities.RawToJson;
import io.restassured.path.json.JsonPath;

public class AddPlaceRequestWithJsonFile {
	
	static String placeId;
	static JsonPath path;
	
	@Test
	public void addPlaceRequestWithJsonFile() throws IOException {
			
			baseURI = "https://rahulshettyacademy.com";
			
			String response = given().log().all().queryParam("key", "qaclick123").
			header("Content-Type","application/json").
			body(new String (Files.readAllBytes(Paths.get("/home/mehuljain/eclipse-workspace/RestAssuredAutomation/src/test/java/com/testdata/AddPlaceRequestData.json")))).
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
	}