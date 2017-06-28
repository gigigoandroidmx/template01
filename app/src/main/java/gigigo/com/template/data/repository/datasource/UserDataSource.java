package gigigo.com.template.data.repository.datasource;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SinlgeUser;

/**
 * Created by Omar on 6/28/17.
 */

public interface UserDataSource {
    ListUsers getListUsers(int page);
    SinlgeUser getSingleUser(int userId);
}
