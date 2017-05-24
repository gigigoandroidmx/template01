package gigigo.com.template.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.kretrofitmanager.ResponseState;
import gigigo.com.template.data.entity.Datum;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.interactor.HomeInteractor;
import gigigo.com.template.presentation.ui.view.home.IViewHome;
import retrofit2.Response;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends KPresenter<IViewHome> {

    private final HomeInteractor interactor;

    public HomePresenter(HomeInteractor interactor) {
        this.interactor = interactor;
    }

    public void getUserList() {
        if(!isViewAttached()) return;

        getView().showLoading(true);

        if(isForceUpdate()) {
            interactor.refreshData();
        }

       /* User user = new User();
        List<Datum> datumList = new ArrayList<>();

        for(int i =0 ; i<10; i++) {
            Datum d = new Datum();
            d.setFirstName("Pedro " + String.valueOf(i));
            datumList.add(d);
        }

        user.setData(datumList);

        getView().showUsers(user);*/

        interactor.setParams(getParams());
        interactor.getUserList(new ICallbackAdapter<User>() {
            @Override
            public void onDataEmpty() {
                if(!isViewAttached()) return;

                getView().showLoading(false);
            }

            @Override
            public void onSuccess(User user, Object... objects) {
                if(!isViewAttached()) return;

                getView().showUsers(user);
            }

            @Override
            public void onUnauthorized(Response<User> response) {
                if(!isViewAttached()) return;

                getView().showLoading(false);
            }

            @Override
            public void onError(Throwable throwable) {
                if(!isViewAttached()) return;

                getView().showLoading(false);
            }

            @Override
            public void onDataNotAvailable(ResponseState responseState) {
                if(!isViewAttached()) return;

                getView().showLoading(false);
            }
        });


        getView().showLoading(false);
    }
}
