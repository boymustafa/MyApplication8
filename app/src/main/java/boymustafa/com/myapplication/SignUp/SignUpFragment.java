package boymustafa.com.myapplication.SignUp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import boymustafa.com.myapplication.DB.DBAdapter;
import boymustafa.com.myapplication.MainActivity;
import boymustafa.com.myapplication.R;

/**
 * Created by Boy on 16/09/2016.
 */
public class SignUpFragment extends Fragment implements SignUpView{

    private String title;
    private EditText etEmail, etPwd, etFName, etlName,etPhone;
    private Button btnSIgnUp;

    private SignUpPresenterImpl sgi;
    private ArrayList<String> paramters = new ArrayList<>();
    private RelativeLayout signupRoot;
    private Spinner user_type_spinner;

    private ArrayAdapter<String> listAdapter;
    private SpinnerAdapter adapter;
    private int iteIndex;
    private String userTYpe;


    public static SignUpFragment newInstance(String title) {
        SignUpFragment fragment = new SignUpFragment();
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        sgi = new SignUpPresenterImpl(this,new DBAdapter(getActivity().getApplicationContext()));

        etEmail = (EditText) v.findViewById(R.id.et_email);
        etPwd = (EditText) v.findViewById(R.id.et_pass);
        etFName = (EditText) v.findViewById(R.id.et_fname);
        etlName = (EditText) v.findViewById(R.id.et_lname);
        etPhone = (EditText) v.findViewById(R.id.et_phone);
        btnSIgnUp = (Button) v.findViewById(R.id.bt_signup);
        signupRoot = (RelativeLayout) v.findViewById(R.id.signUpRoot);
        user_type_spinner = (Spinner) v.findViewById(R.id.user_type_spinner);

        int lengthItem = getActivity().getResources().getStringArray(R.array.user_type).length;
        String[] items = getActivity().getResources().getStringArray(R.array.user_type);
//
//
        sgi.setSpinnerData(lengthItem,items);


        btnSIgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!paramters.isEmpty())
                    paramters.clear();

                paramters.add(etEmail.getText().toString());
                paramters.add(etPwd.getText().toString());
                paramters.add(etFName.getText().toString());
                paramters.add(etlName.getText().toString());
                paramters.add(etPhone.getText().toString());
                paramters.add(userTYpe);
                paramters.add(Integer.toString(user_type_spinner.getSelectedItemPosition()));


                sgi.signUp(paramters);
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        sgi.onDestroy();
        super.onDestroy();

    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void showEmptyFieldError() {
        Snackbar.make(signupRoot, getActivity().getResources().getString(R.string.empty_login_fields), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void emailNotValid() {
        etEmail.setError(getActivity().getResources().getString(R.string.emailError));
    }

    @Override
    public void emailExistError() {
        Snackbar.make(signupRoot, getActivity().getResources().getString(R.string.emailExist), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void wrongPassword() {
        etPwd.setError(getActivity().getResources().getString(R.string.pwdError));
    }

    @Override
    public void wrongPhoneNumber() {
        etPhone.setError(getActivity().getResources().getString(R.string.phoneError));
    }

    @Override
    public void notSelectSpinner() {
        TextView errorText = (TextView)user_type_spinner.getSelectedView();
        errorText.setError("anything here, just to add the icon");
        errorText.setTextColor(Color.RED);//just to highlight that this is an error
        errorText.setText(getActivity().getResources().getString(R.string.userTypeError));//changes the selected item text to this
    }

    @Override
    public void setAdapter(UserTypeModel[] models) {
        adapter = new SpinnerAdapter(getActivity().getApplicationContext(),
              R.layout.spinner_item,
                models);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

//        listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
//                R.layout.spinner_item, data);
//        listAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

    }

    @Override
    public void setSpinner(UserTypeModel[] models) {
//        user_type_spinner.setAdapter(listAdapter);
        user_type_spinner.setAdapter(adapter);
        user_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserTypeModel model = adapter.getItem(position);
                userTYpe = model.getType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void success(String email) {
//        Snackbar.make(signupRoot, "SUKSES COY", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        getActivity().finish();
    }
}
