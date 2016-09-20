package boymustafa.com.myapplication.MainScreen;

import android.util.Log;

import java.util.ArrayList;

import boymustafa.com.myapplication.DB.DBAdapter;

/**
 * Created by Boy on 17/09/2016.
 */
public class MainScreenPresenterImpl implements MainScreenPresenter {

    private MainScreenView mVIew;
    private DBAdapter dbAdapter;
    private ArrayList<String> userInfo = new ArrayList<>();


    public MainScreenPresenterImpl(MainScreenView mVIew, DBAdapter dbAdapter){
        this.mVIew = mVIew;
        this.dbAdapter = dbAdapter;
        this.dbAdapter.open();
    }



    @Override
    public void getUserInfo(String email) {
        if (!userInfo.isEmpty())
            userInfo.clear();




        userInfo = dbAdapter.showSinlgeEntry(email);
        mVIew.setUserDetails(userInfo);
    }

    @Override
    public void editPhoneNumber() {
        mVIew.showDialogPhoneNumber();
    }

    @Override
    public void editPhone(String email, String newPhoneNumber) {
        Log.d("TA","- new number"+newPhoneNumber);
       String successMSg =  dbAdapter.updatePhoneNumber(email,newPhoneNumber);
        if (successMSg.contains("successfully"))
            mVIew.successEditPhone(newPhoneNumber);

    }


    @Override
    public void showUserType(String userType) {
        mVIew.showUserTypeInfo(userType);
    }

    @Override
    public void onDestroy() {
        this.dbAdapter.close();
        mVIew = null;
    }


    @Override
    public void logout() {
        mVIew.logout();
    }
}
