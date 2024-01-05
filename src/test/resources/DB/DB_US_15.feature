Feature:"Subscribers" tablosunda "email" datalarında  "a"  içermeyen dataları listeleyiniz.

  Background: Database bağlantısı yapılır
    * Database bağlantisi kurulur.

  Scenario: "Subscribers" tablosunda "email" datalarında  "a"  içermeyen dataları listelenir

    * subscribersQuery hazirlanir
    * data listelenir ve dogrulanır
    * database connection kapatilir
