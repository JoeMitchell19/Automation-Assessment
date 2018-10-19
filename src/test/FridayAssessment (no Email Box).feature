Feature: To have a working backend with customisable options
  As an admin
  I want to add, remove and update my data on my database
  So that I can go on to host correct data for other applications

Scenario: Add a User to the database
  Given that you are on the create UserScreen
  When the User details are entered on the Create UserScreen 
  And the details are submitted on the Create UserScreen
  Then the Username should be visible on the UsersScreen

Scenario Outline: Add a User to the database
  Given that you are on the create UserScreen
  When the User details "<Username>" username, "<Password>" password, "<ConfirmPassword>" confirm Password, and "<FullName>" Fullname are entered on the Create UserScreen 
  And the details are submitted on the Create UserScreen
  Then the "<Username>" username should be visible on the UsersScreen
  
Examples:
  | Username | Password | ConfirmPassword | FullName |
  | chris95 | guest | guest | Christopher Perrins |
  | m4tt | chickenWing | chickenWing | matthewhunt |
  | dev | data | data | Dev Gonsai | 
  | matthewhunt | chickenWing | chickenWing | matttt |
  
Scenario Outline: View the details of a User on a database
  Given the "<username>" username is visible on the UsersScreen
  When the "<username>" username is clicked on the UserScreen
  Then the User Profile should display the "<username>" username on the ProfileScreen
  
Examples:
  | username |
  | chris95 |
  | dev |
  | matthewhunt |
  
Scenario Outline: Updating the FullName of a User
  Given the "<username>" Username's profile page has been loaded
  And the configure button has been clicked on the profile page
  When I change the old FullName on the Configure Page to a new FullName "<newfullname>"
  And I save the changes to the Configure Page
  Then the Configure Page should show the NewFullName "<newfullname>"
  
Examples:
  | username | newfullname |
  | chris95 | chris chris chris |
  | m4tt | mattttttt |
  | dev | Dev Data |
  