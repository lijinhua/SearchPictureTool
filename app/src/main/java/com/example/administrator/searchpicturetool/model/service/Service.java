package com.example.administrator.searchpicturetool.model.service;

import com.example.administrator.searchpicturetool.model.SosoSearcher;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/1/25 0025.
 * 服务器接口
 */
public interface Service {
    @GET("http://pic.sogou.com/pics")
    Observable<SosoSearcher.SosoImage.WallImageResult> getImageList(
            @Query("reqType") String ajax,
            @Query("reqFrom") String result,
            @Query("query") String word,
            @Query("start" ) int page);
}
