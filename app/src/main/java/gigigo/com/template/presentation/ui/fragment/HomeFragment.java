package gigigo.com.template.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import gigigo.com.kretrofitmanager.ServiceClient;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.kmvp.KThreadExecutor;
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

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private HomeAdapter adapter;

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
    protected void onInitialize() {
        super.onInitialize();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));

        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        IHomeService service = ServiceClient.createService(IHomeService.class);
        HomeInteractor interactor = new HomeInteractor(new KThreadExecutor(), service);
        return new HomePresenter(interactor);
    }

    //endregion

    //region IViewHome members

    @Override
    public void showUsers(User user) {
        if(user != null && user.getData() != null) {
            adapter.set(user.getData());
        }
    }

    @Override
    public void showError(Throwable exception) {
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean active) {
        showProgressIndicator(active);
    }

    //endregion
}
