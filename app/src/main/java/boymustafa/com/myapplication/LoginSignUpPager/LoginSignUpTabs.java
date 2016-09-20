package boymustafa.com.myapplication.LoginSignUpPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.christianbahl.appkit.viewstate.activity.CBActivityMvpToolbarTabsViewState;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

/**
 * Created by Boy on 16/09/2016.
 *
 * In this class there are login and signup tabs. Login will always be first selected tab
 */
public class LoginSignUpTabs extends CBActivityMvpToolbarTabsViewState<List<String>, MvpLceView<List<String>>, LoginSignUpPresenter, LoginSignUpTabAdapter> {

    private List<String> data;
    public int mMyCurrentPosition;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginSignUpTabs.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected LoginSignUpTabAdapter createAdapter() {
        return new LoginSignUpTabAdapter(getSupportFragmentManager());
    }

    @Override
    public LceViewState<List<String>, MvpLceView<List<String>>> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<String> getData() {
        return data;
    }

    @NonNull
    @Override
    public LoginSignUpPresenter createPresenter() {
        return new LoginSignUpPresenter();
    }

    @Override
    public void setData(List<String> data) {
        this.data = data;

        adapter.setItems(data);
        adapter.notifyDataSetChanged();
        tabs.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

//
//    @Override
//    protected void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt("mMyCurrentPosition", tabs.getSelectedTabPosition());
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mMyCurrentPosition = savedInstanceState.getInt("mMyCurrentPosition");
//        // where mMyCurrentPosition should be a public value in your activity.
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(mMyCurrentPosition != 0){
//            tabs.getTabAt(mMyCurrentPosition);
//        }
//    }
}
