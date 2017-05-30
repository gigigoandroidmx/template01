package gigigo.com.kretrofitmanager;

/**
 * @author Juan Godínez Vera - 5/9/2017.
 */
public interface ICall<T> {
    void cancel();
    void enqueue(ICallbackAdapter<T> callback, Object... params);
    ICall<T> clone();
}
