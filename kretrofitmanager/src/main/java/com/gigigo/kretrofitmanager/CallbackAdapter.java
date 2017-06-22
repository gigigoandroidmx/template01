package com.gigigo.kretrofitmanager;

/**
 * @author Juan Godínez Vera - 5/11/2017.
 */
public abstract class CallbackAdapter<T>
        implements ICallbackAdapter<T> {
    @Override
    public void onDataEmpty() { }

    @Override
    public void onUnauthorized() { }

    @Override
    public void onDataNotAvailable(ResponseState entryState) { }
}
