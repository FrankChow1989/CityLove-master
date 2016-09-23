package com.zzy.frank.www.citylove_master.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.fragment.GuanzhuIFragment;
import com.zzy.frank.www.citylove_master.fragment.IGuanzhuFragmen;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuanZhuActivity extends AppCompatActivity
{
    MainPagerAdapter mMainPagerAdapter;
    @Bind(R.id.id_tablayout)
    TabLayout tabLayout;
    @Bind(R.id.id_viewpager_guanzhu)
    ViewPager viewPager;
    @Bind(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_zhu);
        ButterKnife.bind(this);
        toolbar.setTitle("我的关注");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        viewPager.setAdapter(mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart()
    {
        super.onStart();
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
                    return new GuanzhuIFragment();
                default:
                    return new IGuanzhuFragmen();
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "关注我的";
                default:
                    return "我关注的";
            }
        }

        @Override
        public int getCount()
        {
            return 2;
        }
    }
}
