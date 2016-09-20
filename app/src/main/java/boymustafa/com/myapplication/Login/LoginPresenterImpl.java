package boymustafa.com.myapplication.Login;

import android.text.TextUtils;

import boymustafa.com.myapplication.DB.DBAdapter;

/**
 * Created by Boy on 16/09/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private DBAdapter dbAdapter;

    public LoginPresenterImpl(LoginView loginView,DBAdapter dbAdapter){
        this.loginView = loginView;
        this.dbAdapter = dbAdapter;
        this.dbAdapter.open();
    }

    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            loginView.showMsgIfEmpty();
        else {
            /*
            TODO
            1. check if email doesnt exist in db, then call loginview.loginError(String message)
            2. Check if password is not correct, then call loginview.loginError(String message)
             */
            String loginMSg = dbAdapter.login(email,password);
            if (loginMSg.contains("Email") || loginMSg.contains("password"))
                loginView.loginError(loginMSg);
            else
                loginView.loginSuccess(email);
        }
    }

    @Override
    public void onDestroy() {
        dbAdapter.close();
        loginView = null;
    }
}
