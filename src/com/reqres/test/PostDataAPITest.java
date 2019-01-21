package com.reqres.test;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostDataAPITest {
	
	
	//Create user 
	
	@Test
	public void test_CreateUserSuccessful()
	{
		RestAssured.given()
		   .when()
		   .body("{\n"+
				"   \"name\":\"siri\", \n"     +
			    "   \"job\": \"software\" \n"  +		   
				   "}")
		   .post("/api/users")
		   .then()
		   .assertThat().log().all()
		   .statusCode(201);
		  
	}
	
	//Creating user or resource, passing first_name field in the body
		@Test
		public void test_CreateUser()
		{
			String myJson="{\"first_name\":\"sbhagi\"}";
			RestAssured.baseURI="https://reqres.in/api/users/";
		    Response r =given()
		    		.contentType("application/json")
		            .body("{\"first_name\":\"sbhagi\"}")
		            .when()
		            .post("");
	         r.then().statusCode(201);	    
		    String body=r.getBody().asString();
		    
		    System.out.println(body);
		    System.out.println(r.headers());
		    
		}	
		
	//Post example 'register' user validate response
		@Test
	    public void test_RegisterUserSuccessful() {
	        RestAssured.baseURI = "https://reqres.in";
	        RequestSpecification httpRequest = RestAssured.given();
	        httpRequest.header("Content-Type", "application/json");
	        // Create new JSON Object
	        JSONObject Credentials = new JSONObject();
	        Credentials.put("email", "saisree@gdqa.com");
	        Credentials.put("password", "pepperfry");
	        httpRequest.body(Credentials.toString());
	        Response response = httpRequest.post("/api/register");
	        
	        response.then().statusCode(201);	    
		    String body=response.getBody().asString();
		    
		    System.out.println(body);
		    System.out.println(response.headers());
	    }
		
		
		//Post example register user validate response, Missing password
				@Test
			    public void test_RegisterUserUnSuccessful() {
			        RestAssured.baseURI = "https://reqres.in";
			        RequestSpecification httpRequest = RestAssured.given();
			        httpRequest.header("Content-Type", "application/json");
			        // Create new JSON Object
			        JSONObject Credentials = new JSONObject();
			        Credentials.put("email", "saisree@gdqa.com");
			       
			        httpRequest.body(Credentials.toString());
			        Response response = httpRequest.post("/api/register");
			        
			        response.then().statusCode(400);	    
				    String body=response.getBody().asString();
				    
				    System.out.println(body);
				    System.out.println(response.headers());
			    }
		
				
				
				//Post example 'register' user validate response & status code when given request name as "registration", Created new resource
				
				@Test
			    public void test_UserRegistration() {
			        RestAssured.baseURI = "https://reqres.in";
			        RequestSpecification httpRequest = RestAssured.given();
			        httpRequest.header("Content-Type", "application/json");
			        // Create new JSON Object
			        JSONObject Credentials = new JSONObject();
			        Credentials.put("email", "saisree@gdqa.com");
			       
			        httpRequest.body(Credentials.toString());
			        Response response = httpRequest.post("/api/registration");
			        
			        response.then().statusCode(201);	    
				    String body=response.getBody().asString();
				    
				    System.out.println(body);
				    System.out.println(response.headers());
			    }
				
				//Post example Login user validate response
				@Test
			    public void test_LoginUserSuccessful() {
			        RestAssured.baseURI = "https://reqres.in";
			        RequestSpecification httpRequest = RestAssured.given();
			        httpRequest.header("Content-Type", "application/json");
			        // Create new JSON Object
			        JSONObject loginCredentials = new JSONObject();
			        loginCredentials.put("email", "saisree@gdqa.com");
			        loginCredentials.put("password", "pepperfry");
			        httpRequest.body(loginCredentials.toString());
			        Response response = httpRequest.post("/api/login");
			        
			        response.then().statusCode(200);	    
				    String body=response.getBody().asString();
				    
				    System.out.println(body);
				    System.out.println(response.headers());
			    }
						
		 
		//Post example Login user validate response, Missing password
		@Test
	    public void test_LoginUserUnSuccessful() {
	        RestAssured.baseURI = "https://reqres.in";
	        RequestSpecification httpRequest = RestAssured.given();
	        httpRequest.header("Content-Type", "application/json");
	        // Create new JSON Object
	        JSONObject loginCredentials = new JSONObject();
	        loginCredentials.put("email", "saisree@gdqa.com");
	       // loginCredentials.put("password", "pepperfry");
	        httpRequest.body(loginCredentials.toString());
	        Response response = httpRequest.post("/api/login");
	        
	        response.then().statusCode(400);	    
		    String body=response.getBody().asString();
		    
		    System.out.println(body);
		    System.out.println(response.headers());
	    }
		
		
	@Test
	public void testPostRequest_GivenUnSupportedPayloadFormat()
	{
	  given()
	  //.headers("AppKey","Key-value")
	  .param("id", "7")
	  .param("first_name", "Siri")
	  .param("last_name", "bhagi")
	  .param("avatar", "null")
	  .when()
	        .post("https://reqres.in/api/users/")
	   .then()
	         .statusCode(415).log().all();
		
	}

	
}
