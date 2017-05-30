package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.kretrofitmanager.ResponseState;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.base.IExecutor;
import gigigo.com.template.domain.base.KInteractorBase;
import gigigo.com.template.domain.service.IHomeService;
import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeInteractor
        extends KInteractor
        implements IHomeInteractor {

    private final IHomeService service;
    private final IExecutor executor;

    private Callback callback;

    private static User user;

    public HomeInteractor(IExecutor executor, IHomeService service) {
        this.executor = executor;
        this.service = service;
    }

    /**
     * Defines the method to be invoked when
     */
    @Override
    public void run() {
        int page = tryGetParamValueAs(Integer.class, 0);
        ICall<User> call = service.getUserList(page);
        call.enqueue(new ICallbackAdapter<User>() {
            @Override
            public void onDataEmpty() {

            }

            @Override
            public void onSuccess(final User data, Object... params) {
                //this runs on the UI thread
                user = data;
                notifyFetchUserSucces();
            }

            @Override
            public void onUnauthorized(Response<User> response) {

            }

            @Override
            public void onError(Throwable exception) {

            }

            @Override
            public void onDataNotAvailable(ResponseState entryState) {

            }
        });
    }

    private void notifyFetchUserSucces() {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onFetchUserSucces(user);
            }
        });
    }

    @Override
    public void execute(Callback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }

        this.callback = callback;
        executor.run(this);
    }
}
