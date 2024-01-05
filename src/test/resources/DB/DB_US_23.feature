Feature: Depozit Testing
  Scenario: Belirli Bir "created_at" aralığındaki "status=1" olan Depozitleri Toplam Tutarları ile Birlikte listeleyiniz
    * Database bağlantisi kurulur.
    * depozit query hazirlanir
    * depozit datalari dogrulanir
    * database connection kapatilir