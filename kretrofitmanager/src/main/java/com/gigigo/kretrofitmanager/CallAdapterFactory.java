package com.gigigo.kretrofitmanager;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @author Juan Godínez Vera - 5/11/2017.
 */
public class CallAdapterFactory
        extends CallAdapter.Factory {

    /**
     * Returns a call adapter for interface methods that return {@code returnType}, or null if it
     * cannot be handled by this factory.
     *
     * @param returnType
     * @param annotations
     * @param retrofit
     */
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        if(getRawType(returnType) != ICall.class) {
            return null;
        }

        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException("ICall must have generic type.");
        }

        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Executor callbackExecutor = retrofit.callbackExecutor();

        return new CallAdapterHandler<>(responseType, callbackExecutor) ;
    }

    private static final class CallAdapterHandler<R>
            implements CallAdapter<R, ICall<R>> {

        private final Type responseType;
        private final Executor callbackExecutor;

        private CallAdapterHandler(Type responseType, Executor callbackExecutor) {
            this.responseType = responseType;
            this.callbackExecutor = callbackExecutor;
        }


        /**
         * Returns the value type that this adapter uses when converting the HTTP response body to a Java
         * object. For example, the response type for {@code Call<Repo>} is {@code Repo}. This type
         * is used to prepare the {@code call} passed to {@code #adapt}.
         * <p>
         * Note: This is typically not the same type as the {@code returnType} provided to this call
         * adapter's factory.
         */
        @Override
        public Type responseType() {
            return responseType;
        }

        /**
         * Returns an instance of {@code T} which delegates to {@code call}.
         * <p>
         * For example, given an instance for a hypothetical utility, {@code Async}, this instance would
         * return a new {@code Async<R>} which invoked {@code call} when run.
         * <pre><code>
         * &#64;Override
         * public &lt;R&gt; Async&lt;R&gt; adapt(final Call&lt;R&gt; call) {
         *   return Async.create(new Callable&lt;Response&lt;R&gt;&gt;() {
         *     &#64;Override
         *     public Response&lt;R&gt; call() throws Exception {
         *       return call.execute();
         *     }
         *   });
         * }
         * </code></pre>
         *
         * @param call
         */
        @Override
        public ICall<R> adapt(Call<R> call) {
            return new com.gigigo.kretrofitmanager.CallAdapter<>(call, callbackExecutor);
        }
    }
}
