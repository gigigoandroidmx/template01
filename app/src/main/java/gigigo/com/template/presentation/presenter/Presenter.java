package gigigo.com.template.presentation.presenter;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;

import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.domain.base.IExecutor;
import gigigo.com.template.domain.base.KThreadExecutor;

/**
 * Created by Omar on 6/6/17.
 */

public abstract class Presenter<T extends IView> extends KPresenter<T> {

    private KThreadExecutor threadExecutor;

    public void onResume() {
        Log.i("Presneter", "onResume");
        EventBus.getDefault().register(this);
    }

    public void onPause() {
        Log.i("Presneter", "onPause");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSubscriberExceptionEvent(SubscriberExceptionEvent exceptionEvent) {
        if (exceptionEvent != null) {
            handleError(exceptionEvent.throwable);
        }
    }

    public IExecutor getIExecutor() {
        if (threadExecutor == null) {
            threadExecutor = new KThreadExecutor();
        }

        return threadExecutor;
    }
}
