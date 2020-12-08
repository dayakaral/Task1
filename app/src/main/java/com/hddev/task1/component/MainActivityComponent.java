package com.hddev.task1.component;

import com.hddev.task1.Interfaces.MainActivityScope;
import com.hddev.task1.adapter.PostAdapter;
import com.hddev.task1.modules.MainActivityModule;
import com.hddev.task1.service.APIService;

import dagger.Component;

@Component(modules = MainActivityModule.class,dependencies = PostComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    PostAdapter getPostAdapter();
    APIService getApiService();
}
