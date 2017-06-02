package gigigo.com.template.presentation.presenter;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.domain.interactor.ISingleUserInteractor;
import gigigo.com.template.domain.interactor.ListUserInteractor;
import gigigo.com.template.domain.interactor.IListUserInteractor;
import gigigo.com.template.domain.interactor.SingleUserInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends KPresenter<IViewHome>
        implements IListUserInteractor.Callback, ISingleUserInteractor.Callback {

    private final ListUserInteractor listUserInteractor;
    private final SingleUserInteractor singleUserInteractor;

    public HomePresenter(ListUserInteractor listUserInteractor, SingleUserInteractor singleUserInteractor) {
        this.listUserInteractor = listUserInteractor;
        this.singleUserInteractor = singleUserInteractor;
    }

    public void getUserList() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
        listUserInteractor.setParams(getParams());
        listUserInteractor.execute(this);
    }

    public void getSingleUser() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
        singleUserInteractor.setParams(getParams());
        singleUserInteractor.execute(this);
    }

    @Override
    public void onFetchListUsersSuccess(ListUsers data) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showListUsers(data);
    }

    @Override
    public void onFetchSingleUserSuccess(SinlgeUser data) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showSingleUser(data);
    }

    @Override
    public void onError(Throwable exception) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showError(exception);
    }
}
