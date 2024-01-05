Feature: Loan_plans Testing
  Scenario: "delay_value" ve "fixed_charge ya da percent_charge" değerlerine göre loan_plans tablosundaki ilk 3 "name" bilgisini doğrulayınız.
    * Database bağlantisi kurulur.
    * Loan_plans query hazirlanir
    * Loan_plans datalari dogrulanir
    * database connection kapatilir
