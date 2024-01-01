package SerializationAndDeserialization;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Assert;
import java.util.*;
import org.junit.Test;


public class Serialization extends Auzmor  {

    @Test
   public void getjsonfromclassobject() throws JsonProcessingException
   {
       Auzmor az=new Auzmor();
       az.setLogin("kolli.saikumar@atc.xyz");
       az.setPassword("Saikumar@123");
       System.out.println("The values of the objects");
       System.out.println(az.getLogin());
       System.out.println(az.getPassword());


       ObjectMapper objmapper=new ObjectMapper();
       String auzmorJSON=objmapper.writeValueAsString(az);

       System.out.println(auzmorJSON);
       RequestSpecification reqspec= RestAssured.given();
       reqspec.baseUri("https://learn-staging.api.auzmor.com");
       reqspec.contentType(ContentType.JSON);

       //performing the post project
       reqspec.body(auzmorJSON);
       Response response=reqspec.post("/api/v1/users/login");
       response.prettyPrint();
       System.out.println(response.statusCode());
      if(response.statusCode()==200)
      {
          System.out.println("The Transaction was successful");
      }
      else {
          System.out.println("Not successfull");
      }




   }
   
//In the BDD Format for getting the information from the api
    @Test
    public void postdata() {
        // Create a Map to represent the JSON data
        JSONObject Jsonobject= new JSONObject();
        Jsonobject.put("login","kolli.saikumar@atc.xyz");
        Jsonobject.put("password","Saikumar@123");

        // Specify the base URI
        RestAssured.baseURI = "https://learn-staging.api.auzmor.com";

        // Perform the POST request

        RestAssured.given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(Jsonobject)
                .when()
                .post("/api/v1/users/login")  // Replace with your actual endpoint
                .then()
                .statusCode(200).log().all();
        Response response=RestAssured.get("https://learn-staging.api.auzmor.com");

        if( response.getStatusCode()==200)
        {
            System.out.println("Success");
        }
        else {
            System.out.println("Not success");
        }

    }


     /*
   Initially getting the data from the API
    @Test

    public void gettest()
    {
       Response response= RestAssured.get("https://learn-staging.api.auzmor.com");
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.getHeader("Content-Type"));
    }

     */


    /*
    Updating the Things
    @Test
    public void updatedata()
    {
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("middle_name", "hari");
        jsonData.put("gender", "male");

        // Specify the base URI
        RestAssured.baseURI = "https://learn-staging.api.auzmor.com/api/v1/users/login";

        // Perform the POST request
        RestAssured.given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .patch()  // Replace with your actual endpoint
                .then()
                .statusCode(200).log().all();
        Response response= RestAssured.get("https://learn-staging.api.auzmor.com");
        response.getBody().toString();



    }
     */
}
