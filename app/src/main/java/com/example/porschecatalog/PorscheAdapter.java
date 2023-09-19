package com.example.porschecatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PorscheAdapter extends BaseAdapter {
    Context context;
    List<String>models;
    List<String> urls;
    LayoutInflater inflater;
    public PorscheAdapter(Context context, List<String> models,List<String> urls){
        this.context = context;
        this.models = models;
        this.urls = urls;
        inflater = LayoutInflater.from(this.context);
    }
    @Override
    public int getCount() {
        return (int) models.stream().count();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_card,null);
        TextView textView = (TextView)convertView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        textView.setText(models.get(position));
        Picasso.get().load(urls.get(position)).into(imageView);
        return convertView;
    }
}
