
Feature: "users" tablosundaki tum kayitlari "lastname"  sirali,"firstname" Ters sirali olarak listeleyin.Listedeki ilk soyadını dogrulayınız.


  Background: Database bağlantısı yapılır
    * Database bağlantisi kurulur.


  Scenario: "users" tablosundaki tum kayitlari "lastname" sirali,"firstname" Ters sirali olarak listelenir.Listedeki ilk soyadi dogrulanir

    * users tablosunu lastname gore siralama sorgusu hazirlanir ve sorgu sonucu listelenir
    * users listesindeki ilk soyadi dogrulanir
    * database connection kapatilir
