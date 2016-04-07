package com.android.pixia.americandramatoolkit.adapter;

import android.content.Context;
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
        System.out.println(list.size());
        list.addAll(0, data);
        list.remove(list.size() - 1);
        notifyDataSetChanged();
    }

    public TimeAdapter(List<UpdateBean> data, BaseActivity activity) {
        this.list = data;
        this.mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.size()>0) {
            if (list.get(position).getType() != null) {
                return AppConfig.HEADER;
            } else {
                return AppConfig.NOMAL;
            }
        }else {
            return AppConfig.NOMAL;
        }
    }

    @Override
    public TimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType==AppConfig.NOMAL) {
             view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_time, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
        }
        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(TimeAdapter.ViewHolder holder, int position) {

        if(list.size()>0) {
            if (getItemViewType(position) == AppConfig.NOMAL) {
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
            } else {
                if (!list.get(position).getType().startsWith("相")) {
                    holder.mType.setText(list.get(position).getType().replace(" 剧集名称 电视网 集数 本集名称", ""));
                }
            }
//            if(position==list.size()){
//                holder.itemView.setVisibility(View.GONE);
//            }
        }
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

        private TextView mType;

        public ViewHolder(View itemView,int ViewType) {
            super(itemView);
            if(ViewType == AppConfig.NOMAL){
                mAlbum = (ImageView) itemView.findViewById(R.id.album_image);
                mTitle = (TextView) itemView.findViewById(R.id.album_title);
                mCard = (CardView) itemView.findViewById(R.id.cardView);
                mTime = (TextView) itemView.findViewById(R.id.album_time);
            }else {
                mType = (TextView) itemView.findViewById(R.id.text_time_us);
            }
        }
    }
}
