package gigigo.com.template.domain.interactor;

import org.greenrobot.eventbus.EventBus;

import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.CallbackAdapter;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.SingleUserEvent;
import gigigo.com.template.domain.service.IApiService;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUserInteractor extends KInteractor {

    private final IApiService service;

    public SingleUserInteractor(IApiService service) {
        this.service = service;
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
                EventBus.getDefault().post(new SingleUserEvent(data));
            }

            @Override
            public void onError(final Throwable exception) {
                EventBus.getDefault().post(new ErrorEvent(exception));
            }
        });
    }
}
