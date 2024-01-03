package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class DB_Murat_Steps {

    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs;
    int id;
    int user_id;
    int plan_id;
    String loan_number;
    Faker faker = new Faker();
    PreparedStatement preparedStatement;

    //================= US 15 ========================

    @Given("subscribersQuery hazirlanir")
    public void subscribers_query_hazirlanir() throws SQLException {
        query = manage.getSubscribersEmailNotLike();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("data listelenir ve dogrulanır")
    public void data_listelenir_ve_dogrulanır() throws SQLException {
        List<String> aIcerenEmail = new ArrayList<>();
        List<String> aIcermeyenEmail = new ArrayList<>();

        while (rs.next()) {
            String email = rs.getString("email");
            if (email.contains("a")) {
                aIcerenEmail.add(email);
            } else {
                aIcermeyenEmail.add(email);
            }
            System.out.println(aIcermeyenEmail);
        }
        assertFalse(aIcermeyenEmail.isEmpty());
    }

    //================= US 19 ========================

    @Given("usersOrderByLastnameAscFirstnameDesc query hazirlanir")
    public void usersOrderByLastnameAscFirstnameDesc_query_hazirlanir() throws SQLException {
        query = manage.getUsersOrderByLastnameAscFirstnameDesc();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("UsersFirstLastnameInList query hazirlanir")
    public void users_first_lastname_ın_list_query_hazirlanir() throws SQLException {
        query = manage.getUsersFirstLastnameInList();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    //================ US 20 =========================

    @Given("transactionsRemarkSumAmount query hazirlanir")
    public void transactions_remark_sum_amounte_query_hazirlanir() throws SQLException {

        query = manage.getTransactionsRemarkSumAmount();
        rs = DBUtils.getStatement().executeQuery(query);
    }

}
