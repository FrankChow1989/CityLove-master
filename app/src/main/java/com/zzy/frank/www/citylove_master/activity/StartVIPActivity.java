package com.zzy.frank.www.citylove_master.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.fragment.ChongzhiVIPScrollFragment;
import com.zzy.frank.www.citylove_master.fragment.HuafeiVIPScrollFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartVIPActivity extends AppCompatActivity
{

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    MainPagerAdapter mMainPagerAdapter;
    @Bind(R.id.id_startvip_viewpager)
    ViewPager idStartvipViewpager;
    @Bind(R.id.id_startvip_tablayout)
    TabLayout idStartvipTablayout;
    @Bind(R.id.id_startvip_kefu)
    SimpleDraweeView idStartvipKefu;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vip);
        ButterKnife.bind(this);

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

        idStartvipViewpager.setAdapter(mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));

        idStartvipTablayout.setupWithViewPager(idStartvipViewpager);

        Uri uri = Uri.parse("res://com.zzy.frank.www.citylove_master/" + R.drawable.button_kf);
        idStartvipKefu.setImageURI(uri);
    }

    @OnClick({R.id.id_open_s, R.id.id_open_month, R.id.id_startvip_kefu})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_open_s:
                break;
            case R.id.id_open_month:
                break;
            case R.id.id_startvip_kefu:
                Intent intent = new Intent(this, KefuChattingActivity.class);
                startActivity(intent);
                break;
        }
    }

    public class MainPagerAdapter extends FragmentPagerAdapter
    {

        public MainPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new ChongzhiVIPScrollFragment();
                default:
                    return new HuafeiVIPScrollFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "充值会员";
                default:
                    return "领话费会员";
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }
}
