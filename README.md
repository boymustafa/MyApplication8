# MyApplication8

This is the repository I used as my showcase to show my skills in android development.
Important points:
* I used SQLite to store data from this app
* It folows MVP pattern. 
* Unit test code for splash and Login are 100%


## Functionality:
###Splash Screen  
*show some random logo for five seconds.<br />
*Login and SignUp are fragments in tabs.  Login should be default selected.

###Login  Screen
*Contain email, password fields and login button.<br />
*On empty fields login button click should show snack bar with message. “Please complete the form”<br />
*On wrong email login click  it should show error. “Email is not valid” <br />
*On wrong password login click it should show error. “Password should contain one special character and minimum 8 characters required”<br />

###SignUp Screen:
*Contain email, password, first name, last name, mobile number fields user type drop down and SignUp button.<br />
*Email, password, user type and mobile number are compulsory other fields are optional.<br />
*User types (Broker, Agent, Dealer, Private)<br />
*On empty compulsory fields signup button click should show snack bar with message. “Please complete the form”<br />
*On wrong email signup button click should show error. “Email is not valid” <br />
*On wrong password signup button click should show error. “Password should contain one special character and minimum 8 character required”<br />
*On wrong mobile number signup button click should show error. “Mobile number is not valid ( Only check for Malaysian)<br />
*No user type selected signup button click should show error. “Please select user type”<br />

###Main screen:
*Show first and last name in text fields<br />
*Mobile number text field with edit button. ( When edit button click dialog will open and user can change mobile number  and save. On successful editing should show snack bar with message “Number edit successfully”)<br />
*There is one button with text “User Type”. When button click should show Toast message like “Your account type is Private”<br />
*Logout button (When click should show dialog for 5 seconds and then open Splash screen”<br />





