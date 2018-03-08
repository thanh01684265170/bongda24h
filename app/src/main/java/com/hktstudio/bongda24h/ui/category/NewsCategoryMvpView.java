package com.hktstudio.bongda24h.ui.category;

import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.MvpView;


import java.util.List;

/**
 * Created by Hai on 07/03/2018.
 */

public interface NewsCategoryMvpView extends MvpView{
    public void onGeDataSuccess(List<NewsEntity> entity, boolean top);
    void onLoading();
    void onHideLoading();
}
