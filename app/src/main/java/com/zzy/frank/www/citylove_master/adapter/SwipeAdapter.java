package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2016/10/17.
 */
public class SwipeAdapter extends BaseAdapter
{
    /**
     * 上下文对象
     */
    private Context mContext = null;
    private List<User> mList;
    private int mRightWidth = 0;
    /**
     * 存储userId-新来消息的个数
     */
    public Map<String, Integer> mUserMessages = new HashMap<String, Integer>();

    /**
     */
    public SwipeAdapter(Context ctx, List<User> data, int rightWidth, Map<String, Integer> mUserMessages)
    {
        mContext = ctx;
        this.mList = data;
        mRightWidth = rightWidth;
        this.mUserMessages = mUserMessages;
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        User user = mList.get(position);
        String userId = user.getUserId();
        // 获取数据库中所有的用户以及未读消息个数
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chatlist, null);
            viewHolder = new ViewHolder();

            viewHolder.item_left = (RelativeLayout) convertView.findViewById(R.id.item_left);
            viewHolder.item_right = (RelativeLayout) convertView.findViewById(R.id.item_right);

            viewHolder.iv_icon = (RoundImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);

            viewHolder.item_right_txt = (TextView) convertView.findViewById(R.id.item_right_txt);
            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load(user.getHeadIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.iv_icon);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        viewHolder.item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        viewHolder.item_right.setLayoutParams(lp2);

        viewHolder.tv_title.setText(user.getNick());
        viewHolder.tv_msg.setText(user.getLastMSG());

        System.out.println("--------------mUserMessages-----------adapter:" + mUserMessages);

        if (mUserMessages.containsKey(userId))
        {
            if (viewHolder.mBadgeView == null)
                viewHolder.mBadgeView = new BadgeView(mContext);
            viewHolder.mBadgeView.setTargetView(viewHolder.iv_icon);
            viewHolder.mBadgeView.setBadgeGravity(Gravity.TOP
                    | Gravity.RIGHT);
            viewHolder.mBadgeView.setBadgeMargin(0, 0, 0, 0);
            viewHolder.mBadgeView.setBadgeCount(mUserMessages.get(userId));
        } else
        {
            if (viewHolder.mBadgeView != null)
                viewHolder.mBadgeView.setVisibility(View.GONE);
        }

        viewHolder.item_right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mListener != null)
                {
                    mListener.onRightItemClick(v, position);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder
    {
        RelativeLayout item_left;
        RelativeLayout item_right;

        TextView tv_title;
        TextView tv_msg;
        TextView tv_time;
        RoundImageView iv_icon;
        TextView item_right_txt;

        BadgeView mBadgeView;
    }

    /**
     * 单击事件监听器
     */
    private onRightItemClickListener mListener = null;

    public void setOnRightItemClickListener(onRightItemClickListener listener)
    {
        mListener = listener;
    }

    public interface onRightItemClickListener
    {
        void onRightItemClick(View v, int position);
    }
}
