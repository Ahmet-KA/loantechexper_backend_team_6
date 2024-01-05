Feature: Admin notifications data testing

  Scenario: Database üzerinde "admin_notifications" tablosunda "user id =2 " olan kullanıcıların "id numarası 20 den büyük olanları" doğrulayınız.
    * Database bağlantisi kurulur.
    * admin notifications tablosundaki degerler hazirlanir
    * admin notifications datalari dogrulanir
    * database connection kapatilir