package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.db.DBManager;
import com.example.administrator.searchpicturetool.presenter.adapter.UserPagerAdapter;
import com.example.administrator.searchpicturetool.view.activity.MainActivity;
import com.example.administrator.searchpicturetool.presenter.adapter.MyPagerAdapter;
import com.example.administrator.searchpicturetool.view.activity.UserActivity;
import com.jude.beam.bijection.Presenter;

/**
 * Created by Administrator on 2015/11/2 0002.
 */
public class MainPresenter extends Presenter<MainActivity>{
    MyPagerAdapter adapter;
    FragmentManager fragmentManager;
    public int item =1;
    @Override
    protected void onCreateView(MainActivity view) {
        super.onCreateView(view);
        view.getTabLayout().setSelectedTabIndicatorColor(view.getResources().getColor(R.color.white));
        fragmentManager =view.getSupportFragmentManager();
        replaceFragment(0);
    }
    public void replaceFragment(int position){
                item=position;
                if(adapter==null){
                    adapter = new MyPagerAdapter(getView(),fragmentManager);
                }
                getView().getViewPager().setAdapter(adapter);
                getView().getTabLayout().setupWithViewPager(getView().getViewPager());

        }
    public void goToUp(int position){
        adapter.getFragment(getView().getViewPager().getCurrentItem()).getListView().scrollToPosition(position);
    }
    public void stopRefresh(int i){
        if(adapter.getFragment(getView().getViewPager().getCurrentItem())!=null)
        adapter.getFragment(getView().getViewPager().getCurrentItem()).getListView().getSwipeToRefresh().setEnabled(i == 0);
    }

}
