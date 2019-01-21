package com.reqres.test;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutDataAPITest {

	//update user details
	@Test
	public void testPutExample_UserDetails() {
        
		RequestSpecification httpRequest = RestAssured.given();
        
		httpRequest.header("Content-Type", "application/json");
        
		JSONObject UserRecord = new JSONObject();
		//Test data - 1, for update 
        UserRecord.put("first_name", "Janet");
        UserRecord.put("last_name", "Jim");
       
//        Test data -2
  //      UserRecord.put("name", "Marques");
    //    UserRecord.put("job", "zion resident");
        
          
        httpRequest.body(UserRecord.toString());
        
        Response response = httpRequest.put("https://reqres.in/api/users/2");
       
        int code = response.getStatusCode();
        
        System.out.println("response code " +code);
       	    
	    Assert.assertEquals(code, 200);
	    String body=response.getBody().asString();
	    
	    System.out.println(body);
    }
	
}
