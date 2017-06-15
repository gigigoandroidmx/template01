package com.gigigo.kretrofitmanager;


import java.net.HttpURLConnection;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/9/2017.
 */
public class CallAdapter<T>
        implements ICall<T> {

    private Response<T> callbackResponse;

    private final Call<T> call;
    private final Executor callbackExecutor;

    public CallAdapter(Call<T> call, Executor callbackExecutor) {
        this.call = call;
        this.callbackExecutor = callbackExecutor;
    }

    @Override
    public void cancel() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

    @Override
    public void enqueue(ICallbackAdapter<T> callback) {
        call.enqueue(getCallback(callback));
    }

    @Override
    public ICall<T> clone() {
        return new CallAdapter<>(call.clone(), callbackExecutor);
    }

    @Override
    public Response<T> getResponse() {
        return callbackResponse;
    }

    private Callback<T> getCallback(final ICallbackAdapter<T> callback) {

        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, final Response<T> response) {
                if (call.isCanceled()) return;

                if (response != null) {
                    callbackResponse = response;
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        callback.onUnauthorized();
                    } else {
                        callback.onDataNotAvailable(handleErrorResponse(response.code()));
                    }
                } else {
                    callback.onDataEmpty();
                }
            }

            @Override
            public void onFailure(final Call<T> call, final Throwable t) {
                if (!call.isCanceled()) {
                    callback.onError(t);
                }
            }
        };
    }

    private ResponseState handleErrorResponse(int code) {
        String httpMessage = HttpErrorHandling.fromInt(code).toString();
        return new ResponseState(httpMessage, code);
    }
}
