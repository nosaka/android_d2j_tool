// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.parent;

import android.os.Bundle;
import android.support.v4.app.*;
import java.util.List;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

class SectionsPagerAdapter extends FragmentStatePagerAdapter
{

    SectionsPagerAdapter(FragmentManager fragmentmanager, List list, Bundle bundle1)
    {
        super(fragmentmanager);
        tabList = list;
        bundle = bundle1;
    }

    public int getCount()
    {
        return tabList.size();
    }

    public Fragment getItem(int i)
    {
        Object obj = ((EnumTab)tabList.get(i)).getFragment();
        Fragment fragment;
        fragment = (Fragment)((Class) (obj)).newInstance();
        if(bundle != null)
            break MISSING_BLOCK_LABEL_54;
        obj = new Bundle();
_L1:
        ((Bundle) (obj)).putInt("position", i);
        fragment.setArguments(((Bundle) (obj)));
        return fragment;
        try
        {
            obj = (Bundle)bundle.clone();
        }
        catch(InstantiationException instantiationexception)
        {
            instantiationexception.printStackTrace();
            return null;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            return null;
        }
          goto _L1
    }

    private Bundle bundle;
    private List tabList;
}
