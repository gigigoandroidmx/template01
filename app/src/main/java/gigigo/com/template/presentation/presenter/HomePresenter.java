package gigigo.com.template.presentation.presenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import gigigo.com.template.domain.base.Bus;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.SingleUserEvent;
import gigigo.com.template.domain.event.UsersListEvent;
import gigigo.com.template.domain.interactor.ISingleUserInteractor;
import gigigo.com.template.domain.interactor.ListUserInteractor;
import gigigo.com.template.domain.interactor.IListUserInteractor;
import gigigo.com.template.domain.interactor.SingleUserInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends Presenter<IViewHome>
        implements IListUserInteractor.Callback, ISingleUserInteractor.Callback {

    private final ListUserInteractor listUserInteractor;
    private final SingleUserInteractor singleUserInteractor;

    public HomePresenter(ListUserInteractor listUserInteractor, SingleUserInteractor singleUserInteractor, Bus bus) {
        this.listUserInteractor = listUserInteractor;
        this.singleUserInteractor = singleUserInteractor;
        this.bus = bus;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUsersListEvent(UsersListEvent usersListEvent) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showListUsers(usersListEvent.getListUsers());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showError(errorEvent.getError());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleUserEvent(SingleUserEvent singleUserEvent) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showSingleUser(singleUserEvent.getSinlgeUser());
    }
}
