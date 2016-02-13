package com.example.administrator.searchpicturetool.config;

import com.example.administrator.searchpicturetool.util.Utils;

/**
 * Created by Administrator on 2015/11/3 0003.
 */
public class API {

    public static final String imgPath= Utils.getSDPath()+"/MyPictures";
    public static final String baseUrl="http://pic.sogou.com";
    //笑话api
    public static final String JOY_TEXT = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
    public static final String JOY_IMAGE = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_pic";
    public static final String LAIFU_JOY_IMAGE="http://api.laifudao.com/open/tupian.json";
    public static class KEY {
        public static final String STATUS = "showapi_res_code";
        public static final String INFO = "showapi_res_error";
        public static final String DATA = "showapi_res_body";
    }

    public static class CODE {
        public static final int JOKE_SUCCEED = 0;
    }
    public class status{
        public static final int success=200;
        public static final int error=-1;
    }
}
