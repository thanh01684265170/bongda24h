package com.hktstudio.bongda24h.ui.home.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 28/02/2018.
 */

public class CategoryFragment extends Fragment implements CategoryMvpView{
    @BindView(R.id.rcv_category)
    RecyclerView rcvCategory;
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
        initCategory();
        return rootView;
    }
    protected void initCategory(){
        rcvCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        adapter = new CategoryAdapter(list,getContext());
        rcvCategory.setAdapter(adapter);
        presenter.loadCategory();

    }

    @Override
    public void onLoadSuccess(List<CategoryEntity> categoryEntities) {
        list.clear();
        list.addAll(categoryEntities);
        adapter.notifyDataSetChanged();
    }
}
