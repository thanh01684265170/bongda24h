package com.hktstudio.bongda24h.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 01/03/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<CategoryEntity> list;
    private Context mContext;

    public CategoryAdapter(List<CategoryEntity> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        CategoryEntity category = list.get(position);
        holder.tvName.setText(category.getDisplayName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category_name)
        TextView tvName;
        public CategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
