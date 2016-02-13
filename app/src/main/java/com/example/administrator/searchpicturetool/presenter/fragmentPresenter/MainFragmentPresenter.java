package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.searchpicturetool.model.RecommendModel;
import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.presenter.adapter.ImageLoopAdapter;
import com.example.administrator.searchpicturetool.presenter.adapter.RecommendAdapter;
import com.example.administrator.searchpicturetool.view.RollViewPagerItemView;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.example.administrator.searchpicturetool.view.fragment.MainFragment;
import com.jude.beam.bijection.Presenter;
import com.jude.beam.expansion.data.BeamDataFragment;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.hintview.TextHintView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/2/5 0005.
 */
public class MainFragmentPresenter extends Presenter<MainFragment> implements SwipeRefreshLayout.OnRefreshListener{
    private RecommendAdapter adapter;
    @Override
    protected void onCreateView(MainFragment view) {
        super.onCreateView(view);
        adapter = new RecommendAdapter(getView().getContext());
        getView().recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        getView().recyclerView.setAdapter(adapter);
        adapter.addHeader(new RollViewPagerItemView(getView().recyclerView.getSwipeToRefresh()));
        getView().recyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        adapter.addAll(RecommendModel.getRecommends());

    }

}
