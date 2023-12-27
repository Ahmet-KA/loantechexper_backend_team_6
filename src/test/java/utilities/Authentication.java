package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Authentication {

    private Authentication() {

    }

    public static String token;
    public static RequestSpecification spec;
    public static String createToken(String pathParamsUri) {

        if (spec == null) {

            switch (pathParamsUri) {

                case "usertoken":

                    spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("baseUrl")).build();
                    spec.pathParams("first", "api", "second", "usertoken");

                    Map<String, Object> dataCreate = new HashMap<>();
                    dataCreate.put("username", ConfigurationReader.getProperty("username"));
                    dataCreate.put("password", ConfigurationReader.getProperty("password"));

                    Response responseUser = given().contentType(ContentType.JSON)
                            .spec(spec).when().body(dataCreate).post("/{first}/{second}");


                    JsonPath jsonResponse = responseUser.jsonPath();

                    token = jsonResponse.getJsonObject("data.access_token");
                    System.out.println("tokenUser = " + token);
                    break;

                case "admintoken":

                    spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("baseUrl")).build();
                    spec.pathParams("first", "api", "second", "admintoken");

                    Map<String, Object> dataAdminCreate = new HashMap<>();
                    dataAdminCreate.put("username", ConfigurationReader.getProperty("usernameAdmin"));
                    dataAdminCreate.put("password", ConfigurationReader.getProperty("passwordAdmin"));

                    Response responseAdmin = given().contentType(ContentType.JSON)
                            .spec(spec).when().body(dataAdminCreate).post("/{first}/{second}");


                    JsonPath jsonResponseAdmin = responseAdmin.jsonPath();
                    token = jsonResponseAdmin.getString("data.access_token");
                    System.out.println("tokenAdmin = " + token);
                    break;
            }
        }

        return token;

    }


}
