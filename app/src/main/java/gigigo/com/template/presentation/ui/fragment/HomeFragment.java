package gigigo.com.template.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

import gigigo.com.template.R;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.template.presentation.presenter.HomePresenter;
import gigigo.com.template.presentation.ui.base.KFragmentBase;
import gigigo.com.template.presentation.ui.view.home.IViewHome;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment
        extends KFragmentBase<IViewHome, HomePresenter>
        implements IViewHome {

    @Override
    public void onResume() {
        super.onResume();
        showLoading(true);
    }

    //region KFragment members

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onUnbindView() {

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(new HomeInteractor());
    }

    //endregion

    //region IViewHome members

    @Override
    public void showUsers(List<User> userList) {

    }

    @Override
    public void showError(Throwable exception) {

    }

    @Override
    public void showLoading(boolean active) {
        showProgressIndicator(active);
    }

    //endregion
}
