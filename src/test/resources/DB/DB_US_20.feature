
Feature: "Transactions" tablosunda "remark" türüne göre "amount" değerleri toplamını bulup 1000 $dan yüksek olanları doğrulayınız.

  Background: Database bağlantısı yapılır
    * Database bağlantisi kurulur.


  Scenario: "transactions" tablosunda "remark" turune gore "amount" degerleri toplamini bulup 1000 $dan yuksek olanlar dogrulanir

    * transactionsRemarkSumAmount query hazirlanir ve sorgu sonucu listelenir
    * total_amount 1000 $dan yuksek olanlar dogrulanir
    * database connection kapatilir


