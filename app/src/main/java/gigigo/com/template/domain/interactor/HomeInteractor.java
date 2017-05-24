package gigigo.com.template.domain.interactor;

import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.kretrofitmanager.ICallbackAdapter;
import gigigo.com.template.data.entity.User;
import gigigo.com.template.domain.service.IHomeService;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeInteractor
        extends KInteractor {

    private final IHomeService service;

    public HomeInteractor(IHomeService service) {
        this.service = service;
    }

    @Override
    public void refreshData() { }

    public void getUserList(ICallbackAdapter<User> callbackAdapter) {
        int page = tryGetParamValueAs(Integer.class, 0);
        ICall<User> call = service.getUserList(page);
        call.enqueue(callbackAdapter);
    }
}
