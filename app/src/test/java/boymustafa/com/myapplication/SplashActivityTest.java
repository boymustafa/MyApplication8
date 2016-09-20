package boymustafa.com.myapplication;

import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.*;

/**
 * Created by Boy on 18/09/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
// To use Robolectric you'll need to setup some constants.
// Change it according to your needs.
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class SplashActivityTest {


    /*
    private MainActivity activity;
    private ActivityController<MainActivity> controller;
    private MVP_Main.ProvidedPresenterOps presenterOps;
    private StateMaintainer stateMaintainer;
     */

    private SplashActivity activity;
    private ActivityController<SplashActivity> controller;

    @Before
    public void setup(){
        controller = Robolectric.buildActivity(SplashActivity.class);
//        activity = Robolectric.setupActivity(SplashActivity.class);
    }

    @Test
    public void createsAndDestroysActivity() {


        activity = controller
                .create()
                .start()
                .resume()
                .visible()
                .get();
        assertTrue("activity ok",true);
    }


    @After
    public void tearDown() {
        // Destroy activity after every test
        controller
                .pause()
                .stop()
                .destroy();
    }

}
