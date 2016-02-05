package com.example.administrator.searchpicturetool.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.searchpicturetool.config.MySql;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.model.bean.NetImageImpl;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/31 0031.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    public DBManager(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 添加已下载图片信息到数据库
     */
    public  void addHasDownload(DownloadImg img){
        db.beginTransaction();
        db.execSQL("insert into "+ MySql.DownloadTable+" values(null,?,?,?,?)",
                new Object[]{img.getName(),img.getLargUrl(),img.getHeight(),img.getWidth()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 将已删除的下载图片信息从数据库里删除
     */
    public void deleteHasDownload(String fileName){
        db.beginTransaction();
        db.execSQL("delete from "+MySql.DownloadTable+" where fileName =?",new Object[]{fileName});
      //  db.execSQL("delete from " + MySql.DownloadTable + " where fileName = " + "'" + fileName + "'");
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    /**
     * 批量删除已选中下载的图片
     */
    public void deleteDownloadPictures(ArrayList<DownloadImg> imgs){
        db.beginTransaction();
        for(DownloadImg img :imgs){
            new File(img.getName()).delete();
            db.execSQL("delete from "+MySql.DownloadTable+" where fileName =?",new Object[]{img.getName()});
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 查询所有下载的图片信息
     * @return
     */
    public ArrayList<DownloadImg> queryHasDownloadImgs(){
        ArrayList<DownloadImg> imgs = new ArrayList<DownloadImg>();

        Cursor cursor = db.rawQuery("select * from "+MySql.DownloadTable+" order by id desc",null);

        if (cursor!=null&&cursor.moveToFirst()){
            do{
                DownloadImg img = new DownloadImg();
                img.setName(cursor.getString(cursor.getColumnIndex("fileName")));
                img.setLargUrl(cursor.getString(cursor.getColumnIndex("largeImgUrl")));
                img.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
                img.setWidth(cursor.getInt(cursor.getColumnIndex("width")));
                imgs.add(img);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return imgs;

    }




    /**
     * 添加已收藏图片信息到数据库
     */
    public void addHasCollect(NetImage img){
        db.beginTransaction();
        db.execSQL("insert into "+MySql.CollectTable+" values(null,?,?,?,?)",
                new Object[]{img.getThumbImg(),img.getLargeImg(),img.getHeight(),img.getWidth()});
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    /**
     * 将已删除的收藏图片信息从数据库里删除
     */
    public void deleteHasCollect(String largeImgUrl){
        db.beginTransaction();
        db.execSQL("delete from "+MySql.CollectTable+" where largeImgUrl = ?",new Object[]{largeImgUrl});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 批量删除已选中下载的图片
     */
    public void deleteCollectPictures(ArrayList<NetImage> imgs){
        db.beginTransaction();
        for(NetImage img :imgs){
            db.execSQL("delete from "+MySql.CollectTable+" where largeImgUrl =?",new Object[]{img.getLargeImg()});
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 查询所有收藏的图片信息
     * @return
     */
    public ArrayList<NetImage> queryHasCollectImgs(){
        ArrayList<NetImage> imgs = new ArrayList<NetImage>();
        Cursor cursor = db.rawQuery("select * from "+MySql.CollectTable+" order by id desc",null);

        if (cursor!=null&&cursor.moveToFirst()){
            do{
                NetImageImpl netImage= new NetImageImpl();
                netImage.setThumbUrl(cursor.getString(cursor.getColumnIndex("smallImgUrl")));
                netImage.setLargeUrl(cursor.getString(cursor.getColumnIndex("largeImgUrl")));
                netImage.setThumb_height(cursor.getInt(cursor.getColumnIndex("height")));
                netImage.setThumb_width(cursor.getInt(cursor.getColumnIndex("width")));
                imgs.add(netImage);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return imgs;

    }

}
