package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.GuanZhuActivity;
import com.zzy.frank.www.citylove_master.activity.PersonalInfoActivity;
import com.zzy.frank.www.citylove_master.activity.PhotoActivity;
import com.zzy.frank.www.citylove_master.activity.ShezhiActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.activity.ZhengyouIfActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment implements View.OnClickListener
{
    View view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.liner_zuijin_vist, R.id.liner_guanzhu, R.id.linear_personalinfo, R.id.linear_zhengyouif, R.id.linear_shenzhi, R.id.id_person_vip, R.id.linear_photo})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.liner_zuijin_vist:
                intent.setClass(getActivity(), VisitActivity.class);
                break;
            case R.id.liner_guanzhu:
                intent.setClass(getActivity(), GuanZhuActivity.class);
                break;
            case R.id.linear_personalinfo:
                intent.setClass(getActivity(), PersonalInfoActivity.class);
                break;
            case R.id.linear_zhengyouif:
                intent.setClass(getActivity(), ZhengyouIfActivity.class);
                break;
            case R.id.linear_shenzhi:
                intent.setClass(getActivity(), ShezhiActivity.class);
                break;
            case R.id.id_person_vip:
                intent.setClass(getActivity(), VIPActivity.class);
                break;
            case R.id.linear_photo:
                intent.setClass(getActivity(), PhotoActivity.class);
                break;
        }
        startActivity(intent);
    }
}
