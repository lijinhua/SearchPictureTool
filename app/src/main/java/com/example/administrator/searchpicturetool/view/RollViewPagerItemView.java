package com.example.administrator.searchpicturetool.view;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.adapter.ImageLoopAdapter;
import com.example.administrator.searchpicturetool.presenter.adapter.RecommendAdapter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.jude.utils.JUtils;

/**
 * Created by Administrator on 2016/2/9 0009.
 */
public class RollViewPagerItemView implements RecyclerArrayAdapter.ItemView {
    RollPagerView rollPagerView;
    ImageLoopAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    public RollViewPagerItemView(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout =swipeRefreshLayout;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_viewpager,parent,false);
        rollPagerView =(RollPagerView)view.findViewById(R.id.roll_view_pager);
        adapter = new ImageLoopAdapter(rollPagerView);
        rollPagerView.getViewPager().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_MOVE:
                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onBindView(View headerView) {

        rollPagerView.setHintView(new TextHintView(headerView.getContext()));
        rollPagerView.setAdapter(adapter);


    }

}
