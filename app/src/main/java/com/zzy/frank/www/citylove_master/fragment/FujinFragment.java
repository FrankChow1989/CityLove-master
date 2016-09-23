package com.zzy.frank.www.citylove_master.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.bean.FuJinMM;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FujinFragment extends Fragment
{
    View view;
    @Bind(R.id.fujin_list)
    ListView mListView;
    private List<FuJinMM> mList;
    private FujinAdapter mAdapter;
    private Map<Integer, Boolean> isSayHi = new HashMap<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_fujin, container, false);
        ButterKnife.bind(this, view);
        initViews();

        mAdapter = new FujinAdapter(mList, getContext());
        mListView.setAdapter(mAdapter);

        return view;
    }

    private void initViews()
    {
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            FuJinMM fuJinMM = new FuJinMM();
            fuJinMM.setId(i);
            fuJinMM.setPic(R.drawable.a3);
            fuJinMM.setTitle("花季少女");
            fuJinMM.setContent("27岁.上海市.162cm");
            fuJinMM.setFrom("3.22km");
            mList.add(fuJinMM);
            isSayHi.put(i, false);
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.id_fujin_vip, R.id.id_all_sayhi})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_fujin_vip:
                Intent intent = new Intent(getContext(), VIPActivity.class);
                startActivity(intent);
                break;
            case R.id.id_all_sayhi:
                for (int i = 0; i < mList.size(); i++)
                {
                    isSayHi.put(i, true);
                }
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 适配器
     */
    public class FujinAdapter extends BaseAdapter
    {

        private List<FuJinMM> mList;
        private Context context;
        LayoutInflater inflater;

        public FujinAdapter(List<FuJinMM> mList, Context context)
        {
            this.mList = mList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return mList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = null;
            FuJinMM fuJinMM = mList.get(position);

            //这个记录item的id用于操作isCheckedMap来更新CheckBox的状态
            final int id = fuJinMM.getId();

            System.out.println("----------id---------" + id);

            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_fujin, null);
                holder.name = (TextView) convertView.findViewById(R.id.id_item_fujinmm_title);
                holder.content = (TextView) convertView.findViewById(R.id.id_item_fujinmm_content);
                holder.from = (TextView) convertView.findViewById(R.id.id_item_fujinmm_from);
                holder.sayhi = (Button) convertView.findViewById(R.id.id_item_fujinmm_button);
                convertView.setTag(holder);
            } else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(mList.get(id).getTitle());
            holder.content.setText(mList.get(id).getContent());
            holder.from.setText(mList.get(id).getFrom());

            if (isSayHi.get(id) == true)
            {
                holder.sayhi.setText("已打过");
                holder.sayhi.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
                holder.sayhi.setTextColor(context.getResources().getColor(R.color.blacklight));
            }


            holder.sayhi.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    isSayHi.put(id, true);
                    notifyDataSetChanged();
                }
            });


            return convertView;
        }

        public final class ViewHolder
        {
            TextView name;
            TextView content;
            RoundImageView pic;
            TextView from;
            Button sayhi;
        }
    }

}
