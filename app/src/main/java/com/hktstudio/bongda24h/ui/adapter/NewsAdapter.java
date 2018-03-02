package com.hktstudio.bongda24h.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;
import com.hktstudio.bongda24h.util.UtilTime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 28/02/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    public static final int TYPE_HOT_NEWS=0;
    public static final int TYPE_HOME_NEWS=1;
    public static final int TYPE_NORMAL_NEWS=2;
    private Context mContext;
    private List<NewsEntity> list;
    private int type;
    private ItemOnClick itemOnClick;
    public NewsAdapter(Context mContext, List<NewsEntity> list,int type,ItemOnClick itemOnClick) {
        this.mContext = mContext;
        this.list = list;
        this.type = type;
        this.itemOnClick = itemOnClick;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (type){
            case TYPE_HOT_NEWS:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_news,parent,false);
                break;
            case TYPE_HOME_NEWS:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
                break;
            case TYPE_NORMAL_NEWS:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
                break;
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
        }
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        final NewsEntity news = list.get(position);
        holder.tvCategory.setText(news.getCategory().getDisplayName());
        holder.tvDes.setText(news.getTitle());
        holder.tvTimeAgo.setText(UtilTime.timeAgo(news.getLastModifyDate()));
        Glide.with(mContext).load(news.getPostImage()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.onItemClick(position,news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_news)
        ImageView img;
        @BindView(R.id.tv_news_category)
        TextView tvCategory;
        @BindView(R.id.tv_news_des)
        TextView tvDes;
        @BindView(R.id.tv_news_time_ago)
        TextView tvTimeAgo;
        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
