package com.hddev.task1.application;

import android.app.Activity;
import android.app.Application;

import com.hddev.task1.component.DaggerPostComponent;
import com.hddev.task1.component.PostComponent;
import com.hddev.task1.modules.ContextModule;

import timber.log.Timber;

public class PostApplication extends Application {

    private PostComponent postComponent;

    public static PostApplication get (Activity activity) {
        return (PostApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        postComponent = DaggerPostComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public PostComponent getPostComponent() {
        return postComponent;
    }
}
