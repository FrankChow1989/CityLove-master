package com.zzy.frank.www.citylove_master.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzy.frank.www.citylove_master.R;

import butterknife.ButterKnife;

public class IGuanzhuFragmen extends Fragment
{

    View view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_iguanzhu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
