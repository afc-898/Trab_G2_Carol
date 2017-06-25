package com.android.antonio.starwarsapiclient.ui.activities.search;

import android.widget.BaseAdapter;

import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.ui.activities.details.BaseDatailActivity;
import com.android.antonio.starwarsapiclient.ui.adapters.PeopleListAdapter;
import com.swapi.models.People;
import com.swapi.sw.StarWarsApi;

import java.util.List;



public class PeopleSearchActivity extends BaseSearchActivity<People> {
    @Override
    protected void search(int page) {
        StarWarsApi.getApi().getAllPeople(page,this);
    }

    @Override
    protected BaseAdapter initAdapter(List<People> results) {
        return new PeopleListAdapter(results,this);
    }

    @Override
    public void toDetailActivity(People people) {
        BaseDatailActivity.toDetailActivity(this,"people", Utils.getPeopleIdFromUrl(people.url));
    }


}
