package com.android.antonio.starwarsapiclient.ui.activities.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.antonio.starwarsapiclient.R;
import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.data.LastUse;
import com.swapi.models.Planet;
import com.swapi.sw.StarWarsApi;


public class PlanetDetailActivity extends BaseDatailActivity<Planet> {
    TextView tvName;
    TextView tvRotationPeriod;
    TextView tvOrbitalPeriod;
    TextView tvDiameter;
    TextView tvClimate;
    TextView tvTerrain;
    TextView tvGravity;
    TextView tvSurfaceWater;
    TextView tvPopulation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);
        tvName = (TextView) findViewById(R.id.tvName);
        tvRotationPeriod = (TextView) findViewById(R.id.tvRotationPeriod);
        tvOrbitalPeriod = (TextView) findViewById(R.id.tvOrbitalPeriod);
        tvDiameter = (TextView) findViewById(R.id.tvDiameter);
        tvClimate = (TextView) findViewById(R.id.tvClimate);
        tvTerrain = (TextView) findViewById(R.id.tvTerrain);
        tvGravity = (TextView) findViewById(R.id.tvGravity);
        tvSurfaceWater = (TextView) findViewById(R.id.tvSurfaceWater);
        tvPopulation = (TextView) findViewById(R.id.tvPopulation);
        StarWarsApi.getApi().getPlanet(apiId,this);
    }

    @Override
    protected LastUse modelToLastUse(Planet planet) {
        LastUse lastUse = new LastUse();
        lastUse.setName(planet.name);
        int apiId = Utils.getPlanetIdFromUrl(planet.url);
        lastUse.setApiId(apiId);
        lastUse.setType("planet");
        return lastUse;
    }

    @Override
    protected void renderModel(Planet planet) {
        tvName.setText(planet.name);
        tvRotationPeriod.setText(planet.rotationPeriod);
        tvOrbitalPeriod.setText(planet.orbitalPeriod);
        tvDiameter.setText(planet.diameter);
        tvClimate.setText(planet.climate);
        tvTerrain.setText(planet.terrain);
        tvGravity.setText(planet.gravity);
        tvSurfaceWater.setText(planet.surfaceWater);
        tvPopulation.setText(planet.population);
    }
}
