package com.android.antonio.starwarsapiclient.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.antonio.starwarsapiclient.R;
import com.android.antonio.starwarsapiclient.Utils;
import com.swapi.models.People;

import java.util.List;


public class PeopleListAdapter extends BaseAdapter {
    List<People> list;
    Context context;

    public PeopleListAdapter(List<People> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Utils.getPeopleIdFromUrl(list.get(position).url);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_list_item,parent,false);
        ((TextView)view.findViewById(R.id.tvName)).setText(list.get(position).name);
        ((TextView)view.findViewById(R.id.tvGender)).setText(list.get(position).gender);
        return view;
    }
}
