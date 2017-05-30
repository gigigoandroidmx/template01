package gigigo.com.template.domain.base;

import gigigo.com.kmvp.IExecutor;
import gigigo.com.kmvp.KInteractor;
import gigigo.com.kretrofitmanager.ICallbackAdapter;

/**
 * @author Juan Godínez Vera - 5/29/2017.
 */
public abstract class KInteractorBase
        extends KInteractor {

    protected ICallbackAdapter callback;
    private final IExecutor executor;

    public KInteractorBase(IExecutor executor) {
        this.executor = executor;
    }

    public void execute(ICallbackAdapter callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }

        this.callback = callback;
        this.executor.run(this);
    }

}
