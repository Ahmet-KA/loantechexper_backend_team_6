Feature: "users" tablosundaki tum kayitlari "lastname"  sirali,"firstname" Ters sirali olarak listeleyin.Listedeki ilk soyadını dogrulayınız.


  Background: Database bağlantısı yapılır
    * Database bağlantisi kurulur.


  Scenario: "users" tablosundaki tum kayitlari "lastname" sirali,"firstname" Ters sirali olarak listelenir.Listedeki ilk soyadı dogrulanir

    * usersOrderByLastnameAscFirstnameDesc query hazirlanir

    * UsersFirstLastnameInList query hazirlanir

    * database connection kapatilir
