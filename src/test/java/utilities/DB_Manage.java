package utilities;

public class DB_Manage {

    private String query = "Select email FROM students where firstname='Brian' and lastname='Kohlar';";


    public String getQuery() {
        return query;
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
    private String transactionsRemarkSumAmount =" select remark, sum(amount) as total_amount from transactions group by remark having total_amount > 1000;";
    public String getTransactionsRemarkSumAmount() {
        return transactionsRemarkSumAmount;
    }


}
