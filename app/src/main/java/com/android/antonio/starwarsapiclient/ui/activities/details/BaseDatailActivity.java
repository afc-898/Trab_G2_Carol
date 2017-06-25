package com.android.antonio.starwarsapiclient.ui.activities.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.antonio.starwarsapiclient.data.DBHelper;
import com.android.antonio.starwarsapiclient.data.LastUse;
import com.android.antonio.starwarsapiclient.data.LastUsesDAO;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class BaseDatailActivity<T> extends AppCompatActivity implements Callback<T>{
    public static final String API_ID = "api_id";
    protected int apiId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiId = getIntent().getIntExtra(API_ID,0);
    }

    @Override
    public void success(T t, Response response) {
        renderModel(t);
        try {
            new LastUsesDAO(new DBHelper(this)).save(modelToLastUse(t));
        } catch (Exception e) {
            Toast.makeText(this, "Um erro inesperado aconteceu.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    protected abstract LastUse modelToLastUse(T t);
    protected abstract void renderModel(T t);

    public static void toDetailActivity(Context context,String type,int apiId){
        Class activityClass = null;
        if(type.equals("film")){
            activityClass = FilmDetailActivity.class;
        }else if(type.equals("planet")){
            activityClass = PlanetDetailActivity.class;
        }else if(type.equals("people")){
            activityClass = PeopleDetailActivity.class;
        }
        Intent intent = new Intent(context,activityClass);
        intent.putExtra(API_ID,apiId);
        context.startActivity(intent);
    }
}
