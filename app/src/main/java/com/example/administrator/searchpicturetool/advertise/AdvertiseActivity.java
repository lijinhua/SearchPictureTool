package com.example.administrator.searchpicturetool.advertise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;


/**
 * Created by Administrator on 2016/1/18 0018.
 * @deprecated
 */
public class AdvertiseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdManager.getInstance(this).init("2c06c50a35fbdcb3", "8a1191fc53d374ce", false);
        OffersManager.getInstance(this).onAppLaunch();
        OffersManager.getInstance(this).showOffersWall();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OffersManager.getInstance(this).onAppExit();
    }
}
