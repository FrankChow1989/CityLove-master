package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ScrollData;
import com.zzy.frank.www.citylove_master.ui.TimeTaskScroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VIPUpdateActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.listview_autoscroll)
    ListView listviewAutoscroll;
    private List<ScrollData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipupdate);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {

        list = new ArrayList<>();

        toolBar.setTitle("开通VIP");
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

        for (int i = 0; i < 30; i++)
        {
            ScrollData scrollData = new ScrollData();
            scrollData.setName("低调");
            scrollData.setMessage("刚刚领取了 90元 话费");
            list.add(scrollData);
        }

        new Timer().schedule(new TimeTaskScroll(this, listviewAutoscroll, list), 20, 20);

    }
}
