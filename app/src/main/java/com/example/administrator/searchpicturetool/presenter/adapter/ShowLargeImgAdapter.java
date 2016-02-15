package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.utils.JUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/8 0008.
 */
public  class ShowLargeImgAdapter extends PagerAdapter {
    private SimpleDraweeView simpleDraweeView;
    private ArrayList<NetImage> netImages;
    private Context context;
    private LayoutInflater inflater;
    private int screenHeight;
    private int screenWidth;
    private View view;
    ViewGroup.LayoutParams mLayoutParams;

    public ShowLargeImgAdapter(ArrayList<NetImage> netImages, Context context) {
        this.netImages = netImages;
        this.context = context;
        screenHeight = JUtils.getScreenHeight();
        screenWidth = JUtils.getScreenWidth();
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return netImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = inflater.inflate(R.layout.item_large_img, null);
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.photoView);
        mLayoutParams = simpleDraweeView.getLayoutParams();
        if (netImages.get(position).getWidth() != 0 && netImages.get(position).getHeight() != 0) {
           /* float mHeight = ((float) (netImages.get(position).getHeight()) / ((float) (netImages.get(position).getWidth()))) * screenWidth;
                mLayoutParams.width = screenWidth;
                mLayoutParams.height = (int) mHeight;*/
            if(netImages.get(position).getHeight()<=netImages.get(position).getWidth()){
                float mHeight = ((float) (netImages.get(position).getHeight()) / ((float) (netImages.get(position).getWidth()))) * screenWidth;
                mLayoutParams.width = screenWidth;
                mLayoutParams.height = (int)mHeight;
            }else{
                float mWidth =((float) (netImages.get(position).getWidth()) / ((float) (netImages.get(position).getHeight()))) * screenHeight;
                mLayoutParams.height = screenHeight;
                mLayoutParams.width = (int)mWidth;
            }

                simpleDraweeView.setLayoutParams(mLayoutParams);

        }else{
            mLayoutParams.width = screenWidth;
            mLayoutParams.height = screenHeight;
            simpleDraweeView.setLayoutParams(mLayoutParams);
        }
            simpleDraweeView.setImageURI(Uri.parse(netImages.get(position).getLargeImg()));
            container.addView(view);
            return view;
    }
}
