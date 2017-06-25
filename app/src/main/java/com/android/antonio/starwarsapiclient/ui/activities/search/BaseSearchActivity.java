package com.android.antonio.starwarsapiclient.ui.activities.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.antonio.starwarsapiclient.R;
import com.swapi.models.SWModelList;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public abstract class BaseSearchActivity<T> extends AppCompatActivity implements Callback<SWModelList<T>>{

    protected List<T> results = new ArrayList<>();
    protected ListView listResults;
    protected BaseAdapter adapter;
    protected int page = 0;
    protected boolean isSearching = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listResults = (ListView) findViewById(R.id.list_search);
        adapter = initAdapter(results);
        listResults.setAdapter(adapter);
        listResults.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount>results.size()-1 && page>0 && !isSearching){
                    search(++page);
                    isSearching = true;
                }
            }
        });
        listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toDetailActivity(results.get(position));
            }
        });
        search(++page);
        isSearching = true;
    }

    @Override
    public void success(SWModelList<T> peopleSWModelList, Response response) {
        if (!peopleSWModelList.hasMore()) page = -1;
        results.addAll(peopleSWModelList.results);
        adapter.notifyDataSetChanged();
        isSearching = false;
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        isSearching = false;
    }

    protected abstract void search(int page);

    protected abstract BaseAdapter initAdapter(List<T> results);

    public abstract void toDetailActivity(T t);

}
