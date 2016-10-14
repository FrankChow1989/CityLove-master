package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;

import java.util.List;

public class ChatMessageAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;
    private Context context;

    public ChatMessageAdapter(Context context, List<ChatMessage> datas)
    {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        ChatMessage msg = mDatas.get(position);
        return msg.isComing() ? 1 : 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatMessage chatMessage = mDatas.get(position);

        ViewHolder viewHolder = null;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            if (chatMessage.isComing())
            {
                convertView = mInflater.inflate(R.layout.main_chat_from_msg,
                        parent, false);

                viewHolder.createDate = (TextView) convertView
                        .findViewById(R.id.chat_from_createDate);
                viewHolder.content = (TextView) convertView
                        .findViewById(R.id.chat_from_content);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.chat_from_content_pic);
                viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative);
                viewHolder.relativeLayout_pic = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative_pic);
                viewHolder.relativeLayout_record = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative_record);

                viewHolder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
                viewHolder.length = convertView.findViewById(R.id.id_recorder_length);


                if ("1".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.VISIBLE);
                    viewHolder.relativeLayout_pic.setVisibility(View.GONE);
                    viewHolder.relativeLayout_record.setVisibility(View.GONE);

                    viewHolder.content.setText(chatMessage.getMessage());
                    viewHolder.createDate.setText(chatMessage.getDateStr());
                } else if ("2".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.GONE);
                    viewHolder.relativeLayout_pic.setVisibility(View.VISIBLE);
                    viewHolder.relativeLayout_record.setVisibility(View.GONE);

                    System.out.println("-----------type---2------------" + mDatas.get(position).getPic_msg());

                    Glide.with(context)
                            .load(mDatas.get(position).getPic_msg())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(viewHolder.imageView);


                    viewHolder.createDate.setText("20" + chatMessage.getDateStr());
                } else if ("3".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.GONE);
                    viewHolder.relativeLayout_pic.setVisibility(View.GONE);
                    viewHolder.relativeLayout_record.setVisibility(View.VISIBLE);




                }
                convertView.setTag(viewHolder);
            } else
            {
                convertView = mInflater.inflate(R.layout.main_chat_send_msg,
                        null);
                viewHolder.createDate = (TextView) convertView
                        .findViewById(R.id.chat_send_createDate);
                viewHolder.content = (TextView) convertView
                        .findViewById(R.id.chat_send_content);
                viewHolder.content.setText(chatMessage.getMessage());
                viewHolder.createDate.setText("20" + chatMessage.getDateStr());
                convertView.setTag(viewHolder);
            }

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//		Date date = chatMessage.getDate();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        return convertView;
    }

    private class ViewHolder
    {
        public RelativeLayout relativeLayout;
        public RelativeLayout relativeLayout_pic;
        public RelativeLayout relativeLayout_record;
        public TextView createDate;
        public TextView content;
        public ImageView imageView;
        TextView seconds;
        View length;
    }

}
