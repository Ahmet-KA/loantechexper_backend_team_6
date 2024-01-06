Feature: Database üzerinde "admin_notifications" tablosunda "user id= 1" ve "is_read=0"olan kullanıcı sayılarının adedini doğrulayınız.

  Background:
    * Database bağlantisi kurulur.

  Scenario: Database üzerinde "admin_notifications" tablosunda "user id= 1" ve "is_read=0"olan kullanıcı sayılarının adedini doğrulayınız.
    Then admin notifications tablosunda gerekli query hazirlanir
    Then admin notifications datalari resultset ile dogrulanir
    And database connection kapatilir


