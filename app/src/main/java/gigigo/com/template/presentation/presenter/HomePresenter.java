package gigigo.com.template.presentation.presenter;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.template.domain.interactor.IHomeInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends KPresenter<IViewHome>
        implements IHomeInteractor.Callback {

    private final HomeInteractor interactor;

    public HomePresenter(HomeInteractor interactor) {
        this.interactor = interactor;
    }

    public void getUserList() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
        interactor.execute(this);
    }

    @Override
    public void onFetchUserSucces(User data) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showUsers(data);
    }

    @Override
    public void onFecthUserError(Throwable exception) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showError(exception);
    }
}
