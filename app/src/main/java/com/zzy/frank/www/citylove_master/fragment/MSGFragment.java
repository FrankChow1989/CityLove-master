package com.zzy.frank.www.citylove_master.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.MainActivity;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.ChattingActivity;
import com.zzy.frank.www.citylove_master.activity.KefuChattingActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.server.SendMsgORAddFriends;
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
public class MSGFragment extends Fragment implements SendMsgORAddFriends.onNewFriendListener, SendMsgORAddFriends.onNewMessageListener
{

    View view;
    //聊天列表
    @Bind(R.id.chatting_recy)
    ListViewCompat chattingRecy;
    // 用户集合
    List<User> mList;
    ChatAdapter chatAdapter;
    private PushApplication mApplication;

    //未读消息标志位
    private SlideView mLastSlideViewWithStatusOn;

    /**
     * 未读消息总数
     */
    int mUnReadedMsgs;

    @Override
    public void onNewFriend(User u)
    {
        mList.add(u);
        System.out.println("------------增加用户的mList大小------------:" + mList.size());
        chatAdapter.notifyDataSetChanged();
        chattingRecy.setSelection(mList.size() - 1);
    }

    @Override
    public void onNewMessage(ChatMessage message)
    {
        // 如果该用户已经有未读消息，更新未读消息的个数，并通知更新未读消息接口，最后notifyDataSetChanged

        System.out.println("------------增加message------------:" + message.isReaded());

        String userId = message.getUserId();
        if (mUserMessages.containsKey(userId))
        {
            mUserMessages.put(userId, mUserMessages.get(userId) + 1);
        } else
        {
            mUserMessages.put(userId, 1);
        }
        mUnReadedMsgs++;
        notifyUnReadedMsg();
        // 将新来的消息进行存储
        // 通知listview数据改变
        chatAdapter.notifyDataSetChanged();
        chattingRecy.setSelection(mList.size() - 1);

        System.out.println("---------------mUnReadedMsgs-------回调---------" + mUnReadedMsgs);
    }


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
        System.out.println("--------onCreate----------");
        mApplication = (PushApplication) this.getActivity().getApplication();
        chatAdapter = new ChatAdapter();
        mList = mApplication.getUserDB().getUser();
        // 获取数据库中所有的用户以及未读消息个数
        mUserMessages = mApplication.getMessageDB().getUserUnReadMsgs(
                mApplication.getUserDB().getUserIds());
        for (Integer val : mUserMessages.values())
        {
            mUnReadedMsgs += val;
        }
        mList = new ArrayList<>();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        System.out.println("--------onCreateView----------");
        view = inflater.inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, view);
        initViews();
        chattingRecy.setAdapter(chatAdapter);

        notifyUnReadedMsg();

        chattingRecy.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String userId = mList.get(position - 1).getUserId();
                mApplication.getMessageDB().updateReaded(userId);

                if (mUserMessages.containsKey(userId))
                {
                    Integer val = mUserMessages.get(userId);
                    mUnReadedMsgs -= val;
                    mUserMessages.remove(userId);
                    chatAdapter.notifyDataSetChanged();
                    //chattingRecy.setSelection(position);
                    notifyUnReadedMsg();
                    System.out.println("---------------mUnReadedMsgs-------点击---------" + mUnReadedMsgs);
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
        System.out.println("--------onResume----------");
        // 回调未读消息个数的更新
        notifyUnReadedMsg();
        // 设置新朋友的监听
        SendMsgORAddFriends.friendListeners.add(this);
        // 设置新消息的监听
        SendMsgORAddFriends.msgListeners.add(this);
        // 更新用户列表
        mList = mApplication.getUserDB().getUser();
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        System.out.println("--------onPause----------");
        /**
         * 当onPause时，取消监听
         */
        SendMsgORAddFriends.friendListeners.clear();
        SendMsgORAddFriends.msgListeners.clear();
    }

    private void initViews()
    {
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
                Intent intent = new Intent(getContext(), KefuChattingActivity.class);
                startActivity(intent);
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

    @OnClick(R.id.vip)
    public void onClick(View view)
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), VIPActivity.class);
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
            // 获取数据库中所有的用户以及未读消息个数
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
                    viewHolder.mBadgeView.setBadgeMargin(0, 0, 8, 0);
                    viewHolder.mBadgeView.setBadgeCount(mUserMessages.get(userId));
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
