package com.gigigo.template.domain.service;

import com.gigigo.template.data.entity.ListUsers;
import com.gigigo.template.data.entity.SinlgeUser;

import com.gigigo.kretrofitmanager.ICall;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public interface IApiService {

    @GET("/api/users")
    ICall<ListUsers> getListUsers(@Query("page") int page);

    @GET("/api/users/{id}")
    ICall<SinlgeUser> getSingleUser(@Path("id") int userId);
}
