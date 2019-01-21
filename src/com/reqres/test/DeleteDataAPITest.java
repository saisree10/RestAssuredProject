package com.reqres.test;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteDataAPITest {
	
	
	//verify sample data for existence before to deletion
	@Test
	public void test_GetUserInfo() {
	    given().when().get("https://reqres.in/api/users/12")
	        .then().statusCode(200).log().all();
	}
	
	//Performing record deletion
	
	@Test
    public void test_DeleteUser() {
		
        RequestSpecification httpRequest = RestAssured.given();
   
        Response response = httpRequest.delete("https://reqres.in/api/users/?first_name=Janet&id=2");
        //  Test data : https://reqres.in/api/users/12 where id=12 
   // Tried for test data where id=571 =>, Result: empty body as {}
        
        int code=response.getStatusCode();
        
        // It returns error code 204 which means Server successfully processed the request. But, Not returning any response which is correct as per given documentation for API
           
        Assert.assertEquals(code, 204);
	    
	    
    }
	
}
