package com.example.daterequest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.daterequest.Bean.Book;
import com.example.daterequest.R;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private List<Book> list;
    private Context context;
    private LayoutInflater inflater;

    public BookAdapter(Context context,List<Book> list){
         this.context=context;
         this.list=list;
         inflater=LayoutInflater.from(context);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.item_book,null);
        TextView tv_id=(TextView)view.findViewById(R.id.tv_id);
        TextView tv_catalog=(TextView)view.findViewById(R.id.tv_catalog);

        tv_id.setText(list.get(position).getId());
        tv_catalog.setText(list.get(position).getCatalog());
        return view;
    }
}
