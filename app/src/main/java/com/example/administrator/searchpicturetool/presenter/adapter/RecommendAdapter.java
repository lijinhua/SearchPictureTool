package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.view.RecommendContentViewHolder;
import com.example.administrator.searchpicturetool.view.RecommendTipVewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/2/7 0007.
 */
public class RecommendAdapter extends RecyclerArrayAdapter<Object>{
    private int tip=0;
    private int content =1;
    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==tip){
            return   new RecommendTipVewHolder(parent);
        }else if(viewType==content){
            return new RecommendContentViewHolder(parent);
        }
        return  null;
    }


    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position) instanceof RecommendContent){
            return  content;
        } else if(getItem(position) instanceof RecommendTip) return  tip;
       /* if(position==0){

        }
        return  content;*/
        return  -1;
    }
}
