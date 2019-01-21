package com.reqres.test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SetRequestDataTest {


	// The server encountered a temporary error and could not complete your request.
	//Bad Gateway
	
@Test
public void test1_ConnectRequest()
{
	given().
	when()
	     .request("CONNECT", "https://reqres.in/api/users/")
	 .then()
	      .statusCode(502);
	 
}

//Test for redirecting resource - Moved permanently
@Test
public void test2_ConnectRequest()
{
	given().
	when()
	     .request("CONNECT", "http://reqres.in/api/users/")
	 .then()
	      .statusCode(301);
	 
}

}
