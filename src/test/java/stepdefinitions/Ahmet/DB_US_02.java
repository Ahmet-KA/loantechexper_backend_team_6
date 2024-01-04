package stepdefinitions.Ahmet;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.ConfigurationReader;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_US_02 {
    ResultSet resultSet;
    PreparedStatement preparedStatement;


    @And("resultset ile donen remember_token alanindaki data dogrulanir")
    public void resultsetIleDonenRemember_tokenAlanindakiDataDogrulanir() throws SQLException {

        String expected = "1xUgfVUD1Ggx5CVz7mxLvcye8RXRbeFqSktSIkhya321TqDkLOsqhys4pnJv";
        resultSet.next();

        System.out.println("actualRememberToken   = " + resultSet.getString("remember_token"));
        System.out.println("expectedRememberToken = " + expected);

        Assert.assertEquals(expected,resultSet.getString("remember_token"));
    }

    @Then("gerekli {string} adresi için query hazirlanir")
    public void gerekliAdresiIcinQueryHazirlanir(String email) throws SQLException {

        String emailAdres = ConfigurationReader.getProperty(email);
        System.out.println("emailAdres = " + emailAdres);

        DB_Manage.setQuery(DB_Manage.getRememberTokenQuery());
        preparedStatement =DBUtils.getPraperedStatement(DB_Manage.getQuery());
        preparedStatement.setString(1, emailAdres);

        resultSet = preparedStatement.executeQuery();
    }
}
