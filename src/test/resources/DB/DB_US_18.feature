Feature: Categories data insert testing

  Scenario:Categories tablosuna sadece "id,name,discription" içeren datayı ekleyiniz.Eklendiğini Doğrulayınız.
    * Database bağlantisi kurulur.
    * categoriesInsert  hazirlanir
    * categories datalari dogrulanir
    * database connection kapatilir