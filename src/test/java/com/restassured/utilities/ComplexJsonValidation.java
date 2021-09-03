package com.restassured.utilities;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class ComplexJsonValidation {

	static String complexResponse = "{\n" + "\n" + "		\"dashboard\": {\n" + "\n"
			+ "		\"purchaseAmount\": 910,\n" + "\n" + "		\"website\": \"rahulshettyacademy.com\"\n" + "\n"
			+ "		},\n" + "\n" + "		\"courses\": [\n" + "\n" + "		{\n" + "\n"
			+ "		\"title\": \"Selenium Python\",\n" + "\n" + "		\"price\": 50,\n" + "\n"
			+ "		\"copies\": 6\n" + "\n" + "		},\n" + "\n" + "		{\n" + "\n"
			+ "		\"title\": \"Cypress\",\n" + "\n" + "		\"price\": 40,\n" + "\n" + "		\"copies\": 4\n"
			+ "\n" + "		},\n" + "\n" + "		{\n" + "\n" + "		\"title\": \"RPA\",\n" + "\n"
			+ "		\"price\": 45,\n" + "\n" + "		\"copies\": 10\n" + "\n" + "		}\n" + "\n" + "		]\n"
			+ "\n" + "		}\n" + "";

//

//

//

//	
	@Test
	public void testingComplexJson() {

		JsonPath path = RawToJson.rawToJson(complexResponse);

//		1. Print No of courses returned by API : courses.size()
		int courseLength = path.getInt("courses.size()");
		System.out.println(courseLength);

//		2. Print Purchase Amount : dashboard.purchaseAmount
		int purchaseAmount = path.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

//		3. Print Title of the first course : courses[0].title
		String titleFirstCourse = path.getString("courses[0].title");
		System.out.println(titleFirstCourse);

//		4. Print All course titles and their respective Prices
		for (int i = 0; i < courseLength; i++) {
			System.out.println(path.getString("courses[" + i + "].title"));
			System.out.println(path.getString("courses[" + i + "].price"));
		}

//		5. Print no of copies sold by RPA Course
		for (int i = 0; i < courseLength; i++) {
			String courseTitle = path.getString("courses[" + i + "].title");
			if (courseTitle.equals("RPA")) {
				System.out.println(path.getString("courses[" + i + "].copies"));
				break;
			}
		}

//		6. Verify if Sum of all Course prices matches with Purchase Amount
		int sum = 0;
		for (int i = 0; i < courseLength; i++) {
			sum = sum + (path.getInt("courses[" + i + "].price") * path.getInt("courses[" + i + "].copies"));
		}
		System.out.println("Sum is => " + sum);
		Assert.assertEquals(purchaseAmount, sum);

	}

}