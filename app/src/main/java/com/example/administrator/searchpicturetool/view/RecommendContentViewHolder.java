package com.example.administrator.searchpicturetool.view;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/2/7 0007.
 */
public class RecommendContentViewHolder extends BaseViewHolder<Object> {
    private SimpleDraweeView simpleDraweeView1;
    private SimpleDraweeView simpleDraweeView2;
    private TextView title1;
    private TextView title2;
    private TextView content1;
    private TextView content2;
    public RecommendContentViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend);
        simpleDraweeView1=$(R.id.recomend_img1);
        simpleDraweeView2=$(R.id.recomend_img2);
        title1 =$(R.id.recommend_title1);
        title2 =$(R.id.recommend_title2);
        content1 =$(R.id.recommend_content1);
        content2 =$(R.id.recommend_content2);
    }

    @Override
    public void setData(Object data) {

        super.setData(data);
        if(data instanceof RecommendContent){
            RecommendContent recommendContent = (RecommendContent)data;
            simpleDraweeView1.setImageURI(Uri.parse(recommendContent.getImgUrl1()));
            simpleDraweeView2.setImageURI(Uri.parse(recommendContent.getImgUrl2()));
            title1.setText(recommendContent.getTitle1());
            title2.setText(recommendContent.getTitle2());
            content1.setText(recommendContent.getContent1());
            content2.setText(recommendContent.getContent2());
        }

    }
}
