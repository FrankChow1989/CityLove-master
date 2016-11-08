package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.HomemmItemActivity;
import com.zzy.frank.www.citylove_master.activity.SreachActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.adapter.HomeAdapter;
import com.zzy.frank.www.citylove_master.api.RxService;
import com.zzy.frank.www.citylove_master.bean.Grils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{

    View view;
    @Bind(R.id.gradview_home)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refreshlayout)
    SwipeRefreshLayout swipeRefreshlayout;
    private HomeAdapter mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getData();
        return view;
    }


    private void getData()
    {
        RxService.getJokeApi().getJoke()
                .subscribeOn(Schedulers.io())//在非UI线程中获取数据
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行更新UI
                .subscribe(new Observer<List<Grils>>()
                {
                    @Override
                    public void onCompleted()
                    {

                    }

                    @Override
                    public void onError(Throwable e)
                    {

                    }

                    @Override
                    public void onNext(final List<Grils> grils)
                    {

                        System.out.println("-------------onNext------------");

                        mAdapter = new HomeAdapter(grils, getContext());
                        mRecyclerView.setAdapter(mAdapter);

                        swipeRefreshlayout.setRefreshing(false);

                        mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(View view, int position)
                            {
                                //Toast.makeText(getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), HomemmItemActivity.class);
                                intent.putExtra("id", grils.get(position).getId());
                                intent.putExtra("pic", grils.get(position).getIcon());
                                intent.putExtra("name", grils.get(position).getNickname());
                                intent.putExtra("addr", grils.get(position).getAddr());
                                intent.putExtra("age",grils.get(position).getAge());
                                intent.putExtra("focus", grils.get(position).getAttention());
                                intent.putExtra("distance", grils.get(position).getDistance());

                                intent.putExtra("photos", grils.get(position).getPhotos());
                                intent.putExtra("space", grils.get(position).getSpace());
                                intent.putExtra("data", grils.get(position).getData());
                                intent.putExtra("info", grils.get(position).getInfo());
                                intent.putExtra("condition", grils.get(position).getCondition());

                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position)
                            {
                                Toast.makeText(getContext(), "LongClick" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void initViews()
    {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        //设置RecyclerView的布局管理
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshlayout.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.id_homemm_vip, R.id.id_homemm_sreach})
    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.id_homemm_vip:
                intent.setClass(getActivity(), VIPActivity.class);
                break;
            case R.id.id_homemm_sreach:
                intent.setClass(getActivity(), SreachActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onRefresh()
    {
        getData();
    }
}
