package gigigo.com.template.presentation.ui.base;

import gigigo.com.kmvp.IView;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public interface IViewBase
        extends IView {

    void showError(Throwable exception);
    void showLoading(boolean active);
}