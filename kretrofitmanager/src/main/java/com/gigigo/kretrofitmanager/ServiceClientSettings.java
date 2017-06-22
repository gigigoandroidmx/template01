package com.gigigo.kretrofitmanager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public class ServiceClientSettings {

    private static final int CONNECT_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    private final List<String> endpoints = new ArrayList<>();
    private final List<Converter.Factory> converterFactories = new ArrayList<>();

    private CallAdapter.Factory callAdapterFactory;
    private Interceptor loggingInterceptor;
    private Interceptor connectivityInterceptor;

    private int connectTimeout = CONNECT_TIMEOUT;
    private int writeTimeout = WRITE_TIMEOUT;
    private int readTimeout = READ_TIMEOUT;

    public ServiceClientSettings addEndpoint(String endpoint) {
        endpoints.add(endpoint);
        return this;
    }

    public String getEndpoint(int position) {
        if(endpoints != null && !endpoints.isEmpty()) {
            return endpoints.get(position);
        }

        return null;
    }

    public ServiceClientSettings addConverterFactory(Converter.Factory factory) {
        converterFactories.add(factory);
        return this;
    }

    public boolean hasConverterFactories() {
        return converterFactories != null && !converterFactories.isEmpty();
    }

    public List<Converter.Factory> getConverterFactories() {
        return converterFactories;
    }

    public CallAdapter.Factory getCallAdapterFactory() {
        return callAdapterFactory;
    }

    public ServiceClientSettings setCallAdapterFactory(CallAdapter.Factory factory) {
        callAdapterFactory = factory;
        return this;
    }

    public Interceptor getLoggingInterceptor() {
        return loggingInterceptor;
    }

    public ServiceClientSettings setLoggingInterceptor(Interceptor interceptor) {
        loggingInterceptor = interceptor;
        return this;
    }

    public Interceptor getConnectivityInterceptor() {
        return connectivityInterceptor;
    }

    public ServiceClientSettings setConnectivityInterceptor(Interceptor interceptor) {
        connectivityInterceptor = interceptor;
        return this;
    }

    public ServiceClientSettings setConnectTimeout(int timeout) {
        connectTimeout = timeout;
        return this;
    }

    public ServiceClientSettings setWriteTimeout(int timeout) {
        writeTimeout = timeout;
        return this;
    }

    public ServiceClientSettings setReadTimeout(int timeout) {
        readTimeout = timeout;
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }
}
