package stepdefinitions;

import io.cucumber.java.en.Given;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_US20_Steps {

    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs;

    @Given("transactionsRemarkSumAmount query hazirlanir")
    public void transactions_remark_sum_amounte_query_hazirlanir() throws SQLException {

        query = manage.getTransactionsRemarkSumAmount();
        rs = DBUtils.getStatement().executeQuery(query);
    }

}
