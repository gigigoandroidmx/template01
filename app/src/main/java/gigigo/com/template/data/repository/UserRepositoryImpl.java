package gigigo.com.template.data.repository;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.data.service.IApiService;

/**
 * Created by Omar on 6/28/17.
 */

public class UserRepositoryImpl implements UserRepository {

    private IApiService apiService;
    private Callback callback;

    public UserRepositoryImpl(IApiService apiService, Callback callback) {
        this.apiService = apiService;
        this.callback = callback;
    }

    @Override
    public void getListUsers(int page) {

        ICall<ListUsers> call = apiService.getListUsers(page);

        call.enqueue(new CallbackAdapter<ListUsers>() {
            @Override
            public void onSuccess(ListUsers data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });
    }

    @Override
    public void getSingleUser(int id) {
        ICall<SinlgeUser> call = apiService.getSingleUser(id);
        call.enqueue(new CallbackAdapter<SinlgeUser>() {
            @Override
            public void onSuccess(SinlgeUser data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });
    }
}
