package gigigo.com.template.domain.event;

import gigigo.com.template.data.entity.SinlgeUser;

/**
 * Created by Omar on 6/6/17.
 */

public class SingleUserEvent {
    SinlgeUser sinlgeUser;

    public SingleUserEvent(SinlgeUser sinlgeUser) {
        this.sinlgeUser = sinlgeUser;
    }

    public SinlgeUser getSinlgeUser() {
        return sinlgeUser;
    }

    public void setSinlgeUser(SinlgeUser sinlgeUser) {
        this.sinlgeUser = sinlgeUser;
    }
}
