Feature:Verify user_ids with "amount" value between $100 and $500 in the "deposits" table in the database


  Scenario: TC_01 User_id test of deposit amounts within a certain range

    * Database connection is established
    * depositIdQuery is prepared
    * User ids count is verified
    * Database connection is closed