package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.CallbackAdapter;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.domain.base.Bus;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.UsersListEvent;
import gigigo.com.template.domain.service.IApiService;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ListUserInteractor extends KInteractor {

    private final IApiService service;
    private Bus bus;

    public ListUserInteractor(IApiService service, Bus bus) {
        this.service = service;
        this.bus = bus;
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
                bus.post(new UsersListEvent(data));
            }

            @Override
            public void onError(final Throwable exception) {
                bus.post(new ErrorEvent(exception));
            }
        });
    }
}
