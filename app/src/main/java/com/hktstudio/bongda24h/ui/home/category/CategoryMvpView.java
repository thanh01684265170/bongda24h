package com.hktstudio.bongda24h.ui.home.category;

import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.ui.base.MvpView;

import java.util.List;

/**
 * Created by Hai on 01/03/2018.
 */

public interface CategoryMvpView extends MvpView {
    void onLoadSuccess(List<CategoryEntity> list);
}
