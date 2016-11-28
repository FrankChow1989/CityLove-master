package com.zzy.frank.www.citylove_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zzy.frank.www.citylove_master.R;
import com.zzy.frank.www.citylove_master.Recorder.MediaManager;
import com.zzy.frank.www.citylove_master.activity.ChattingSeePicActivity;
import com.zzy.frank.www.citylove_master.bean.ChatMessage;
import com.zzy.frank.www.citylove_master.face.FaceConversionUtil;
import com.zzy.frank.www.citylove_master.util.T;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;
    private Context context;

    View mAnimView;

    ArrayList<String> mPicUrls;

    private int mMinItemWidth;
    private int mMaxItemWidth;

    SharedPreferences sp;

    public ChatMessageAdapter(Context context, List<ChatMessage> datas)
    {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mPicUrls = new ArrayList<>();

        System.out.println("-------mData-------:" + mDatas.size());
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);

        sp = context.getSharedPreferences("user", context.MODE_PRIVATE);
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ChatMessage chatMessage = mDatas.get(position);
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
                viewHolder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.chat_from_content_pic);
                viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative);
                viewHolder.relativeLayout_pic = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative_pic);
                viewHolder.relativeLayout_record = (RelativeLayout) convertView.findViewById(R.id.chat_from_relative_record);
                viewHolder.head_pic1 = (SimpleDraweeView) convertView.findViewById(R.id.chat_from_icon);
                viewHolder.head_pic2 = (SimpleDraweeView) convertView.findViewById(R.id.chat_from_icon_pic);
                viewHolder.head_pic3 = (SimpleDraweeView) convertView.findViewById(R.id.chat_from_icon_record);

                viewHolder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
                viewHolder.length = convertView.findViewById(R.id.id_recorder_length);

                if ("0".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.VISIBLE);
                    viewHolder.relativeLayout_pic.setVisibility(View.GONE);
                    viewHolder.relativeLayout_record.setVisibility(View.GONE);

                    Uri uri = Uri.parse("res://com.zzy.frank.www.citylove_master/" + mDatas.get(position).getIcon());
                    viewHolder.head_pic1.setImageURI(uri);
                    viewHolder.content.setText(chatMessage.getMessage());
                    viewHolder.createDate.setText(chatMessage.getDateStr());
                } else if ("1".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.GONE);
                    viewHolder.relativeLayout_pic.setVisibility(View.VISIBLE);
                    viewHolder.relativeLayout_record.setVisibility(View.GONE);

                    Uri uri1 = Uri.parse("res://com.zzy.frank.www.citylove_master/" + mDatas.get(position).getIcon());
                    viewHolder.head_pic2.setImageURI(uri1);

                    Uri uri = Uri.parse(mDatas.get(position).getPic_msg());
                    viewHolder.imageView.setImageURI(uri);

                    viewHolder.imageView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            T.showShort(context, "----------图片点击-----------" + position);

                            Intent intent = new Intent(context, ChattingSeePicActivity.class);
                            intent.putExtra("pic", chatMessage.getPic_msg());
                            context.startActivity(intent);

                        }
                    });
                    viewHolder.createDate.setText(chatMessage.getDateStr());
                } else if ("2".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.GONE);
                    viewHolder.relativeLayout_pic.setVisibility(View.GONE);
                    viewHolder.relativeLayout_record.setVisibility(View.VISIBLE);

                    Uri uri = Uri.parse("res://com.zzy.frank.www.citylove_master/" + mDatas.get(position).getIcon());
                    viewHolder.head_pic3.setImageURI(uri);

                    viewHolder.seconds.setText(3 + "\"");
                    ViewGroup.LayoutParams lp = viewHolder.length.getLayoutParams();
                    lp.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f * 3));

                    viewHolder.length.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {

                            if (mAnimView != null)
                            {
                                mAnimView = null;
                            }

                            mAnimView = v.findViewById(R.id.id_recorder_anim);
                            mAnimView.setBackgroundResource(R.drawable.anim_recorder_play);
                            AnimationDrawable anim = (AnimationDrawable) mAnimView
                                    .getBackground();
                            anim.start();

                            T.showShort(context, "-------------语音点击-------------");
                            MediaManager.playSound(context, chatMessage.getRecord_path(),
                                    new MediaPlayer.OnCompletionListener()
                                    {
                                        @Override
                                        public void onCompletion(MediaPlayer mp)
                                        {
                                            mAnimView.setBackgroundResource(R.drawable.rc_ic_voice_receive);
                                        }
                                    });
                        }
                    });

                    viewHolder.createDate.setText(chatMessage.getDateStr());
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

                viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.chat_send_relative);
                viewHolder.relativeLayout_record = (RelativeLayout) convertView.findViewById(R.id.chat_send_relative_record);
                viewHolder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time_send);
                viewHolder.length = convertView.findViewById(R.id.id_recorder_length_send);
                viewHolder.head_pic4 = (SimpleDraweeView) convertView.findViewById(R.id.chat_send_icon);

                Uri uri = Uri.parse("file://" + sp.getString("headpic", ""));
                viewHolder.head_pic4.setImageURI(uri);

                if ("0".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.VISIBLE);
                    viewHolder.relativeLayout_record.setVisibility(View.GONE);

                    SpannableString spannableString = FaceConversionUtil.getInstace().getExpressionString(context, mDatas.get(position).getMessage());
                    viewHolder.content.setText(spannableString);
                    viewHolder.createDate.setText(chatMessage.getDateStr());
                } else if ("2".equals(mDatas.get(position).getMsgType()))
                {
                    viewHolder.relativeLayout.setVisibility(View.GONE);
                    viewHolder.relativeLayout_record.setVisibility(View.VISIBLE);

                    viewHolder.seconds.setText(Math.round(mDatas.get(position).getRecord_timelength()) + "\"");
                    ViewGroup.LayoutParams lp = viewHolder.length.getLayoutParams();
                    lp.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f * mDatas.get(position).getRecord_timelength()));

                    viewHolder.length.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {

                            if (mAnimView != null)
                            {
                                mAnimView = null;
                            }

                            mAnimView = v.findViewById(R.id.id_recorder_anim_send);
                            mAnimView.setBackgroundResource(R.drawable.anim_recorder_play);
                            AnimationDrawable anim = (AnimationDrawable) mAnimView
                                    .getBackground();
                            anim.start();

                            T.showShort(context, "-------------语音点击-------------");
                            MediaManager.playSound(context, chatMessage.getRecord_path(),
                                    new MediaPlayer.OnCompletionListener()
                                    {
                                        @Override
                                        public void onCompletion(MediaPlayer mp)
                                        {
                                            mAnimView.setBackgroundResource(R.drawable.rc_ic_voice_sent);
                                        }
                                    });
                        }
                    });

                    viewHolder.createDate.setText(chatMessage.getDateStr());

                }
                convertView.setTag(viewHolder);
            }

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder
    {
        public RelativeLayout relativeLayout;
        public RelativeLayout relativeLayout_pic;
        public RelativeLayout relativeLayout_record;
        public SimpleDraweeView head_pic1;
        public SimpleDraweeView head_pic2;
        public SimpleDraweeView head_pic3;
        public SimpleDraweeView head_pic4;
        public TextView createDate;
        public TextView content;
        public SimpleDraweeView imageView;
        TextView seconds;

        View length;
    }

}
