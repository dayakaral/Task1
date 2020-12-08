package com.hddev.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hddev.task1.adapter.PostAdapter;
import com.hddev.task1.application.PostApplication;
import com.hddev.task1.component.DaggerMainActivityComponent;
import com.hddev.task1.component.MainActivityComponent;
import com.hddev.task1.model.Post;
import com.hddev.task1.modules.MainActivityModule;
import com.hddev.task1.service.APIService;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    PostAdapter postAdapter;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .postComponent(PostApplication.get(this).getPostComponent())
                .build();

        apiService = mainActivityComponent.getApiService();
        postAdapter = mainActivityComponent.getPostAdapter();
        recyclerView.setAdapter(postAdapter);
        populateUsers();
    }

    private void populateUsers() {
        APIService service = getService();
        final Observable<List<Post>> postList = service.getPostList();
        postList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.d(d.toString());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        postAdapter.setPosts(posts);
                        postAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        postAdapter.notifyDataSetChanged();
                    }
                });
    }

    private APIService getService() {
        return apiService;
    }
}