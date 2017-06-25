package com.android.antonio.starwarsapiclient.ui.activities.search;

import android.widget.BaseAdapter;

import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.ui.activities.details.BaseDatailActivity;
import com.android.antonio.starwarsapiclient.ui.adapters.PlanetListAdapter;
import com.swapi.models.Planet;
import com.swapi.sw.StarWarsApi;

import java.util.List;


public class PlanetsSearchActivity extends BaseSearchActivity<Planet> {
    @Override
    protected void search(int page) {
        StarWarsApi.getApi().getAllPlanets(page,this);
    }

    @Override
    protected BaseAdapter initAdapter(List<Planet> results) {
        return new PlanetListAdapter(results,this);
    }

    @Override
    public void toDetailActivity(Planet planet) {
        BaseDatailActivity.toDetailActivity(this,"planet", Utils.getPlanetIdFromUrl(planet.url));
    }


}
