package boymustafa.com.myapplication.MainScreen;

import java.util.ArrayList;

/**
 * Created by Boy on 17/09/2016.
 */
public interface MainScreenView {

    //if user go directly to main view and his data doesnt exist in db, show error
    void userIsNotRegistered();

    void setUserDetails(ArrayList<String> userDetails);

    void showUserTypeInfo(String userType);

    void showDialogPhoneNumber();

    void successEditPhone(String newPhneNumber);

    void logout();


}
