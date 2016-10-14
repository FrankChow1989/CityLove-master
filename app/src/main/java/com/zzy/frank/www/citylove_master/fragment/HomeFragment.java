package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zzy.frank.www.citylove_master.MainActivity;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.HomemmItemActivity;
import com.zzy.frank.www.citylove_master.activity.SreachActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.adapter.HomeAdapter;
import com.zzy.frank.www.citylove_master.bean.Homemm;
import com.zzy.frank.www.citylove_master.util.VolleyInterface;
import com.zzy.frank.www.citylove_master.util.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{

    View view;
    @Bind(R.id.gradview_home)
    RecyclerView mRecyclerView;
    private List<Homemm> mList;
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
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://oetlj49uy.qnssl.com/home.txt";
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject obj)
                    {
                        System.out.println("---sssssssssssssss---:" + obj);
                        pasreData(obj);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.getMessage();
            }
        })
        {
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
            {
                JSONObject jsonObject;
                try
                {
                    jsonObject = new JSONObject(new String(response.data, "GBK"));
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                }
            }
        };
//        objRequest.setTag("obj");
        queue.add(objRequest);
    }

    private void pasreData(JSONObject obj)
    {
        try
        {
            JSONArray array = obj.getJSONArray("home");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject js = (JSONObject) array.opt(i);
                Homemm homemm = new Homemm();
                homemm.setId(js.getString("id"));
                homemm.setName(js.getString("nickname"));
                homemm.setPic(js.getString("pic"));

                mList.add(homemm);
            }

            mAdapter = new HomeAdapter(mList, getContext());
            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener()
            {
                @Override
                public void onItemClick(View view, int position)
                {
                    //Toast.makeText(getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), HomemmItemActivity.class);
                    intent.putExtra("id", mList.get(position).getId());
                    intent.putExtra("pic", mList.get(position).getPic());
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position)
                {
                    Toast.makeText(getContext(), "LongClick" + position, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void initViews()
    {
        mList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        //设置RecyclerView的布局管理
        mRecyclerView.setLayoutManager(layoutManager);

        //设置RecyclerView的Item间分割线
//        DividerItemDecoration decor = new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST);
//        mRecyclerView.addItemDecoration(decor);

//        for (int i = 0; i < 18; i++)
//        {
//            Homemm homemm = new Homemm();
//            homemm.setPic(R.drawable.a3);
//            homemm.setName("洋葱的眼泪|24岁");
//            mList.add(homemm);
//        }
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
}
