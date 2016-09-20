package boymustafa.com.myapplication.SignUp;

import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Boy on 16/09/2016.
 */
public interface SignUpPresenter {

    /*
    email, password, first name, last name, mobile number,user type.
    mandatory parameters: email, password, mobile number,usertype
     */
    void signUp(ArrayList<String> parameters);

    void setSpinnerData(int itemLength, String[] data);

    void onDestroy();

}
