package com.hktstudio.bongda24h.ui.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.ui.search.SearchNewsActivity;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.vpg_home)
    ViewPager vpgHome;
    HomePageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initPage();
    }

    /**
     * init fragment and viewpager
     */
    protected  void initPage(){
        adapter = new HomePageAdapter(getSupportFragmentManager(),getBaseContext());
        vpgHome.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpgHome);

    }

    /**
     * create menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                startActivity(new Intent(this, SearchNewsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
