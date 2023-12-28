package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;

import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static stepdefinitions.API_Stepdefinitions.fullPath;

public class ReusableMethods {

    public static Response response;

    public static Response getResponse(String token) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .get(fullPath);
        response.prettyPrint();
        return response;
    }

    public static Response postResponse(String token, Object requestBody) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(requestBody)
                .post(fullPath);
        response.prettyPrint();
        return response;
    }

    public static Response deleteResponse(String token, Object requestBody) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(requestBody)
                .delete(fullPath);
        response.prettyPrint();
        return response;
    }

    public static Response patchResponse(String token, Object requestBody) {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + token)
                .when()
                .body(requestBody.toString())
                .patch(fullPath);
        response.prettyPrint();
        return response;
    }

    public static void parametreAyarlama(String rawPaths) {

        String[] paths = rawPaths.split("/");

        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            spec.pathParam(key, value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }
}
