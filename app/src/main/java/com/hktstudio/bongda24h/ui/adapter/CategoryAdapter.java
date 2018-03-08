package com.hktstudio.bongda24h.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hai on 01/03/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<CategoryEntity> list;
    private Context mContext;
    int[] color;
    ItemOnClick itemOnClick;
    public CategoryAdapter(List<CategoryEntity> list, Context mContext,ItemOnClick itemOnClick) {
        this.list = list;
        this.mContext = mContext;
        this.itemOnClick = itemOnClick;
        color = mContext.getResources().getIntArray(R.array.arrayColor);;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_category,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, final int position) {
        final CategoryEntity category = list.get(position);
        holder.tvName.setText(category.getDisplayName());
        holder.tvLabel.setText(category.getDisplayName().charAt(0)+"");
        holder.img.setColorFilter(color[position%9]);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnClick.onItemClick(position,category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category_name)
        TextView tvName;
        @BindView(R.id.layout_root)
         View view;
        @BindView(R.id.img_category)
        CircleImageView img;
        @BindView(R.id.tv_category_label)
        TextView tvLabel;
        public CategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
