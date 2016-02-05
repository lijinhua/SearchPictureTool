package com.example.administrator.searchpicturetool.config;

import com.example.administrator.searchpicturetool.util.Utils;

/**
 * Created by Administrator on 2015/11/3 0003.
 */
public class API {

    public static final String imgPath= Utils.getSDPath()+"/MyPictures";
    public static final String baseUrl="http://pic.sogou.com";
    public class status{
        public static final int success=200;
        public static final int error=-1;
    }
}
