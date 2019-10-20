Feature: Homepage login

    Background: User is on the demo-store website
        Given user is on the demo-store website
        Then user should see page title is "Madison Island"
        
    Scenario: Login with username and password successully
        When user wants to go to login page 
        Then user should be redirected to login page and should see "LOGIN OR CREATE AN ACCOUNT"
        And user should see page title is "Customer Login"
        When user input correct username and password
        Then user should be redirect to account page

    Scenario: Log out successfully
        Given user has logged in
        When user wants to log out
        Then user should be redirected to logout page and see "YOU ARE NOW LOGGED OUT"
        Then user should be redirected to home page
#    
#    Scenario: Dashboard messaging
#        Given user has logged in
#        Then user should see "ACCOUNT INFORMATION"
#        Then user should see "CONTACT INFORMATION"
#        Then user should see "NEWSLETTERS"
#        Then user should see "ADDRESS BOOK"
#        Then user should see "DEFAULT BILLING ADDRESS"
#        Then user should see "DEFAULT SHIPPING ADDRESS"
     