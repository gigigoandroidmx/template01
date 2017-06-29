package gigigo.com.template.data.repository;

/**
 * Created by Omar on 6/28/17.
 */

public interface UserRepository {
    void getListUsers(int page);
    void getSingleUser(int id);
}
