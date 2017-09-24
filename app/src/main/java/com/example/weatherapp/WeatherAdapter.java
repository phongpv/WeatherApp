package com.example.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhongPham on 9/21/2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherAdapterViewHolder>{
    private Context mContext;
    private List<Weather> mList;
    private WeatherOnTtemListener listener;

    public WeatherAdapter(Context mContext, WeatherOnTtemListener listener) {
        this.mContext = mContext;
        mList = new ArrayList<>();
        this.listener = listener;
    }

    //create interface OnClick
    public interface WeatherOnTtemListener{
        void onClick(int position);
    }

    public void setmList(List<Weather> mList) {
        this.mList = mList;
    }

    @Override
    public WeatherAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_list,parent,false);
        return new WeatherAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherAdapterViewHolder holder, int position) {
        holder.txtDay.setText(mList.get(position).getDay());
        holder.txtCenciusMin.setText(mList.get(position).getCenciusMin());
        holder.txtCenciusMax.setText(mList.get(position).getCenciusMax());
        holder.txtState.setText(mList.get(position).getState());
        holder.txtPressure.setText(mList.get(position).getPressure());
        holder.txtDesc.setText(mList.get(position).getDescription());
        holder.imgHinh.setImageResource(mList.get(position).getHinh());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class WeatherAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtDay, txtCenciusMin, txtCenciusMax, txtState, txtPressure, txtDesc;
        ImageView imgHinh;
        public WeatherAdapterViewHolder(View itemView) {
            super(itemView);
            txtDay = (TextView) itemView.findViewById(R.id.txtDay);
            txtCenciusMin = (TextView) itemView.findViewById(R.id.txtCenciusMin);
            txtCenciusMax = (TextView) itemView.findViewById(R.id.txtCenciusMax);
            txtState = (TextView) itemView.findViewById(R.id.txtState);
            txtPressure = (TextView) itemView.findViewById(R.id.txtPressure);
            txtDesc = (TextView) itemView.findViewById(R.id.txtDesc);

            imgHinh = (ImageView) itemView.findViewById(R.id.imgHinh);

            itemView.getRootView().setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition());
        }
    }
}
