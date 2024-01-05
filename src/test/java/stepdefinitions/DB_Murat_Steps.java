package stepdefinitions;

import io.cucumber.java.en.Given;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class DB_Murat_Steps {

    DB_Manage manage = new DB_Manage();
    String query;
    ResultSet rs = null;


    //================= US 15 ========================

    @Given("subscribersQuery hazirlanir")
    public void subscribers_query_hazirlanir() throws SQLException {
        query = manage.getSubscribersEmailNotLike();
        rs = DBUtils.getStatement().executeQuery(query);
    }

    @Given("data listelenir ve dogrulanır")
    public void data_listelenir_ve_dogrulanır() throws SQLException {
        List<String> aIcerenEmail = new ArrayList<>();
        List<String> aIcermeyenEmail = new ArrayList<>();

        while (rs.next()) {
            String email = rs.getString("email");
            if (email.contains("a")) {
                aIcerenEmail.add(email);
            } else {
                aIcermeyenEmail.add(email);
            }
            System.out.println(aIcermeyenEmail);
        }
        assertFalse(aIcermeyenEmail.isEmpty());
    }

    //================= US 19 ========================

    @Given("users tablosunu lastname gore siralama sorgusu hazirlanir ve sorgu sonucu listelenir")
    public void usersOrderByLastnameAscFirstnameDesc_query_hazirlanir() throws SQLException {
        query = manage.getUsersOrderByLastnameAscFirstnameDesc();
        rs = DBUtils.getStatement().executeQuery(query);
        System.out.println(DBUtils.getColumnData(query, "lastname"));
    }

    @Given("users listesindeki ilk soyadi dogrulanir")
    public void usersListesiIlkSoyadiDogrula() throws SQLException {
        String expectedValue = "ABSHIREBATZ";
        String actuelValue = DBUtils.getColumnData(query, "lastname").get(0).toString();

        assertEquals(expectedValue, actuelValue);
    }

    //================ US 20 =========================

    List<List<Object>> istenenRemarkListesi;

    @Given("transactionsRemarkSumAmount query hazirlanir ve sorgu sonucu listelenir")
    public void transactions_remark_sum_amounte_query_hazirlanir() throws SQLException {

        query = manage.getTransactionsRemarkSumAmount();
        istenenRemarkListesi = DBUtils.getQueryResultList(query);
        for (List<Object> each : istenenRemarkListesi) {
            System.out.println(each);
        }
    }

    @Given("total_amount 1000 $dan yuksek olanlar dogrulanir")
    public void total_amount_$dan_yuksek_olanlar_dogrulanir() {

        for (List<Object> each : istenenRemarkListesi) {
            double remarkTotalAmount = ((Number) each.get(1)).doubleValue();
            // Object'i Number'a  sonra double'a dönüştür,

            assertTrue(remarkTotalAmount > 1000);
        }
    }
}
