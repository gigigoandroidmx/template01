package gigigo.com.template.data.repository.datasource;

import com.gigigo.kretrofitmanager.ServiceClient;

import java.io.IOException;
import java.net.HttpURLConnection;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.data.service.IApiService;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Omar on 6/28/17.
 */

public class UserApiDataSourceImpl implements UserDataSource {

    private IApiService apiService;

    public UserApiDataSourceImpl() {
        apiService = ServiceClient.createService(IApiService.class);
    }

    @Override
    public ListUsers getListUsers(int page) {
//        try {
//            Call<ListUsers> call = apiService.getListUsers(page);
//            Response<ListUsers> response = call.execute();
//
//            if (response != null) {
//                if (response.isSuccessful()) {
//                    return response.body();
//                } /*else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    callback.onUnauthorized();
//                } else {
//                    callback.onDataNotAvailable(handleErrorResponse(response.code()));
//                }
//            } else {
//                callback.onDataEmpty();*/
//            }
//
////            return (ListUsers) call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    public SinlgeUser getSingleUser(int userId) {
//        try {
//            Call call = apiService.getSingleUser(userId);
//            return (SinlgeUser) call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }
}
