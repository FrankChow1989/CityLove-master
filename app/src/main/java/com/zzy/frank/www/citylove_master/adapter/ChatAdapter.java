package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jauker.widget.BadgeView;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ChatList;
import com.zzy.frank.www.citylove_master.bean.User;
import com.zzy.frank.www.citylove_master.ui.RoundImageView;

import java.util.List;

/**
 * Created by pc on 2016/9/19.
 */
public class ChatAdapter extends BaseAdapter
{
    private Context context;
    private List<User> mList;

    public ChatAdapter(Context context, List<User> mList)
    {
        this.context = context;
        this.mList = mList;
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
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chatlist, null);

            viewHolder.imageView = (RoundImageView) convertView.findViewById(R.id.id_chatlist_pic);
            viewHolder.name = (TextView) convertView.findViewById(R.id.id_chatlist_name);
            viewHolder.message = (TextView) convertView.findViewById(R.id.id_chatlist_msg);

            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context)
                .load(mList.get(position).getHeadIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);

        viewHolder.name.setText(mList.get(position).getNick());
        viewHolder.message.setText(mList.get(position).getLastMSG());

        if (viewHolder.mBadgeView == null)
        {
            viewHolder.mBadgeView = new BadgeView(context);
            viewHolder.mBadgeView.setTargetView(viewHolder.imageView);
            viewHolder.mBadgeView.setBadgeGravity(Gravity.TOP
                    | Gravity.RIGHT);
            viewHolder.mBadgeView.setBadgeMargin(0, 0, 0, 0);
            viewHolder.mBadgeView.setBadgeCount(10);
        }
        return convertView;
    }

    class ViewHolder
    {
        RoundImageView imageView;
        TextView name;
        TextView message;
        BadgeView mBadgeView;
    }
}
