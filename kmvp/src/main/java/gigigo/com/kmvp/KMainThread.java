package gigigo.com.kmvp;

import android.os.Handler;
import android.os.Looper;

/**
 * Defines a class for run actions on the main thread of the application.
 *
 * @author Juan Godínez Vera - 5/30/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public class KMainThread {

    private final Handler handler;

    public KMainThread() {
        handler = new Handler(android.os.Looper.getMainLooper());
    }

    public void post(final IFunction function) {
        if(isOnUiThread()) {
            function.apply();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    function.apply();
                }
            });
        }
    }

    public boolean isOnUiThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}