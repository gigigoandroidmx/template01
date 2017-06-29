package gigigo.com.template.domain.interactor;

import com.gigigo.kretrofitmanager.ServiceClient;

import org.greenrobot.eventbus.EventBus;

import gigigo.com.template.data.entity.SingleUser;
import gigigo.com.template.data.repository.RepositoryCallback;
import gigigo.com.template.data.repository.UserRepository;
import gigigo.com.template.data.repository.UserRepositoryImpl;
import gigigo.com.template.data.service.IApiService;
import gigigo.com.template.domain.base.KInteractor;
import gigigo.com.template.domain.event.ErrorEvent;
import gigigo.com.template.domain.event.SingleUserEvent;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUserInteractor extends KInteractor implements RepositoryCallback<SingleUser> {

    private UserRepository userRepository;

    public SingleUserInteractor() { }

    /**
     * Defines the method to be invoked when the use case is executed
     */
    @Override
    public void run() {
        int userId = tryGetParamValueAs(Integer.class, 0);

        IApiService service = ServiceClient.createService(IApiService.class);
        userRepository = new UserRepositoryImpl(service, this);
        userRepository.getSingleUser(userId);
    }

    @Override
    public void onSuccess(SingleUser data) {
        EventBus.getDefault().post(new SingleUserEvent(data));
    }

    @Override
    public void onError(Throwable exception) {
        EventBus.getDefault().post(new ErrorEvent(exception));
    }
}
