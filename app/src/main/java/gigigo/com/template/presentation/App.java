package gigigo.com.template.presentation;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gigigo.com.kretrofitmanager.CallAdapterFactory;
import gigigo.com.kretrofitmanager.ServiceClient;
import gigigo.com.template.BuildConfig;
import gigigo.com.template.presentation.utils.Connectivity;
import gigigo.com.template.presentation.utils.RequestInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private void initialize() {HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if(BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor(
                new Connectivity(this));

        ServiceClient.init()
                .setLoggingInterceptor(loggingInterceptor)
                .setCallAdapterFactory(new CallAdapterFactory())
                .setConnectivityInterceptor(requestInterceptor)
                .addEndpoint(BuildConfig.HOST)
                .setConverterFactory(GsonConverterFactory.create(gson));

    }
}
