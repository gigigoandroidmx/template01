package gigigo.com.kretrofitmanager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public class ServiceClientSettings {

    private final List<String> endpoints = new ArrayList<>();

    private Converter.Factory converterFactory;
    private CallAdapter.Factory callAdapterFactory;
    private Interceptor loggingInterceptor;
    private Interceptor connectivityInterceptor;

    public List<String> endpoints() {
        return endpoints;
    }

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

    public Converter.Factory getConverterFactory() {
        return converterFactory;
    }

    public ServiceClientSettings setConverterFactory(Converter.Factory factory) {
        converterFactory = factory;
        return this;
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
}
