package com.zzy.frank.www.citylove_master.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage
{
    private String message;
    private String pic_msg;
    private String record_path;
    private boolean isComing;
    private Date date;
    private String userId;
    private int icon;
    private String msgType;

    public void setDateStr(String dateStr)
    {
        this.dateStr = dateStr;
    }

    private String nickname;
    private boolean readed;
    private String dateStr;

    public String getPic_msg()
    {
        return pic_msg;
    }

    public void setPic_msg(String pic_msg)
    {
        this.pic_msg = pic_msg;
    }

    public String getRecord_path()
    {
        return record_path;
    }

    public void setRecord_path(String record_path)
    {
        this.record_path = record_path;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public ChatMessage()
    {
    }

    public ChatMessage(
            String message, String pic_msg, String record_path, boolean isComing,
            String userId, int icon, String msgType, String nickname, boolean readed,
            String dateStr)
    {
        super();
        this.message = message;
        this.pic_msg = pic_msg;
        this.record_path = record_path;
        this.isComing = isComing;
        this.userId = userId;
        this.icon = icon;
        this.msgType = msgType;
        this.nickname = nickname;
        this.readed = readed;
        this.dateStr = dateStr;
    }

    public String getDateStr()
    {
        return dateStr;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isComing()
    {
        return isComing;
    }

    public void setComing(boolean isComing)
    {
        this.isComing = isComing;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateStr = df.format(date);
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public boolean isReaded()
    {
        return readed;
    }

    public void setReaded(boolean readed)
    {
        this.readed = readed;
    }

}
