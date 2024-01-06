package utilities;

public class QueryManage {

    private String supportTicketQuery = "SELECT * FROM support_tickets WHERE ticket LIKE '4%' LIMIT 1;";




    public String getSupportTicketQuery() {
        return supportTicketQuery;
    }


    private static String setMobileUpdate = "UPDATE u168183796_qaloantec.users SET mobile = ?  WHERE username LIKE '%e_';";

    private String adminsQuery= "Select remember_token from admins  where email='info@loantechexper.com';";


    public String getAdminsQuery() {
        return adminsQuery;
    }


    public static String getSetMobileUpdate() {
        return setMobileUpdate;
    }
}
