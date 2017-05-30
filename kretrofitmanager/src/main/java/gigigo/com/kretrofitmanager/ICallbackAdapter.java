package gigigo.com.kretrofitmanager;

import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public interface ICallbackAdapter<T> {
    void onDataEmpty();
    void onSuccess(T data, Object... params);
    void onUnauthorized(Response<T> response);
    void onError(Throwable exception);
    void onDataNotAvailable(ResponseState entryState);
}
