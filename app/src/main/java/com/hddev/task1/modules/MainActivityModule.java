package com.hddev.task1.modules;

import com.hddev.task1.Interfaces.MainActivityScope;
import com.hddev.task1.MainActivity;
import com.hddev.task1.adapter.PostAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final MainActivity mainActivity;


    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @MainActivityScope
    @Provides
    public PostAdapter postAdapter(Picasso picasso) {
        return new PostAdapter(mainActivity, picasso);
    }
}
