package gigigo.com.kretrofitmanager;

import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/11/2017.
 */
public abstract class CallbackAdapter<T>
        implements ICallbackAdapter<T> {
    @Override
    public void onDataEmpty() { }

    @Override
    public void onUnauthorized(Response<T> response) { }


    @Override
    public void onDataNotAvailable(ResponseState entryState) { }
}
