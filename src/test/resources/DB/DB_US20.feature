Feature: "Transactions" tablosunda "remark" türüne göre "amount" değerleri toplamını bulup 1000 $dan yüksek olanları doğrulayınız.

  Background: Database bağlantısı yapılır
    * Database baglantısı kurulur


  Scenario: "transactions" tablosunda "remark" turune gore "amount" degerleri toplamini bulup 1000 $dan yuksek olanlar dogrulanir

    * transactionsRemarkSumAmount query hazirlanir

    * Database kapatilir


