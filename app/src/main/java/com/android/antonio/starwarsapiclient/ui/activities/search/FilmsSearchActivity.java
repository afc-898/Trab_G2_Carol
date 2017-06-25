package com.android.antonio.starwarsapiclient.ui.activities.search;

import android.widget.BaseAdapter;

import com.android.antonio.starwarsapiclient.Utils;
import com.android.antonio.starwarsapiclient.ui.activities.details.BaseDatailActivity;
import com.android.antonio.starwarsapiclient.ui.adapters.FilmListAdapter;
import com.swapi.models.Film;
import com.swapi.sw.StarWarsApi;

import java.util.List;

public class FilmsSearchActivity extends BaseSearchActivity<Film> {
    @Override
    protected void search(int page) {
        StarWarsApi.getApi().getAllFilms(page,this);
    }

    @Override
    protected BaseAdapter initAdapter(List<Film> results) {
        return new FilmListAdapter(results,this);
    }

    @Override
    public void toDetailActivity(Film film) {
        BaseDatailActivity.toDetailActivity(this,"film", Utils.getFilmIdFromUrl(film.url));
    }


}
