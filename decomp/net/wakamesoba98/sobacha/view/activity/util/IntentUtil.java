// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import net.wakamesoba98.sobacha.compatible.DualModeUtil;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.card.StatusCardManager;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

public class IntentUtil
{

    public IntentUtil()
    {
    }

    public void startActivityOrAddFragment(ViewPagerActivity viewpageractivity, Class class1, EnumViewPagerFragment enumviewpagerfragment, Bundle bundle)
    {
        boolean flag = DualModeUtil.canUseDualModeLayout(viewpageractivity);
        boolean flag1 = PreferenceUtil.getBooleanPreference(viewpageractivity, EnumPrefs.DUAL_TIMELINE_MODE);
        if(flag && flag1)
        {
            viewpageractivity.getStatusCard().close();
            class1 = new ViewPagerFragment();
            bundle.putInt("activity", enumviewpagerfragment.ordinal());
            class1.setArguments(bundle);
            viewpageractivity = viewpageractivity.getSupportFragmentManager().beginTransaction();
            viewpageractivity.add(0x7f0e0085, class1);
            viewpageractivity.setTransition(4097);
            viewpageractivity.addToBackStack(null);
            viewpageractivity.commit();
            return;
        } else
        {
            class1 = new Intent(viewpageractivity, class1);
            class1.putExtras(bundle);
            viewpageractivity.startActivity(class1);
            return;
        }
    }
}
