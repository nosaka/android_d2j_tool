// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public abstract class ThemeActivity extends AppCompatActivity
{

    public ThemeActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        setTheme((new ThemeManager(this)).getActivityTheme());
        super.onCreate(bundle);
    }
}
