package com.zzy.frank.www.citylove_master.bean;

import java.util.List;

/**
 * Created by pc on 2016/11/8.
 */
public class ScretItem
{
    String icon;
    String nickname;
    String addr;
    String age;
    String date;

    String message;
    String pic;
    String remand;

    public List<ScretMsg> getMsgs()
    {
        return msgs;
    }

    public void setMsgs(List<ScretMsg> msgs)
    {
        this.msgs = msgs;
    }

    List<ScretMsg> msgs;

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getRemand()
    {
        return remand;
    }

    public void setRemand(String remand)
    {
        this.remand = remand;
    }

}
