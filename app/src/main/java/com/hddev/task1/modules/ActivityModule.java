package com.hddev.task1.modules;

import android.app.Activity;
import android.content.Context;

import com.hddev.task1.Interfaces.PostApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @PostApplicationScope
    @Provides
    public Context context(){ return context; }
}
