package com.example.nueva.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nueva.Book;
import com.example.nueva.R;
import com.example.nueva.library.libro;

import java.util.List;

public class BooksAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<libro> books;

    // 1
    public BooksAdapter(Context context, List<libro> books) {
        this.mContext = context;
        this.books = books;
    }

    // 2

    @Override
    public int getCount() {
        return books.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final libro book = books.get(position);

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayoutbook, null);
        }

        // 3
       // final ImageView imageView = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
        final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
       // final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

        // 4
      //  imageView.setImageResource(book.getImageResource());
        nameTextView.setText(book.getTitulo());
        authorTextView.setText(book.getIdlibro());

        return convertView;
    }

}
