package stepdefinitions;

import io.cucumber.java.en.Given;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_US19_Steps {

    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs;

    @Given("usersOrderByLastnameDescFirstname query hazirlanir")
    public void users_order_by_lastname_desc_firstname_query_hazirlanir() throws SQLException {
        query = manage.getUsersOrderByLastnameDescFirstname();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("UsersFirstLastnameInList query hazirlanir")
    public void users_first_lastname_ın_list_query_hazirlanir() throws SQLException {
        query = manage.getUsersFirstLastnameInList();
        rs = DBUtils.getStatement().executeQuery(query);
    }


}
