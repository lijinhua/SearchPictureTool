package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.view.fragment.MainFragment;
import com.example.administrator.searchpicturetool.view.fragment.NetImgFragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/11/2 0002.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private HashMap<String,NetImgFragment> fragments;
    private String[] tabs;
    public MyPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        tabs=context.getResources().getStringArray(R.array.tab);
        fragments = new HashMap<String,NetImgFragment>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if(position==0){
            fragment = new MainFragment();
        }else{
            fragment = new NetImgFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tab", position);
            fragment.setArguments(bundle);
            fragments.put(position+"",(NetImgFragment)fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

       return tabs[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        if(position!=0){
            fragments.remove(position);
        }

    }
    public NetImgFragment getFragment(int position){
        return fragments.get(position+"");
    }
}
