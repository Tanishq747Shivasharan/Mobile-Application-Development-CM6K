package com.example.examinationprep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

    Context ctx;
    String[] titles, authors;
    int[] covers;

    public BookAdapter(Context ctx, String[] titles, String[] authors, int[] covers) {
        this.ctx = ctx;
        this.titles = titles;
        this.authors = authors;
        this.covers = covers;
    }
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int pos) {
        return titles[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder pattern for smooth scrolling
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.imgBook);
            holder.title = convertView.findViewById(R.id.tvTitle);
            holder.author = convertView.findViewById(R.id.tvAuthor);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setImageResource(covers[position]);
        holder.title.setText(titles[position]);
        holder.author.setText(authors[position]);
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView title, author;
    }
}
