package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.widght.PhotoView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/13 0013.
 */
public class ShowDownloadLargeImgAdapter extends PagerAdapter{
    private View view;
    private ArrayList<DownloadImg> downloadImgs;
    private Context context;
    private LayoutInflater inflater;
    private int screenHeight;
    private int screenWidth;
    private SimpleDraweeView simpleDraweeView;
    ViewGroup.LayoutParams mLayoutParams;
    public ShowDownloadLargeImgAdapter(ArrayList<DownloadImg> downloadImgs,Context context) {
        this.downloadImgs = downloadImgs;
        this.context=context;
        screenHeight = JUtils.getScreenHeight();
        screenWidth = JUtils.getScreenWidth();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return downloadImgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
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
        if(downloadImgs.get(position).getHeight()<=downloadImgs.get(position).getWidth()){
            float mHeight = ((float) (downloadImgs.get(position).getHeight()) / ((float) (downloadImgs.get(position).getWidth()))) * screenWidth;
            mLayoutParams.width = screenWidth;
            mLayoutParams.height = (int)mHeight;
        }else{
            float mWidth =((float) (downloadImgs.get(position).getWidth()) / ((float) (downloadImgs.get(position).getHeight()))) * screenHeight;
            mLayoutParams.height = screenHeight;
            mLayoutParams.width = (int)mWidth;
        }
        simpleDraweeView.setLayoutParams(mLayoutParams);
        simpleDraweeView.setImageURI(Uri.fromFile(new File(downloadImgs.get(position).getName())));
     //   simpleDraweeView.setImageBitmap(BitmapFactory.decodeFile(downloadImgs.get(position).getName()));
        container.addView(view);
        return  view;
    }
}
