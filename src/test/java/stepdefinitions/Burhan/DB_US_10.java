package stepdefinitions.Burhan;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_US_10 {
    String query ;
    ResultSet resultSet;

    @Then("diviceTokensQuery hazirlanir")
    public void divice_tokens_query_hazirlanir() throws SQLException {
        query = DB_Manage.getdeviceTokens();


    }
    @Then("datanin eklendigi dogrulanir")
    public void datanin_eklendigi_dogrulanir() throws SQLException {
        int rowcount = DBUtils.getStatement().executeUpdate(query);
        Assert.assertEquals(1,rowcount);

    }
}
