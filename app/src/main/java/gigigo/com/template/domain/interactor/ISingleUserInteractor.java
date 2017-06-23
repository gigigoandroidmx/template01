package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.ICallback;
import gigigo.com.template.data.entity.SingleUser;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public interface ISingleUserInteractor {

    interface Callback
            extends ICallback {
        void onFetchSingleUserSuccess(SingleUser data);
    }

    void execute(Callback callback);
}
