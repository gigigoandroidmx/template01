package gigigo.com.template.presentation.presenter;

import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.domain.base.Bus;

/**
 * Created by Omar on 6/6/17.
 */

public class Presenter<T extends IView> extends KPresenter<T> {

    protected Bus bus;

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }
}
