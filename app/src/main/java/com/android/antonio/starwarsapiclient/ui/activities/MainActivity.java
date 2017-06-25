package com.android.antonio.starwarsapiclient.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.android.antonio.starwarsapiclient.R;
import com.android.antonio.starwarsapiclient.data.DBHelper;
import com.android.antonio.starwarsapiclient.data.LastUse;
import com.android.antonio.starwarsapiclient.data.LastUsesDAO;
import com.android.antonio.starwarsapiclient.ui.activities.details.BaseDatailActivity;
import com.android.antonio.starwarsapiclient.ui.activities.search.FilmsSearchActivity;
import com.android.antonio.starwarsapiclient.ui.activities.search.PeopleSearchActivity;
import com.android.antonio.starwarsapiclient.ui.activities.search.PlanetsSearchActivity;
import com.android.antonio.starwarsapiclient.ui.adapters.LastUseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSearch;
    RadioButton rbPeople;
    RadioButton rbFilms;
    RadioButton rbPlanets;
    ListView lvLastUses;
    List<LastUse> lastUses = new ArrayList<>();
    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        rbFilms = (RadioButton)findViewById(R.id.rbFilms);
        rbPeople = (RadioButton)findViewById(R.id.rbPeople);
        rbPlanets = (RadioButton)findViewById(R.id.rbPlanets);
        lvLastUses = (ListView) findViewById(R.id.lvLastsSearches);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        adapter = new LastUseListAdapter(lastUses,this);
        lvLastUses.setAdapter(adapter);
        lvLastUses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LastUse lastUse = lastUses.get(position);
                BaseDatailActivity.toDetailActivity(MainActivity.this,lastUse.getType(),lastUse.getApiId());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lastUses.clear();
        lastUses.addAll(new LastUsesDAO(new DBHelper(this)).getAll());
        adapter.notifyDataSetChanged();
    }

    void search(){
        Class searchActivityClass = null;
        if (rbPlanets.isChecked()){
            searchActivityClass = PlanetsSearchActivity.class;
        }else if(rbFilms.isChecked()){
            searchActivityClass = FilmsSearchActivity.class;
        } else if (rbPeople.isChecked()){
            searchActivityClass = PeopleSearchActivity.class;
        }
        startActivity(new Intent(this,searchActivityClass));
    }
}
