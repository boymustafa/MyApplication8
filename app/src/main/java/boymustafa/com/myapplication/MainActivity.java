package boymustafa.com.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import boymustafa.com.myapplication.DB.DBAdapter;
import boymustafa.com.myapplication.MainScreen.MainScreenPresenterImpl;
import boymustafa.com.myapplication.MainScreen.MainScreenView;

public class MainActivity extends AppCompatActivity implements MainScreenView {
    
    Button userTypeBtn,logoutBtn,editPhoneBtn;
    private MainScreenPresenterImpl mImpl;
    private LinearLayout rootMainLayout;
    private EditText etName,etPhone;
    private String type,email;

    static final int MSG_DISMISS_DIALOG = 0;

    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImpl = new MainScreenPresenterImpl(this, new DBAdapter(this));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");

            userTypeBtn = (Button) findViewById(R.id.userTypeBtn);
            logoutBtn = (Button) findViewById(R.id.logoutBtn);
            editPhoneBtn = (Button) findViewById(R.id.editPhoneBtn);
            rootMainLayout = (LinearLayout) findViewById(R.id.rootMainLayout);
            etName = (EditText) findViewById(R.id.et_name);
            etPhone = (EditText) findViewById(R.id.et_hp);

            mImpl.getUserInfo(email);
        }
        else {
            Toast.makeText(MainActivity.this, "User is not allowed to go here before register", Toast.LENGTH_SHORT).show();
            finish();
        }







        userTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.showUserType(type);
            }
        });

        editPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.editPhoneNumber();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImpl.logout();
            }
        });



    }

    @Override
    public void userIsNotRegistered() {
        
    }

    @Override
    public void setUserDetails(ArrayList<String> userDetails) {
        String name = userDetails.get(3)+" "+userDetails.get(4);
        etName.setText(name);
        etPhone.setText(userDetails.get(5));
        type = userDetails.get(6);

    }

    @Override
    public void showUserTypeInfo(String userType) {
        Toast.makeText(MainActivity.this, "Your account type is "+userType, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogPhoneNumber() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.phone_edit_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.et_phone_edit);

        dialogBuilder.setTitle("Edit Phone Number");
//        dialogBuilder.setMessage("Enter desired phone number");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                mImpl.editPhone(email,edt.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public void successEditPhone(String newPhneNumber) {
        Snackbar.make(rootMainLayout, getResources().getString(R.string.succesEditPhone), Snackbar.LENGTH_LONG).show();
        etPhone.setText(newPhneNumber);
    }


    @Override
    public void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.logout));
        builder.setMessage("Loging out.....");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                mAlertDialog.dismiss();
                mAlertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                startActivity(intent);
                finish();
            }
        })
                .setNegativeButton("cancel", null);
        mAlertDialog = builder.create();
        mAlertDialog.show();
        // dismiss dialog in TIME_OUT ms
        mHandler.sendEmptyMessageDelayed(MSG_DISMISS_DIALOG, 2000);
    }

    @Override
    protected void onDestroy() {
        mImpl.onDestroy();
        super.onDestroy();
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_DISMISS_DIALOG:
                    if (mAlertDialog != null && mAlertDialog.isShowing()) {
                        mAlertDialog.dismiss();
                        Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;

                default:
                    break;
            }
        }
    };
}
