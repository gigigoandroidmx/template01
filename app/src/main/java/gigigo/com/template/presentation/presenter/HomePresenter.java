package gigigo.com.template.presentation.presenter;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends KPresenter<IViewHome> {

    private final HomeInteractor interactor;

    public HomePresenter(HomeInteractor interactor) {
        this.interactor = interactor;
    }
}
