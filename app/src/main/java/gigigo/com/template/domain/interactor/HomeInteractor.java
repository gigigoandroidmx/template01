package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.IExecutor;
import gigigo.com.kmvp.IFunction;
import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.CallbackAdapter;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.service.IHomeService;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeInteractor
        extends KInteractor
        implements IHomeInteractor {

    private final IHomeService service;
    private final IExecutor executor;

    private Callback callback;

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

        /**
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<User>() {
            @Override
            public void onSuccess(final User data, Object... params) {
                //this runs on the UI thread
                post(new IFunction() {
                    @Override
                    public void apply() {
                        callback.onFetchUserSucces(data);
                    }
                });
            }

            @Override
            public void onError(final Throwable exception) {
                //this runs on the UI thread
                post(new IFunction() {
                    @Override
                    public void apply() {
                        callback.onFetchUserError(exception);
                    }
                });
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
