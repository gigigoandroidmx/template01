package com.gigigo.template.domain.interactor;

import com.gigigo.kretrofitmanager.ServiceClient;
import com.gigigo.template.data.entity.ListUsers;
import com.gigigo.template.domain.base.KInteractor;
import com.gigigo.template.domain.event.ErrorEvent;
import com.gigigo.template.domain.event.UsersListEvent;
import com.gigigo.template.domain.service.IApiService;

import org.greenrobot.eventbus.EventBus;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;
import com.gigigo.kretrofitmanager.ICallbackAdapter;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ListUserInteractor extends KInteractor {

    public ListUserInteractor() {}

    /**
     * Defines the method to be invoked when
     */
    @Override
    public void run() {
        int page = tryGetParamValueAs(Integer.class, 0);
        IApiService service = ServiceClient.createService(IApiService.class);
        ICall<ListUsers> call = service.getListUsers(page);

        /** 
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
         */
        call.enqueue(new CallbackAdapter<ListUsers>() {
            @Override
            public void onSuccess(final ListUsers data) {
                EventBus.getDefault().post(new UsersListEvent(data));
            }

            @Override
            public void onError(final Throwable exception) {
                EventBus.getDefault().post(new ErrorEvent(exception));
            }
        });
    }
}
