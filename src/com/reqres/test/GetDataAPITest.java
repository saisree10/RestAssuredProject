package com.reqres.test;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class GetDataAPITest {
	
	
// validation of status code
@Test
public void testStatusCode()
{
RestAssured.baseURI="https://reqres.in";
	
	given().
	          param("page","2").		          
	when().
	         get("/api/users")
	       
	  // when asserting with status code 500, Test resulted in assertion failure, A test of negative case which is correct as expected
	.then().assertThat().statusCode(200);
	
	System.out.println("Status 200 'OK' == SUCCESS");
}

//This will verify code and print complete response in the console
@Test
public void testLoggingData()
{
  given().get("https://reqres.in/api/users?page=2").
  then().
      statusCode(200).
      log().all();
	
}

//verifying single body content using org.hamcrest.Matchers library's equalTo method
@Test
public void testEqualToFunction_SingleContent()
{
	
	given().

	 param("page","2"). 
	 param("per_page","3").
	 param("total","12").
	 param("total_pages","4").
	 param("data","first_name").
     	get("https://reqres.in/api/users?page=2/firstname/Charles/").
	  
     // Assert that correct status code is returned.
	then().assertThat().statusCode(200).
	body("data.first_name[1]", equalTo("Charles"));
	
}

//verifying multiple body content using org.hamcrest.Matchers library's equalTo method
@Test
public void testEqualToFunction_MultipleContent()
{
	
	given().

	 param("page","2"). 
	 param("per_page","3").
	 param("total","5").
	 param("total_pages","4").
	 param("data","first_name").
   	get("https://reqres.in/api/users?page=2").
	  
   // Assert that correct status code is returned.
	then().assertThat().statusCode(200)
	
	.body("data.first_name[1]", equalTo("Charles"))
	//.body("data[1].id", equalTo("5"))
	.body("data.last_name[1]", equalTo("Morris"))
	.body("data.avatar[1]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg"));
	
}


//validating or testing with incorrect input for No. of Users
@Test
public void test_RequestInvalidNoOfUsers() {
    given().when().get("/api/users/100000000000000000000")
        .then().statusCode(404);
}

//validation of client side error - 404, Requesting wrong resource name URL which is unavailable in the Server
@Test
public void testGetDetails()
{
RestAssured.baseURI = "https://reqres.in/api/users/Testdata";
RequestSpecification httpRequest = RestAssured.given();
Response response = httpRequest.get("/T1213213188");

// Get the status code from the Response. In case of 
// a successful interaction with the web service, we
// should get a status code of 200 when given correct URL. But,Here we are expecting 404 requesting with incorrect . So, Assertion should pass
int statusCode = response.getStatusCode();

// Assert that correct status code is returned.
Assert.assertEquals(statusCode /*actual value*/, 404 /*expected value*/, "Correct status code returned:?");
}



//verify response type
@Test
public void testContentType()
{
	given()
	.get("https://reqres.in/api/users/")
	.then()
           .statusCode(200)	
           .contentType(ContentType.JSON);
}


//Delay in response, with response in 3512ms
@Test
public void testDelayedResponse()
{
	given()
	.get("https://reqres.in/api/users?delay=3")
	.then()
           .statusCode(200)	
           .contentType(ContentType.JSON);
}

//verify parameters and headers can be set
@Test
public void testParametersAndHeaders()
{
	given().
	param("Key1","value1")
	.param("headA", "valueA")
	.when()
	.get("https://reqres.in/api/users/?page=2")
	.then()
	.statusCode(200).and().header("Server", "cloudflare")
	.and().header("Content-Encoding", "gzip")
	.log().all();
}
}
