package com.example.administrator.searchpicturetool.view.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.ShowLargeImgActivityPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Administrator on 2015/11/4 0004.
 */
@RequiresPresenter(ShowLargeImgActivityPresenter.class)
public class ShowLargeImgActivity extends BeamBaseActivity<ShowLargeImgActivityPresenter> implements OnMenuItemClickListener,
        OnMenuItemLongClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.large_page)
    TextView pg_tv;
    @InjectView(R.id.large_viewPager)
    ViewPager viewPager;
    FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private boolean hasCollected =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_large_img);
        ButterKnife.inject(this);
        onSetToolbar(toolbar);
        toolbar.setTitle("");
        hasCollected = getIntent().getBooleanExtra("hasCollected", false);
        fragmentManager = getSupportFragmentManager();
        initMenuFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            setResult(200,new Intent().putExtra("position",getPresenter().getPosition()));
            super.onBackPressed();

        }
    }


    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(true);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.ic_request);

        MenuObject send = new MenuObject("下载图片");
        send.setResource(R.drawable.ic_download);
        MenuObject addFr = new MenuObject("分享图片");
        addFr.setResource(R.drawable.ic_share);
        MenuObject addFav;
        if(hasCollected){
            JUtils.Log("取消收藏");
            addFav = new MenuObject("取消收藏");
        }else{
            addFav = new MenuObject("收藏图片");
        }
        addFav.setResource(R.drawable.ic_collect);
        MenuObject block3 = new MenuObject("剪辑图片");
        block3.setResource(R.drawable.ic_cut);
        MenuObject block = new MenuObject("设为桌面背景");
        block.setResource(R.drawable.ic_wrapper);
        MenuObject block2 = new MenuObject("设为锁屏背景");
        block2.setResource(R.drawable.ic_lock);

        menuObjects.add(close);
        menuObjects.add(send);
      //  menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(block3);
        menuObjects.add(block);
        menuObjects.add(block2);
        menuObjects.add(addFav);
       /* for(MenuObject object :menuObjects){
            object.setBgColor(Color.parseColor("#303F9F"));
        }*/
        return menuObjects;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch(position){
            case 1:
                //下载图片
                getPresenter().savePicture();
                break;
            case 2:
                //保存图片
                getPresenter().sharePicture();
                break;
            case 3:
                //剪辑图片
                JUtils.Toast("下个版本开放，敬请期待");
                break;
            case 4:
                //设置为桌面壁纸
                getPresenter().setWallWrapper();
                break;
            case 5:
                //设置为锁屏壁纸
                getPresenter().setLockWrapper();
                break;
            case 6:
                //收藏或取消收藏图片
                if(!hasCollected){
                    getPresenter().collectPicture();
                }else{
                    getPresenter().requestCollectPicture();
                }
                break;

        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
    //    JUtils.Toast("Long clicked on position " + position);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
    public TextView getPg_tv(){
        return pg_tv;
    }
    public void setHasCollected(boolean hasCollected) {
        this.hasCollected = hasCollected;
    }
}
