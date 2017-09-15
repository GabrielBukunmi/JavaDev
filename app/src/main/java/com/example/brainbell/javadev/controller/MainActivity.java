package com.example.brainbell.javadev.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.brainbell.javadev.model.ItemAdapter;
import com.example.brainbell.javadev.R;
import com.example.brainbell.javadev.api.Client;
import com.example.brainbell.javadev.api.Service;
import com.example.brainbell.javadev.model.Item;
import com.example.brainbell.javadev.model.ItemResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        swipeContainer=(SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        //configure the refreshing colour
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        //this listener will load the json file and display a toast when the swipeRefreshLayout is refreshed
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this,"Page Refreshed",Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void initViews(){
        pd=new ProgressDialog(this);
        pd.setMessage("Fetching Github Users.....");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        //set the layout manager of the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    private void loadJSON(){
        Disconnected=(TextView) findViewById(R.id.disconnected);
        try{
            Client Client=new Client();
            Service apiService= Client.getClient().create(Service.class);
            Call<ItemResponse> call=apiService.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items=response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();

                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable throwable) {
                    Log.d("Error", throwable.getMessage());

                    Toast.makeText(MainActivity.this,"Error Fetching data",Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        }
        catch (Exception exception){
            Log.d("Error",exception.getMessage());
            Toast.makeText(this,exception.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
