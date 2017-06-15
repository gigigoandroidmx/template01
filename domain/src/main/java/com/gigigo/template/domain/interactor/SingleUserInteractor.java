package com.gigigo.template.domain.interactor;

import com.gigigo.template.data.entity.SinlgeUser;
import com.gigigo.template.domain.base.KInteractor;
import com.gigigo.template.domain.event.ErrorEvent;
import com.gigigo.template.domain.event.SingleUserEvent;
import com.gigigo.template.domain.service.IApiService;

import org.greenrobot.eventbus.EventBus;

import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;
import com.gigigo.kretrofitmanager.ICallbackAdapter;

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
