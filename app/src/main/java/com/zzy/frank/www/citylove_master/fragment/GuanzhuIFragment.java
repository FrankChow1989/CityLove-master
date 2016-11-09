package com.zzy.frank.www.citylove_master.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.VIPUpdateActivity;
import com.zzy.frank.www.citylove_master.adapter.CommonAdapter;
import com.zzy.frank.www.citylove_master.bean.VisitCommon;
import com.zzy.frank.www.citylove_master.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class GuanzhuIFragment extends Fragment
{
    View view;
    @Bind(R.id.id_guanzhu_i_recy)
    RecyclerView idGuanzhuIRecy;

    List<VisitCommon> mList;
    CommonAdapter commonAdapter;
    @Bind(R.id.bt_guanzhu_VIPupdate)
    Button btGuanzhuVIPupdate;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_guanzhu_i, container, false);
        ButterKnife.bind(this, view);
        initView();
        commonAdapter = new CommonAdapter(mList, getContext());
        idGuanzhuIRecy.setAdapter(commonAdapter);

        return view;
    }

    private void initView()
    {
        mList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置RecyclerView的布局管理
        idGuanzhuIRecy.setLayoutManager(layoutManager);

        //设置RecyclerView的Item间分割线
        DividerItemDecoration decor = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST);
        idGuanzhuIRecy.addItemDecoration(decor);

        for (int i = 0; i < 10; i++)
        {
            VisitCommon common = new VisitCommon();
            common.setPic(R.drawable.h7);
            common.setName("小泥鳅");
            common.setContent("22岁.上海市.165cm");
            common.setFrom("5.95km");
            mList.add(common);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.bt_guanzhu_VIPupdate)
    public void onClick()
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), VIPUpdateActivity.class);
        startActivity(intent);
    }
}
