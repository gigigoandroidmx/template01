package gigigo.com.template.presentation.presenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.SingleUserEvent;
import gigigo.com.template.domain.event.UsersListEvent;
import gigigo.com.template.domain.interactor.ListUserInteractor;
import gigigo.com.template.domain.interactor.SingleUserInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter extends Presenter<IViewHome> {

    private final ListUserInteractor listUserInteractor;
    private final SingleUserInteractor singleUserInteractor;

    public HomePresenter(ListUserInteractor listUserInteractor,
                         SingleUserInteractor singleUserInteractor) {
        this.listUserInteractor = listUserInteractor;
        this.singleUserInteractor = singleUserInteractor;
    }

    public void getUserList() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
        listUserInteractor.setParams(getParams());
        getIExecutor().run(listUserInteractor);
    }

    public void getSingleUser() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
        singleUserInteractor.setParams(getParams());
        getIExecutor().run(singleUserInteractor);
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
        getView().showSingleUser(singleUserEvent.getSingleUser());
    }

    /*@Override
    public void onErrorBus(Throwable exception) {
        if(!isViewAttached()) return;

        getView().showLoading(false);
        getView().showError(exception);
    }*/

    @Override
    public void handleError(Throwable exception) {
        super.handleError(exception);
    }
}
