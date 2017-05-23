package gigigo.com.template.presentation.ui.base;

import android.view.View;

import gigigo.com.kmvp.IPresenter;
import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KFragment;
import gigigo.com.template.presentation.ui.widget.ProgressIndicator;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public abstract class KFragmentBase<V extends IView, P extends IPresenter<V>>
        extends KFragment<V, P> {

    private ProgressIndicator progressIndicator;

    @Override
    protected void onBindView(View view) {
        this.progressIndicator = new ProgressIndicator(getContext());
    }

    public void showProgressIndicator(boolean active) {
        if(active && !progressIndicator.isShowing()) {
            progressIndicator.show();
        } else {
            progressIndicator.dismiss();
        }
    }
}
