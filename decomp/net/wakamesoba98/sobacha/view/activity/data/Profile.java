// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.data;

import android.databinding.BaseObservable;

public class Profile extends BaseObservable
{

    public Profile(String s, String s1, String s2, String s3)
    {
        name = s;
        location = s1;
        url = s2;
        description = s3;
    }

    public boolean equals(Object obj)
    {
        boolean flag2;
        boolean flag3;
        flag2 = true;
        flag3 = false;
        if(this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag3;
        if(obj == null) goto _L4; else goto _L3
_L3:
        flag = flag3;
        if(getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (Profile)obj;
        if(name == null) goto _L7; else goto _L6
_L6:
        flag = flag3;
        if(!name.equals(((Profile) (obj)).name)) goto _L4; else goto _L8
_L8:
        if(location == null) goto _L10; else goto _L9
_L9:
        flag = flag3;
        if(!location.equals(((Profile) (obj)).location)) goto _L4; else goto _L11
_L11:
        if(url == null)
            break MISSING_BLOCK_LABEL_151;
        flag = flag3;
        if(!url.equals(((Profile) (obj)).url)) goto _L4; else goto _L12
_L12:
        boolean flag1;
        if(description != null)
        {
            flag1 = description.equals(((Profile) (obj)).description);
        } else
        {
            flag1 = flag2;
            if(((Profile) (obj)).description != null)
                flag1 = false;
        }
        return flag1;
_L7:
        if(((Profile) (obj)).name != null)
            return false;
          goto _L8
_L10:
        if(((Profile) (obj)).location != null)
            return false;
          goto _L11
        if(((Profile) (obj)).url != null)
            return false;
          goto _L12
    }

    public String getDescription()
    {
        return description;
    }

    public String getLocation()
    {
        return location;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }

    public int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if(name != null)
            i = name.hashCode();
        else
            i = 0;
        if(location != null)
            j = location.hashCode();
        else
            j = 0;
        if(url != null)
            k = url.hashCode();
        else
            k = 0;
        if(description != null)
            l = description.hashCode();
        return ((i * 31 + j) * 31 + k) * 31 + l;
    }

    public void setDescription(String s)
    {
        description = s;
        notifyPropertyChanged(1);
    }

    public void setLocation(String s)
    {
        location = s;
        notifyPropertyChanged(3);
    }

    public void setName(String s)
    {
        name = s;
        notifyPropertyChanged(4);
    }

    public void setUrl(String s)
    {
        url = s;
        notifyPropertyChanged(6);
    }

    private String description;
    private String location;
    private String name;
    private String url;
}
