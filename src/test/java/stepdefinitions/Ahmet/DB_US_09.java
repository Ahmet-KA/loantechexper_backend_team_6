package stepdefinitions.Ahmet;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.SQLException;

public class DB_US_09 {



    @Then("loans tablosuna gerekli insert query hazirlanir")
    public void loans_tablosuna_gerekli_insert_query_hazirlanir() throws SQLException {

        DB_Manage.setQuery(DB_Manage.getLoansInsertQuery());
        DB_Manage.setLoansNumber(DB_Manage.getFaker().internet().password());
        DB_Manage.setLoansUserId(DB_Manage.getFaker().number().numberBetween(100,1000));
        DB_Manage.setLoansId(DB_Manage.getFaker().number().numberBetween(700,1000));
        DB_Manage.setLoansPlanId(DB_Manage.getFaker().number().numberBetween(0,1));

        DB_Manage.setPreparedStatement(DBUtils.getPraperedStatement(DB_Manage.getQuery()));
        DB_Manage.getPreparedStatement().setInt(1,DB_Manage.getLoansId());
        DB_Manage.getPreparedStatement().setString(2,DB_Manage.getLoansNumber());
        DB_Manage.getPreparedStatement().setInt(3,DB_Manage.getLoansPlanId());
        DB_Manage.getPreparedStatement().setInt(4,DB_Manage.getLoansUserId());

        System.out.println("Loan_Number" + DB_Manage.getLoansNumber());

        Object rowCount = DB_Manage.getPreparedStatement().executeUpdate();
        Assert.assertEquals(1,rowCount);


    }
    @When("loans tablosundan silinecek data query hazirlanir")
    public void loans_tablosundan_silinecek_data_query_hazirlanir() throws SQLException {

        DB_Manage.setQuery(DB_Manage.getLoansDeleteQuery());
        DB_Manage.setPreparedStatement(DBUtils.getPraperedStatement(DB_Manage.getQuery()));
        DB_Manage.getPreparedStatement().setInt(1,DB_Manage.getLoansId());

    }
    @Then("datanin silindigi dogrulanir")
    public void datanin_silindigi_dogrulanir() throws SQLException {

        int rowCount = DB_Manage.getPreparedStatement().executeUpdate();
        Assert.assertEquals(1,rowCount);
    }
}
