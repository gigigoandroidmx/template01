package com.gigigo.template.domain;

import org.greenrobot.eventbus.EventBus;

import com.gigigo.template.domain.base.Bus;

/**
 * Created by Omar on 6/6/17.
 */

public class BusImpl extends EventBus implements Bus {

    @Override
    public void post(Object event) {
        super.post(event);
    }
}
