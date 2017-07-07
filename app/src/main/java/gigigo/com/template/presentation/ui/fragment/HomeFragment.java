package gigigo.com.template.presentation.ui.fragment;


import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import gigigo.com.template.presentation.presenter.IViewHome;
import gigigo.com.template.presentation.utils.permissions.PermissionsManager;
import gigigo.com.template.presentation.utils.permissions.PermissionsResult;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends KFragmentBase<IViewHome, HomePresenter>
        implements IViewHome, PermissionsResult {

    public static final String TAG = HomeFragment.class.getSimpleName();
    public static final int PERMISSIONS_REQUEST_CAMERA_AND_EXTERNAL_STORAGE = 12;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.textview_userdetail)
    TextView textviewUserDetail;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private HomeAdapter adapter;
    private PermissionsManager permissionsManager;

    private String[] permissionsRequired = new String[]
            {
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsManager != null)
            permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

        permissionsManager = new PermissionsManager(getFragmentContext());
        permissionsManager.setPermissionsResult(this);
        permissionsManager.setFragment(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionsManager.checkPermissions(permissionsRequired,
                        PERMISSIONS_REQUEST_CAMERA_AND_EXTERNAL_STORAGE,
                        true, true);
            }
        });
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

    @Override
    public void onPermissionsGranted(int requestCode) {
        Log.i(TAG, "onPermissionsGranted -> requestCode = " + requestCode);
    }

    @Override
    public void onPermissionsDenied(int requestCode) {
        Log.i(TAG, "onPermissionsDenied -> requestCode = " + requestCode);
    }

    @Override
    public void onPermissionsDeniedPermanently(int requestCode) {
        Log.i(TAG, "onPermissionsDeniedPermanently -> requestCode = " + requestCode);
    }

    //endregion
}
