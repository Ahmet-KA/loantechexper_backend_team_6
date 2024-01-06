Feature: "users" tablosunda sondan bir önceki harfi e olan "usernamelerin" "mobile" numarasını update ediniz

  Background:
    * Database bağlantisi kurulur.

  Scenario: "users" tablosunda sondan bir önceki harfi e olan "usernamelerin" "mobile" numarasını update ediniz

    * Kullanıcılar tablosu sondan bir önceki e harfini içeren kullanıcı adlarını listeler "<expectedmobile>"
    * user tablosunda gerekli update islemleri yapilir
    * database connection kapatilir
