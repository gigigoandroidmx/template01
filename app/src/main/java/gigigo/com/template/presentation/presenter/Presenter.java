package gigigo.com.template.presentation.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;

import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KPresenter;

/**
 * Created by Omar on 6/6/17.
 */

public abstract class Presenter<T extends IView> extends KPresenter<T> {

    public void onResume() {
        EventBus.getDefault().register(this);
    }

    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSubscriberExceptionEvent(SubscriberExceptionEvent exceptionEvent) {
        if(exceptionEvent != null) {
            onErrorBus(exceptionEvent.throwable);
        }
    }

    public abstract void onErrorBus(Throwable exception);
}
