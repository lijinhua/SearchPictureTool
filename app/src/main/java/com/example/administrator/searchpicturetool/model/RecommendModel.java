package com.example.administrator.searchpicturetool.model;

import android.content.Context;

import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.util.RecommendComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by wenhuaijun on 2016/2/12 0012.
 * 目前给推荐列表提供测试数据
 */
public class RecommendModel {
   public static ArrayList<Object> getRecommends(){
       ArrayList<Object> recommends = new ArrayList<>();
       recommends.add(new RecommendTip(0,"热门搜索"));
       recommends.add(new RecommendTip(1,"手机壁纸"));
       recommends.add(new RecommendTip(2,"美女"));
       recommends.add(new RecommendTip(3,"其它"));
       recommends.add(new RecommendContent(0.1,
               "http://www.ghowim.com/XTZT/UploadPic/2014-3/20143623342116857.jpg"
               ,"巨乳美女","这里的妹纸有“胸器”"
               ,"http://img002.21cnimg.com/photos/album/20140426/m600/2B0FC607532B0E059D4F6B08DF946C02.jpg"
               ,"二次元","海量二次元图片在这里"
       ));
       recommends.add(new RecommendContent(1.1,
               "http://img3.imgtn.bdimg.com/it/u=3415496284,3224842209&fm=206&gp=0.jpg"
               ,"唯美壁纸","柔和唯美的手机壁纸"
               ,"http://h.hiphotos.baidu.com/image/h%3D200/sign=5cfadaf331d12f2ed105a9607fc2d5ff/d0c8a786c9177f3e401f472977cf3bc79f3d56ee.jpg"
               ,"风景壁纸","有山有水有佳人"
       ));
       recommends.add(new RecommendContent(2.1,
               "http://c.hiphotos.baidu.com/image/h%3D300/sign=abaceeaf6309c93d18f208f7af3cf8bb/aa64034f78f0f736cd78e8a00e55b319eac41388.jpg"
               ,"丝袜美女","各种丝袜美女图片应有竟有"
               ,"http://img2.imgtn.bdimg.com/it/u=1053087850,3362135948&fm=21&gp=0.jpg"
               ,"制服诱惑","千万别点开，不然hold不住"
       ));
       recommends.add(new RecommendContent(3.1,
               "http://img4.imgtn.bdimg.com/it/u=2641000571,2074706793&fm=21&gp=0.jpg"
               ,"暴走漫画","小伙伴们都惊呆了"
               ,"http://imgsrc.baidu.com/forum/w=580/sign=2cd6648a3c12b31bc76ccd21b6193674/e2f4b31bb051f819d943fb87dfb44aed2c73e784.jpg"
               ,"斗图","斗图表情包统统搜罗"
       ));
       //根据推荐的类型对数据进行排序
       RecommendComparator comparator  = new RecommendComparator();
       Collections.sort(recommends,comparator);
       return  recommends;
   }
    public static ArrayList<Object> getRecommends2(final Context context, final Subscriber<ArrayList<Object>> subscriber){
        final ArrayList<Object> recommends = new ArrayList<>();
        BmobQuery<RecommendTip> queryTip = new BmobQuery<>();
        queryTip.findObjects(context, new FindListener<RecommendTip>() {
            @Override
            public void onSuccess(List<RecommendTip> list) {
                recommends.addAll(list);
                BmobQuery<RecommendContent> queryContent = new BmobQuery<>();
                queryContent.findObjects(context, new FindListener<RecommendContent>() {
                    @Override
                    public void onSuccess(List<RecommendContent> list) {
                        recommends.addAll(list);
                        RecommendComparator comparator  = new RecommendComparator();
                        Collections.sort(recommends, comparator);
                        subscriber.onNext(recommends);
                    }

                    @Override
                    public void onError(int i, String s) {
                        subscriber.onError(new Throwable("query error"));
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                subscriber.onError(new Throwable("query error"));
            }
        });
        return null;
    }
}
