package stepdefinitions.Ahmet;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.DBUtils;
import utilities.DB_Manage;

import java.sql.SQLException;

public class DB_US_024 {

    @Then("ilgili tabloda kayıtlı son id data bilgisi için select query hazırlanır")
    public void ilgili_tabloda_kayıtlı_son_id_data_bilgisi_için_select_query_hazırlanır() throws SQLException {

        DB_Manage.setQuery(DB_Manage.getAdminNotificationsSelect());
        DB_Manage.setPreparedStatement(DBUtils.getPraperedStatement(DB_Manage.getQuery()));
        DB_Manage.setResultSet(DB_Manage.getPreparedStatement().executeQuery());

        DB_Manage.getResultSet().next();
        DB_Manage.setAdminNotificationsId(DB_Manage.getResultSet().getInt("id"));
        System.out.println("Son id data = " + DB_Manage.getResultSet().getInt("id"));
    }

    @When("ilgili tabloda belirlenen son id arttırılarak insert query hazırlanır")
    public void ilgiliTablodaBelirlenenSonIdArttırılarakInsertQueryHazırlanır() throws SQLException {

        DB_Manage.setQuery(DB_Manage.getAdminNotificationsInsert());
        DB_Manage.setPreparedStatement(DBUtils.getPraperedStatement(DB_Manage.getQuery()));
        DB_Manage.getPreparedStatement().setInt(1, DB_Manage.getAdminNotificationsId() + 1);

        int rowCount = DB_Manage.getPreparedStatement().executeUpdate();
        Assert.assertEquals(1, rowCount);

    }

    @And("insert yapılan kaydın istenilen alanlarını update etmek için query hazırlanır")
    public void insertYapılanKaydınIstenilenAlanlarınıUpdateEtmekIcinQueryHazırlanır() throws SQLException {

        DB_Manage.setQuery(DB_Manage.getAdminNotificationsUpdate());
        DB_Manage.setPreparedStatement(DBUtils.getPraperedStatement(DB_Manage.getQuery()));
        DB_Manage.getPreparedStatement().setInt(1, DB_Manage.getAdminNotificationsId());

    }

    @When("update işleminin yapıldıgı dogrulanır")
    public void updateIslemininYapıldıgıDogrulanır() throws SQLException {

        int rowCount = DB_Manage.getPreparedStatement().executeUpdate();
        Assert.assertEquals(1, rowCount);
    }
}
