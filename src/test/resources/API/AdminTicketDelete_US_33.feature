Feature: Bir yönetici (admin) olarak API baglantisi üzerinden sistemdeki tickets kaydini silebilmeliyim.

Scenario Outline:  api/tickets/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve dogru (id) iceren bir DELETE
request gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Ticket
deleted" oldugu dogrulanmali

  Given The API user sets "api/tickets/delete/<id>" path parameters
  # 309-310-313 ID ile calistirilabilir
  And Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder
  Then  The API user verifies that the status code is 200
  And   It must be verified that the message information in the response body is "Ticket deleted"

  Examples:
    | id  |
    | 309 |


Scenario:api/tickets/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir DELETE
request gönderildiginde dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No id" oldugu dogrulanmali


  Given The API user sets "api/tickets/delete" path parameters
  And Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder
  Then  The API user verifies that the status code is 203
  And   It must be verified that the message information in the response body is "No id"



Scenario Outline :api/tickets/delete/{{id}} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir
DELETE request gönderildiginde dönen status code'in 203 oldugu ve response body'deki message bilgisinin "No ticket."
oldugu dogrulanmali

  Given The API user sets "api/tickets/delete/<id>" path parameters
  And Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder
  Then  The API user verifies that the status code is 203
  And   It must be verified that the message information in the response body is "No ticket."

  Examples:
    | id  |
    | 1254 |

Scenario Outline: api/tickets/delete/{{id}} endpoint'ine gecersiz authorization bilgileri ile bir DELETE body gönderildiginde
dönen status code'in 401 oldugu ve response body'deki error bilgisinin "Unauthorized request" oldugu dogrulanmali

  Given The API user sets "api/tickets/delete/<id>" path parameters
  And Api kullanicisi user ticket delete endpointinden donen responsei gecersiz authorization bilgisi iile kaydeder
  Then  The API user verifies that the status code is 401
  And   It must be verified that the message information in the response body is "Unauthorized request"

  Examples:
    | id  |
    | 1254 |

Scenario Outline:API uzerinden silinmek istenen tickets kaydinin silindigi, API uzerinden dogrulanmali.
(Response body'de dönen Deleted ticket id ile api/tickets/details/{{id}} endpoint'ine GET request
gönderilerek kaydın silindiği doğrulanabilir.)

  Given The API user sets "api/tickets/details/<id>" path parameters
  # 308-311-312 ile calistirilabilir
  And Api kullanicisi user ticket delete endpointinden donen responsei gecerli authorization bilgisi iile kaydeder
  Then  The API user verifies that the status code is 203
  And   It must be verified that the message information in the response body is "No ticket."

  Examples:
    | id  |
    | 308 |
