package com.example.administrator.searchpicturetool.presenter.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public class ImageLoopAdapter extends LoopPagerAdapter{
    private String[] imageUrl={"http://d.hiphotos.baidu.com/image/pic/item/c2cec3fdfc039245d5d7137b8494a4c27d1e2567.jpg"
    ,"http://g.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f697f8f081d30e924b899f36c.jpg"
    };
    public ImageLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_rollviewpager_, null);
        SimpleDraweeView mSimpleDraweeView =(SimpleDraweeView)view.findViewById(R.id.viewPager_img);
        mSimpleDraweeView.setImageURI(Uri.parse(imageUrl[position]));
        return view;
    }

    @Override
    protected int getRealCount() {
        return imageUrl.length;
    }
}
