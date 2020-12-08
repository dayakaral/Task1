package com.hddev.task1.component;

import com.hddev.task1.Interfaces.PostApplicationScope;
import com.hddev.task1.modules.PicassoModule;
import com.hddev.task1.modules.PostModule;
import com.hddev.task1.service.APIService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@PostApplicationScope
@Component(modules = {PostModule.class, PicassoModule.class})
public interface PostComponent {
    APIService getApiService();
    Picasso getPicasso();
}
