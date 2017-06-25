package com.android.antonio.starwarsapiclient.ui.activities.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.antonio.starwarsapiclient.R;
import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.data.LastUse;
import com.swapi.models.People;
import com.swapi.models.Planet;
import com.swapi.sw.StarWarsApi;



public class PeopleDetailActivity extends BaseDatailActivity<People> {
    TextView tvName;
    TextView tvHeight;
    TextView tvWeight;
    TextView tvHairColor;
    TextView tvSkinColor;
    TextView tvEyeColor;
    TextView tvBirthYear;
    TextView tvGender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_detail);
        tvName = (TextView) findViewById(R.id.tvName);
        tvHeight = (TextView) findViewById(R.id.tvHeight);
        tvWeight = (TextView) findViewById(R.id.tvWeight);
        tvHairColor = (TextView) findViewById(R.id.tvHairColor);
        tvSkinColor = (TextView) findViewById(R.id.tvSkinColor);
        tvEyeColor = (TextView) findViewById(R.id.tvEyeColor);
        tvBirthYear = (TextView) findViewById(R.id.tvBirthYear);
        tvGender = (TextView) findViewById(R.id.tvGender);
        StarWarsApi.getApi().getPeople(apiId,this);
    }

    @Override
    protected LastUse modelToLastUse(People people) {
        LastUse lastUse = new LastUse();
        lastUse.setName(people.name);
        int apiId = Utils.getPeopleIdFromUrl(people.url);
        lastUse.setApiId(apiId);
        lastUse.setType("people");
        lastUse.setId("film"+apiId);
        return lastUse;
    }

    @Override
    protected void renderModel(People people) {
        tvName.setText(people.name);
        tvHeight.setText(people.height.concat("cm"));
        tvWeight.setText(people.mass.concat("kg"));
        tvHairColor.setText(people.hairColor);
        tvSkinColor.setText(people.skinColor);
        tvEyeColor.setText(people.eyeColor);
        tvBirthYear.setText(people.birthYear);
        tvGender.setText(people.gender);
    }
}
