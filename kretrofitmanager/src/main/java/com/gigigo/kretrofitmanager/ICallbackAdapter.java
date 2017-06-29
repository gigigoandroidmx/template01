package com.gigigo.kretrofitmanager;

import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public interface ICallbackAdapter<T> {
    void onDataEmpty();
    void onSuccess(T data);
    void onUnauthorized();
    void onError(Throwable exception);
    void onDataNotAvailable(ResponseState entryState);
    ResponseState handleErrorResponse(Response<T> response);
}
