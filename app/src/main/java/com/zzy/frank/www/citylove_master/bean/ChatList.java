package com.zzy.frank.www.citylove_master.bean;

/**
 * Created by pc on 2016/9/19.
 */
public class ChatList
{
    private static final long serialVersionUID = 1L;
    private String UserId;
    private int pic;
    private String title;
    private String lastMsg;
    private String time;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public String getUserId()
    {
        return UserId;
    }

    public void setUserId(String userId)
    {
        UserId = userId;
    }

    public int getPic()
    {
        return pic;
    }

    public void setPic(int pic)
    {
        this.pic = pic;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLastMsg()
    {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg)
    {
        this.lastMsg = lastMsg;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
