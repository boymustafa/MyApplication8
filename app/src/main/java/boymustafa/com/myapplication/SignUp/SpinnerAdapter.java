package boymustafa.com.myapplication.SignUp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Boy on 17/09/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<UserTypeModel> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private UserTypeModel[] values;

    public SpinnerAdapter(Context context, int textViewResourceId,
                          UserTypeModel[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.length;
    }

    public UserTypeModel getItem(int position){
        return values[position];
    }

    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getType());

        return label;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getType());

        return label;
    }

}
