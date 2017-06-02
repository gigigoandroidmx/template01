package gigigo.com.template.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import gigigo.com.kmvp.KViewHolder;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.User;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeViewHolder
        extends KViewHolder<User> {

    @BindView(R.id.imageview_avatar)
    ImageView imageviewAvatar;

    @BindView(R.id.textview_firstname)
    TextView textViewFirstname;

    public HomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull User item) {
        super.onBindViewHolder(item);

        textViewFirstname.setText(item.getFirstName());

        Glide.with(getContext())
                .load(item.getAvatar())
                .into(imageviewAvatar);
    }
}
