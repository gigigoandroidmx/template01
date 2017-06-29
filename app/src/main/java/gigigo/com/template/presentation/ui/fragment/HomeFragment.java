package gigigo.com.template.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import gigigo.com.kmvp.IAction;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SingleUser;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.ListUserInteractor;
import gigigo.com.template.domain.interactor.SingleUserInteractor;
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
        presenter.onResume();

        presenter.setParams(1);
        presenter.getUserList();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
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
                if (null == argument) return;

                presenter.setParams(argument.getId());
                presenter.getSingleUser();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
//        UserDataSource userDataSource = new UserApiDataSourceImpl();
//        UserRepository userRepository = new UserRepositoryImpl();

        ListUserInteractor listUserInteractor = new ListUserInteractor();
        SingleUserInteractor singleUserInteractor = new SingleUserInteractor();

        return new HomePresenter(listUserInteractor, singleUserInteractor);
    }

    //endregion

    //region IViewHome members

    @Override
    public void showListUsers(final ListUsers listUsers) {
        if (null == listUsers || listUsers.getUserList() == null) return;
        adapter.set(listUsers.getUserList());
    }

    @Override
    public void showSingleUser(final SingleUser user) {
        if (null == user || user.getData() == null) return;

        String data = "Id: " + String.valueOf(user.getData().getId()) + "\n" +
                "First Name: " + user.getData().getFirstName() + "\n" +
                "Last Name: " + user.getData().getLastName();

        textviewUserDetail.setText(data);
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
