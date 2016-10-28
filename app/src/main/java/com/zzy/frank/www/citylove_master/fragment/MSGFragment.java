package com.zzy.frank.www.citylove_master.fragment;


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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.PushApplication;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.activity.ChattingActivity;
import com.zzy.frank.www.citylove_master.activity.KefuChattingActivity;
import com.zzy.frank.www.citylove_master.activity.VIPActivity;
import com.zzy.frank.www.citylove_master.activity.VisitActivity;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.server.SendMsgORAddFriends;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;
import com.zzy.frank.www.citylove_master.ui.SwipeListView;

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
    SwipeListView chattingRecy;
    // 用户集合
    List<User> mList;
    ChatAdapter chatAdapter;
    private PushApplication mApplication;

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

        String userId = message.getUserId();
        System.out.println("------------userID----------=========:" + userId);
        System.out.println("------------mUserMessages----------=========:" + mUserMessages);

        if (mUserMessages.containsKey(userId))
        {
            mUserMessages.put(userId, mUserMessages.get(userId) + 1);
            System.out.println("------------mUserMessages----------add1:" + mUserMessages);
        } else
        {
            System.out.println("------------mUserMessages----------add2:" + mUserMessages);
            mUserMessages.put(userId, 1);
        }

        mUnReadedMsgs++;
        notifyUnReadedMsg();
        // 将新来的消息进行存储
        // 通知listview数据改变
        chatAdapter.notifyDataSetChanged();
        chattingRecy.setSelection(mList.size() - 1);

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

       chatAdapter.setOnRightItemClickListener(new onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {

                Toast.makeText(getContext(), "删除第  " + (position+1)+" 对话记录",
                        Toast.LENGTH_SHORT).show();
            }
        });

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
                    chattingRecy.setSelection(position);
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
        System.out.println("--------onResume----------");
        // 回调未读消息个数的更新
        notifyUnReadedMsg();
        // 设置新朋友的监听
        SendMsgORAddFriends.friendListeners.add(this);
        // 设置新消息的监听
        SendMsgORAddFriends.msgListeners.add(this);
        // 更新用户列表
        mList = mApplication.getUserDB().getUser();
        mUserMessages = mApplication.getMessageDB().getUserUnReadMsgs(
                mApplication.getUserDB().getUserIds());

        for (Integer val : mUserMessages.values())
        {
            mUnReadedMsgs += val;
        }
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
            if (convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chatlist, null);
                viewHolder = new ViewHolder(convertView);

                convertView.setTag(viewHolder);

            } else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Glide.with(getContext())
                    .load(mList.get(position).getHeadIcon())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.imageView);

            viewHolder.tv_title.setText(mList.get(position).getNick());
            viewHolder.tv_msg.setText(mList.get(position).getLastMSG());

            System.out.println("------------mUserMessages----------adapter:" + mUserMessages);

            if (mUserMessages.containsKey(userId))
            {
                if (viewHolder.mBadgeView == null)
                    viewHolder.mBadgeView = new BadgeView(mApplication);
                viewHolder.mBadgeView.setTargetView(viewHolder.imageView);
                viewHolder.mBadgeView.setBadgeGravity(Gravity.TOP
                        | Gravity.RIGHT);
                viewHolder.mBadgeView.setBadgeMargin(0, 0, 0, 0);
                viewHolder.mBadgeView.setBadgeCount(mUserMessages.get(userId));
            } else
            {
                if (viewHolder.mBadgeView != null)
                    viewHolder.mBadgeView.setVisibility(View.GONE);
            }

            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            viewHolder.item_left.setLayoutParams(lp1);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(chattingRecy.getRightViewWidth(), LinearLayout.LayoutParams.MATCH_PARENT);
            viewHolder.item_right.setLayoutParams(lp2);

            viewHolder.item_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRightItemClick(v, position);
                    }
                }
            });

            return convertView;
        }

        class ViewHolder
        {
            RoundImageView imageView;
            LinearLayout item_left;
            LinearLayout item_right;

            TextView tv_title;
            TextView tv_msg;
            TextView tv_time;

            TextView item_right_txt;
            BadgeView mBadgeView;

            public ViewHolder(View view)
            {
                imageView = (RoundImageView) view.findViewById(R.id.iv_icon);
                item_left = (LinearLayout)view.findViewById(R.id.item_left);
                item_right = (LinearLayout)view.findViewById(R.id.item_right);

                tv_title = (TextView)view.findViewById(R.id.tv_title);
                tv_msg = (TextView)view.findViewById(R.id.tv_msg);
                tv_time = (TextView)view.findViewById(R.id.tv_time);

                item_right_txt = (TextView)view.findViewById(R.id.item_right_txt);
            }
        }

        /**
         * 单击事件监听器
         */
        private onRightItemClickListener mListener = null;

        public void setOnRightItemClickListener(onRightItemClickListener listener){
            mListener = listener;
        }
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }
}
