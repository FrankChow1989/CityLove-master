package com.zzy.frank.www.citylove_master.bean;

import java.util.ArrayList;

/**
 * Created by pc on 2016/10/25.
 */
public class Grils
{
    String id;
    String nickname;
    String icon;
    String addr;
    String distance;
    String age;
    String attention;
    String edit;

    String[] condition;
    String[] info;
    String[] data;
    String[] space;
    String[] photos;

    ArrayList<Msgs> message;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public String getDistance()
    {
        return distance;
    }

    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAttention()
    {
        return attention;
    }

    public void setAttention(String attention)
    {
        this.attention = attention;
    }

    public String getEdit()
    {
        return edit;
    }

    public void setEdit(String edit)
    {
        this.edit = edit;
    }

    public String[] getCondition()
    {
        return condition;
    }

    public void setCondition(String[] condition)
    {
        this.condition = condition;
    }

    public String[] getInfo()
    {
        return info;
    }

    public void setInfo(String[] info)
    {
        this.info = info;
    }

    public String[] getData()
    {
        return data;
    }

    public void setData(String[] data)
    {
        this.data = data;
    }

    public String[] getSpace()
    {
        return space;
    }

    public void setSpace(String[] space)
    {
        this.space = space;
    }

    public String[] getPhotos()
    {
        return photos;
    }

    public void setPhotos(String[] photos)
    {
        this.photos = photos;
    }

    public ArrayList<Msgs> getMessage()
    {
        return message;
    }

    public void setMessage(ArrayList<Msgs> message)
    {
        this.message = message;
    }
}
