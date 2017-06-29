package gigigo.com.template.domain.event;

import gigigo.com.template.data.entity.SingleUser;

/**
 * Created by Omar on 6/6/17.
 */

public class SingleUserEvent {
    SingleUser singleUser;

    public SingleUserEvent(SingleUser singleUser) {
        this.singleUser = singleUser;
    }

    public SingleUser getSingleUser() {
        return singleUser;
    }
}
