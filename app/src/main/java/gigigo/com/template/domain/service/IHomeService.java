package gigigo.com.template.domain.service;

import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.template.data.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public interface IHomeService {

    @GET("/api/users")
    ICall<User> getUserList(@Query("page") int page);
}
