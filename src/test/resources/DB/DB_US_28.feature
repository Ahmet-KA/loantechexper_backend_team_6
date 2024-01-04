Feature: Update logs update testing

  Scenario: "update_logs" tablosunda "version=? " ve "id=?" olan datanın "update_log" değerini update edip doğrulayınız.
    * Database bağlantisi kurulur.
    * updatelogsInsertVersion  hazirlanir
    * updatelogs datalari update edilir
    * updatelogs datalari dogrulanir
    * database connection kapatilir