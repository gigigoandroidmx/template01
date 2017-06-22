package com.gigigo.kretrofitmanager;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public interface ICallbackAdapter<T> {
    void onDataEmpty();
    void onSuccess(T data);
    void onUnauthorized();
    void onError(Throwable exception);
    void onDataNotAvailable(ResponseState entryState);
}
