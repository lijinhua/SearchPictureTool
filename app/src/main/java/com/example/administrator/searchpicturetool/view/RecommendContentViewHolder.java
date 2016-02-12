package com.example.administrator.searchpicturetool.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/2/7 0007.
 */
public class RecommendContentViewHolder extends BaseViewHolder<RecommendContent> implements View.OnClickListener {
    private SimpleDraweeView simpleDraweeView1;
    private SimpleDraweeView simpleDraweeView2;
    private CardView cardView1;
    private CardView cardView2;
    private TextView title1;
    private TextView title2;
    private TextView content1;
    private TextView content2;
    RecommendContent recommendContent;
    public RecommendContentViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend);
        simpleDraweeView1=$(R.id.recomend_img1);
        simpleDraweeView2=$(R.id.recomend_img2);
        title1 =$(R.id.recommend_title1);
        title2 =$(R.id.recommend_title2);
        content1 =$(R.id.recommend_content1);
        content2 =$(R.id.recommend_content2);
        cardView1 =$(R.id.recommend_cardview1);
        cardView2 =$(R.id.recommend_cardview2);
    }

    @Override
    public void setData(RecommendContent data) {

        super.setData(data);
            recommendContent = data;
            simpleDraweeView1.setImageURI(Uri.parse(recommendContent.getImgUrl1()));
            simpleDraweeView2.setImageURI(Uri.parse(recommendContent.getImgUrl2()));
            title1.setText(recommendContent.getTitle1());
            title2.setText(recommendContent.getTitle2());
            content1.setText(recommendContent.getContent1());
            content2.setText(recommendContent.getContent2());
            cardView1.setOnClickListener(this);
            cardView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.recommend_cardview1:
                bundle.putString("search",recommendContent.getTitle1());
                break;
            case R.id.recommend_cardview2:
                bundle.putString("search",recommendContent.getTitle2());
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("search", bundle);
        intent.setClass(getContext(), SearchActivity.class);
        getContext().startActivity(intent);
    }
}
