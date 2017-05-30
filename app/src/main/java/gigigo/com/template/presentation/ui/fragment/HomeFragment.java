package gigigo.com.template.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import gigigo.com.kretrofitmanager.ServiceClient;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.template.domain.interactor.ThreadExecutor;
import gigigo.com.template.domain.service.IHomeService;
import gigigo.com.template.presentation.presenter.HomePresenter;
import gigigo.com.template.presentation.ui.adapter.HomeAdapter;
import gigigo.com.template.presentation.ui.base.KFragmentBase;
import gigigo.com.template.presentation.ui.view.home.IViewHome;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment
        extends KFragmentBase<IViewHome, HomePresenter>
        implements IViewHome {

    RecyclerView recyclerView;
    HomeAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.setParams(1);
        presenter.getUserList();
    }

    //region KFragment members

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));

        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onUnbindView() {

    }

    @Override
    protected HomePresenter createPresenter() {
        IHomeService service = ServiceClient.createService(IHomeService.class);
        HomeInteractor interactor = new HomeInteractor(new ThreadExecutor(), service);
        return new HomePresenter(interactor);
    }

    //endregion

    //region IViewHome members

    @Override
    public void showUsers(User user) {
        if(user != null && user.getData() != null) {
            adapter.addRange(user.getData());
        }
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
