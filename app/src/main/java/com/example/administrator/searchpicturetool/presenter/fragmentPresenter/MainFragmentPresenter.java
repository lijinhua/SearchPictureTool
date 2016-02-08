package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.presenter.adapter.ImageLoopAdapter;
import com.example.administrator.searchpicturetool.presenter.adapter.RecommendAdapter;
import com.example.administrator.searchpicturetool.view.RollViewPagerItemView;
import com.example.administrator.searchpicturetool.view.fragment.MainFragment;
import com.jude.beam.bijection.Presenter;
import com.jude.beam.expansion.data.BeamDataFragment;
import com.jude.rollviewpager.hintview.TextHintView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/2/5 0005.
 */
public class MainFragmentPresenter extends Presenter<MainFragment> implements SwipeRefreshLayout.OnRefreshListener{
  //  private ImageLoopAdapter imageLoopAdapter;
    private RecommendAdapter adapter;
    @Override
    protected void onCreateView(MainFragment view) {
        super.onCreateView(view);
       // imageLoopAdapter = new ImageLoopAdapter(getView().mRollPagerView);
       // getView().mRollPagerView.setHintView(new TextHintView(getView().getContext()));
    //    getView().mRollPagerView.setAdapter(imageLoopAdapter);
        adapter = new RecommendAdapter(getView().getContext());
        getView().recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        getView().recyclerView.setAdapter(adapter);
        adapter.addHeader(new RollViewPagerItemView());
        getView().recyclerView.setRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        RecommendTip tip = new RecommendTip();
        tip.setTip("热门搜索");
        RecommendContent content = new RecommendContent(
                "http://d.hiphotos.baidu.com/image/pic/item/c2cec3fdfc039245d5d7137b8494a4c27d1e2567.jpg"
                ,"title1","content1"
                ,"http://g.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f697f8f081d30e924b899f36c.jpg"
                ,"title2","content2"
                );
        ArrayList items = new ArrayList();
        items.add(tip);
        items.add(content);
        items.add(content);
        adapter.addAll(items);
    }
}
