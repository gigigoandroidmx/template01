package gigigo.com.template.data.repository;

/**
 * Created by omar on 6/29/17.
 */

public interface RepositoryCallback<T> {
    void onSuccess(T data);
    void onError(Throwable exception);
}
