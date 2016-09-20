package boymustafa.com.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import static org.junit.Assert.*;

import boymustafa.com.myapplication.DB.SQLiteHelper;

/**
 * Created by Boy on 18/09/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class DBTest {

    private Context context;
    private SQLiteHelper helper;


    @Before
    public void setup(){
        context = RuntimeEnvironment.application;
        helper = new SQLiteHelper(context,"login.db",null,2);
    }

    @Test
    public void testDBCreated(){
        SQLiteDatabase db = helper.getWritableDatabase();
        // Verify is the DB is opening correctly
        assertTrue("DB didn't open", db.isOpen());
        db.close();
    }


}
