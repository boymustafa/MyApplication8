package boymustafa.com.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Boy on 17/09/2016.
 */
public class DBAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 2;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "CREATE TABLE USERS("+
            "ID integer PRIMARY KEY   autoincrement  NOT NULL,"+
            "EMAIL             VARCHAR(50) NOT NULL,"+
            "PASSWORD             TEXT NOT NULL,"+
            "FIRST_NAME           VARCHAR(50) NOT NULL,"+
            "LAST_NAME            VARCHAR(50) NOT NULL,"+
            "CONTACT_NUMBER       VARCHAR(50) NOT NULL,"+
            "USER_ROLE            VARCHAR(50) DEFAULT 'private'"+
            ")";
    // Variable to hold the database instance
    public SQLiteDatabase db = null;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private SQLiteHelper dbHelper;
    public  DBAdapter(Context _context)
    {
        context = _context;
        dbHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public  DBAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }


    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }


    public void insertEntry(ArrayList<String> User) throws SQLException
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("EMAIL", User.get(0));
        newValues.put("PASSWORD", User.get(1));
        newValues.put("FIRST_NAME", User.get(2));
        newValues.put("LAST_NAME", User.get(3));
        newValues.put("CONTACT_NUMBER", User.get(4));
        newValues.put("USER_ROLE", User.get(5));

        // Insert the row into your table
        db.insert("USERS", null, newValues);
        Toast.makeText(context, "Users data Is Successfully Saved", Toast.LENGTH_LONG).show();

    }

    public int deleteEntry(String email)
    {
        //String id=String.valueOf(ID);
        String where="EMAIL=?";
        int numberOFEntriesDeleted= db.delete("USERS", where, new String[]{email}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public ArrayList<String> showSinlgeEntry(String userEmail) throws SQLException
    {
        ArrayList<String> userData = new ArrayList<String>();

        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{userEmail}, null, null, null);

        if (cursor.getCount() < 1) // User Not Exist
        {
            cursor.close();
            return userData;
        }
        cursor.moveToFirst();

        String uid = cursor.getString(cursor.getColumnIndex("ID"));
        String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
        String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
        String contactNumber = cursor.getString(cursor.getColumnIndex("CONTACT_NUMBER"));
        String userRole = cursor.getString(cursor.getColumnIndex("USER_ROLE"));

        userData.add(uid);
        userData.add(email);
        userData.add(password);
        userData.add(firstName);
        userData.add(lastName);
        userData.add(contactNumber);
        userData.add(userRole);

        cursor.close();


        return userData;
    }

    public String getSinlgeEntry(String UserID) throws SQLException
    {
        String password = "";

        Cursor cursor = db.query("USERS", null, " ID=?", new String[]{UserID}, null, null, null);

        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }

        cursor.moveToFirst();
        password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;

    }
    public String  updatePhoneNumber(String email, String phoneNumber) throws SQLException
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("CONTACT_NUMBER", phoneNumber);

        String where="EMAIL=?";
        db.update("USERS",updatedValues, where, new String[]{email});
        return "Number edit successfully";
    }

    public boolean checkEmail(String userEmail) throws SQLException
    {

        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{userEmail}, null, null, null);

        //--true: email exist
        return cursor.moveToFirst();

    }


    public String  login(String email, String password) throws SQLException
    {

        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);

        //--true: email doesnt exist
        if (cursor.getCount() < 1){
            return "Email doesn't exist";
        }else{
            cursor.moveToFirst();
            String pwd = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            if(!password.equals(pwd)){
                return "Invalid password";
            }
        }

        return "success";

    }

}
