package gigigo.com.template.data.repository;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;
import com.gigigo.kretrofitmanager.ResponseState;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SingleUser;
import gigigo.com.template.data.service.IApiService;

/**
 * Created by Omar on 6/28/17.
 */

public class UserRepositoryImpl implements UserRepository {

    private IApiService apiService;
    private RepositoryCallback callback;

    public UserRepositoryImpl(IApiService apiService, RepositoryCallback callback) {
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

            @Override
            public void onDataNotAvailable(ResponseState entryState) {
                callback.onError(entryState);
            }
        });
    }

    @Override
    public void getSingleUser(int id) {
        ICall<SingleUser> call = apiService.getSingleUser(id);
        call.enqueue(new CallbackAdapter<SingleUser>() {
            @Override
            public void onSuccess(SingleUser data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(Throwable exception) {
                callback.onError(exception);
            }
        });
    }
}
