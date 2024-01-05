package stepdefinitions.Burhan;


import io.cucumber.java.en.Then;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

public class DB_US_03 {
    String query;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    @Then("CronSchedulesAmountQuery hazirlanir")
    public void cron_schedules_amount_query_hazirlanir() throws SQLException {
        query=DB_Manage.getCronSchedulesAmountQuery();
        resultSet=DBUtils.getStatement().executeQuery(query);

    }

    @Then("resultset ile donen data dogrulanir")
    public void resultset_ile_donen_data_dogrulanir() throws SQLException {
        resultSet.next();
        String expectedname1 = "5 Minutes";
        String name=resultSet.getString("name");
        Assert.assertEquals(expectedname1,name);
    }

}
