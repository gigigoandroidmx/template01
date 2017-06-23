package gigigo.com.template.domain.interactor;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;

import gigigo.com.kmvp.domain.IExecutor;
import gigigo.com.kmvp.domain.KInteractor;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.service.IApiService;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ListUserInteractor
        extends KInteractor
        implements IListUserInteractor {

    private final IApiService service;
    private final IExecutor executor;

    private Callback callback;

    public ListUserInteractor(IExecutor executor, IApiService service) {
        this.executor = executor;
        this.service = service;
    }

    /**
     * Defines the method to be invoked when
     */
    @Override
    public void run() {
        int page = tryGetParamValueAs(Integer.class, 0);
        ICall<ListUsers> call = service.getListUsers(page);

        /**
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<ListUsers>() {
            @Override
            public void onSuccess(final ListUsers data) {
                callback.onFetchListUsersSuccess(data);
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
