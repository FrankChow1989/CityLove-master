package com.zzy.frank.www.citylove_master.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by pc on 2016/4/14.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
}
