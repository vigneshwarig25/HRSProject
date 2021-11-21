package HealthRecoverySolutionsTestCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import HealthRecoverySolutionsBase.HealthRecoverySolutionBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HealthRecoverySolutions_API extends HealthRecoverySolutionBase   {
	
	String user = prop.getProperty("username");	
	String password = prop.getProperty("password");

	//Established connection using GET method
	@Test(priority=1)
	public void getapiTest()
	{

		Response resp = RestAssured.get("https://cc.healthrecoverysolutions.com/app/login");
		int sts_code = resp.getStatusCode();
		System.out.println("Get Status code: " +sts_code);
		Assert.assertEquals(sts_code, 200);	
		
	}
	
	//Verifying invalid username and password using PUT method and assertion
	@Test(priority=2)
	public void putapiTest() 
	{
		
		
		Response resp = RestAssured.put("https://cc.healthrecoverysolutions.com/app/login/Login.html?username=+user&password=+password");
		int sts_code = resp.getStatusCode();
		System.out.println("Put Get Status code: " +sts_code);
		Assert.assertEquals(sts_code, 200);	
	}
	
	///Verifying invalid username and password using POST method and assertion
	@Test(priority=3)
	public void postapiTest()
	{
				
		RequestSpecification reqst = RestAssured.given();
		reqst.header("accept", "application/json");
		reqst.header("accept-encoding", "gzip,deflate");
		reqst.header("accept-language", "en-US,en;q=0.8");
		reqst.header("content-Type", "application/json");
	
		HashMap data = new HashMap();
		data.put("type", "credentials");
		data.put("username", user);
		data.put("password", password);
		data.put("source", "ClinicianConnect 2");		
		reqst.body(data.toString());
		Response postresp=reqst.post("https://gateway.healthrecoverysolutions.com/v1/tokens");
		int resp_Code = postresp.getStatusCode();
		//System.out.println("Response code:" +resp_Code);
		Assert.assertEquals(resp_Code, 422);		
		String postrsp = postresp.asString();
		System.out.println("Post response:" +postrsp);		
		Assert.assertEquals(postresp.jsonPath().getString("message"), "The given data was invalid.");		
		}


}
