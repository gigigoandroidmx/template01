package gigigo.com.template.presentation;

import android.app.Application;

/**
 * Interfaz base para la vista
 *
 * @author [nombre] - 4/18/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public class App
        extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {

    }
}
