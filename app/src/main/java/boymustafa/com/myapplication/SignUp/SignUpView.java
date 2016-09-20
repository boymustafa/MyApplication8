package boymustafa.com.myapplication.SignUp;

/**
 * Created by Boy on 16/09/2016.
 */
public interface SignUpView {

    //if email/password/mobile number/user type is empty, show snack bar “Please complete the form”
    void showEmptyFieldError();

    //if email is not valid, show error “Email is not valid”
    void emailNotValid();

    //if email already exist in DB, show error "Email already exist"
    void emailExistError();

    //if password is not correct show error “Password should contain one special character and minimum 8 character required”
    void wrongPassword();

    //if phone number is not malaysian number, show error “Mobile number is not valid ( Only check for Malaysian)"
    void wrongPhoneNumber();

    //if user didnt select any item on spinner, show error “Please select user type”
    void notSelectSpinner();

//    void setAdapter(String[] data);
//
//    void setSpinner(String[] data);

    void setAdapter(UserTypeModel[] users);

    void setSpinner(UserTypeModel[] users);

    void success(String email);


}
