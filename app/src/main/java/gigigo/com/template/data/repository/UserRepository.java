package gigigo.com.template.data.repository;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SingleUser;

/**
 * @author Juan Godínez Vera - 6/22/2017.
 */
public class UserRepository
        implements IUserRepository {
    
    @Override
    public ListUsers getListUsers(int page) {
        return null;
    }

    @Override
    public SingleUser getSingleUser(int userId) {
        return null;
    }
}
