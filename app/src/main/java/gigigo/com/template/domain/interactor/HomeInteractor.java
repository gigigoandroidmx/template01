package gigigo.com.template.domain.interactor;

import butterknife.ButterKnife;
import gigigo.com.kmvp.IAction;
import gigigo.com.kmvp.IFunction;
import gigigo.com.kmvp.IPredicate;
import gigigo.com.kmvp.KInteractor;
import gigigo.com.kmvp.KMainThread;
import gigigo.com.kretrofitmanager.CallbackAdapter;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.kretrofitmanager.ResponseState;
import gigigo.com.template.data.entity.User;
import gigigo.com.kmvp.IExecutor;
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

        /**
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<User>() {
            @Override
            public void onSuccess(final User data, Object... params) {
                user = data;

                //this runs on the UI thread
                post(new IAction() {
                    @Override
                    public <T> void invoke(T argument) {
                        callback.onFetchUserSucces(user);
                    }
                }, user);
            }

            @Override
            public void onError(final Throwable exception) {
                post(new IFunction() {
                    @Override
                    public void apply() {
                        callback.onFecthUserError(exception);
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
