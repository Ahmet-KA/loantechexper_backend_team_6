
@silme

Feature:Update_logs tablosunda "id=?" değerine göre bir datayı siliniz ve silindiğini doğrulayınız
  Background: Database bağlantısı yapılır
    Then Database bağlantisi kurulur.
  Scenario: Update_logs tablosunda "id=?" değerine göre bir datayı siliniz ve silindiğini doğrulayınız
    Then update log delete query hazirlanir ve  Update_logs tablosuna execute edilir
    Then database connection kapatilir


