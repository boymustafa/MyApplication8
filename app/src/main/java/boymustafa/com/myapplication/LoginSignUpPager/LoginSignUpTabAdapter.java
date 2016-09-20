package boymustafa.com.myapplication.LoginSignUpPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import boymustafa.com.myapplication.Login.LoginFragment;
import boymustafa.com.myapplication.SignUp.SignUpFragment;

/**
 * Created by Boy on 16/09/2016.
 */
public class LoginSignUpTabAdapter extends FragmentStatePagerAdapter {

    private List<String> items;

    public LoginSignUpTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        switch (position){
            case 0:
                return LoginFragment.newInstance(items.get(position));
            case 1:
                return SignUpFragment.newInstance(items.get(position));
        }

        return null;

    }

    @Override public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override public CharSequence getPageTitle(int position) {
        return items.get(position);
    }

    public void setItems(List<String> items) {
        this.items = items;
    }


}
