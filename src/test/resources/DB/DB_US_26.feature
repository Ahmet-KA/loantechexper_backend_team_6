Feature: Installments Testing
  Scenario: "loan_id=1" olan Kredilere Ait "total_delay_charge" bedelini "loan_id" degerine göre gruplandırarak hesaplayınız.
    * Database bağlantisi kurulur.
    * installment query hazirlanir
    * installment datalari dogrulanir
    * database connection kapatilir
