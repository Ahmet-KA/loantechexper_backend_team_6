Feature:Add multiple data to the "admin_password_resets" table in the database at the same time.


  Scenario: TC_01 At the same time adding multiple data to the "admin_password_resets" table test

    * Database connection is established
    * adminpasswordInsertQuery is prepared
    * It is verified that new datas are inserted
    * Database connection is closed