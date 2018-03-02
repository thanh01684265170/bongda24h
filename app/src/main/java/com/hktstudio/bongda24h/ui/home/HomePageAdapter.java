package com.hktstudio.bongda24h.ui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.ui.home.category.CategoryFragment;
import com.hktstudio.bongda24h.ui.home.home.HomeFragment;

/**
 * Created by Hai on 28/02/2018.
 */

public class HomePageAdapter extends FragmentPagerAdapter {
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    Context mContext;
    public HomePageAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        this.mContext = mContext;
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return homeFragment;
            case 1:
                return categoryFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.home);
            case 1:
                return mContext.getString(R.string.category);
            default:
                return null;
        }
    }
}
