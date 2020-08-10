package com.rupeek.RestAssured.API;

import com.rupeek.RestAssured.setUp.TestSetup;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetCustomerApi extends TestSetup {

	public static Response authenticate()
	{
		Response response=given().baseUri(configProperties.getBaseURI()).header("Content-Type","application/json")
				.body("{\n" +
				"    \"username\":\"rupeek\",\n" +
				"    \"password\":\"password\"\n" +
				"}").log().all().when()
				.post(configProperties.getAuthenticateEndpoint()).then().log().all().extract().response();
		
		return response;
		
		
	}
	
	
	public static  Response GetCustomers(String token)
	{
		Response response=given().baseUri(configProperties.getBaseURI()).basePath(configProperties.getBasePath()).
		header("Authorization","Bearer "+token).log().all()
				.when()
		        .get(configProperties.getGetcustomerAPIEndPoint()).then().log().all().extract().response();
		
		return response;
		
		
	}
	public static  Response GetCustomerswithPhone(String token,String phone)
	{
		Response response=given().baseUri(configProperties.getBaseURI()).basePath(configProperties.getBasePath()).
				header("Authorization","Bearer "+token)
				.pathParam("phone",phone).log().all()
				.when()
				.get(configProperties.getGetSpecificCustomerEndpoint()).then().log().all().extract().response();

		return response;


	}
}
