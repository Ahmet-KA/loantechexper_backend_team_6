
  Feature: Data Testing
    @3
    Scenario: "id=?" olan kullanıcının "is_read=0" olan bildirimlerini '1' Olarak Update edip doğrulayınız.

      Given Database bağlantisi kurulur.
      Then  ilgili tabloda kayıtlı son id data bilgisi için select query hazırlanır
      When  ilgili tabloda belirlenen son id arttırılarak insert query hazırlanır
      And   insert yapılan kaydın istenilen alanlarını update etmek için query hazırlanır
      When  update işleminin yapıldıgı dogrulanır
      Then  database connection kapatilir




