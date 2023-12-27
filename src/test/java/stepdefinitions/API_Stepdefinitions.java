package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.Authentication;
import utilities.ConfigurationReader;

import java.util.Arrays;

import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;

public class API_Stepdefinitions {

    public static String fullPath;
    Response response;
    String mesaj;
    JsonPath jsonPath;
    JSONObject requestBody;


    @Given("API kullanıcısı {string} yolu parametrelerini ayarlar")
    public void apı_kullanıcısı_yolu_parametrelerini_ayarlar(String rawPaths) {

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

    @Given("API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder")
    public void apı_kullanıcısı_kullanıcı_profili_endpointden_gelen_yanıtı_geçerli_yetkilendirme_bilgileriyle_kaydeder() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .body(requestBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }

    @Then("API kullanıcısı durum kodunun {int} olduğunu doğrular")
    public void apı_kullanıcısı_durum_kodunun_olduğunu_doğrular(Integer status) {

        response.then().assertThat().statusCode(status);

    }

    @Then("API Kullanıcısı, response body'deki message bilgisinin {string} oldugu dogrulanmali")
    public void apı_kullanıcısı_response_body_deki_message_bilgisinin_oldugu_dogrulanmali(String message) {

        response.then().assertThat().body("message", Matchers.equalTo(message));
    }

    @Then("Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir patch request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDogruDatalarIcerenBirpatchRequestHazirlar() {

    /*    --data '{
        "firstname":"Mehmet",
                "lastname" :"Genç",
                "address" :"New York",
                "state":"New York City",
                "zip":"125874",
                "city":"New York City"
    }*/
        requestBody = new JSONObject();
        requestBody.put("firstname", "Mehmet");
        requestBody.put("lastname", "Genç");
        requestBody.put("address", "New York");
        requestBody.put("state", "New York City");
        requestBody.put("zip", "125874");
        requestBody.put("city", "New York City");

    }

    @Then("Api kullanicisi user profile endpointine gondermek icin eksik datalar iceren bir patch request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinEksikDatalarIcerenBirPatchRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("address", "New York");
        requestBody.put("state", "New York City");
        requestBody.put("zip", "125874");
        requestBody.put("city", "New York City");
    }

    @And("API Kullanıcısı, response body'deki remark bilgisinin {string} oldugu dogrulanmali")
    public void apıKullanıcısıResponseBodyDekiRemarkBilgisininOlduguDogrulanmali(String message) {

        response.then().assertThat().body("remark",Matchers.equalTo(message));

    }

    @Then("Api kullanicisi user profile endpointine gondermek icin data içermeyen bir patch request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDataIcermeyenBirPatchRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("firstname", "");
        requestBody.put("lastname", "");
        requestBody.put("address", "");
        requestBody.put("state", "");
        requestBody.put("zip", "");
        requestBody.put("city", "");
    }


    @And("API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçersiz yetkilendirme bilgileriyle kaydeder.durum kodunun {int} olduğunu doğrular.response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void apıKullanıcısıKullanıcıProfiliEndpointdenGelenYanıtıGecersizYetkilendirmeBilgileriyleKaydederDurumKodununOldugunuDogrularResponseBodyDekiErrorBilgisininOlduguDogrulanmali(int status, String message) {

        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("username"))
                    .when()
                    .body(requestBody.toString())
                    .patch(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }


    @Then("Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir post request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDogruDatalarIcerenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("name","Ahmet Kaya");
        requestBody.put("description","about about");
    }

    @And("Api kullanicisi post request gonderir ve api categories add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder")
    public void apiKullanicisiPostRequestGonderirVeApiCategoriesAddEndpointindenDonenResponseiGecerliAuthorizationBilgisiIleKaydeder() {

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @Then("Api kullanicisi user profile endpointine gondermek icin eksik datalar iceren bir post request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinEksikDatalarIcerenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("name","Ahmet Kaya");
    }

    @Then("Api kullanicisi user profile endpointine gondermek icin data icermeyen bir post request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDataIcermeyenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("name","");
        requestBody.put("description","");
    }


    @And("API kullanıcısı, kullanıcı profili endpointden gelen yanıtı geçersiz yetkilendirme bilgileriyle kaydeder.")
    public void apıKullanıcısıKullanıcıProfiliEndpointdenGelenYanıtıGecersizYetkilendirmeBilgileriyleKaydeder() {

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("user"))
                .when()
                .body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @And("API Kullanıcısı, response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void apıKullanıcısıResponseBodyDekiErrorBilgisininOlduguDogrulanmali(String message) {

        response.then().assertThat().body("message.error[0]",Matchers.equalTo(message));
    }

    @Given("The API user sets {string} path parameters")
    public void theAPIUserSetsPathParameters(String rawPaths) {
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


    @Then("The API user saves the response from the admin loans running endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoansRunningEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }

    @And("The API user verifies that the status code is {int}")
    public void theAPIUserVerifiesThatTheStatusCodeIs(int status) {
        response.then()
                .assertThat()
                .statusCode(status);

    }
    @And("The API user verifies that the remark information in the response body is {string}")
    public void theAPIUserVerifiesThatTheRemarkInformationInTheResponseBodyIs(String remark) {
        response.then()
                .assertThat()
                .body("remark", Matchers.equalTo(remark));
    }

    @And("The API user records the response with invalid authorization information, verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserRecordsTheResponseWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                    .when()
                    .get(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));



    }

    @Then("The API user saves the response from the admin loans details id  endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoansDetailsIdEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }




    @Then("The API user verifies that the content of the data field in the response body includes {int}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {string}, {int}, {int}, {string}, {string}, {int}, {string}, {string}, {string}, {string}")
    public void the_api_user_verifies_that_the_content_of_the_data_field_in_the_response_body_includes(int id,String loan_number, int user_id, int plan_id ,String amount, String per_installment, int installment_interval, int delay_value, String charge_per_installment, String delay_charge, int given_installment, int total_installment, String application_form, String admin_feedback, int status, String due_notification_sent, String approved_at, String created_at, String updated_at) {
        jsonPath = response.jsonPath();

        Assert.assertEquals(id, jsonPath.getInt("data.id"));
        Assert.assertEquals(loan_number, jsonPath.getString("data.loan_number"));
        Assert.assertEquals(user_id, jsonPath.getInt("data.user_id"));
        Assert.assertEquals(plan_id, jsonPath.getInt("data.plan_id"));
        Assert.assertEquals(amount, jsonPath.getString("data.amount"));
        Assert.assertEquals(per_installment, jsonPath.getString("data.per_installment"));
        Assert.assertEquals(installment_interval, jsonPath.getInt("data.installment_interval"));
        Assert.assertEquals(delay_value, jsonPath.getInt("data.delay_value"));
        Assert.assertEquals(charge_per_installment, jsonPath.getString("data.charge_per_installment"));
        Assert.assertEquals(delay_charge, jsonPath.getString("data.delay_charge"));
        Assert.assertEquals(given_installment, jsonPath.getInt("data.given_installment"));
        Assert.assertEquals(total_installment, jsonPath.getInt("data.total_installment"));
        Assert.assertEquals(application_form, jsonPath.getString("data.application_form"));
        Assert.assertNull(admin_feedback, jsonPath.getString("data.admin_feedback"));
        Assert.assertEquals(status, jsonPath.getInt("data.status"));
        Assert.assertNull(due_notification_sent, jsonPath.getString("data.due_notification_sent"));
        Assert.assertEquals(approved_at, jsonPath.getString("data.approved_at"));
        Assert.assertEquals(created_at, jsonPath.getString("data.created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("data.updated_at"));
    }


}
