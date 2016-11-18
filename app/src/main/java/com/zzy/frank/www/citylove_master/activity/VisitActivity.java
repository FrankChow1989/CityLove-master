package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.CommonAdapter;
import com.zzy.frank.www.citylove_master.bean.VisitCommon;
import com.zzy.frank.www.citylove_master.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitActivity extends AppCompatActivity
{


    CommonAdapter commonAdapter;

    List<VisitCommon> mList;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.visit_recy)
    RecyclerView visitRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        ButterKnife.bind(this);

        toolBar.setTitle("最近来访");
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
        initView();

        commonAdapter = new CommonAdapter(mList, this);
        visitRecy.setAdapter(commonAdapter);

    }

    private void initView()
    {
        mList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //设置RecyclerView的布局管理
        visitRecy.setLayoutManager(layoutManager);

        //设置RecyclerView的Item间分割线
        DividerItemDecoration decor = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST);
        visitRecy.addItemDecoration(decor);

        for (int i = 0; i < 8; i++)
        {
            VisitCommon visitCommon = new VisitCommon();
            visitCommon.setPic(R.drawable.h7);
            visitCommon.setName("思思");
            visitCommon.setContent("20岁.上海市.163cm");
            visitCommon.setFrom("3.12km");
            mList.add(visitCommon);
        }
    }

    @OnClick(R.id.bt_huiyuan_update)
    public void onClick()
    {
        Intent intent = new Intent();
        intent.setClass(this,VIPUpdateActivity.class);
        startActivity(intent);
    }
}
