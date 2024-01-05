Feature: Data Testing

  @10
  Scenario:Database üzerinde "divice_tokens" tablosuna istenen veriyi tek sorguda ekleyiniz.

    When Database bağlantisi kurulur.
    Then  diviceTokensQuery hazirlanir
    And  datanin eklendigi dogrulanir
    Then database connection kapatilir