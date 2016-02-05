package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.NetImage;

import java.util.HashMap;

/**
 * Created by Mr.Jude on 2015/2/23.
 */
public interface SearcherConstructor {
    public HashMap<String,String> getHeader();
    public String getUrl(String word, int page);
    public NetImage[] getImageList(String response);
}
