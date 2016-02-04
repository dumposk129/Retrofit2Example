package com.f0hzz52.retrofit2example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    android.support.v7.widget.RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        callRecyclerViewAdapter();
    }

    private void callRecyclerViewAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), null);
        recyclerViewAdapter.sendRecyclerView(recyclerView);
        recyclerViewAdapter.loadListItem();
    }
}
