package gigigo.com.template.data.repository;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SingleUser;

/**
 * @author Juan Godínez Vera - 6/22/2017.
 */
public interface IUserRepository {
    ListUsers getListUsers(int page);
    SingleUser getSingleUser(int userId);
}
