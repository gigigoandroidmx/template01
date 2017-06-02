package gigigo.com.template.domain.service;

import gigigo.com.kretrofitmanager.ICall;
import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public interface IApiService {

    @GET("/api/users")
    ICall<ListUsers> getListUsers(@Query("page") int page);

    @GET("/api/users/{id}")
    ICall<SinlgeUser> getSingleUser(@Path("id") int userId);
}
