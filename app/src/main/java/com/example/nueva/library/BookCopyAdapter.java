package com.example.nueva.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nueva.R;

import java.util.List;

public class BookCopyAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<BookCopy> books;

    public BookCopyAdapter(Context context, List<BookCopy> books) {
        this.mContext = context;
        this.books = books;
    }


    @Override
    public int getCount() {
        return books.size();
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
        // 1
        final BookCopy book = books.get(position);

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayoutbook_copy, null);
        }

        // 3
        // final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
        final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_another);
        // final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

        // 4
        //  imageView.setImageResource(book.getImageResource());
        nameTextView.setText(book.getInventario());
        authorTextView.setText(book.getCod_barras());

        return convertView;
    }
}
