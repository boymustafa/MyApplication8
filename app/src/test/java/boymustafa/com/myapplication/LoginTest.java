package boymustafa.com.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.test.AndroidTestCase;
import android.widget.Button;
import android.widget.EditText;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import boymustafa.com.myapplication.DB.DBAdapter;
import boymustafa.com.myapplication.DB.SQLiteHelper;
import boymustafa.com.myapplication.Login.LoginFragment;

import static org.robolectric.util.FragmentTestUtil.startFragment;

import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;
import org.robolectric.util.FragmentTestUtil;


import java.io.File;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

import static org.junit.Assert.*;

/**
 * Created by Boy on 18/09/2016.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class LoginTest extends AndroidTestCase {

    LoginFragment fragment;
    private EditText emailTV,passTV;
    private Button btnLogin;

    private Context context;
    private SQLiteHelper helper;
    private DBAdapter dbAdapter;
    private final String emailDummy = "mijon@mijon.com";
    @Before
    public void setUp() throws Exception
    {
        fragment = LoginFragment.newInstance("Login");
        SupportFragmentTestUtil.startVisibleFragment(fragment);
        btnLogin = (Button) fragment.getView().findViewById(R.id.bt_login);
        emailTV = (EditText) fragment.getView().findViewById(R.id.et_username);
        passTV = (EditText) fragment.getView().findViewById(R.id.et_pass);
    }

    @Test
    public void loginTestWithEmptyEmailandPassword(){
        btnLogin.performClick();
        assertThat("Show error for Email field ", emailTV, is(CoreMatchers.notNullValue()));
        assertThat("Show error for password field ", passTV, is(CoreMatchers.notNullValue()));
    }

    @Test
    public void loginFailure() {
        emailTV.setText("invalid@email");
        passTV.setText("zxcvbnm#");
        btnLogin.performClick();

        assertThat("Show error for Email field ", emailTV, is(CoreMatchers.notNullValue()));
        assertThat("Show error for password field ", passTV, is(CoreMatchers.notNullValue()));
    }

    @Test
    public void loginSuccess(){
        emailTV.setText("gembok@gembok.com");
        passTV.setText("zxcvbnm#");
        btnLogin.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity has started", application.getNextStartedActivity(), is(nullValue()));


    }

    private boolean isEmailExist(){
        return dbAdapter.checkEmail(emailDummy);
    }

    private boolean deleteData(){
        return dbAdapter.deleteEntry(emailDummy) > 0 ;
    }

    private void createDummyData(){
//        helper = new SQLiteHelper(context,"login.db",null,2);

        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("mijon@mijon.com");
        dummyData.add("zxcvbnm#");
        dummyData.add("mijon");
        dummyData.add("mijon");
        dummyData.add("0189826490");
        dummyData.add("Dealer");
    }
}
