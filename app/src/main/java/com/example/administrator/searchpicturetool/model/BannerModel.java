package com.example.administrator.searchpicturetool.model;

import android.content.Context;

import com.example.administrator.searchpicturetool.model.bean.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import rx.Subscriber;

/**
 * Created by wenhuaijun on 2016/2/15 0015.
 */
public class BannerModel {
    public static  void getBanners(Context context, final Subscriber<List<Banner>> subscriber){
        BmobQuery<Banner> query  = new BmobQuery<Banner>();
        query.findObjects(context, new FindListener<Banner>() {
            @Override
            public void onSuccess(List<Banner> list) {
                subscriber.onNext(list);
            }

            @Override
            public void onError(int i, String s) {
                subscriber.onError(new Throwable("on error"));
            }
        });
    };
}
