package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzy.frank.www.citylove_master.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VIPActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        ButterKnife.bind(this);

        toolBar.setTitle("VIP");
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
    }

    @OnClick({R.id.liner_vip, R.id.id_bt_kefu})
    public void onClick(View view)
    {
        Intent intent = new Intent();

        switch (view.getId())
        {
            case R.id.liner_vip:
                intent.setClass(this, StartVIPActivity.class);
                break;
            case R.id.id_bt_kefu:
                intent.setClass(this, KefuChattingActivity.class);
                break;
        }
        startActivity(intent);
    }
}
