package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.ChattingActivity;
import com.zzy.frank.www.citylove_master.activity.KefuChattingActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.adapter.ChatAdapter;
import com.zzy.frank.www.citylove_master.bean.ChatList;
import com.zzy.frank.www.citylove_master.bean.FuJinMM;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.util.SharePreferenceUtil;

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
public class MSGFragment extends Fragment
{

    View view;
    @Bind(R.id.chatting_recy)
    ListView chattingRecy;

    List<User> mList;
    ChatAdapter chatAdapter;
    private PushApplication mApplication;

    /**
     * 未读消息总数
     */
    private int mUnReadedMsgs;

    /**
     * 提供未读消息更新的回调，比如来了一个新消息或者用户点击查看某个用户的消息
     *
     * @author zhy
     */
    public interface OnUnReadMessageUpdateListener
    {
        void unReadMessageUpdate(int count);
    }

    /**
     * 存储userId-新来消息的个数
     */
    public Map<String, Integer> mUserMessages = new HashMap<String, Integer>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mApplication = (PushApplication) this.getActivity().getApplication();
        mList = mApplication.getUserDB().getUser();

        System.out.println("-----------size---------:" + mList.size());

        for (int i = 0; i < mList.size(); i++)
        {
            System.out.println("---------pic----------"+mList.get(i).getHeadIcon());
        }


        chatAdapter = new ChatAdapter(getContext(), mList);


        // 获取数据库中所有的用户以及未读消息个数
        mUserMessages = mApplication.getMessageDB().getUserUnReadMsgs(
                mApplication.getUserDB().getUserIds());
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, view);
        initViews();
        chattingRecy.setAdapter(chatAdapter);

        notifyUnReadedMsg();
        System.out.println("----------------:" + mUserMessages);

        chattingRecy.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChattingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // 更新用户列表
        mList = mApplication.getUserDB().getUser();
        chatAdapter.notifyDataSetChanged();
    }

    private void initViews()
    {
        mList = new ArrayList<>();

        View v = LayoutInflater.from(getContext()).inflate(R.layout.chatheadview, null);
        chattingRecy.addHeaderView(v);

        LinearLayout ly_hongniang = (LinearLayout) v.findViewById(R.id.id_chat_hongniang);
        LinearLayout ly_visit = (LinearLayout) v.findViewById(R.id.id_chat_visit);
        LinearLayout ly_guanzhu = (LinearLayout) v.findViewById(R.id.id_chat_guanzhu);

        ly_hongniang.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });

        ly_visit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), VisitActivity.class);
                startActivity(intent);
            }
        });

        ly_guanzhu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), VisitActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 回调未读消息个数
     */
    private void notifyUnReadedMsg()
    {
        if (getActivity() instanceof OnUnReadMessageUpdateListener)
        {
            OnUnReadMessageUpdateListener listener = (OnUnReadMessageUpdateListener) getActivity();
            listener.unReadMessageUpdate(mUnReadedMsgs);
        }
    }

    @OnClick({R.id.vip, R.id.bianji})
    public void onClick(View view)
    {
        Intent intent = new Intent();

        switch (view.getId())
        {
            case R.id.vip:

                intent.setClass(getActivity(), VIPActivity.class);

                break;
            case R.id.bianji:

                intent.setClass(getActivity(), KefuChattingActivity.class);
                break;
        }
        startActivity(intent);
    }
}
