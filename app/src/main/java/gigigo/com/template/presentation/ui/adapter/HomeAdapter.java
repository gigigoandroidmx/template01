package gigigo.com.template.presentation.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import gigigo.com.kmvp.IAction;
import gigigo.com.kmvp.KAdapter;
import gigigo.com.kmvp.KViewHolder;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.User;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeAdapter
        extends KAdapter<User> {

    private final IAction<User> actionCommand;

    public HomeAdapter(IAction<User> actionCommand) {
        this.actionCommand = actionCommand;
    }

    @Override
    public KViewHolder<User> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent, R.layout.item_home);
        HomeViewHolder viewHolder = new HomeViewHolder(view);
        viewHolder.setActionCommand(actionCommand);
        return viewHolder;
    }
}
