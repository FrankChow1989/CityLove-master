package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stfalcon.frescoimageviewer.ImageViewer;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.GirlsPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GirlsPhotoActivity extends AppCompatActivity
{
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.id_girls_photo_recy)
    RecyclerView idGirlsPhotoRecy;
    GirlsPhotoAdapter girlsPhotoAdapter;

    private static final String[] posters = {
            "https://pp.vk.me/c630619/v630619423/4637a/vAOodrqPzQM.jpg",
            "https://pp.vk.me/c630619/v630619423/46395/71QKIPW6BWM.jpg",
            "https://pp.vk.me/c630619/v630619423/46383/GOTf1IvHKoc.jpg",
            "https://pp.vk.me/c630619/v630619423/4638c/i1URx2fWj20.jpg",
            "https://pp.vk.me/c630619/v630619423/4639e/BPoHv4xEikA.jpg",
            "https://pp.vk.me/c630619/v630619423/463a7/9EjA0oqA_yQ.jpg",
            "https://pp.vk.me/c630619/v630619423/463b0/VLPAZQJ0kuI.jpg",
            "https://pp.vk.me/c630619/v630619423/463b9/O3-hk8kIvdY.jpg",
            "https://pp.vk.me/c630619/v630619423/463c2/WgtvE0FQwVY.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_photo);
        ButterKnife.bind(this);
        initViews();
        initEvents();

        girlsPhotoAdapter = new GirlsPhotoAdapter(posters,this);
        idGirlsPhotoRecy.setAdapter(girlsPhotoAdapter);

        girlsPhotoAdapter.setOnItemClickListener(new GirlsPhotoAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                new ImageViewer.Builder(GirlsPhotoActivity.this, posters)
                        .setStartPosition(position)
                        .show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {

            }
        });
    }

    private void initEvents()
    {

    }

    private void initViews()
    {
        toolBar.setTitle("我的相册");
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //设置RecyclerView的布局管理
        idGirlsPhotoRecy.setLayoutManager(layoutManager);
    }
}
