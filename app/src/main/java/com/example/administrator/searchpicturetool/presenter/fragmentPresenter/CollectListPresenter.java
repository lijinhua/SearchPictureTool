package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.searchpicturetool.app.APP;
import com.example.administrator.searchpicturetool.model.SqlModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.example.administrator.searchpicturetool.view.fragment.CollectFragment;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JFileManager;

import java.util.ArrayList;

import rx.functions.Action1;

/**
 * Created by Administrator on 2015/11/12 0012.
 */
public class CollectListPresenter extends BeamListFragmentPresenter<CollectFragment,NetImage> implements RecyclerArrayAdapter.OnItemClickListener{
    ArrayList<NetImage> netImages;
    @Override
    protected void onCreateView(CollectFragment view) {
        super.onCreateView(view);
        view.getListView().getRecyclerView().setHasFixedSize(false);
        view.getListView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
       /* JFileManager.Folder folder = JFileManager.getInstance().getFolder(APP.Dir.Object);
        netImages =(ArrayList<NetImage>)folder.readObjectFromFile("netImages");*/
        onRefresh();

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        SqlModel.getCollectImgs(getView().getContext()).subscribe(new Action1<ArrayList<NetImage>>() {
            @Override
            public void call(ArrayList<NetImage> imgs) {
                netImages = imgs;
                if (netImages == null || netImages.size() == 0) {
                    getView().getListView().showEmpty();
                }
                getRefreshSubscriber().onNext(netImages);
                getAdapter().setOnItemClickListener(CollectListPresenter.this);
            }
        });

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("netImages", netImages);
        intent.putExtra("hasCollected",true);
        intent.setClass(getView().getContext(), ShowLargeImgActivity.class);
        getView().startActivityForResult(intent,100);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==100){
            onRefresh();
        }
    }
}
