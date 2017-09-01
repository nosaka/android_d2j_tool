// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fullscreen;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Method;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;

public class FullScreen
{
    private class NavigationBarManager
    {

        void hideNavigation(Activity activity)
        {
            activity = activity.getWindow().getDecorView();
            activity.setSystemUiVisibility(4098);
            activity.setOnSystemUiVisibilityChangeListener(activity. new android.view.View.OnSystemUiVisibilityChangeListener() {

                public void onSystemUiVisibilityChange(int i)
                {
                    decorView.setSystemUiVisibility(4098);
                }

                final NavigationBarManager this$1;
                final View val$decorView;

            
            {
                this$1 = final_navigationbarmanager;
                decorView = View.this;
                super();
            }
            }
);
        }

        final FullScreen this$0;

        private NavigationBarManager()
        {
            this$0 = FullScreen.this;
            super();
        }

    }


    public FullScreen()
    {
        String s = Build.MODEL;
        if(!s.equals("IS01") && !s.equals("SH-10B"))
            break MISSING_BLOCK_LABEL_50;
        fullScreenForIS01 = Class.forName("jp.co.sharp.android.softguide.SoftGuideManager").getMethod("setFullScreenMode", new Class[] {
            Boolean.TYPE
        });
        return;
        Exception exception;
        exception;
        Log.d("is01fullscreen", (new StringBuilder()).append("failed").append(exception.getMessage()).append(":").append(exception.getClass().toString()).toString());
        return;
    }

    public void hideNavigationBar(Activity activity)
    {
        boolean flag = PreferenceUtil.getBooleanPreference(activity, EnumPrefs.FULL_SCREEN);
        boolean flag1 = PreferenceUtil.getBooleanPreference(activity, EnumPrefs.HIDE_NAVIGATION_BAR);
        if(flag && flag1 && SystemVersion.isKitKatOrLater())
            (new NavigationBarManager()).hideNavigation(activity);
    }

    public void hideNotificationBar(Activity activity)
    {
        if(PreferenceUtil.getBooleanPreference(activity, EnumPrefs.FULL_SCREEN))
            activity.getWindow().addFlags(1024);
    }

    public void hideSoftGuide()
    {
        try
        {
            if(fullScreenForIS01 != null)
                fullScreenForIS01.invoke(null, new Object[] {
                    Boolean.valueOf(true)
                });
            return;
        }
        catch(Exception exception)
        {
            Log.d("is01fullscreen", "failed");
        }
    }

    public void showNotificationBar(Activity activity)
    {
        if(PreferenceUtil.getBooleanPreference(activity, EnumPrefs.FULL_SCREEN))
            activity.getWindow().clearFlags(1024);
    }

    private static final String MODEL_IS01 = "IS01";
    private static final String MODEL_SH10B = "SH-10B";
    private Method fullScreenForIS01;
}
