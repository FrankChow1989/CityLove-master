package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.adapter.ScretAdapter;
import com.zzy.frank.www.citylove_master.bean.ScretItem;
import com.zzy.frank.www.citylove_master.bean.ScretMsg;
import com.zzy.frank.www.citylove_master.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScretVedioActivity extends AppCompatActivity
{

    @Bind(R.id.id_scretvedio_recy)
    ListView mRecylerView;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    private ScretAdapter mAdapter;
    private List<ScretItem> mList;

    List<ScretMsg> scretMsgs;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scret_vedio);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        toolBar.setTitle("Ta的私房视频");
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

        Intent intent = getIntent();

        mList = new ArrayList<>();
        scretMsgs = new ArrayList<>();

        for (int i = 0; i < 4; i++)
        {
            ScretMsg scretMsg = new ScretMsg();
            scretMsg.setSectname("日后再说");
            scretMsg.setSectmsg("出来吗？我就在你附近");
            scretMsgs.add(scretMsg);
        }

        for (int i = 0; i < 4; i++)
        {
            ScretItem scretItem = new ScretItem();
            scretItem.setIcon(intent.getStringExtra("icon"));
            scretItem.setNickname(intent.getStringExtra("name"));
            scretItem.setAddr(intent.getStringExtra("addr"));
            scretItem.setAge(intent.getStringExtra("age"));
            scretItem.setPic("https://oetlj49uy.qnssl.com/001.jpg");
            scretItem.setMsgs(scretMsgs);
            mList.add(scretItem);
        }

        mAdapter = new ScretAdapter(mList, this);
        mRecylerView.setAdapter(mAdapter);

    }
}
