package hooks;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigurationReader;

public class HooksAPI {

    public static RequestSpecification spec;

    @Before(order = 0)
    public void setUpApi(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("baseUrl")).build();
    }

}
