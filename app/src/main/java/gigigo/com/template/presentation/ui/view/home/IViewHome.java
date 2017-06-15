package gigigo.com.template.presentation.ui.view.home;

import com.gigigo.template.data.entity.ListUsers;
import com.gigigo.template.data.entity.SinlgeUser;
import gigigo.com.template.presentation.ui.base.IViewBase;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public interface IViewHome
        extends IViewBase {

    void showListUsers(ListUsers listUsers);
    void showSingleUser(SinlgeUser user);
}
