package com.gigigo.kretrofitmanager;

import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/9/2017.
 */
public interface ICall<T> {
    void cancel();
    void enqueue(ICallbackAdapter<T> callback);
    ICall<T> clone();
    Response<T> getResponse();
}
