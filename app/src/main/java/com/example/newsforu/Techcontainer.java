package com.example.newsforu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Techcontainer extends Fragment {
    String apiKey = "1a4be06e3c5d444e96761b01b2dc2f00";
    ArrayList<ApiClass> apiClassArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView technologyrecyclerView;
    private  String category = "technology";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.technologycontainer, null);

        technologyrecyclerView = v.findViewById(R.id.technologycylerview);
        apiClassArrayList = new ArrayList<>();
        technologyrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), apiClassArrayList);
        technologyrecyclerView.setAdapter(adapter);

        allNews();
        return v;
    }

    private void allNews() {
        ApiUtility.getApiInterface().getCategoryNews(country, category, 100, apiKey).enqueue(new Callback<NewsClass>() {
            @Override
            public void onResponse(Call<NewsClass> call, Response<NewsClass> response) {
                if(response.isSuccessful()){
                    apiClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsClass> call, Throwable t) {

            }
        });
    }
}