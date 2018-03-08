package com.hktstudio.bongda24h.ui.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;
import com.hktstudio.bongda24h.ui.adapter.CategoryAdapter;
import com.hktstudio.bongda24h.ui.category.NewsCategoryActivity;
import com.hktstudio.bongda24h.util.UtilSharedPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 28/02/2018.
 */

public class CategoryFragment extends Fragment implements CategoryMvpView,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.rcv_category)
    RecyclerView rcvCategory;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swRefresh;
    CategoryAdapter adapter;
    List<CategoryEntity> list;
    CategoryPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CategoryPresenter();
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category,container,false);
        ButterKnife.bind(this,rootView);
        swRefresh.setOnRefreshListener(this);
        initCategory();
        return rootView;
    }
    protected void initCategory(){
        rcvCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        adapter = new CategoryAdapter(list, getContext(), new ItemOnClick() {
            @Override
            public void onItemClick(int pos, Object obj) {
                CategoryEntity entity = (CategoryEntity) obj;
                Intent t = new Intent(getContext(), NewsCategoryActivity.class);
                t.putExtra("data",entity.toString());
                getContext().startActivity(t);
            }
        });
        rcvCategory.setAdapter(adapter);
        presenter.loadCategory();

    }

    @Override
    public void onLoadSuccess(List<CategoryEntity> categoryEntities) {
        UtilSharedPreference.setString(getContext(),"category",categoryEntities.toString());

        list.clear();
        list.addAll(categoryEntities);
        adapter.notifyDataSetChanged();
        swRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadFail() {
        String rs = UtilSharedPreference.getString(getContext(),"category","");
        if(!rs.equals("")){
            Gson gson = new Gson();
            List<CategoryEntity> categoryEntities = gson.fromJson(rs, new TypeToken<List<CategoryEntity>>(){}.getType());
            list.clear();
            list.addAll(categoryEntities);
            adapter.notifyDataSetChanged();
        }
        swRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.loadCategory();
    }
}
