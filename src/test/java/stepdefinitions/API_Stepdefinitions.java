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
                .body(requestBody)
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
        requestBody.put("name","Ahmet Kaya01");
        requestBody.put("description","about01");
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


    @And("Response bodyde dönen Added category id ile api categories details id endpoint'ine GET request gönderilerek kayıt oluşturulduğu doğrulanabilir")
    public void responseBodydeDonenAddedCategoryIdIleApiCategoriesDetailsIdEndpointIneGETRequestGonderilerekKayıtOlusturulduguDogrulanabilir() {

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

        response.then().assertThat().body("data[0].id",Matchers.equalTo(id));


    }
}
