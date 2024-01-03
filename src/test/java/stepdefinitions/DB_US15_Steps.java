package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class DB_US15_Steps {

    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs;
    int id;
    int user_id;
    int plan_id;
    String loan_number;
    Faker faker = new Faker();
    PreparedStatement preparedStatement;

    @Given("Database baglantısı kurulur")
    public void database_baglantısı_kurulur() {
        DBUtils.createConnection();
    }

    @Given("subscribersQuery hazirlanir")
    public void subscribers_query_hazirlanir() throws SQLException {
        query = manage.getSubscribersEmailNotLike();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("data dogrulanır")
    public void data_dogrulanır() throws SQLException {
        List<String> aicerenEmail = new ArrayList<>();
        List<String> aicermeyenEmail = new ArrayList<>();

        while (rs.next()) {
            String email = rs.getString("email");
            if (email.contains("a")) {
                aicerenEmail.add(email);
            } else {
                aicermeyenEmail.add(email);
            }
            System.out.println(aicermeyenEmail);
        }
        assertFalse(aicermeyenEmail.isEmpty());
    }

    @Given("Database kapatilir")
    public void database_kapatilir() {
        DBUtils.closeConnection();
    }
}
