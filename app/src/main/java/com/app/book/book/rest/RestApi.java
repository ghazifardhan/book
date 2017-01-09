package com.app.book.book.rest;

/**
 * Created by Ghazi on 08/01/2017.
 */

import com.app.book.book.models.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/book")
    Call<Model> loadListBook();

}
