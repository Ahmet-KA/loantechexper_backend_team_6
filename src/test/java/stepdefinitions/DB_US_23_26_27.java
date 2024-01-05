package stepdefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_US_23_26_27 {

    String query;
    ResultSet resultSet;
    @Given("depozit query hazirlanir")
    public void depozit_query_hazirlanir() throws SQLException {
        query = DB_Manage.getDepozitquery();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("depozit datalari dogrulanir")
    public void depozit_datalari_dogrulanir() throws SQLException {
        int topamount = 8970;
        resultSet.next();
        Assert.assertEquals(topamount,resultSet.getInt("total_amount"));
    }

    @Given("installment query hazirlanir")
    public void installment_query_hazirlanir() throws SQLException {
        query = DB_Manage.getinstallmentsquery();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("installment datalari dogrulanir")
    public void installment_datalari_dogrulanir() throws SQLException {
        int total_delay_charge = 10;
        resultSet.next();
        Assert.assertEquals(total_delay_charge,resultSet.getInt("total_delay_charge"));
    }

    @Given("Loan_plans query hazirlanir")
    public void loan_plans_query_hazirlanir() throws SQLException {
        query = DB_Manage.getloan_plansquery();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }
    @Given("Loan_plans datalari dogrulanir")
    public void loan_plans_datalari_dogrulanir() throws SQLException {
        List<String> actualName = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            actualName.add(name);
        }
        Assert.assertEquals("Luna",actualName.get(0));
        Assert.assertEquals("Luna",actualName.get(1));
        Assert.assertEquals("Kredi5",actualName.get(2));
    }






}
