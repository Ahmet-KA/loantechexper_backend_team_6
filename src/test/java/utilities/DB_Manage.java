package utilities;

import com.github.javafaker.Faker;
import io.cucumber.java.sl.In;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_Manage {


    private static String rememberTokenQuery = "select remember_token from admins where email=?;";
    private static String loansInsertQuery = "insert into loans(id,loan_number,plan_id,user_id) values (?,?,?,?)";
    private static String loansDeleteQuery = "delete from loans where id=?";
    private static Integer loansId;
    private static String loansNumber;
    private static Integer loansPlanId;
    private static Integer loansUserId;
    private static String query;
    private static Faker faker = new Faker();
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;


    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static void setResultSet(ResultSet resultSet) {
        DB_Manage.resultSet = resultSet;
    }

    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public static void setPreparedStatement(PreparedStatement preparedStatement) {
        DB_Manage.preparedStatement = preparedStatement;
    }

    public static Faker getFaker() {
        return faker;
    }

    public static String getQuery() {
        return query;
    }


    public static void setQuery(String query) {
        DB_Manage.query = query;
    }

    public static void setLoansId(Integer loansId) {
        DB_Manage.loansId = loansId;
    }

    public static Integer getLoansId() {
        return loansId;
    }

    public static void setLoansNumber(String loansNumber) {
        DB_Manage.loansNumber = loansNumber;
    }

    public static String getLoansNumber() {
        return loansNumber;
    }

    public static void setLoansPlanId(Integer loansPlanId) {
        DB_Manage.loansPlanId = loansPlanId;
    }

    public static Integer getLoansPlanId() {
        return loansPlanId;
    }

    public static void setLoansUserId(Integer loansUserId) {
        DB_Manage.loansUserId = loansUserId;
    }

    public static Integer getLoansUserId() {
        return loansUserId;
    }

    public static String getRememberTokenQuery() {
        return rememberTokenQuery;
    }

    public static String getLoansInsertQuery() {
        return loansInsertQuery;
    }

    public static String getLoansDeleteQuery() {
        return loansDeleteQuery;

    }

    //murat
    private String subscribersEmailNotLike = "select * from subscribers where email not like '%a%';";

    public String getSubscribersEmailNotLike() {
        return subscribersEmailNotLike;
    }

    //murat
    private String usersOrderByLastnameDescFirstname = "select * from users order by lastname asc, firstname DESC;";

    public String getUsersOrderByLastnameDescFirstname() {
        return usersOrderByLastnameDescFirstname;
    }

    //murat
    private String UsersFirstLastnameInList = " select lastname from users where lastname is not null order by lastname asc, firstname DESC LIMIT 1;";

    public String getUsersFirstLastnameInList() {
        return UsersFirstLastnameInList;
    }

    //murat
    private String transactionsRemarkSumAmount = " select remark, sum(amount) as total_amount from transactions group by remark having total_amount > 1000;";

    public String getTransactionsRemarkSumAmount() {
        return transactionsRemarkSumAmount;

    }
}
