package gigigo.com.template.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.gigigo.kretrofitmanager.ServiceClient;

import butterknife.BindView;
import gigigo.com.kmvp.IAction;
import gigigo.com.kmvp.domain.KThreadExecutor;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.ListUserInteractor;
import gigigo.com.template.domain.interactor.SingleUserInteractor;
import gigigo.com.template.domain.service.IApiService;
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

    @BindView(R.id.textview_userdetail)
    TextView textviewUserDetail;

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



        adapter = new HomeAdapter(new IAction<User>() {
            @Override
            public void invoke(User argument) {
                if(null == argument) return;

                presenter.setParams(argument.getId());
                presenter.getSingleUser();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        IApiService service = ServiceClient.createService(IApiService.class);
        KThreadExecutor threadExecutor = new KThreadExecutor();

        ListUserInteractor listUserInteractor = new ListUserInteractor(threadExecutor, service);
        SingleUserInteractor singleUserInteractor = new SingleUserInteractor(threadExecutor, service);
        return new HomePresenter(listUserInteractor, singleUserInteractor);
    }

    //endregion

    //region IViewHome members

    @Override
    public void showListUsers(final ListUsers listUsers) {
        if(null == listUsers || listUsers.getUserList() == null) return;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.set(listUsers.getUserList());
            }
        });
    }

    @Override
    public void showSingleUser(final SinlgeUser user) {
        if(null == user || user.getData() == null) return;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String data = "Id: " + String.valueOf(user.getData().getId()) + "\n" +
                        "First Name: " + user.getData().getFirstName() + "\n" +
                        "Last Name: " + user.getData().getLastName();

                textviewUserDetail.setText(data);
            }
        });
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
