package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.IExecutor;
import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.CallbackAdapter;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.domain.base.Bus;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.SingleUserEvent;
import gigigo.com.template.domain.service.IApiService;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUserInteractor
        extends KInteractor
        implements ISingleUserInteractor {

    private final IApiService service;
    private final IExecutor executor;

    private Callback callback;
    private Bus bus;

    public SingleUserInteractor(IExecutor executor, IApiService service, Bus bus) {
        this.executor = executor;
        this.service = service;
        this.bus = bus;
    }

    /**
     * Defines the method to be invoked when the use case is executed
     */
    @Override
    public void run() {
        int userId = tryGetParamValueAs(Integer.class, 0);
        ICall<SinlgeUser> call = service.getSingleUser(userId);

        /**
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<SinlgeUser>() {
            @Override
            public void onSuccess(final SinlgeUser data) {
                /*callback.onFetchSingleUserSuccess(data);*/
                bus.post(new SingleUserEvent(data));
            }

            @Override
            public void onError(final Throwable exception) {
                /*callback.onError(exception);*/
                bus.post(new ErrorEvent(exception));
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
