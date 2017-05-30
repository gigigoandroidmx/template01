package gigigo.com.kretrofitmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public class ServiceClient {

    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    private static ServiceClientSettings serviceClientSettings;

    public static ServiceClientSettings init() {
        serviceClientSettings = new ServiceClientSettings();
        return serviceClientSettings;
    }

    public static ServiceClientSettings getSettings() {
        return serviceClientSettings;
    }

    public static <T> T createService(Class<T> classType) {
        return createService(classType, 0);
    }

    public static <T> T createService(Class<T> classType, int endpointIndex) {
        String endpoint = ServiceClient.getSettings()
                .getEndpoint(endpointIndex);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        if(ServiceClient.getSettings().getLoggingInterceptor() != null) {
            okHttpBuilder.interceptors().add(ServiceClient.getSettings().getLoggingInterceptor() );
        }

        if(ServiceClient.getSettings().getConnectivityInterceptor() != null) {
            okHttpBuilder.interceptors().add(ServiceClient.getSettings().getConnectivityInterceptor());
        }

        OkHttpClient okHttpClient = okHttpBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient);

        if(ServiceClient.getSettings().getConverterFactory() != null) {
            retrofitBuilder.addConverterFactory(ServiceClient.getSettings().getConverterFactory());
        }

        if(ServiceClient.getSettings().getCallAdapterFactory() != null) {
            retrofitBuilder.addCallAdapterFactory(ServiceClient.getSettings().getCallAdapterFactory());
        }

        return retrofitBuilder.build()
                .create(classType);
    }
}
