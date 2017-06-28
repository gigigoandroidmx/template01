package gigigo.com.template.data.repository;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;

/**
 * Created by Omar on 6/28/17.
 */

public interface UserRepository {
    void getListUsers(int page);
    void getSingleUser(int id);

    interface Callback<T> {
        void onSuccess(T data);
        void onError(Throwable exception);
    }
}
