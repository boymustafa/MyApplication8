package boymustafa.com.myapplication.Login;

/**
 * Created by Boy on 16/09/2016.
 */
public interface LoginView {

    //On empty fields login button click should show snack bar with message. “Please complete the form”
    void showMsgIfEmpty();


    //email and password are correct, move to next page
    void loginSuccess(String email);

    //if email is wrong, will show "Email is not valid". if password is wrong, will show “Password should contain one special character and minimum 8 characters required”
    void loginError(String message);


}
