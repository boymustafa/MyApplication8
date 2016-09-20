package boymustafa.com.myapplication.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import boymustafa.com.myapplication.DB.DBAdapter;
import boymustafa.com.myapplication.MainActivity;
import boymustafa.com.myapplication.R;

/**
 * Created by Boy on 16/09/2016.
 */
public class LoginFragment extends Fragment implements LoginView {

    private String title;
    private RelativeLayout root;
    private LoginPresenterImpl lpi;
    public EditText etUsername,etPassword;
    public Button btLogin;

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        fragment.setTitle(title);

        return fragment;
    }

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        root = (RelativeLayout) v.findViewById(R.id.loginRoot);

        lpi = new LoginPresenterImpl(this, new DBAdapter(getActivity().getApplicationContext()));

        etUsername = (EditText) v.findViewById(R.id.et_username);
        etPassword = (EditText) v.findViewById(R.id.et_pass);
        btLogin = (Button) v.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lpi.login(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });

        return v;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void showMsgIfEmpty() {
        Snackbar.make(root, getActivity().getResources().getString(R.string.empty_login_fields), Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void loginSuccess(String email) {
        //TODO: call new screen
        Snackbar.make(root, "SUSKE", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginError(String message) {
        //TODO: show error message

        if (message.contains("Email"))
            etUsername.setError(getActivity().getResources().getString(R.string.emailError));
        else if (message.contains("password"))
            etPassword.setError(getActivity().getResources().getString(R.string.pwdError));

    }

    @Override
    public void onDestroy() {
        lpi.onDestroy();
        super.onDestroy();
    }
}
