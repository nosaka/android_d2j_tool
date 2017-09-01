// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.parent;

import android.os.Bundle;
import android.support.v4.app.*;
import net.wakamesoba98.sobacha.view.fragment.other.ImageViewerFragment;

public class ImageViewerPagerAdapter extends FragmentStatePagerAdapter
{

    public ImageViewerPagerAdapter(FragmentManager fragmentmanager, String as[], Bundle bundle1)
    {
        super(fragmentmanager);
        tabList = as;
        bundle = bundle1;
    }

    public int getCount()
    {
        return tabList.length;
    }

    public Fragment getItem(int i)
    {
        String s = tabList[i];
        ImageViewerFragment imageviewerfragment = new ImageViewerFragment();
        Bundle bundle1;
        if(bundle == null)
            bundle1 = new Bundle();
        else
            bundle1 = (Bundle)bundle.clone();
        bundle1.putInt("position", i);
        bundle1.putString("url", s);
        imageviewerfragment.setArguments(bundle1);
        return imageviewerfragment;
    }

    private Bundle bundle;
    private String tabList[];
}
