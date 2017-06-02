package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.ICallback;
import gigigo.com.template.data.entity.ListUsers;

/**
 * @author Juan Godínez Vera - 5/30/2017.
 */
public interface IListUserInteractor {
    interface Callback
            extends ICallback {
        void onFetchListUsersSuccess(ListUsers data);
    }

    void execute(Callback callback);
}
