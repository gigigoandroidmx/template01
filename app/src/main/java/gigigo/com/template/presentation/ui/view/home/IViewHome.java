package gigigo.com.template.presentation.ui.view.home;

import java.util.List;

import gigigo.com.template.data.entity.User;
import gigigo.com.template.presentation.ui.base.IViewBase;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public interface IViewHome
        extends IViewBase {

    void showUsers(List<User> userList);
}
