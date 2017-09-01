// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;

abstract class AppCompatPreferenceActivity extends PreferenceActivity
{

    AppCompatPreferenceActivity()
    {
    }

    private AppCompatDelegate getDelegate()
    {
        if(mDelegate == null)
            mDelegate = AppCompatDelegate.create(this, null);
        return mDelegate;
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getDelegate().addContentView(view, layoutparams);
    }

    public MenuInflater getMenuInflater()
    {
        return getDelegate().getMenuInflater();
    }

    public ActionBar getSupportActionBar()
    {
        return getDelegate().getSupportActionBar();
    }

    public void invalidateOptionsMenu()
    {
        getDelegate().invalidateOptionsMenu();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        getDelegate().onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle)
    {
        getDelegate().installViewFactory();
        getDelegate().onCreate(bundle);
        super.onCreate(bundle);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    protected void onPostCreate(Bundle bundle)
    {
        super.onPostCreate(bundle);
        getDelegate().onPostCreate(bundle);
    }

    protected void onPostResume()
    {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    protected void onStop()
    {
        super.onStop();
        getDelegate().onStop();
    }

    protected void onTitleChanged(CharSequence charsequence, int i)
    {
        super.onTitleChanged(charsequence, i);
        getDelegate().setTitle(charsequence);
    }

    public void setContentView(int i)
    {
        getDelegate().setContentView(i);
    }

    public void setContentView(View view)
    {
        getDelegate().setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        getDelegate().setContentView(view, layoutparams);
    }

    public void setSupportActionBar(Toolbar toolbar)
    {
        getDelegate().setSupportActionBar(toolbar);
    }

    private AppCompatDelegate mDelegate;
}
