package gigigo.com.template.domain.interactor;

import com.gigigo.kretrofitmanager.ServiceClient;

import org.greenrobot.eventbus.EventBus;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.repository.RepositoryCallback;
import gigigo.com.template.data.repository.UserRepository;
import gigigo.com.template.data.repository.UserRepositoryImpl;
import gigigo.com.template.data.service.IApiService;
import gigigo.com.template.domain.base.KInteractor;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.UsersListEvent;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ListUserInteractor extends KInteractor implements RepositoryCallback<ListUsers> {

    private UserRepository userRepository;

    public ListUserInteractor() { }

    /**
     * Defines the method to be invoked when
     */
    @Override
    public void run() {
        int page = tryGetParamValueAs(Integer.class, 0);

        IApiService service = ServiceClient.createService(IApiService.class);
        userRepository = new UserRepositoryImpl(service, this);
        userRepository.getListUsers(page);
    }

    @Override
    public void onSuccess(ListUsers data) {
        EventBus.getDefault().post(new UsersListEvent(data));
    }

    @Override
    public void onError(Throwable exception) {
        EventBus.getDefault().post(new ErrorEvent(exception));
    }
}
