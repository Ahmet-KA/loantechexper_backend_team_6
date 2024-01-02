package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.builder.RequestSpecBuilder;

import io.cucumber.java.en.When;

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
import static org.junit.Assert.assertTrue;

public class API_Stepdefinitions {

    public static String fullPath;
    Response response;
    Response response1;
    String mesaj;
    JsonPath jsonPath;
    JsonPath jsonPath1;
    JSONObject requestBody;


    @Given("API kullanıcısı {string} yolu parametrelerini ayarlar")
    public void apı_kullanıcısı_yolu_parametrelerini_ayarlar(String rawPaths) {
        spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("baseUrl")).build();
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

        response.then().assertThat().body("remark", Matchers.equalTo(message));

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

        assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }


    @Then("Api kullanicisi user profile endpointine gondermek icin dogru datalar iceren bir post request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDogruDatalarIcerenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("name", "Ahmet Kaya");
        requestBody.put("description", "about about");
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
        requestBody.put("name", "Ahmet Kaya");
    }

    @Then("Api kullanicisi user profile endpointine gondermek icin data icermeyen bir post request hazirlar")
    public void apiKullanicisiUserProfileEndpointineGondermekIcinDataIcermeyenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("name", "");
        requestBody.put("description", "");
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

        response.then().assertThat().body("message.error[0]", Matchers.equalTo(message));
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
    public void the_api_user_verifies_that_the_content_of_the_data_field_in_the_response_body_includes(int id, String loan_number, int user_id, int plan_id, String amount, String per_installment, int installment_interval, int delay_value, String charge_per_installment, String delay_charge, int given_installment, int total_installment, String application_form, String admin_feedback, int status, String due_notification_sent, String approved_at, String created_at, String updated_at) {
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


    @And("The API user prepares a POST request containing the correct data to send to the admin loanplans add endpoint")
    public void theAPIUserPreparesAPOSTRequestContainingTheCorrectDataToSendToTheAdminLoanplansAddEndpoint() {

        requestBody = new JSONObject();
        requestBody.put("category_id", 1);
        requestBody.put("name", "King Loan 15 ");
        requestBody.put("title", "King Loan 15");
        requestBody.put("total_installment", 20);
        requestBody.put("installment_interval", 20);
        requestBody.put("per_installment", "4.00");
        requestBody.put("minimum_amount", "2000.00000000");
        requestBody.put("maximum_amount", "5000.00000000");
        requestBody.put("delay_value", 25);
        requestBody.put("fixed_charge", "100.00000000");
        requestBody.put("percent_charge", "1.00000000");
        requestBody.put("is_featured", 0);
        requestBody.put("application_fixed_charge", "20.00000000");
        requestBody.put("application_percent_charge", "3.00000000");
        requestBody.put("instruction", "King Loan Plan 15");


    }

    @Then("The API user saves the response from the admin loanplans add endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoanplansAddEndpointWithValidAuthorizationInformation() {
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


    @And("The API user prepares a POST request with missing data to send to the admin loanplans add endpoint.")
    public void theAPIUserPreparesAPOSTRequestWithMissingDataToSendToTheAdminLoanplansAddEndpoint() {
        requestBody = new JSONObject();
        requestBody.put("per_installment", "4.00");
        requestBody.put("minimum_amount", "2000.00000000");
        requestBody.put("maximum_amount", "5000.00000000");
        requestBody.put("delay_value", 25);
        requestBody.put("fixed_charge", "100.00000000");
        requestBody.put("percent_charge", "1.00000000");
        requestBody.put("is_featured", 0);
        requestBody.put("application_fixed_charge", "20.00000000");
        requestBody.put("application_percent_charge", "3.00000000");
        requestBody.put("instruction", "King Loan Plan 10");
    }

    @And("The API user prepares a POST request without data to send to the admin loanplans add endpoint")
    public void theAPIUserPreparesAPOSTRequestWithoutDataToSendToTheAdminLoanplansAddEndpoint() {
        requestBody = new JSONObject();
    }


    @When("The API user sends a POST request and saves the response from the admin loanplans add endpoint with invalid authorization information")
    public void theAPIUserSendsAPOSTRequestAndSavesTheResponseFromTheAdminLoanplansAddEndpointWithInvalidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                .when()
                .body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @And("The API user verifies that the error information in the response body is {string}")
    public void theAPIUserVerifiesThatTheErrorInformationInTheResponseBodyIs(String error) {
        response.then()
                .assertThat()
                .body("message.error[0]", Matchers.equalTo(error));
    }

    @Then("The API user saves the response from the admin loanplans details id  endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoanplansDetailsIdEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }


    @Then("The API user verifies that the id information in the response body is {int}")
    public void the_api_user_verifies_that_the_id_information_in_the_response_body_is(int id) {
        jsonPath = response.jsonPath();

        Assert.assertEquals(id, jsonPath.getInt("data[0].id"));
    }

    @Given("Response bodyde dönen Added category id ile api categories details id endpoint'ine GET request gönderilerek kayıt oluşturulduğu doğrulanabilir")
    public void response_bodyde_dönen_added_category_id_ile_api_categories_details_id_endpoint_ine_get_request_gönderilerek_kayıt_oluşturulduğu_doğrulanabilir() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data[\"Added category id\"]");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath + "/" + id);

        response.prettyPrint();

        response.then().assertThat().body("data[0].id", Matchers.equalTo(id));
    }


    @And("The API user saves the response from the admin loans approve endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoansApproveEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath);

        response.prettyPrint();
    }

    @Then("The API user verifies that the message information in the response body is {string}")
    public void the_api_user_verifies_that_the_message_information_in_the_response_body_is(String message) {
        response.then()
                .assertThat()
                .body("message", Matchers.equalTo(message));
    }

    @And("The API User verifies that the message information in the response body is {string}")
    public void theAPIUserVerifiesThatTheMessageInformationInTheResponseBodyIs(String message) {
        response.then()
                .assertThat()
                .body("data.message", Matchers.equalTo(message));
    }


    @When("The API user sends a POST request and saves the response from the admin loans approve endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserSendsAPOSTRequestAndSavesTheResponseFromTheAdminLoansApproveEndpointWithInvalidAuthorizationInformation() {
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                    .when()
                    .patch(fullPath);

            response.prettyPrint();
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));
    }



    @And("It is verified that the created record has been deleted by sending a DELETE request to the api loanplans delete id endpoint with the Added plan id returned in the response body.")
    public void ıtIsVerifiedThatTheCreatedRecordHasBeenDeletedBySendingADELETERequestToTheApiLoanplansDeleteIdEndpointWithTheAddedPlanIdReturnedInTheResponseBody() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data[\"Added plan id\"]");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath + "/" + id);

        response.prettyPrint();

        jsonPath = response.jsonPath();
        Integer deleteId = jsonPath.getInt("data[\"Deleted loan plan id\"]");
        System.out.println("deleteId = " + deleteId);

        Assert.assertEquals(id,deleteId);
    }




    @And("It must be verified that the message information in the response body is {string}")
    public void ıtMustBeVerifiedThatTheMessageInformationInTheResponseBodyIs(String message) {

        response.then().assertThat().body("data.message",Matchers.equalTo(message));
    }


    @Then("endpoint'e gecerli authorization bilgileri ile bir GET request gönderilir ve kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequestGonderilirVeKaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }


    @Given("API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydeder")
    public void apı_kullanıcısı_donen_response_u_gecerli_authorization_bilgisi_ile_kaydeder() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath);

        response.prettyPrint();

    }

    @Given("API kullanicisi PATCH request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun {int} ve response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void apı_kullanicisi_patch_request_gonderir_ve_donen_response_u_gecersız_authorization_bilgisi_ile_kaydeder(int status, String message) {
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                    .when()
                    .patch(fullPath);

            response.prettyPrint();
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));
    }



    @Then("The API user saves the response from the admin deposit delete endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminDepositDeleteEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();
    }

    @Then("A DELETE request that does not contain \\(id) is sent and the response is recorded.")
    public void aDELETERequestThatDoesNotContainIdIsSentAndTheResponseIsRecorded() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();
    }




    @Then("A DELETE request containing an unregistered \\(id) is sent and the response is recorded.")
    public void aDELETERequestContainingAnUnregisteredIdIsSentAndTheResponseIsRecorded() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();
    }




    @Then("A DELETE body is sent with invalid authorization information and the response is recorded.Then  The API user verifies that the status code is {int} And The API user verifies that the error information in the response body is {string}")
    public void aDELETEBodyIsSentWithInvalidAuthorizationInformationAndTheResponseIsRecordedThenTheAPIUserVerifiesThatTheStatusCodeIsAndTheAPIUserVerifiesThatTheErrorInformationInTheResponseBodyIs(int status, String message) {

        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("admin"))
                    .when()
                    .delete(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }



    @Then("endpointe gecersiz authorization bilgileri ile bir GET request gönderilir ve kaydedilir durum kodunun {int} olduğunu doğrular. response bodydeki error bilgisinin {string} oldugu dogrulanmali")
    public void endpointeGecersizAuthorizationBilgileriIleBirGETRequestGonderilirVeKaydedilirDurumKodununOldugunuDogrularResponseBodydekiErrorBilgisininOlduguDogrulanmali(int arg0, String arg1) {

        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("admin"))
                    .when()
                    .get(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @Then("Api kullanicisi user ticket add endpointine gondermek icin dogru datalar iceren bir post request hazirlar")
    public void apiKullanicisiUserTicketAddEndpointineGondermekIcinDogruDatalarIcerenBirPostRequestHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("subject","AhmetKaya");
        requestBody.put("priority","high");
        requestBody.put("message","KayaTicket");
    }

    @When("Api kullanicisi post request gonderir ve user ticket add endpointinden donen responsei gecerli authorization bilgisi ile kaydeder")
    public void apiKullanicisiPostRequestGonderirVeUserTicketAddEndpointindenDonenResponseiGecerliAuthorizationBilgisiIleKaydeder() {

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @Then("endpoint'e gecerli authorization bilgileri ile bir patch request gönderilir ve kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirPatchRequestGonderilirVeKaydedilir() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("\"Opened ticked id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .patch(fullPath + "/" + id);

        response.prettyPrint();

    }

    @And("Api kullanicisi yeni olusturulan kaydin bilgilerini kaydeder.")
    public void apiKullanicisiYeniOlusturulanKaydinBilgileriniKaydeder() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("\"Opened ticked id\"");
        System.out.println("id = " + id);

        response1 = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .get(fullPath + "/" + id);

        jsonPath1 = response1.jsonPath();
        response1.prettyPrint();


    }

    @Then("Close olanın Response da dönen bilgilerini \\(user_id, name, email, ticket, subject, status, priority, last_reply, created_at, updated_at) dogrulayiniz")
    public void closeOlanınResponseDaDonenBilgileriniUser_idNameEmailTicketSubjectStatusPriorityLast_replyCreated_atUpdated_atDogrulayiniz() {

         /*
        "status": 200,
    "success": true,
    "data": {
        "id": 360,
        "user_id": 10,
        "name": "Ahmet Kaya",
        "email": "ahmetkaya@gmail.com",
        "ticket": "906670",
        "subject": "Ahmet Ticket",
        "status": 3,
        "priority": 0,
        "last_reply": "2023-12-31 09:05:50",
        "created_at": "2023-12-31T14:05:50.000000Z",
        "updated_at": "2023-12-31T14:07:57.000000Z"
         */

        String user_id1 = jsonPath1.getString("data.name");
        System.out.println("user_id1 = " + user_id1);
        String user_id = jsonPath.getString("data.name");
        System.out.println("user_id = " + user_id);
        Assert.assertEquals(jsonPath1.getInt("data.user_id"),jsonPath.getInt("data.user_id"));

        Assert.assertEquals(jsonPath1.getString("data.name"),jsonPath.getString("data.name"));
        Assert.assertEquals(jsonPath1.getString("data.email"),jsonPath.getString("data.email"));
        Assert.assertEquals(jsonPath1.getString("data.ticket"),jsonPath.getString("data.ticket"));
        Assert.assertEquals(jsonPath1.getString("data.subject"),jsonPath.getString("data.subject"));
        Assert.assertNotEquals(jsonPath1.getInt("status"),jsonPath.getInt("status"));
        Assert.assertEquals(jsonPath1.getString("data.priority"),jsonPath.getString("data.priority"));
        Assert.assertEquals(jsonPath1.getString("data.last_reply"),jsonPath.getString("data.last_reply"));
        Assert.assertEquals(jsonPath1.getString("data.created_at"),jsonPath.getString("data.created_at"));
        Assert.assertNotEquals(jsonPath1.getString("data.updated_at"),jsonPath.getString("data.updated_at"));

    }

    @Then("endpoint e gecerli authorization bilgileri ile bir GET request gönderilir kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequestGonderilirKaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }


    @And("endpoint'e gecerli authorization bilgileri ile bir GET request gönderilir  kayıt edilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequestGonderilirKayıtEdilir() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("\"Closed ticket id\"");
        System.out.println("Closed ticket id = " + id);


        response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath + "/" + id);

        jsonPath = response.jsonPath();
        response.prettyPrint();
    }


    //murat
    @Given("API kullanicisi GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder")
    public void apı_kullanicisi_get_request_gonderir_ve_donen_response_u_gecerli_authorization_bilgisi_ile_kaydeder() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();

    }

    //murat
    @Given("API kullanicisi GET request gonderir ve donen response'u GECERSİZ authorization ile kaydeder ve durum kodunun {int} ve response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void apı_kullanicisi_get_request_gonderir_ve_donen_response_u_gecersız_authorization_bilgisi_ile_kaydeder(int status, String message) {
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                    .when()
                    .get(fullPath);

            response.prettyPrint();
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));
    }

    //murat


    //murat
    @Then("API Kullanıcısı, response body'deki data.message bilgisinin {string} oldugu dogrulanmali")
    public void apı_kullanıcısı_response_body_deki_data_message_bilgisinin_oldugu_dogrulanmali(String message) {
        jsonPath = response.jsonPath();
        response.then().assertThat().body("data.message", Matchers.equalTo(message));
    }

    //murat





    @Then("The API user saves the response from the admin deposit delete endpoint with invalid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminDepositDeleteEndpointWithInvalidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                .when()
                .body(requestBody.toString())
                .delete(fullPath);

        response.prettyPrint();
    }



    @When("The API user sends a delete request and saves the response from the admin deposit delete endpoint with invalid authorization information verifies that the status code is '401' and confirms that the error information is Unauthorized")
    public void theAPIUserSendsADeleteRequestAndSavesTheResponseFromTheAdminLoansApproveEndpointWithInvalidAuthorizationInformationVerifiesThatTheStatusCodeIsAndConfirmsThatTheErrorInformationIsUnauthorized() {
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("password"))
                    .when()
                    .delete(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }



    @Then("The API user saves the get response from the admin deposit details endpoint with valid authorization information")
    public void theAPIUserSavesTheGetResponseFromTheAdminDepositDetailsEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();
    }

    @Then("The API user saves the response from the admin loanplans list endpoint with valid authorization information")
    public void theAPIUserSavesTheResponseFromTheAdminLoanplansListEndpointWithValidAuthorizationInformation() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Then("Verify the information of the one with the id {int} in the API user response body: {int}, {int},{string}, {string}, {string}, {string}, {string}, {int}, {int}, {string}, {string}, {string}, {int}, {string}, {string}, {int}, {int}, {string}, {string}")
    public void verify_the_information_of_the_one_with_the_id_in_the_apı_user_response_body(int dataIndex, int categoryId, int formId, String name, String title, String minimum_amount, String maximum_amount, String per_installment, int installment_interval, int total_installment, String application_fixed_charge, String application_percent_charge, String instruction, int delay_value, String fixed_charge, String percent_charge, int is_featured, int status, String created_at, String updated_at) {

        jsonPath = response.jsonPath();

        Assert.assertEquals(categoryId, jsonPath.getInt("data[" + dataIndex + "].category_id"));
        Assert.assertEquals(formId, jsonPath.getInt("data[" + dataIndex + "].form_id"));
        Assert.assertEquals(name, jsonPath.getString("data[" + dataIndex + "].name"));
        Assert.assertEquals(title, jsonPath.getString("data[" + dataIndex + "].title"));
        Assert.assertEquals(minimum_amount, jsonPath.getString("data[" + dataIndex + "].minimum_amount"));
        Assert.assertEquals(maximum_amount, jsonPath.getString("data[" + dataIndex + "].maximum_amount"));
        Assert.assertEquals(per_installment, jsonPath.getString("data[" + dataIndex + "].per_installment"));
        Assert.assertEquals(installment_interval, jsonPath.getInt("data[" + dataIndex + "].installment_interval"));
        Assert.assertEquals(total_installment, jsonPath.getInt("data[" + dataIndex + "].total_installment"));
        Assert.assertEquals(application_fixed_charge, jsonPath.getString("data[" + dataIndex + "].application_fixed_charge"));
        Assert.assertEquals(application_percent_charge, jsonPath.getString("data[" + dataIndex + "].application_percent_charge"));
        Assert.assertEquals(instruction, jsonPath.getString("data[" + dataIndex + "].instruction"));
        Assert.assertEquals(delay_value, jsonPath.getInt("data[" + dataIndex + "].delay_value"));
        Assert.assertEquals(fixed_charge, jsonPath.getString("data[" + dataIndex + "].fixed_charge"));
        Assert.assertEquals(percent_charge, jsonPath.getString("data[" + dataIndex + "].percent_charge"));
        Assert.assertEquals(is_featured, jsonPath.getInt("data[" + dataIndex + "].is_featured"));
        Assert.assertEquals(status, jsonPath.getInt("data[" + dataIndex + "].status"));
        Assert.assertEquals(created_at, jsonPath.getString("data[" + dataIndex + "].created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("data[" + dataIndex + "].updated_at"));
    }

    @Then("The API user prepares a POST request containing the correct data to send to the user ticket add endpoint")
    public void the_apı_user_prepares_a_post_request_containing_the_correct_data_to_send_to_the_user_ticket_add_endpoint() {
   /*
    {
    "subject":"Blue Test Ticket",
    "priority":"priority",
    "message":"Blue Test Ticket Message-"
   }
   */   //id 184

        requestBody = new JSONObject();
        requestBody.put("subject", "Blue Test Ticket");
        requestBody.put("priority", "priority");
        requestBody.put("message", "Blue Test Ticket Message-");

    }

    @Then("The API user sends a POST request and saves the response from the user ticket add endpoint with valid authorization information")
    public void the_apı_user_sends_a_post_request_and_saves_the_response_from_the_user_ticket_add_endpoint_with_valid_authorization_information() {
        response= given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization","Bearer "+Authentication.createToken("usertoken"))
                .when().body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    @Given("The API user prepares a POST request containing no data to send to the user ticket add endpoint")
    public void the_apı_user_prepares_a_post_request_containing_no_data_to_send_to_the_user_ticket_add_endpoint() {
        requestBody=new JSONObject();
    }

    @Given("The API user prepares a POST request with incomplete missing data to send to the user ticket add endpoint")
    public void the_apı_user_prepares_a_post_request_with_incomplete_missing_data_to_send_to_the_user_ticket_add_endpoint() {
        requestBody=new JSONObject();
        requestBody.put("subject", "Blue Test Ticket");
    }

    @Given("The API user sends a POST request and saves the response from the user ticket add endpoint with invalid authorization information")
    public void the_apı_user_sends_a_post_request_and_saves_the_response_from_the_user_ticket_add_endpoint_with_invalid_authorization_information() {
        response= given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization","Bearer "+ ConfigurationReader.getProperty("invalidToken"))
                .when().body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @Given("The API user verifies that the error message in the response body is {string}")
    public void the_apı_user_verifies_that_the_error_message_in_the_response_body_is(String errorMessage) {
        response.then().assertThat().body("message.error[0]",Matchers.equalTo(errorMessage));

    }

    @Given("The API user saves the response from the user ticket detail endpoint with valid authorization information")
    public void the_apı_user_saves_the_response_from_the_user_ticket_detail_endpoint_with_valid_authorization_information() {
        response=given().spec(spec).header("Accept", "application/json")
                .headers("Authorization","Bearer "+Authentication.createToken("usertoken"))
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Given("The API user verifies that the success value in the response body is true")
    public void the_apı_user_verifies_that_the_success_value_in_the_response_body_is_true() {
        response.then().assertThat().body("success",Matchers.equalTo(true));
    }

    @And("The API user verifies that the id value in the response body is {int}")
    public void theAPIUserVerifiesThatTheIdValueInTheResponseBodyIsDataId(int dataId) {
        jsonPath=response.jsonPath();
        Assert.assertEquals(dataId,jsonPath.getInt("data.id"));
    }
    @Then("The API user sends a DELETE request to delete the created ticket and saves the response from the user ticket delete endpoint with valid authorization information")
    public void The_apı_user_sends_a_delete_request_to_delete_the_created_ticket_and_saves_the_response_from_the_user_ticket_delete_endpoint_with_valid_authorization_information() {
        jsonPath=response.jsonPath();
        String id =jsonPath.getString("\"Opened ticked id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .delete(fullPath + "/" + id);

        response.prettyPrint();
    }

    @And("The API user verifies that the data message information in the response body is {string}")
    public void theAPIUserVerifiesThatTheDataMessageInformationInTheResponseBodyIs(String message) {

        response.then().assertThat().body("data.message",Matchers.equalTo(message));
    }
    @Given("The API User saves the response from the user ticket detail id endpoint with valid authorization information")
    public void the_apı_user_saves_the_response_from_the_user_ticket_detail_id_endpoint_with_valid_authorization_information() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .get(fullPath);

        response.prettyPrint();

    }


    @And("The API user send a PATCH request and saves the response from the user ticket close endpoint with invalid authorization information and verifies that the status code is {int} and the error message is {string}")
    public void theAPIUserSendAPATCHRequestAndSavesTheResponseFromTheUserTicketCloseEndpointWithInvalidAuthorizationInformationAndVerifiesThatTheStatusCodeIsAndTheErrorMessageIs(int code, String message) {
        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("invalid"))
                    .when()
                    .patch(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
            System.out.println(mesaj);
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @And("The API user sends Get request and saves response from the user ticket detail id endpoint with valid authorization information")
    public void theAPIUserSendsGetRequestAndSavesResponseFromTheUserTicketDetailIdEndpointWithValidAuthorizationInformation() {

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .get(fullPath);
        response.prettyPrint();

    }
    @And("The API user Verifies that the status information in the response body is {int}")
    public void theAPIUserVerifiesThatTheStatusInformationInTheResponseBodyIsStatus(int status) {
        jsonPath=response.jsonPath();
        Assert.assertEquals(status,jsonPath.getInt("data.status"));

    }

    @And("The API user send a PATCH request with added ticket id and saves the response from the user ticket close endpoint with valid authorization information")
    public void theAPIUserSendAPATCHRequestWithAddedTicketIdAndSavesTheResponseFromTheUserTicketCloseEndpointWithValidAuthorizationInformation() {
        jsonPath=response.jsonPath();
        String id =jsonPath.getString("\"Opened ticked id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .patch(fullPath + "/" + id);

        response.prettyPrint();
    }

    @And("The API user sends Get request with closed ticket id and saves response from the user ticket detail id endpoint with valid authorization information")
    public void theAPIUserSendsGetRequestWithClosedTicketIdAndSavesResponseFromTheUserTicketDetailIdEndpointWithValidAuthorizationInformation() {
        jsonPath=response.jsonPath();
        String id =jsonPath.getString("\"Closed ticket id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .get(fullPath + "/" + id);

        response.prettyPrint();
    }


    @And("The API user sends a DELETE request and saves the response from the user ticket delete endpoint with valid authorization information")
    public void theAPIUserSendsADELETERequestAndSavesTheResponseFromTheUserTicketDeleteEndpointWithValidAuthorizationInformation() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();
    }

    @And("The API user send a DELETE request and saves the response from the user ticket close endpoint with invalid authorization information and verifies that the status code is {int} and the error message is {string}")
    public void theAPIUserSendADELETERequestAndSavesTheResponseFromTheUserTicketCloseEndpointWithInvalidAuthorizationInformationAndVerifiesThatTheStatusCodeIsAndTheErrorMessageIs(int code, String message) {
        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("invalid"))
                    .when()
                    .delete(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
            System.out.println(mesaj);
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));
    }

    @And("The API user sends a GET request with deleted ticket id and saves the response from the user ticket detail endpoint with valid authorization information")
    public void theAPIUserSendsAGETRequestWithDeletedTicketIdAndSavesTheResponseFromTheUserTicketDetailEndpointWithValidAuthorizationInformation() {
        jsonPath=response.jsonPath();
        String id =jsonPath.getString("data.\"Deleted ticket id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath + "/" + id);

        response.prettyPrint();
    }

    @Given("The API user send a PATCH request and saves the response from the user ticket close endpoint with valid authorization information")
    public void the_apı_user_send_a_patch_request_and_saves_the_response_from_the_user_ticket_close_endpoint_with_valid_authorization_information() {
        response=given().spec(spec).header("Accept", "application/json")
                .headers("Authorization","Bearer "+Authentication.createToken("usertoken"))
                .when()
                .patch(fullPath);
        response.prettyPrint();
    }

    @Given("Api kullanicisi api withdraw methods add endpointine gondermek icin dogru datalar iceren bir patch request hazirlar")
    public void api_kullanicisi_api_withdraw_methods_add_endpointine_gondermek_icin_dogru_datalar_iceren_bir_patch_request_hazirlar() {

        //curl --location -g '{{base_url}}api/withdraw/methods/add' \
        //--data '{
        //        "name": "Method 5",
        //        "min_limit": "200.00000000",
        //        "max_limit": "7000.00000000",
        //        "fixed_charge": "150.00000000",
        //        "rate": "2.00000000",
        //        "percent_charge": "3.00",
        //        "currency": "USD",
        //        "description": "Test Method 5"
        //}'
        requestBody = new JSONObject();
        requestBody.put("name", "Ali");
        requestBody.put("min_limit", "50000");
        requestBody.put("max_limit", "8000000");
        requestBody.put("fixed_charge", "150.00000000");
        requestBody.put("rate", "2.00000000");
        requestBody.put("percent_charge", "3.01");
        requestBody.put("currency", "USD");
        requestBody.put("description", "Test Ali");

    }

    @Given("API kullanıcısı, api withdraw methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder")
    public void apı_kullanıcısı_api_withdraw_methods_add_endpointden_gelen_yanıtı_geçerli_yetkilendirme_bilgileriyle_kaydeder() {

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

    @Given("endpoint'ine gecerli authorization bilgileri ve dogru \\(id) iceren bir PATCH body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_dogru_id_iceren_bir_patch_body_gönderilir_ve_kaydedilir() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data.Method.id");
        System.out.println("id = " + id);
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath + "/" + id);
        response.prettyPrint();

    }

    @Given("API Kullanıcısı, body deki mesaj bilgisinin {string} oldugunu dogrular")
    public void apı_kullanıcısı_body_deki_mesaj_bilgisinin_oldugunu_dogrular(String message) {

        response.then()
                .assertThat()
                .body("data.message", Matchers.equalTo(message));

    }

    @Given("endpoint'ine gecerli authorization bilgileri ve dogru \\(id) icermeyen bir PATCH body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_dogru_id_icermeyen_bir_patch_body_gönderilir_ve_kaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath);
        response.prettyPrint();

    }

    @Given("endpoint'ine gecerli authorization bilgileri ve kaydi olmayan \\(id) icermeyen bir PATCH body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_kaydi_olmayan_id_icermeyen_bir_patch_body_gönderilir_ve_kaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath);

        response.prettyPrint();

    }


    @Given("endpoint'ine gecersiz authorization bilgileri ve dogru \\(id) iceren bir PATCH body gönderilir ve kaydedilir API kullanıcısı durum kodunun {int} olduğunu doğrular. API Kullanıcısı, response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void endpoint_ine_gecersiz_authorization_bilgileri_ve_dogru_id_iceren_bir_patch_body_gönderilir_ve_kaydedilir_apı_kullanıcısı_durum_kodunun_olduğunu_doğrular_apı_kullanıcısı_response_body_deki_error_bilgisinin_oldugu_dogrulanmali(Integer int1, String string) {
        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data.Method.id");
        System.out.println("id = " + id);
        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("baseUrl"))
                    .when()
                    .patch(fullPath + "/" + id);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }


    @Given("API kullanicisi guncellenen id icin GET request gonderir ve donen response'u gecerli authorization bilgisi ile kaydeder")
    public void apı_kullanicisi_guncellenen_id_icin_get_request_gonderir_ve_donen_response_u_gecerli_authorization_bilgisi_ile_kaydeder() {
        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data[\"Method id\"]");
        System.out.println("id = " + id);
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath + "/" + id);
        response.prettyPrint();
        response.then().assertThat().body("data.id", Matchers.equalTo(id));

    }

    @Given("Api kullanicisi api blog add endpointine gondermek icin dogru datalar iceren bir post request hazirlar")
    public void api_kullanicisi_api_subscriber_add_endpointine_gondermek_icin_dogru_datalar_iceren_bir_patch_request_hazirlar() {
        requestBody = new JSONObject();
        requestBody.put("title", "Test Blog 3");
        requestBody.put("description", "Test Blog S");
    }

    @Given("API kullanıcısı, api blog methods add endpointden gelen yanıtı geçerli yetkilendirme bilgileriyle kaydeder")
    public void apı_kullanıcısı_api_subscriber_methods_add_endpointden_gelen_yanıtı_geçerli_yetkilendirme_bilgileriyle_kaydeder() {
        response = given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when().body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();

    }

    @Given("endpoint'ine gecerli authorization bilgileri ve dogru \\(id) iceren bir DELETE body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_dogru_id_iceren_bir_delete_body_gönderilir_ve_kaydedilir() {
        jsonPath = response.jsonPath();
        String id = jsonPath.getString("data.\"Added blog id\"");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath + "/" + id);

        response.prettyPrint();


    }

    @Given("endpoint'ine gecerli authorization bilgileri ile bir DELETE body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ile_bir_delete_body_gönderilir_ve_kaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();

    }

    @Given("endpoint'ine gecersiz authorization bilgileri ve dogru id iceren bir DELETE body gönderilir ve kaydedilir API kullanıcısı durum kodunun {int} olduğunu doğrular. API Kullanıcısı, response body'deki error bilgisinin {string} oldugu dogrulanmali")
    public void endpoint_ine_gecersiz_authorization_bilgileri_ve_dogru_id_iceren_bir_delete_body_gönderilir_ve_kaydedilir_apı_kullanıcısı_durum_kodunun_olduğunu_doğrular_apı_kullanıcısı_response_body_deki_error_bilgisinin_oldugu_dogrulanmali(Integer int1, String string) {

        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("admin"))
                    .when()
                    .delete(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @Given("endpoint'ine gecerli authorization bilgileri ve dogru \\(id) iceren bir DELETE body gönderilir ve kaydin silindigi dogrulanir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_dogru_id_iceren_bir_delete_body_gönderilir_ve_kaydin_silindigi_dogrulanir() {
        jsonPath = response.jsonPath();
        String id = jsonPath.getString("data[\"Added plan id\"]");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath + "/" + id);

        response.prettyPrint();

        jsonPath = response.jsonPath();
        String deleteId = jsonPath.getString("data[\"Removed  blog id\"]");
        System.out.println("deleteId = " + deleteId);

        Assert.assertEquals(id, deleteId);
    }


    @Given("API kullanicisi api loans reject id endpointine gondermek icin dogru datalar iceren bir POST request hazirlar")
    public void apı_kullanicisi_api_loans_reject_id_endpointine_gondermek_icin_dogru_datalar_iceren_bir_post_request_hazirlar() {
        requestBody = new JSONObject();
        requestBody.put("reason", "Bank info is wrong.");
    }

    @Given("endpoint'ine gecerli authorization bilgileri ve dogru \\(id) iceren bir POST body gönderilir ve kaydedilir")
    public void endpoint_ine_gecerli_authorization_bilgileri_ve_dogru_id_iceren_bir_post_body_gönderilir_ve_kaydedilir() {
        response = given()
                .spec(spec).contentType(ContentType.JSON
                )
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when().body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @Given("API Kullanıcısı, body deki reason bilgisinin {string} oldugunu dogrular")
    public void apı_kullanıcısı_body_deki_reason_bilgisinin_oldugunu_dogrular(String Reason) {
        response.then()
                .assertThat()
                .body("data.Reason", Matchers.equalTo(Reason));


    }

    @Given("API kullanicisi api loans reject id endpointine gondermek icin dogru datalar icermeyen bir POST request hazirlar")
    public void apı_kullanicisi_api_loans_reject_id_endpointine_gondermek_icin_dogru_datalar_icermeyen_bir_post_request_hazirlar() {
        requestBody = new JSONObject();
        requestBody.put("reason", "");
    }


    @Given("endpoint'ine gecersiz authorization bilgileri ve dogru \\(id) iceren bir POST body gönderilir ve kaydedilir")
    public void endpoint_ine_gecersiz_authorization_bilgileri_ve_dogru_id_iceren_bir_post_body_gönderilir_ve_kaydedilir() {


        response = given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + ConfigurationReader.getProperty("baseUrl"))
                .when().body(requestBody.toString())
                .post(fullPath);

        response.prettyPrint();

    }

    @Given("API Kullanıcısı, body deki error bilgisinin {string} oldugunu dogrular")
    public void apı_kullanıcısı_body_deki_error_bilgisinin_oldugunu_dogrular(String string) {
        response.then()
                .assertThat()
                .body("message.error[0]", Matchers.equalTo(string));

    }



    //

    @Given("The API admin verifies that the content of the data field in the response body includes {int}, {int}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {int}, {int}, {string}, {string}, {string}")
    public void the_apı_admin_verifies_that_the_content_of_the_data_field_in_the_response_body_includes(int user_id, int method_code, String amount, String method_currency, String charge, String rate, String final_amo, String btc_amo, String btc_wallet, String trx, String payment_try, int status, int from_api, String admin_feedback, String created_at, String updated_at) {
        jsonPath = response.jsonPath();
        Assert.assertEquals(user_id, jsonPath.getInt("data.user_id"));
        Assert.assertEquals(method_code, jsonPath.getInt("data.method_code"));
        Assert.assertEquals(amount, jsonPath.getString("data.amount"));
        Assert.assertEquals(method_currency, jsonPath.getString("data.method_currency"));
        Assert.assertEquals(charge, jsonPath.getString("data.charge"));
        Assert.assertEquals(rate, jsonPath.getString("data.rate"));
        Assert.assertEquals(final_amo, jsonPath.getString("data.final_amo"));
        Assert.assertEquals(btc_amo, jsonPath.getString("data.btc_amo"));
        Assert.assertEquals(btc_wallet, jsonPath.getString("data.btc_wallet"));
        Assert.assertEquals(trx, jsonPath.getString("data.trx"));
        Assert.assertEquals(payment_try, jsonPath.getString("data.payment_try"));
        Assert.assertEquals(status, jsonPath.getInt("data.status"));
        Assert.assertEquals(from_api, jsonPath.getInt("data.from_api"));
        Assert.assertNull(admin_feedback, jsonPath.getString("data.admin_feedback"));
        Assert.assertEquals(created_at, jsonPath.getString("data.created_at"));
        Assert.assertEquals(updated_at, jsonPath.getString("data.updated_at"));
    }

    @Given("API Kullanıcısı, body deki reason bilgisinin <null> oldugunu dogrular")
    public void apı_kullanıcısı_body_deki_reason_bilgisinin_null_oldugunu_dogrular() {
        response.then()
                .assertThat()
                .body("data.Reason", Matchers.equalTo(null));


    }





    @Then("Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir ve kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequesttGonderilirVeKaydedilir() {


        jsonPath = response.jsonPath();
        String id = jsonPath.getString("data[\"Status Updated id\"]");
        System.out.println("id = " + id);

        response = given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        jsonPath=response.jsonPath();
        response.prettyPrint();



    }

    // US69

    @Then("Kullanci  end point'ine dogru dataalar  iceren bir POST body gönderir")
    public void kullanciEndPointIneDogruDataalarIcerenBirPOSTBodyGönderir() {


// {
//    "title":"Test Blog 3",
//    "description":"Test açıklama 3"
//}

        requestBody = new JSONObject();
        requestBody.put("title", "Test Blog 3");
        requestBody.put("description", "Test açıklama 3");


    }

    @When("The API user sends a POST request and saves the response add endpoint with valid authorization information")
    public void theAPIUserSendsAPOSTRequestAndSavesTheResponseAddEndpointWithValidAuthorizationInformation() {

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


    @Then("Kullanci  end point'ine eksik dataalar  iceren bir POST body gönderir")
    public void kullanciEndPointIneEksikDataalarIcerenBirPOSTBodyGönderir() {


        requestBody = new JSONObject();
        requestBody.put("title", "");
        requestBody.put("description", "");


    }

    @Then("Kullanci  end point'ine gecerli authorization bilgileri ve data icermeyen bir POST body gonderir")
    public void kullanciEndPointIneGecerliAuthorizationBilgileriVeDataIcermeyenBirPOSTBodyGonderir() {


        requestBody = new JSONObject();
        requestBody.put("title", "");
        requestBody.put("description", "");

    }

    @Then("Kullanci  end point'ine gecersiz authorization biilgileri ile bir POST body  gönderir")
    public void kullanciEndPointIneGecersizAuthorizationBiilgileriIleBirPOSTBodyGönderir() {


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


    // US 15
    @Then("API kullanicisi donen Patch response gecersiz autorization bilgisi ile kaydedip durum kodunun {int} ve mesajin {string} oldugunu dogrrular")
    public void apıKullanicisiDonenPatchResponseGecersizAutorizationBilgisiIleKaydedipDurumKodununVeMesajinOldugunuDogrrular(int arg0, String arg1) {

        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("usertoken"))
                    .when()
                    .patch(fullPath);

            response.prettyPrint();
        } catch (Exception e) {
            mesaj = e.getMessage();
        }

        assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));

    }

    @Then("API kullanıcısı donen response'u gecerli authorization bilgisi ile kaydeder")
    public void apıKullanıcısıDonenResponseUGecerliAuthorizationBilgisiIleKaydeder() {

        try {
            response = given()
                    .spec(spec)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                    .when()
                    .get(fullPath);
        } catch (Exception e) {
            mesaj = e.getMessage();
        }
        System.out.println("mesaj: " + mesaj);

        Assert.assertTrue(mesaj.contains("status code: 401, reason phrase: Unauthorized"));


    }

    @Then("The API user Verifies that the status information in the response boddy is {int}")
    public void the_apı_user_verifies_that_the_status_information_in_the_response_boddy_is(int status) {

        jsonPath = response.jsonPath();

        response.then().assertThat().body("data[0].status",Matchers.equalTo(status));

    }

    //US33



    @Then("ID al ve silme islemini gerceklestigini onayla")
    public void ıdAlVeSilmeIsleminiGerceklestiginiOnayla() {

        jsonPath = response.jsonPath();
        Integer id = jsonPath.getInt("data[\"Added plan id\"]");
        System.out.println("id = " + id);

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath + "/" + id);

        response.prettyPrint();

        jsonPath = response.jsonPath();
        Integer deleteId = jsonPath.getInt("data[\"Deleted loan plan id\"]");
        System.out.println("deleteId = " + deleteId);

        Assert.assertEquals(id, deleteId);
    }


    @And("Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder")
    public void apiKullanicisiUserTicketDeleteEndpointindenDonenResponseiGecerliAuthorizationBilgisiIileKaydeder() {


        response = given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .delete(fullPath);

        response.prettyPrint();

    }

    @And("Api kullanicisi user ticket delete endpointinden donen responsei gecersiz authorization bilgisi iile kaydeder")
    public void apiKullanicisiUserTicketDeleteEndpointindenDonenResponseiGecersizAuthorizationBilgisiIileKaydeder() {


        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admin"))
                .when()
                .delete(fullPath);

        response.prettyPrint();

    }

    @And("API Kullanıcısı response body'deki data.message bilgisinin {string} oldugu dogrulanmalig")
    public void apıKullanıcısıResponseBodyDekiDataMessageBilgisininOlduguDogrulanmalig(String arg0) {

        jsonPath = response.jsonPath();
        String exeptChange = "Status changed";
        String change = jsonPath.getString("data[\"message\"]");

        Assert.assertEquals(exeptChange, change);
    }


    @Then("Status id'nin  new status bigisi ile esit oldugunu dogrular")
    public void statusIdNinNewStatusBigisiIleEsitOldugunuDogrular() {

        Assert.assertEquals(jsonPath1.getString("data[\"new status\"]"),jsonPath.getString("data[0].status"));

        // response.then().assertThat().body("data[\"new status\"]",Matchers.equalTo())
        String status1= jsonPath1.getString("data[\"new status\"]");
        String status2= jsonPath.getString("data[0].status");
        System.out.println("sonuc1" + status1);
        System.out.println("sonuc"+ status2);


    }

    @Then("API kullanıcısı donen PATCH response'u gecerli authorization bilgisi ile kaydederr")
    public void apıKullanıcısıDonenPATCHResponseUGecerliAuthorizationBilgisiIleKaydederr() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .patch(fullPath);

        jsonPath1=response.jsonPath();
        String newStatus= jsonPath1.getString("data[\"new status\"]");
        System.out.println("new status"+ newStatus);

        response.prettyPrint();

    }

    @Then("Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequesttGonderilirKaydedilir() {

        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);
    }

    @Then("Endpoint'e gecerli authorization bilgileri ile biir GET requestt gonderilir ve kaydedilir")
    public void endpointEGecerliAuthorizationBilgileriIleBiirGETRequesttGonderilirVeKaydedilir() {
        response = given()
                .spec(spec)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

    }

    @Then("Endpoint'e gecerli authorization bilgileri ile bir GET requestt gonderilir ve kaydediliir")
    public void endpointEGecerliAuthorizationBilgileriIleBirGETRequesttGonderilirVeKaydediliir() {



        response = given()
                .spec(spec).contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .headers("Authorization", "Bearer " + Authentication.createToken("admintoken"))
                .when()
                .get(fullPath);

        jsonPath=response.jsonPath();
        response.prettyPrint();


    }
}







