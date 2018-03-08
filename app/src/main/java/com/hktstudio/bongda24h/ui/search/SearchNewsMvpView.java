package com.hktstudio.bongda24h.ui.search;

import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.MvpView;

import java.util.List;

/**
 * Created by Hai on 08/03/2018.
 */

public interface SearchNewsMvpView extends MvpView{
    void onGetDataSuccess(List<NewsEntity> list,boolean top);
    void onHideLoading();
}
