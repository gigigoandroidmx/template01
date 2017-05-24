package gigigo.com.template.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import gigigo.com.kmvp.KViewHolder;
import gigigo.com.template.R;
import gigigo.com.template.data.entity.Datum;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeViewHolder
        extends KViewHolder<Datum> {

    TextView textViewFirstname;

    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Datum item) {
        super.onBindViewHolder(item);

        textViewFirstname = (TextView)itemView.findViewById(R.id.textview_firstname);

        textViewFirstname.setText(item.getFirstName());
    }
}
