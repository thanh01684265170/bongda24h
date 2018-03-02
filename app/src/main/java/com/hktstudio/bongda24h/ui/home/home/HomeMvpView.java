package com.hktstudio.bongda24h.ui.home.home;

import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.MvpView;

import java.util.List;

/**
 * Created by Hai on 28/02/2018.
 */

public interface HomeMvpView extends MvpView {
    void onGetHotNewsSuccess(List<NewsEntity> hotNews);
    void onGetHomeNewsSuccess(List<NewsEntity> homeNews,boolean top);
    void onLoading();
    void onHideLoading();
}
