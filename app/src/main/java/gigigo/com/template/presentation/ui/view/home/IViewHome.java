package gigigo.com.template.presentation.ui.view.home;

import gigigo.com.template.data.entity.User;
import gigigo.com.template.presentation.ui.base.IViewBase;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public interface IViewHome
        extends IViewBase {

    void showUsers(User userList);
}
