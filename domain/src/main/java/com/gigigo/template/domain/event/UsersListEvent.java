package com.gigigo.template.domain.event;

import com.gigigo.template.data.entity.ListUsers;

/**
 * Created by Omar on 6/6/17.
 */

public class UsersListEvent {
    private ListUsers listUsers;

    public UsersListEvent(ListUsers listUsers) {
        this.listUsers = listUsers;
    }

    public ListUsers getListUsers() {
        return listUsers;
    }

    public void setListUsers(ListUsers listUsers) {
        this.listUsers = listUsers;
    }
}
