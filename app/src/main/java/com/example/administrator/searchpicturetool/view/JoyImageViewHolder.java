package com.example.administrator.searchpicturetool.view;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

/**
 * Created by Administrator on 2016/2/13 0013.
 */
public class JoyImageViewHolder extends BaseViewHolder<ImageJoy>{
    private SimpleDraweeView simpleDraweeView;
    private TextView title;
    float width;
    float height;
    float sccrenWidth;
    ViewGroup.LayoutParams layoutParams;
    public JoyImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_joyimage);
        simpleDraweeView =$(R.id.joyImg);
        title = $(R.id.joy_title);
        sccrenWidth = JUtils.getScreenWidth();
    }

    @Override
    public void setData(ImageJoy data) {
        super.setData(data);
        height =Float.valueOf(data.getHeight());
        width = Float.valueOf(data.getWidth());
        layoutParams= simpleDraweeView.getLayoutParams();
        layoutParams.height= (int)((height/width)*sccrenWidth);
        simpleDraweeView.setLayoutParams(layoutParams);
        title.setText(data.getTitle());
        simpleDraweeView.setImageURI(Uri.parse(data.getThumburl()));
    }
}
