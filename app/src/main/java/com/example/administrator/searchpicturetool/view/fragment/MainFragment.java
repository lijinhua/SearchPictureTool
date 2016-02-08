package com.example.administrator.searchpicturetool.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.MainFragmentPresenter;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.rollviewpager.RollPagerView;


/**
 * Created by Administrator on 2016/2/5 0005.
 */

@RequiresPresenter(MainFragmentPresenter.class)
public class MainFragment extends BeamFragment<MainFragmentPresenter>{
   // public RollPagerView mRollPagerView;
    public EasyRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main,null);
     //   mRollPagerView =(RollPagerView)view.findViewById(R.id.roll_view_pager);
        recyclerView = (EasyRecyclerView)view.findViewById(R.id.easy_recyclerview);
        return view;
    }
}
