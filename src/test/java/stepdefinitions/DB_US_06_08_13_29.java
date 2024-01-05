package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DB_US_06_08_13_29 {
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    DB_Manage manage = new DB_Manage();

    int id;
    int cronJobid;
    Date startDate;
    Date endDate;
    int dutaion;
    String error;
    Date createdAt;
    Date upDatedAt;

    String query;


    Faker faker = new Faker();



    // US 06

    @Then("Support message tablosuna gerekli query hazirlanir")
    public void support_message_tablosuna_gerekli_query_hazirlanir() throws SQLException {

    query= DB_Manage.getSupportMessageSupportTicket();
    resultSet= DBUtils.getStatement().executeQuery(DB_Manage.getSupportMessageSupportTicket());

    }

    @Then("resultset ile donen datanin {string} oldugu dogrulanir")
    public void resultsetIleDonenDataninOlduguDogrulanir(String arg0) throws SQLException {

            int expected2= 424;
            resultSet.next();
            System.out.println("actual"+resultSet.getInt("id"));
            System.out.println("expected"+expected2);

            Assert.assertEquals(expected2,resultSet.getInt("id"));
        }

        // US08


    @Then("Users  tablosuna gerekli query hazirlaniir")
    public void users_tablosuna_gerekli_query_hazirlaniir() throws SQLException {

query=DB_Manage.getUsersFirstNameLastName();
resultSet=DBUtils.getStatement().executeQuery(DB_Manage.getUsersFirstNameLastName());


    }
    @Then("resultset ile donen datanin firstname ve lastname bilgielri dogrulanir")
    public void resultset_ile_donen_datanin_firstname_ve_lastname_bilgielri_dogrulanir() throws SQLException {

  String expectedIsım= "Mehmet";
    resultSet.next();
    Assert.assertEquals(expectedIsım,resultSet.getString("firstname"));

        System.out.println("sonuc"+resultSet.getString("firstname"));


    String expectedSoyIsım= "Genç";
    resultSet.getRow();
    Assert.assertEquals(expectedSoyIsım,resultSet.getString("lastname"));
        System.out.println("sonuc"+resultSet.getString("lastname"));


    }

    //US 13


    @Then("cron jobs  tablosuna gerekli query hazirlaniir")
    public void cron_jobs_tablosuna_gerekli_query_hazirlaniir() throws SQLException {


  query=DB_Manage.getCronJobLogsUpdate();
  preparedStatement=DBUtils.getPraperedStatement(query);
  preparedStatement.setInt(1,faker.number().numberBetween(900,1000));



    }

    @Then("resultset ile donen datanin tabloya eklendigi dogrulanir")
    public void resultset_ile_donen_datanin_tabloya_eklendigi_dogrulanir() throws SQLException {


        int rowCount=  preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);

    }

    // US29


    @Then("update log delete query hazirlanir ve  Update_logs tablosuna execute edilir")
    public void updateLogDeleteQueryHazirlanirVeUpdate_logsTablosunaExecuteEdilir()  throws SQLException {


        query=DB_Manage.getUpdateLogDeleteQuery();
        preparedStatement= DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,799);


        int rowCountt=  preparedStatement.executeUpdate();


     Assert.assertEquals(0,rowCountt);



    }



    }



