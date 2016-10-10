package com.zzy.frank.www.citylove_master.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.ChattingActivity;
import com.zzy.frank.www.citylove_master.activity.KefuChattingActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.ui.ListViewCompat;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;
import com.zzy.frank.www.citylove_master.ui.SlideView;

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
    ListViewCompat chattingRecy;

    List<User> mList;
    ChatAdapter chatAdapter;
    private PushApplication mApplication;

    private SlideView mLastSlideViewWithStatusOn;

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
        // 获取数据库中所有的用户以及未读消息个数
        mUserMessages = mApplication.getMessageDB().getUserUnReadMsgs(
                mApplication.getUserDB().getUserIds());

        for (Integer val : mUserMessages.values()) {
            mUnReadedMsgs += val;
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, view);
        initViews();
        mList = mApplication.getUserDB().getUser();
        chatAdapter = new ChatAdapter();
        chattingRecy.setAdapter(chatAdapter);

        notifyUnReadedMsg();

        chattingRecy.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String userId = mList.get(position - 1).getUserId();
                //未读消息更新为已经读取

                System.out.println("-------userid-----:" + userId);

                mApplication.getMessageDB().updateReaded(userId);
                System.out.println("---------unread-------:" + mApplication.getMessageDB().getUnreadedMsgsCountByUserId(userId));

                chattingRecy.setAdapter(chatAdapter);

                if (mUserMessages.containsKey(userId))
                {
                    Integer val = mUserMessages.get(userId);
                    mUnReadedMsgs -= val;
                    mUserMessages.remove(userId);
                    chatAdapter.notifyDataSetChanged();
                    notifyUnReadedMsg();
                }

                Intent intent = new Intent();
                intent.putExtra("userid", mList.get(position - 1).getUserId());
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

    private class ChatAdapter extends BaseAdapter
    {
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
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            User user = mList.get(position);
            String userId = user.getUserId();

            ViewHolder viewHolder;
            SlideView slideView = (SlideView) convertView;

            if (slideView == null)
            {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_chatlist, null);
                slideView = new SlideView(getContext());
                slideView.setContentView(view);

                viewHolder = new ViewHolder(slideView);
                slideView.setOnSlideListener(new SlideView.OnSlideListener()
                {
                    @Override
                    public void onSlide(View view, int status)
                    {
                        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view)
                        {
                            mLastSlideViewWithStatusOn.shrink();
                        }

                        if (status == SLIDE_STATUS_ON)
                        {
                            mLastSlideViewWithStatusOn = (SlideView) view;
                        }
                    }
                });

                slideView.setTag(viewHolder);

            } else
            {
                viewHolder = (ViewHolder) slideView.getTag();
            }

            user.slideView = slideView;
            user.slideView.shrink();

            Glide.with(getContext())
                    .load(mList.get(position).getHeadIcon())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.imageView);

            viewHolder.name.setText(mList.get(position).getNick());
            viewHolder.message.setText(mList.get(position).getLastMSG());

            viewHolder.deleteHolder.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mList.remove(position);
                    mApplication.getUserDB().updateUser(mList);
                    chatAdapter.notifyDataSetChanged();
                }
            });


            if (mUserMessages.containsKey(userId))
            {
                if (viewHolder.mBadgeView == null)
                {
                    viewHolder.mBadgeView = new BadgeView(getContext());
                    viewHolder.mBadgeView.setTargetView(viewHolder.imageView);
                    viewHolder.mBadgeView.setBadgeGravity(Gravity.TOP
                            | Gravity.RIGHT);
                    viewHolder.mBadgeView.setBadgeMargin(0, 0, 0, 0);
                    viewHolder.mBadgeView.setBadgeCount(mUserMessages.get(userId));
                    //viewHolder.mBadgeView.setBadgeCount(0);
                }
            } else
            {
                if (viewHolder.mBadgeView != null)
                    viewHolder.mBadgeView.setVisibility(View.GONE);
            }

            return slideView;
        }

        class ViewHolder
        {
            RoundImageView imageView;
            TextView name;
            TextView message;
            BadgeView mBadgeView;
            public ViewGroup deleteHolder;

            public ViewHolder(View view)
            {
                imageView = (RoundImageView) view.findViewById(R.id.id_chatlist_pic);
                name = (TextView) view.findViewById(R.id.id_chatlist_name);
                message = (TextView) view.findViewById(R.id.id_chatlist_msg);
                deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
            }
        }
    }
}
