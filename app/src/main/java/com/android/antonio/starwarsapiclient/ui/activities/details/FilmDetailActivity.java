package com.android.antonio.starwarsapiclient.ui.activities.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.antonio.starwarsapiclient.R;
import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.data.LastUse;
import com.swapi.models.Film;
import com.swapi.models.People;
import com.swapi.sw.StarWarsApi;


public class FilmDetailActivity extends BaseDatailActivity<Film> {
    TextView tvTitle;
    TextView tvEpisode;
    TextView tvDirector;
    TextView tvProducer;
    TextView tvReleaseDate;
    TextView tvOpeningCrawl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvEpisode = (TextView) findViewById(R.id.tvEpisode);
        tvDirector = (TextView) findViewById(R.id.tvDirector);
        tvProducer = (TextView) findViewById(R.id.tvProducer);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvOpeningCrawl = (TextView) findViewById(R.id.tvOpeningCrawl);
        StarWarsApi.getApi().getFilm(apiId,this);
    }

    @Override
    protected LastUse modelToLastUse(Film film) {
        LastUse lastUse = new LastUse();
        lastUse.setName(film.title);
        int apiId = Utils.getFilmIdFromUrl(film.url);
        lastUse.setApiId(apiId);
        lastUse.setType("film");
        lastUse.setId("film"+apiId);
        return lastUse;
    }

    @Override
    protected void renderModel(Film film) {
        tvTitle.setText(film.title);
        tvEpisode.setText(film.episodeId+"");
        tvDirector.setText(film.director);
        tvProducer.setText(film.producer);
        tvReleaseDate.setText(film.releaseDate);
        tvOpeningCrawl.setText(film.openingCrawl);
    }
}
