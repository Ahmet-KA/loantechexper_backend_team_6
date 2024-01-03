Feature:"Subscribers" tablosunda "email" datalarında  "a"  içermeyen dataları listeleyiniz.

  Background: Database bağlantısı yapılır
    * Database baglantısı kurulur

  Scenario: "Subscribers" tablosunda "email" datalarında  "a"  içermeyen dataları listelenir

    * subscribersQuery hazirlanir
    * data dogrulanır
    * Database kapatilir
