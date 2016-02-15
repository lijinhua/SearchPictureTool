package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.Banner;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.utils.JUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public class ImageLoopAdapter extends DynamicPagerAdapter {
    /*private String[] imageUrl={"http://img1.imgtn.bdimg.com/it/u=3783098954,3739904331&fm=21&gp=0.jpg"
    ,"http://img0.imgtn.bdimg.com/it/u=1576452016,3706819836&fm=21&gp=0.jpg"
            ,"http://img0.imgtn.bdimg.com/it/u=2076375700,2476864830&fm=206&gp=0.jpg"
            ,"http://img3.imgtn.bdimg.com/it/u=3680937390,3514330375&fm=206&gp=0.jpg"
    };*/
    private List<Banner> banners;
    public ImageLoopAdapter(List<Banner> banners) {
        this.banners =banners;
    }

    @Override
    public View getView(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_rollviewpager_, null);
        SimpleDraweeView mSimpleDraweeView =(SimpleDraweeView)view.findViewById(R.id.viewPager_img);
        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("search",banners.get(position).getSearchTip());
                Intent intent = new Intent();
                intent.putExtra("search", bundle);
                intent.setClass(container.getContext(), SearchActivity.class);
                container.getContext().startActivity(intent);
            }
        });
        mSimpleDraweeView.setImageURI(Uri.parse(banners.get(position).getImageUrl()));
        return view;
    }


    @Override
    public int getCount() {
        return banners.size();
    }
}
