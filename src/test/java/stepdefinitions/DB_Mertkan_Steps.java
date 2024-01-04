package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Mertkan_Steps {
    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs;

    Faker faker = new Faker();
    PreparedStatement preparedStatement;

    int id;
    String name;
    String description;

    String update_log;
    String version;

    @Given("admin notifications tablosundaki degerler hazirlanir")
    public void admin_notifications_tablosundaki_degerler_hazirlanir() throws SQLException {
        query = manage.getAdminNotificationsUserIdSelect();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("admin notifications datalari dogrulanir")
    public void admin_notifications_datalari_dogrulanir() throws SQLException {
        List<String> idNumarasi20denBuyukOlanlar = new ArrayList<>();
        while (rs.next()) {

            String id = rs.getString("id");
            idNumarasi20denBuyukOlanlar.add(id);
        }
        System.out.println(idNumarasi20denBuyukOlanlar);

    }

    @Given("gateways tablosundaki degerler hazirlanir")
    public void gateways_tablosundaki_degerler_hazirlanir() throws SQLException {
        query = manage.getGateways();
        rs = DBUtils.getStatement().executeQuery(query);
    }
    @Given("gateways datalari dogrulanir")
    public void gateways_datalari_dogrulanir() throws SQLException {
        List<String> enBuyuk5Code = new ArrayList<>();
        while (rs.next()) {

            String code = rs.getString("code");
            enBuyuk5Code.add(code);
        }
        System.out.println(enBuyuk5Code);

    }
    @Given("categoriesInsert  hazirlanir")
    public void categories_ınsert_hazirlanir() throws SQLException {
        query = manage.getCategoriesInsert();
        id=faker.number().numberBetween(910,990);
        name=faker.name().fullName();
        description=faker.ancient().hero();
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,description);
        System.out.println("**"+id+"**"+name+"**"+description);

    }
    @Given("categories datalari dogrulanir")
    public void categories_datalari_dogrulanir() throws SQLException {
        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);
    }


    @Given("updatelogsInsertVersion  hazirlanir")
    public void updatelogs_ınsert_version_hazirlanir() throws SQLException {
        query = manage.getUpdateLogsInsertVersion();
        id = faker.number().numberBetween(250,300);
        version = "?";
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,version);
        System.out.println("**"+id+"**"+version+"**");

    }
    @Given("updatelogs datalari update edilir")
    public void updatelogs_datalari_update_edilir() throws SQLException {
        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);
        query=manage.getUpdateLogsUpdateLog();
        update_log=faker.ancient().hero();
        preparedStatement.setString(1,update_log);

    }
    @Given("updatelogs datalari dogrulanir")
    public void updatelogs_datalari_dogrulanir() throws SQLException {
     query=manage.getUpdateLogsUpdatedLog();
     rs = DBUtils.getStatement().executeQuery(query);
        System.out.println(update_log);
    }
}
