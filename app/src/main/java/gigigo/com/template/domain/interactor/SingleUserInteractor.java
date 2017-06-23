package gigigo.com.template.domain.interactor;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;

import gigigo.com.kmvp.domain.IExecutor;
import gigigo.com.kmvp.domain.KInteractor;
import gigigo.com.template.data.entity.SingleUser;
import gigigo.com.template.data.service.IApiService;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUserInteractor
        extends KInteractor
        implements ISingleUserInteractor {

    private final IApiService service;
    private final IExecutor executor;

    private Callback callback;

    public SingleUserInteractor(IExecutor executor, IApiService service) {
        this.executor = executor;
        this.service = service;
    }

    /**
     * Defines the method to be invoked when the use case is executed
     */
    @Override
    public void run() {
        int userId = tryGetParamValueAs(Integer.class, 0);
        ICall<SingleUser> call = service.getSingleUser(userId);

        /**
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<SingleUser>() {
            @Override
            public void onSuccess(final SingleUser data) {
                callback.onFetchSingleUserSuccess(data);
            }

            @Override
            public void onError(final Throwable exception) {
                callback.onError(exception);
            }
        });
    }

    @Override
    public void execute(Callback callback) {
        if(null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }

        this.callback = callback;
        executor.run(this);
    }
}
