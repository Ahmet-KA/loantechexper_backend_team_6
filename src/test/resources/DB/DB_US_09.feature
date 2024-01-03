
  Feature: Data Testing
    @2
    Scenario: Database üzerinden "loans" tablosunda "loan_number" girerek istenen datayı siliniz ve silindiğini doğrulayınız.

      Given Database bağlantisi kurulur.
      Then  loans tablosuna gerekli insert query hazirlanir
      When  loans tablosundan silinecek data query hazirlanir
      Then  datanin silindigi dogrulanir
      And   database connection kapatilir










