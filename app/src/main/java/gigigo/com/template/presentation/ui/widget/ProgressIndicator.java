package gigigo.com.template.presentation.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import gigigo.com.template.R;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ProgressIndicator
        extends Dialog {

//    private TextView textviewTitle;
//    private ContentLoadingProgressBar contentloadingProgressbar;
//    private TextView textviewContent;
//
    private final Context context;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     */
    public ProgressIndicator(Context context) {
        super(context);
        this.context = context;
    }

//    @Override
//    public void setTitle(CharSequence title) {
//        if(textviewTitle != null) {
//            textviewTitle.setVisibility(View.VISIBLE);
//            textviewTitle.setText(title);
//        }
//    }
//
//    @Override
//    public void setTitle(@StringRes int titleId) {
//        setTitle(context.getText(titleId));
//    }
//
//    public void setContent(CharSequence title) {
//        if(textviewContent != null) {
//            textviewContent.setVisibility(View.VISIBLE);
//            textviewContent.setText(title);
//        }
//    }
//
//    public void setContent(@StringRes int contentId) {
//        setContent(context.getText(contentId));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_indicator_layout);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//        contentloadingProgressbar = (ContentLoadingProgressBar) findViewById(R.id.contentloading_progressbar);
//        textviewTitle = (TextView) findViewById(R.id.textview_title);
//        textviewContent = (TextView) findViewById(R.id.textview_content);
    }
}
