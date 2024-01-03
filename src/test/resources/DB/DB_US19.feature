Feature: "users" tablosundaki tum kayitlari "lastname"  sirali,"firstname" Ters sirali olarak listeleyin.Listedeki ilk soyadını dogrulayınız.


  Background: Database bağlantısı yapılır
    * Database baglantısı kurulur


  Scenario: "users" tablosundaki tum kayitlari "lastname" sirali,"firstname" Ters sirali olarak listelenir.Listedeki ilk soyadı dogrulanir

    * usersOrderByLastnameDescFirstname query hazirlanir

    * UsersFirstLastnameInList query hazirlanir

    * Database kapatilir
