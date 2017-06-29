package gigigo.com.template.domain.event;

/**
 * Created by Omar on 6/6/17.
 */

public class ErrorEvent {
    Throwable error;

    public ErrorEvent(Throwable error) {
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }
}
