package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.ScretMsg;

import java.util.List;

/**
 * Created by pc on 2016/11/24.
 */
public class CommentAdapter extends BaseAdapter
{
    private Context context;
    private List<ScretMsg> mList;

    public CommentAdapter(Context context, List<ScretMsg> mList)
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_scret_comment, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.id_scret_comment_name);
            viewHolder.msg = (TextView) convertView.findViewById(R.id.id_scret_comment_msg);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mList.get(position).getSectname());
        viewHolder.msg.setText(mList.get(position).getSectmsg());

        return convertView;
    }

    class ViewHolder
    {
        TextView name;
        TextView msg;
    }
}
