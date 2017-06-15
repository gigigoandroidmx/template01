package com.gigigo.template.domain.event;

import com.gigigo.template.data.entity.SinlgeUser;

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
