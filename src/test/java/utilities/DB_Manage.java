package utilities;

public class DB_Manage {

    private String query="Select email FROM students where firstname='Brian' and lastname='Kohlar';";


    public String getQuery() {
        return query;
    }

}
