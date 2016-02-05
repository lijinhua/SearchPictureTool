package com.example.administrator.searchpicturetool.util;
import android.os.Environment;
import com.example.administrator.searchpicturetool.app.APP;
import java.io.File;

/**
 * Created by Administrator on 2015/11/7 0007.
 */
public class Utils {
    public static String getSDPath(){
        File sdDir;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }else {
            sdDir =  APP.getInstance().getFilesDir();
        }
        return sdDir.toString();
    }


}
