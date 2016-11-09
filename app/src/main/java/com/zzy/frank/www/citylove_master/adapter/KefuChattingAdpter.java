package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.bean.KeFuBean;
import com.zzy.frank.www.citylove_master.face.FaceConversionUtil;

import java.util.List;

/**
 * Created by pc on 2016/9/23.
 */
public class KefuChattingAdpter extends BaseAdapter
{

    private List<KeFuBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int tid;

    public KefuChattingAdpter(List<KeFuBean> mList, Context context)
    {
        this.mList = mList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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

        Holder holder = new Holder();
        //判断一下，是哪个的信息，不同的信息，使用不同的view,比较简单，不解释了
        if (mList.get(position).getState() == KeFuBean.JIESHOU)
        {
            convertView = layoutInflater.inflate(R.layout.main_chat_from_msg,
                    null);
            holder.time = (TextView) convertView.findViewById(R.id.chat_from_createDate);
            holder.contenTextView = (TextView) convertView
                    .findViewById(R.id.chat_from_content);
            holder.imageView = (ImageView) convertView.findViewById(R.id.chat_from_icon);

            convertView.setTag(holder);

        } else
        {
            convertView = layoutInflater.inflate(
                    R.layout.main_chat_send_msg, null);

            holder.time = (TextView) convertView.findViewById(R.id.chat_send_createDate);
            holder.contenTextView = (TextView) convertView
                    .findViewById(R.id.chat_send_content);
            holder.imageView = (ImageView) convertView.findViewById(R.id.chat_send_icon);

            convertView.setTag(holder);

        }

        holder.time.setText(mList.get(position).getDateStr());
        SpannableString spannableString = FaceConversionUtil.getInstace().getExpressionString(context, mList.get(position).getMessage());
        holder.contenTextView.setText(spannableString);

        return convertView;
    }

    static class Holder
    {
        public TextView time;
        public TextView contenTextView;
        public ImageView imageView;
    }
}
