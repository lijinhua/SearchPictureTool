package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.ViewPager;

import com.example.administrator.searchpicturetool.app.APP;
import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.model.SaveBitmapModel;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.model.WrapperModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.util.Utils;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.example.administrator.searchpicturetool.presenter.adapter.ShowLargeImgAdapter;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.jude.beam.bijection.Presenter;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;

import java.io.File;
import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by Administrator on 2015/11/4 0004.
 */
public class ShowLargeImgActivityPresenter extends Presenter<ShowLargeImgActivity> implements ViewPager.OnPageChangeListener{
    private NetImage netImage;
    int currentPosition=0;
    private ArrayList<NetImage> netImages;
    ShowLargeImgAdapter adapter;
    /**
     * -1 初始化
     * 0 下载图片
     * 1 分享图片
     * 3 设为壁纸
     * 4 设为锁屏
     */
    private int state =-1;

    @Override
    protected void onCreateView(ShowLargeImgActivity view) {
        super.onCreateView(view);
        netImages =(ArrayList<NetImage>)view.getIntent().getSerializableExtra("netImages");
        currentPosition =view.getIntent().getIntExtra("position", 0);
        adapter  = new ShowLargeImgAdapter(netImages,getView());
        getView().getViewPager().setAdapter(adapter);
        getView().getViewPager().setCurrentItem(currentPosition);
        getView().getViewPager().addOnPageChangeListener(this);
        getView().getPg_tv().setText((currentPosition + 1) + "/" + netImages.size());
    }
    public void savePicture(){
        state=0;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }
    public void collectPicture(){
        SqlModel.addCollectImg(getView(),netImages.get(currentPosition));
        JUtils.Toast("已收藏");
        /*JFileManager.Folder folder = JFileManager.getInstance().getFolder(APP.Dir.Object);
        ArrayList<NetImage> mNetImages =(ArrayList<NetImage>)folder.readObjectFromFile("netImages");
        if (mNetImages==null){
            mNetImages = new ArrayList<NetImage>();
        }
        mNetImages.add(netImages.get(currentPosition));
        folder.writeObjectToFile(mNetImages, "netImages");*/

    }
    public void requestCollectPicture(){
        SqlModel.deleteCollectImgByUrl(getView(),netImages.get(currentPosition).getLargeImg());
        getView().setResult(100);
        getView().finish();
    }

    public void sharePicture(){
        state=1;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }
    public void setWallWrapper(){
        state=3;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }
    public void setLockWrapper(){
        state=4;
        downloadBitmap(getView(), netImages.get(currentPosition).getLargeImg());
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getView().getPg_tv().setText((position+1)+"/"+netImages.size());
        currentPosition=position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getPosition(){
        return currentPosition;
    }

    public void downloadBitmap( final Context context, String url){
        SaveBitmapModel.getFrescoDownloadBitmap(context, url).subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(final Bitmap bitmap) {
                if(state==0||state==1){
                    SaveBitmapModel.getSaveBitmapObservable(bitmap).subscribe(saveSubscriber);
                }else if (state==3){
                    //设置桌面壁纸
                    WrapperModel.getSetWallWrapperObservable(bitmap, context).subscribe(callbackSubscriber);

                }else if(state==4){
                    //设置锁屏壁纸
                    WrapperModel.getSetLockWrapperObservable(bitmap,context).subscribe(callbackSubscriber);
                }
            }
            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                JUtils.Toast("操作失败！");
            }
        }, CallerThreadExecutor.getInstance());

    }
    //保存图片后的观察者
    Action1<String> saveSubscriber = new Action1<String>() {
        @Override
        public void call(String path) {
            if(!path.equals(API.status.error)){
                if(state==0) {
                    JUtils.Toast("下载图片成功，已下载到SdCard的MyPictures目录里");
                    //保存到数据库
                    SqlModel.addDownloadImg(getView(),netImages.get(currentPosition),path);
                }
                if(state==1){
                   // startShareImg(path);
                    Utils.startShareImg(path,getView());
                }
            }else{
                JUtils.Toast("未知错误");
            }
        }
    };
    //设置事件发生后的消费该事件的观察者
    Action1<Integer> callbackSubscriber = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            if(integer.intValue()== API.status.success){
                JUtils.Toast("设置成功！");
            }else{
                JUtils.Toast("设置失败...");
            }
        }
    };
  /*  private void startShareImg(String path){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image*//*");
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        getView().startActivity(Intent.createChooser(shareIntent, "请选择"));
    }*/

}
