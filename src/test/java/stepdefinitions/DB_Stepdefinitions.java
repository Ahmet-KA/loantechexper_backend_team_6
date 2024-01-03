package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Stepdefinitions {


    @Given("Database bağlantisi kurulur.")
    public void database_bağlantisi_kurulur() {

        DBUtils.createConnection();
    }

    @Then("database connection kapatilir")
    public void database_connection_kapatilir() {

        DBUtils.closeConnection();
    }


}
