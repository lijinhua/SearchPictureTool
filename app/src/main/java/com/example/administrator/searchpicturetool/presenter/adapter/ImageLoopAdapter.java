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
    private String[] imageUrl={"http://img1.imgtn.bdimg.com/it/u=3783098954,3739904331&fm=21&gp=0.jpg"
    ,"http://img0.imgtn.bdimg.com/it/u=1576452016,3706819836&fm=21&gp=0.jpg"
            ,"http://img0.imgtn.bdimg.com/it/u=2076375700,2476864830&fm=206&gp=0.jpg"
            ,"http://img3.imgtn.bdimg.com/it/u=3680937390,3514330375&fm=206&gp=0.jpg"
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
