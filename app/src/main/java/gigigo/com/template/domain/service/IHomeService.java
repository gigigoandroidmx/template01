package gigigo.com.template.domain.service;

import java.util.List;

import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.template.data.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public interface IHomeService {

    @GET("/api/users?page={page}")
    ICall<List<User>> getUserList(@Path("page") int page);
}
