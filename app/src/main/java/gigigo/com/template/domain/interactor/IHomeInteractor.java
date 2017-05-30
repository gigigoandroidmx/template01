package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.ICallback;
import gigigo.com.template.data.entity.User;

/**
 * @author Juan Godínez Vera - 5/30/2017.
 */
public interface IHomeInteractor {
    interface Callback extends ICallback {
        void onFetchUserSucces(User data);
        void onFecthUserError();
    }

    void execute(Callback callback);
}
