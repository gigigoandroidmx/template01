package gigigo.com.template.presentation.utils;

import java.io.IOException;

import com.gigigo.kretrofitmanager.IConnectivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Juan Godínez Vera - 5/16/2017.
 */
public class RequestInterceptor
        implements Interceptor {

    private final IConnectivity mConnectivity;

    public RequestInterceptor(IConnectivity mConnectivity) {
        this.mConnectivity = mConnectivity;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (mConnectivity != null &&
                mConnectivity.isConnected()) {
            Request request = chain.request();

//            Headers.Builder headerBuilder = new Headers.Builder();
//            headerBuilder.add("Content-Type", "application/json");
//            headerBuilder.add("Accept", "application/json");

            request = request.newBuilder()
//                    .headers(headerBuilder.build())
                    .build();

            return chain.proceed(request);
        } else {
            throw new IOException("Parece que su conexión a Internet está desactivada.\nPor favor, enciéndala y vuelva a intentarlo.");
        }
    }
}
