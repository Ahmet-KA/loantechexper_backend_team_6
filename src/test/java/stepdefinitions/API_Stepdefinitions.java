package stepdefinitions;

import io.cucumber.java.en.Given;
import utilities.Authentication;
import utilities.ConfigurationReader;

public class API_Stepdefinitions {

    @Given("token uretilir.")
    public void token_uretilir() {

        Authentication.createToken(ConfigurationReader.getProperty("userTokenUri"));
    }
}
