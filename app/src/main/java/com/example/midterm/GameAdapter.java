package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.Game;

import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<Game>{
    private ArrayList<Game> dataSet;
    private Context context;

    private static class ViewHolder{
        TextView tvGamename;
        TextView tvGamerating;
        TextView tvGameprice;
    }

    public GameAdapter(ArrayList<Game> data,Context context){
        super(context,R.layout.gamelist_item,data);
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Game game = getItem(position);

        ViewHolder holder;

        if (convertView== null){
            holder = new ViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.gamelist_item,parent,false);

        holder.tvGamename = convertView.findViewById(R.id.setgamename);
        holder.tvGamerating = convertView.findViewById(R.id.setrating);
        holder.tvGameprice = convertView.findViewById(R.id.setprice);


        convertView.setTag(holder);
    }else{
        holder= (ViewHolder) convertView.getTag();

    }
        holder.tvGamename.setText(game.getGamename());
        holder.tvGamerating.setText(game.getRating()+"/10");
        holder.tvGameprice.setText("$"+game.getPrice());
        return convertView;
    }
}
