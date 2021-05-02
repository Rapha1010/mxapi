package com.mxapi.mxapi;

import java.util.ArrayList;

import org.apache.http.entity.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mxapi.repositories.TerminalRepository;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class MxapiApplicationTests {
	
	@Autowired
	private TerminalRepository tr;
	
	@Test
	public void RequiredFieldsAreNotNullsInJsonResponseTest() {
		//RestAssured.baseURI = "http://localhost:8080/mxapi/";
		RestAssured.baseURI = "https://rapha-mxapi.herokuapp.com/mxapi";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("listar");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Required Fields
		ArrayList<Integer> logics = jsonPathEvaluator.get("logic");
		ArrayList<String> serials = jsonPathEvaluator.get("serial");
		ArrayList<String> models = jsonPathEvaluator.get("model");
		ArrayList<String> versions = jsonPathEvaluator.get("version");
		
		for (int i = 0; i < logics.size(); i++) { 
			
			String actual;
			
			actual = Integer.toString(logics.get(i));
			Assert.assertNotNull("logic is null", actual);
			
			actual = serials.get(i);
			Assert.assertNotNull("serial is null", actual);
			
			actual = models.get(i);
			Assert.assertNotNull("model is null", actual);
			
			actual = versions.get(i);
			Assert.assertNotNull("version is null", actual);
		
		}
		
	}
	
	@Test
	public void VerifySearchingValuesInJsonResponseTest() {
		RestAssured.baseURI = "http://localhost:8080/mxapi/buscar";
		//RestAssured.baseURI = "https://rapha-mxapi.herokuapp.com/mxapi";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("8.00b3/terminal/44332211");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		if (jsonPathEvaluator.get("logic") == null) return;
		
		//Required Fields
		ArrayList<Integer> logics = jsonPathEvaluator.get("logic");
		ArrayList<String> serials = jsonPathEvaluator.get("serial");
		ArrayList<String> models = jsonPathEvaluator.get("model");
		ArrayList<String> versions = jsonPathEvaluator.get("version");
		
		for (int i = 0; i < logics.size(); i++) { 
			
			String actual;
			//required values
			actual = Integer.toString(logics.get(i));
			Assert.assertNotNull("logic is null", actual);
			
			actual = serials.get(i);
			Assert.assertNotNull("serial is null", actual);
			
			actual = models.get(i);
			Assert.assertNotNull("model is null", actual);
			
			actual = versions.get(i);
			Assert.assertNotNull("version is null", actual);
		
		}
		
	}
	
	@Test
	public void VerifyIfAddValuesIsWorking() throws JSONException {
		RestAssured.baseURI = "http://localhost:8080/mxapi/";
		//RestAssured.baseURI = "https://rapha-mxapi.herokuapp.com/mxapi";
		RequestSpecification httpRequest = RestAssured.given().urlEncodingEnabled(true)
														.header("Accept", ContentType.DEFAULT_TEXT);
		Response response = httpRequest.get("adicionar");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("text","44332211;777;PWWIN;0;F04A2E4088B;4;8.00b5;0;16777216;PWWIN");
		requestParams.put("LastName", "Singh");
		
		httpRequest.body(requestParams.toString());
		
		System.out.println("-" + response.body().asString());
		
		if (jsonPathEvaluator.get("logic") == null) return;
		
		//Required Fields
		ArrayList<Integer> logics = jsonPathEvaluator.get("logic");
		ArrayList<String> serials = jsonPathEvaluator.get("serial");
		ArrayList<String> models = jsonPathEvaluator.get("model");
		ArrayList<String> versions = jsonPathEvaluator.get("version");
		
		for (int i = 0; i < logics.size(); i++) { 
			
			String actual;
			//required values
			actual = Integer.toString(logics.get(i));
			Assert.assertNotNull("logic is null", actual);
			
			actual = serials.get(i);
			Assert.assertNotNull("serial is null", actual);
			
			actual = models.get(i);
			Assert.assertNotNull("model is null", actual);
			
			actual = versions.get(i);
			Assert.assertNotNull("version is null", actual);
		
		}
		
	}

}
