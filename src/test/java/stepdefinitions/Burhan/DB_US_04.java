package stepdefinitions.Burhan;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_US_04 {
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    @Then("depositsAmountQuery hazirlanir")
    public void deposits_amount_query_hazirlanir() throws SQLException {
        String query = DB_Manage.getDepositsAmount();
        resultSet = DBUtils.getStatement().executeQuery(query);

    }
    @Then("{string} olan datanin charge degeri dogrulanir")
    public void olan_datanin_charge_degeri_dogrulanir(String string) throws SQLException {
        int charge = 102;
        resultSet.next();
        Assert.assertEquals(charge,resultSet.getInt("charge"));

    }
}
