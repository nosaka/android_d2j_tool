// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.base;

import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity.base:
//            AppCompatPreferenceActivity

public abstract class PreferenceActivityBase extends AppCompatPreferenceActivity
{

    public PreferenceActivityBase()
    {
    }

    public volatile void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addContentView(view, layoutparams);
    }

    public volatile MenuInflater getMenuInflater()
    {
        return super.getMenuInflater();
    }

    public volatile ActionBar getSupportActionBar()
    {
        return super.getSupportActionBar();
    }

    public volatile void invalidateOptionsMenu()
    {
        super.invalidateOptionsMenu();
    }

    public volatile void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public volatile void setContentView(int i)
    {
        super.setContentView(i);
    }

    public volatile void setContentView(View view)
    {
        super.setContentView(view);
    }

    public volatile void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.setContentView(view, layoutparams);
    }

    public volatile void setSupportActionBar(Toolbar toolbar)
    {
        super.setSupportActionBar(toolbar);
    }
}
