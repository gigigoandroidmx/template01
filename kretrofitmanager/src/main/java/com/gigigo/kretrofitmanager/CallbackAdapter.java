package com.gigigo.kretrofitmanager;

import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/11/2017.
 */
public abstract class CallbackAdapter<T> implements ICallbackAdapter<T> {
    @Override
    public void onDataEmpty() { }

    @Override
    public void onUnauthorized() { }

    @Override
    public void onDataNotAvailable(ResponseState entryState) { }

    @Override
    public ResponseState handleErrorResponse(Response<T> response) {
        int code = response.code();
        String httpMessage = HttpErrorHandling.fromInt(code).toString();
        return new ResponseState(httpMessage, code);
    }
}
