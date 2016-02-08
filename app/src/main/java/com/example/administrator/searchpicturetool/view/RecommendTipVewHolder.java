package com.example.administrator.searchpicturetool.view;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public class RecommendTipVewHolder extends BaseViewHolder<Object>{
    private TextView tip;
    private Button btn;
    public RecommendTipVewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_tip);
        tip =(TextView)itemView.findViewById(R.id.recommend_tip);
    }

    @Override
    public void setData(Object data) {
        super.setData(data);
        if(data instanceof RecommendTip){
            tip.setText(((RecommendTip)data).getTip());
        }

    }
}
