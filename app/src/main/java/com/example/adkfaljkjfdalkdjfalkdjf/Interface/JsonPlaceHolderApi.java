package com.example.adkfaljkjfdalkdjfalkdjf.Interface;

import com.example.adkfaljkjfdalkdjfalkdjf.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Posts>>getPosts();
}
