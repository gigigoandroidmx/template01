package com.gigigo.kretrofitmanager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public class ServiceClient {


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

        okHttpBuilder.connectTimeout(ServiceClient.getSettings().getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(ServiceClient.getSettings().getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(ServiceClient.getSettings().getReadTimeout(), TimeUnit.SECONDS);

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

        if(ServiceClient.getSettings().hasConverterFactories()) {
            for(Converter.Factory factory : ServiceClient.getSettings().getConverterFactories()) {
                retrofitBuilder.addConverterFactory(factory);
            }
        }

        if(ServiceClient.getSettings().getCallAdapterFactory() != null) {
            retrofitBuilder.addCallAdapterFactory(ServiceClient.getSettings().getCallAdapterFactory());
        }

        return retrofitBuilder.build()
                .create(classType);
    }
}
