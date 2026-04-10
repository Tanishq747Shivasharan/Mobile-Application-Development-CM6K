package com.example.examinationprep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

    Context context;
    String[] titles, authors;
    int[] covers;

    public BookAdapter(Context context, String[] titles, String[] authors, int[] covers) {
        this.context = context;
        this.titles = titles;
        this.authors = authors;
        this.covers = covers;
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView img = view.findViewById(R.id.imgBook);
        TextView title = view.findViewById(R.id.tvTitle);
        TextView author = view.findViewById(R.id.tvAuthor);

        img.setImageResource(covers[i]);
        title.setText(titles[i]);
        author.setText(authors[i]);

        return view;
    }
}