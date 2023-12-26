package hooks;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.Authentication;
import utilities.ConfigurationReader;

public class HooksAPI {

    public static RequestSpecification spec;

    public static  String token;

    @Before
    public void setUpApi(){
        //spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("base_url")).build();
    }

    @Before
    public void beforeGenerateToken(){
       //token = Authentication.createToken();
    }
}
