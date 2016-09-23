package com.zzy.frank.www.citylove_master.bean;

import android.graphics.Bitmap;

/**
 * Created by pc on 2016/9/22.
 */
public class MyPhoto
{
    private String id;
    private Bitmap photo;
    private String local_photo;

    public String getLocal_photo()
    {
        return local_photo;
    }

    public void setLocal_photo(String local_photo)
    {
        this.local_photo = local_photo;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Bitmap getPhoto()
    {
        return photo;
    }

    public void setPhoto(Bitmap photo)
    {
        this.photo = photo;
    }
}
