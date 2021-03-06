package com.zzy.frank.www.citylove_master.bean;

import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String UserId;//
    private String channelId;
    private String lastMSG;
    private String nick;//
    private String headIcon;//
    private int group;

    public String getLastMSG()
    {
        return lastMSG;
    }

    public void setLastMSG(String lastMSG)
    {
        this.lastMSG = lastMSG;
    }

    public User(
            String UserId, String channelId, String lastMSG, String nick, String headIcon,
            int group)
    {
        // TODO Auto-generated constructor stub
        this.UserId = UserId;
        this.channelId = channelId;
        this.nick = nick;
        this.headIcon = headIcon;
        this.group = group;
        this.lastMSG = lastMSG;
    }

    public User()
    {
    }

    public String getUserId()
    {
        return UserId;
    }

    public void setUserId(String userId)
    {
        UserId = userId;
    }

    public String getChannelId()
    {
        return channelId;
    }

    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getNick()
    {
        return nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
    }

    public String getHeadIcon()
    {
        return headIcon;
    }

    public void setHeadIcon(String headIcon)
    {
        this.headIcon = headIcon;
    }

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "User [UserId=" + UserId + ", channelId=" + channelId
                + ", nick=" + nick + ", headIcon=" + headIcon + ", group="
                + group + "]";
    }

}
