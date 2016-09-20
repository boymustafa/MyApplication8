package boymustafa.com.myapplication.SignUp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;

import boymustafa.com.myapplication.DB.DBAdapter;
import boymustafa.com.myapplication.R;
import boymustafa.com.myapplication.Utils;

/**
 * Created by Boy on 16/09/2016.
 */
public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView signUpView;
    String email, password,fName,lName,phone,user_type,user_typeID;
    DBAdapter dbAdapter;

    public SignUpPresenterImpl(SignUpView signUpView, DBAdapter dbAdapter){
        this.signUpView = signUpView;
        this.dbAdapter = dbAdapter;
        this.dbAdapter.open();
    }


    @Override
    public void signUp(ArrayList<String> parameters) {


        for (int i=0;i<parameters.size();i++){
            email = parameters.get(0);
            password = parameters.get(1);
            fName = parameters.get(2);
            lName = parameters.get(3);
            phone = parameters.get(4);
            user_type = parameters.get(5);
            user_typeID = parameters.get(6);
        }



        if (TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(phone)

                ){
            signUpView.showEmptyFieldError();
        }

        else{
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Log.d("emailCheck",email);
                signUpView.emailNotValid();
            } else if (!password.matches("^(?=.*[A-Za-z])(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")){
                signUpView.wrongPassword();
            } else if (!Utils.isValidPhoneNumber(phone,"MY")){
                signUpView.wrongPhoneNumber();
            } else if (user_typeID.equals("0")){
                signUpView.notSelectSpinner();
            }

            else {


                /*
                TODO: insert into database. DO some checking, if email already exist, show error to user. IF no error, insert
                new data to DB and move to another screen
                 */

                if(dbAdapter.checkEmail(email))
                    signUpView.emailExistError();
                else {
                    dbAdapter.insertEntry(parameters);
                    signUpView.success(email);
                }



            }

        }


    }

    @Override
    public void setSpinnerData(int length,String[] items) {

        UserTypeModel[] models = new UserTypeModel[length];
        for (int i=0;i<models.length;i++){
            models[i] = new UserTypeModel();
            models[i].setId(i+1);
            models[i].setType(items[i]);
        }

        signUpView.setAdapter(models);
        signUpView.setSpinner(models);

    }

    @Override
    public void onDestroy() {
        dbAdapter.close();
        signUpView = null;
    }
}
