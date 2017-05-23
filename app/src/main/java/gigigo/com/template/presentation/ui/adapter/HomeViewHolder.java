package gigigo.com.template.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import gigigo.com.kmvp.KViewHolder;
import gigigo.com.template.data.entity.User;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeViewHolder
        extends KViewHolder<User> {

    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull User item) {
        super.onBindViewHolder(item);


    }
}
