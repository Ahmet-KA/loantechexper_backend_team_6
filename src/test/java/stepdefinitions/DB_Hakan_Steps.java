package stepdefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;
import utilities.QueryManage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB_Hakan_Steps {

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    DB_Manage manage = new DB_Manage();
    String query;

    int rowCount;
    List<String> actualSubjectList = new ArrayList<>();

    List<String> actualMobileList = new ArrayList<>();

    @Given("support tickets tablosuna gerekli query hazirlanir")
    public void support_tickets_tablosuna_gerekli_query_hazirlanir() throws SQLException {
        query = manage.getSupportTickets();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Given("support tickets datalari dogrulanir")
    public void support_tickets_datalari_dogrulanir() throws SQLException {
        while (resultSet.next()) {
            String subject = resultSet.getString("subject");
            System.out.println("Subject: " + subject);
        }
    }

    @Given("admin notifications tablosunda gerekli query hazirlanir")
    public void admin_notifications_tablosunda_gerekli_query_hazirlanir() throws SQLException {
        query = manage.getAdminNotificationsVerification();
        resultSet = DBUtils.getStatement().executeQuery(query);
    }

    @Given("admin notifications datalari resultset ile dogrulanir")
    public void admin_notifications_datalari_resultset_ile_dogrulanir() throws SQLException {
        if (resultSet.next()) {
            int unreadCount = resultSet.getInt("unreadCount");
            System.out.println("Bildirim Sayısı: " + unreadCount);
        }
    }


    @Given("Kullanıcılar tablosu sondan bir önceki e harfini içeren kullanıcı adlarını listeler {string}")
    public void Kullanıcılar_tablosu_sondan_bir_önceki_e_harfini_içeren_kullanıcı_adlarını_listeler(String expectedmobile) throws SQLException {

        query = QueryManage.getSetMobileUpdate();

        preparedStatement = DBUtils.getPraperedStatement(query);

        preparedStatement.setString(1, expectedmobile);

        rowCount = preparedStatement.executeUpdate();
    }

    @Given("user tablosunda gerekli update islemleri yapilir")
    public void user_tablosunda_gerekli_update_islemleri_yapilir() {

        Assert.assertEquals(18,rowCount);

    }

}



