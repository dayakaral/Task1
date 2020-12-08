package com.hddev.task1.modules;

import android.content.Context;

import com.hddev.task1.Interfaces.ApplicationContext;
import com.hddev.task1.Interfaces.PostApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }


    @PostApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}