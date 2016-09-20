package boymustafa.com.myapplication.Login;

/**
 * Created by Boy on 16/09/2016.
 */
public interface LoginPresenter {

    void login(String email, String password); //do login

    void onDestroy();

}
