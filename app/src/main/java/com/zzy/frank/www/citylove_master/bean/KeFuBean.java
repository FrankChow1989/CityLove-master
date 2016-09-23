package com.zzy.frank.www.citylove_master.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pc on 2016/9/23.
 */
public class KeFuBean
{
    public static final int SEND=1;
    public static final int JIESHOU=2;
    private int state;
    private String message;
    private Date date;
    private String dateStr;

    public Date getDate()
    {
        return date;
    }

    public void setDateStr(String dateStr)
    {
        this.dateStr = dateStr;
    }

    public String getDateStr()
    {
        return dateStr;
    }

    public void setDate(Date date)
    {
        this.date = date;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateStr = df.format(date);
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
