package gigigo.com.template.presentation.presenter;

import gigigo.com.template.data.entity.ListUsers;
import gigigo.com.template.data.entity.SingleUser;
import gigigo.com.template.presentation.ui.base.IViewBase;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public interface IViewHome
        extends IViewBase {

    void showListUsers(ListUsers listUsers);
    void showSingleUser(SingleUser user);
}
