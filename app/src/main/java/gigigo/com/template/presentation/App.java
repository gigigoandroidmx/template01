package gigigo.com.template.presentation;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import org.greenrobot.eventbus.EventBus;

import gigigo.com.kretrofitmanager.CallAdapterFactory;
import gigigo.com.kretrofitmanager.ServiceClient;
import gigigo.com.template.BuildConfig;
import gigigo.com.template.presentation.utils.Connectivity;
import gigigo.com.template.presentation.utils.RequestInterceptor;
import okhttp3.internal.platform.Platform;
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

    private void initialize() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        LoggingInterceptor loggerInterceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor(
                new Connectivity(this));

        ServiceClient.init()
                .setLoggingInterceptor(loggerInterceptor)
                .setCallAdapterFactory(new CallAdapterFactory())
                .setConnectivityInterceptor(requestInterceptor)
                .addEndpoint(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson));

        /* Configuration eventbus */
        EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .installDefaultEventBus();
    }
}
