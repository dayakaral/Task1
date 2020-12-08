package com.hddev.task1.service;

import com.hddev.task1.model.Post;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;


public interface APIService {

    @GET("issues")
    Observable<List<Post>> getPostList();
}
