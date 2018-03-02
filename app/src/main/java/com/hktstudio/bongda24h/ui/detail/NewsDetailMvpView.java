package com.hktstudio.bongda24h.ui.detail;

import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.ui.base.MvpView;

/**
 * Created by Hai on 01/03/2018.
 */

public interface NewsDetailMvpView extends MvpView {
    public void onGetDetailSuccess(NewsDetailEntity entity);
}
