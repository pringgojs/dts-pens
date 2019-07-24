package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.retrofitapi.adapter.ItemAdapter;
import com.example.retrofitapi.constants.Constants;
import com.example.retrofitapi.models.Item;
import com.example.retrofitapi.models.Result;
import com.example.retrofitapi.services.APIService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvItem;
    FloatingActionButton fabAdd;

    ArrayList<Item> items = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItem = findViewById(R.id.rvItem);
        fabAdd = findViewById(R.id.fabAdd);

        rvItem.setLayoutManager(new LinearLayoutManager(this));
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddAndUpdateActivity.class);
                startActivity(intent);
            }
        });

        loadDataFromServer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromServer();
    }

    private void loadDataFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.. ");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        final Call<Result> result = apiService.getAll(Constants.TOKEN);

        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Result jsonResult = response.body();

                items = jsonResult.getItems();

                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this);
                rvItem.setAdapter(itemAdapter);

                if (items != null) {
                    itemAdapter.setListItem(items);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}

