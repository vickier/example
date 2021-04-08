package com.example.daterequest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.daterequest.Bean.Weather;
import com.example.daterequest.R;

import java.util.List;

public class weatherAdapter extends BaseAdapter {
    private List<Weather> list;
    private LayoutInflater inflater;
    private Context context;

    public weatherAdapter(Context context, List<Weather> list) {
        this.context=context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        View view = inflater.inflate(R.layout.weather_list, null);
        TextView tv_date=(TextView)view.findViewById(R.id.tv_date);
        TextView tv_fl=(TextView)view.findViewById(R.id.tv_fl);
        TextView tv_fx=(TextView)view.findViewById(R.id.tv_fx);
        TextView tv_high=(TextView)view.findViewById(R.id.tv_high);
        TextView tv_low=(TextView)view.findViewById(R.id.tv_low);
        TextView tv_type=(TextView)view.findViewById(R.id.tv_type);

        tv_date.setText(list.get(position).getDate());
        tv_fl.setText(list.get(position).getFl());
        tv_fx.setText(list.get(position).getFx());
        tv_high.setText(list.get(position).getHigh());
        tv_low.setText(list.get(position).getLow());
        tv_type.setText(list.get(position).getType());

        return view;
    }

}
