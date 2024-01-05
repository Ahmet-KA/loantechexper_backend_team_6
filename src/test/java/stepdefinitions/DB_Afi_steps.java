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

public class DB_Afi_steps {
    String query;
    ResultSet resultSet;
    String email1; String email2; String token1; String token2;int status; String created_at;

    Faker faker=new Faker();
    PreparedStatement preparedStatement;

    @Given("Database connection is established")
    public void database_connection_is_established() {
        DBUtils.createConnection();
    }


    @Given("depositsTotalUSDAmountQuery is prepared")
    public void deposits_total_usd_amount_query_is_prepared() throws SQLException {
        query= DB_Manage.getTotalUSDQuery();
        resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("totalAmountData is verified")
    public void total_amount_data_is_verified() throws SQLException {

        double expectedTotalUSD=897876.18000000;
        resultSet.next();
        double actualTotalUSD=resultSet.getDouble(1);
        System.out.println("exp: "+ expectedTotalUSD+ "  act: "+ actualTotalUSD);
        Assert.assertEquals(expectedTotalUSD,actualTotalUSD,2);
    }
    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        DBUtils.closeConnection();
    }

    @Given("adminpasswordInsertQuery is prepared")
    public void adminpassword_ınsert_query_is_prepared() throws SQLException {
        query=DB_Manage.getAdminpasswordInsertQuery();
        email1=faker.internet().emailAddress();
        email2=faker.internet().emailAddress();
        token1=faker.internet().password();
        token2=faker.internet().password();
        status=1;
        created_at="2024.01.03 19:00";
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1,email1);
        preparedStatement.setString(2,token1);
        preparedStatement.setInt(3,status);
        preparedStatement.setString(4,created_at);
        preparedStatement.setString(5,email2);
        preparedStatement.setString(6,token2);
        preparedStatement.setInt(7,status);
        preparedStatement.setString(8,created_at);

    }
    @Given("It is verified that new datas are inserted")
    public void ıt_is_verified_that_new_datas_are_inserted() throws SQLException {

        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(2,rowCount);
    }
    @Given("depositIdQuery is prepared")
    public void deposit_ıd_query_is_prepared() throws SQLException {
        query=DB_Manage.getDepositIdQuery();
        resultSet=DBUtils.getStatement().executeQuery(query);

    }
    @Given("User ids count is verified")
    public void user_ids_count_is_verified() {
        int expectedIDCount=26;
        List<Object>  idList=DBUtils.getColumnData(query,"filtered_id");
        int actualIdCount=idList.size();
        System.out.println("list size: "+actualIdCount);
        Assert.assertEquals(expectedIDCount,actualIdCount);

    }

    ///////////////////////////////////////////////////////////////
    @Given("rememberTokenQuery is prepared")
    public void remember_token_query_is_prepared() throws SQLException {
        query="SELECT remember_token FROM admins where   email='info@loantechexper.com';";
        resultSet=DBUtils.getStatement().executeQuery(query);

    }
    @Given("Remember token data is verified")
    public void remember_token_data_is_verified() throws SQLException {

        String expectedToken="1xUgfVUD1Ggx5CVz7mxLvcye8RXRbeFqSktSIkhya321TqDkLOsqhys4pnJv";
        resultSet.next();
        String actualToken=resultSet.getString(1);
        Assert.assertEquals(expectedToken,actualToken);

    }
    @Given("cronschedulesQuery is prepared")
    public void cronschedules_query_is_prepared() throws SQLException {
        query="SELECT name FROM cron_schedules LIMIT 2;";
        resultSet=DBUtils.getStatement().executeQuery(query);


    }
    @Given("name datas is verified")
    public void name_datas_is_verified() {
        List <String> expectedNameList=new ArrayList<>();
        expectedNameList.add("5 Minutes");
        expectedNameList.add("10 Minutes");

        List<Object> actualNamelist=DBUtils.getColumnData(query,"name");

        Assert.assertEquals(expectedNameList.get(0),actualNamelist.get(0));
        Assert.assertEquals(expectedNameList.get(1),actualNamelist.get(1));

    }

    @Given("chargeValueQuery is prepared")
    public void charge_value_query_is_prepared() throws SQLException {
        query="SELECT charge FROM deposits WHERE amount<500.000 AND trx='4GC9SMZUS69S';";
        resultSet=DBUtils.getStatement().executeQuery(query);

    }
    @Given("charge data is verified")
    public void charge_data_is_verified() throws SQLException {
        int expectedCharge = 102;
        resultSet.next();
        int actualCharge= resultSet.getInt(1);
        Assert.assertEquals(expectedCharge,actualCharge);


    }

    @Given("supportTicketQuery is prepared")
    public void support_ticket_query_is_prepared() throws SQLException {
     query="SELECT support_ticket_id FROM support_messages WHERE message='56utizi';";
     resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("support ticket id data is verified")
    public void support_ticket_id_data_is_verified() throws SQLException {
        int expectedId=38;
        resultSet.next();
        int actualId=resultSet.getInt(1);
        System.out.println("actual id: "+actualId);
        Assert.assertEquals(expectedId,actualId);
    }

    @Given("supportTicketSubjectQuery is prepared")
    public void support_ticket_subject_query_is_prepared() throws SQLException {
       query="SELECT subject FROM support_tickets WHERE ticket LIKE '4%';";
       resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("subject datas are verified")
    public void ssubject_datas_are_verified() {
        String expectedSubject="Blue Test Ticket";
        List<Object> subjectList=DBUtils.getColumnData(query,"subject");
        Assert.assertTrue(subjectList.contains(expectedSubject));

    }

    @Given("userIdNameQuery is prepared")
    public void user_ıd_name_query_is_prepared() throws SQLException {
        query="SELECT * from users where not country_code='TR' and  id=11 ;";
        resultSet=DBUtils.getStatement().executeQuery(query);

    }
    @Given("user datas are verified")
    public void user_datas_are_verified() throws SQLException {
        String expectedName="Mehmet";
        String expectedLastName="Genç";
        resultSet.next();
        Assert.assertEquals(expectedName,resultSet.getString("firstname"));
        Assert.assertEquals(expectedLastName,resultSet.getString("lastname"));

    }

    @Given("loanNumberInsertQuery is prepared")
    public void loan_number_ınsert_query_is_prepared() throws SQLException {
        query="INSERT INTO  loans (id,loan_number,user_id,plan_id) values (?,?,?,?);";
       preparedStatement=DBUtils.getPraperedStatement(query);
       preparedStatement.setInt(1,518);
       preparedStatement.setString(2,"FT110000TT333");
       preparedStatement.setInt(3,104);
       preparedStatement.setInt(4,211);
       int rowCount=preparedStatement.executeUpdate();
       Assert.assertEquals(1,rowCount);


    }
    @Given("loanNumberDeleteQuery is prepared")
    public void loan_number_delete_query_is_prepared() throws Exception {
        query="DElETE from loans where loan_number=?;";
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setString(1,"FT110000TT333");

    }
    @Given("It is verified tahat the data is deleted")
    public void ıt_is_verified_tahat_the_data_is_deleted() throws SQLException {

        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);


    }

    @Given("deviceTokenInsertQuery is prepared")
    public void device_token_ınsert_query_is_prepared() throws SQLException {
        query="INSERT INTO  device_tokens (id,user_id,is_app,token) values (?,?,?,?);";
        preparedStatement=DBUtils.getPraperedStatement(query);
        preparedStatement.setInt(1,200);
        preparedStatement.setInt(2,10);
        preparedStatement.setInt(3,3);
        preparedStatement.setString(4,"ABBCD00000010");


    }
    @Given("device token data is verified")
    public void device_token_data_is_verified() throws SQLException {
        int rowCount=preparedStatement.executeUpdate();
        Assert.assertEquals(1,rowCount);
    }

    @Given("adminNotificationQuery is prepared")
    public void admin_notification_query_is_prepared() throws SQLException {
        query="select count(id) from admin_notifications where user_id=1 and is_read=0;";
        resultSet=DBUtils.getStatement().executeQuery(query);
    }
    @Given("user count is verified")
    public void user_count_is_verified() throws SQLException {
        int expectedUserCount=1;
        resultSet.next();
        int actualUserCount=resultSet.getInt(1);
        Assert.assertEquals(expectedUserCount,actualUserCount);

    }





}
