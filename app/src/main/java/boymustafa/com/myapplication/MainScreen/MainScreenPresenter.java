package boymustafa.com.myapplication.MainScreen;

/**
 * Created by Boy on 17/09/2016.
 */
public interface MainScreenPresenter {

    //get user info from DB
    void getUserInfo(String email); //need to add paramer, DB, after merge.

    void editPhoneNumber();

    //edit info and save to database
    void editPhone(String email,String newPhoneNumber);

    void showUserType(String userType);


    void onDestroy();

    //
    void logout();

}
