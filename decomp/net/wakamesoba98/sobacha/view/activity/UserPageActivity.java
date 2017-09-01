// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            ViewPagerActivity

public class UserPageActivity extends ViewPagerActivity
{

    public UserPageActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = (Bundle)getIntent().getExtras().clone();
        bundle.putInt("activity", EnumViewPagerFragment.USER_PAGE.ordinal());
        ViewPagerFragment viewpagerfragment = new ViewPagerFragment();
        viewpagerfragment.setArguments(bundle);
        setFragment(viewpagerfragment, "userPageFragment", 0x7f0e0085);
    }
}
