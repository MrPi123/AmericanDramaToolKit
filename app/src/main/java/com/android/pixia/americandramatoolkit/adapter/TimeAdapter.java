package com.android.pixia.americandramatoolkit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pixia.americandramatoolkit.AppConfig;
import com.android.pixia.americandramatoolkit.R;
import com.android.pixia.americandramatoolkit.base.BaseActivity;
import com.android.pixia.americandramatoolkit.bean.UpdateBean;
import com.android.pixia.americandramatoolkit.view.USInfoActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixia on 2016/4/7.
 */
public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> implements FastScrollRecyclerView.SectionedAdapter {

    List<UpdateBean> list = new ArrayList<>();
    BaseActivity mActivity;

    public void init(List<UpdateBean> data){
        list.clear();
        list.addAll(0, data);
        list.remove(list.size() - 1);
        notifyDataSetChanged();
    }

    public UpdateBean getItem(int position){
        return list.get(position);
    }
    public TimeAdapter(List<UpdateBean> data, BaseActivity activity) {
        this.list = data;
        this.mActivity = activity;
    }

    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_time, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeAdapter.ViewHolder holder, int position) {
            Glide.with(mActivity)
                    .load(list.get(position).getPicUrl())
                    .listener(GlidePalette.with(list.get(position).getPicUrl())
                                    .use(GlidePalette.Profile.VIBRANT_LIGHT)
                                    .intoBackground(holder.mCard)
                                    .intoTextColor(holder.mTitle)
                                    .intoTextColor(holder.mTime)
                    )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mAlbum);
            holder.mTitle.setText(list.get(position).getTitle());
            holder.mTime.setText(list.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        String time = list.get(position).getTime();
        time = time.substring(1,2);
        return time;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mAlbum;
        private TextView mTitle;
        private TextView mTime;
        private CardView mCard;


        public ViewHolder(View itemView) {
            super(itemView);
            mAlbum = (ImageView) itemView.findViewById(R.id.album_image);
            mTitle = (TextView) itemView.findViewById(R.id.album_title);
            mCard = (CardView) itemView.findViewById(R.id.cardView);
            mTime = (TextView) itemView.findViewById(R.id.album_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, USInfoActivity.class);
                    intent.putExtra("bean", list.get(getLayoutPosition()));
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
