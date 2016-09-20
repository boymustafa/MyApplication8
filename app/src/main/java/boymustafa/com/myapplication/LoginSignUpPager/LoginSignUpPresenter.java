package boymustafa.com.myapplication.LoginSignUpPager;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boy on 16/09/2016.
 */
public class LoginSignUpPresenter extends MvpBasePresenter<MvpLceView<List<String>>> {

    public void loadData(boolean contentPresent) {
        if (isViewAttached()) {
            getView().showLoading(contentPresent);
        }
        if (isViewAttached()) {
            getView().setData(getItems());
            getView().showContent();
        }
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();
        items.add("Login");
        items.add("Sign-Up");

        return items;
    }
}
