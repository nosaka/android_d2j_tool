// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.notification;

import android.content.Context;
import android.provider.Settings;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.preference.prefs.StreamPreferences;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;

// Referenced classes of package net.wakamesoba98.sobacha.notification:
//            NotificationSoundManager

public class OverlayNotification
{

    public OverlayNotification(Context context1, StreamPreferences streampreferences)
    {
        context = context1;
        themeManager = new ThemeManager(context1);
        soundManager = new NotificationSoundManager(context1, streampreferences);
    }

    private boolean canDrawOverlays()
    {
        return !SystemVersion.isMarshmallowOrLater() || Settings.canDrawOverlays(context);
    }

    private void dismiss()
    {
        if(windowManager != null && view != null)
        {
            windowManager.removeView(view);
            windowManager = null;
            view = null;
        }
    }

    private void initializeAnimation(Context context1)
    {
        scaleAnimation1 = new ScaleAnimation(1.0F, 1.0F, 0.0F, 1.0F, 0.0F, DisplayMetricsUtil.convertDipToPixel(context1, 14));
        scaleAnimation1.setDuration(250L);
        scaleAnimation2 = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F, 0.0F, DisplayMetricsUtil.convertDipToPixel(context1, 14));
        scaleAnimation2.setDuration(1000L);
        scaleAnimation3 = new ScaleAnimation(1.0F, 1.0F, 1.0F, 0.0F, 0.0F, DisplayMetricsUtil.convertDipToPixel(context1, 14));
        scaleAnimation3.setDuration(250L);
    }

    private void setAnimation()
    {
        scaleAnimation1.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                hasStartedAnimation1 = false;
                textViewNotification.clearAnimation();
                textViewNotification.startAnimation(scaleAnimation2);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                hasStartedAnimation1 = true;
            }

            final OverlayNotification this$0;

            
            {
                this$0 = OverlayNotification.this;
                super();
            }
        }
);
        scaleAnimation2.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                hasStartedAnimation2 = false;
                textViewNotification.clearAnimation();
                textViewNotification.startAnimation(scaleAnimation3);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                hasStartedAnimation2 = true;
            }

            final OverlayNotification this$0;

            
            {
                this$0 = OverlayNotification.this;
                super();
            }
        }
);
        scaleAnimation3.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                hasStartedAnimation3 = false;
                textViewNotification.clearAnimation();
                dismiss();
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                hasStartedAnimation3 = true;
            }

            final OverlayNotification this$0;

            
            {
                this$0 = OverlayNotification.this;
                super();
            }
        }
);
    }

    private void setTextViewProperties(String s, EnumStatusType enumstatustype)
    {
        int i;
        String s1;
        TextView textview;
        if(view == null)
            return;
        textview = (TextView)view.findViewById(0x7f0e0132);
        i = 0;
        s1 = "";
        static class _cls4
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.MENTION.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.DIRECT_MESSAGE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.DIRECT_MESSAGE_USER.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.RETWEETED.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.FAVORITED.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        _cls4..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 1 5: default 72
    //                   1 117
    //                   2 140
    //                   3 140
    //                   4 163
    //                   5 186;
           goto _L1 _L2 _L3 _L3 _L4 _L5
_L1:
        enumstatustype = s1;
_L7:
        textview.setBackgroundColor(i);
        textview.setText(String.format(enumstatustype, new Object[] {
            (new StringBuilder()).append("@").append(s).toString()
        }));
        return;
_L2:
        i = themeManager.getThemeColor(0x7f0d0070);
        enumstatustype = ResourceHelper.getString(context, 0x7f0700c3);
        continue; /* Loop/switch isn't completed */
_L3:
        i = themeManager.getThemeColor(0x7f0d0076);
        enumstatustype = ResourceHelper.getString(context, 0x7f0700eb);
        continue; /* Loop/switch isn't completed */
_L4:
        i = themeManager.getThemeColor(0x7f0d007c);
        enumstatustype = ResourceHelper.getString(context, 0x7f0700f5);
        continue; /* Loop/switch isn't completed */
_L5:
        i = themeManager.getThemeColor(0x7f0d0068);
        enumstatustype = ResourceHelper.getString(context, 0x7f0700bb);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void startAnimation()
    {
        textViewNotification.startAnimation(scaleAnimation1);
    }

    public void show(String s, EnumStatusType enumstatustype)
    {
        soundManager.play();
        if(!canDrawOverlays())
            return;
        if(windowManager == null && view == null)
        {
            LayoutInflater layoutinflater = LayoutInflater.from(context);
            android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(-1, -1, 2003, 56, -3);
            windowManager = (WindowManager)context.getSystemService("window");
            view = layoutinflater.inflate(0x7f030060, null);
            windowManager.addView(view, layoutparams);
        }
        textViewNotification = (TextView)view.findViewById(0x7f0e0132);
        if(hasStartedAnimation1 || hasStartedAnimation2 || hasStartedAnimation3)
            textViewNotification.clearAnimation();
        setTextViewProperties(s, enumstatustype);
        initializeAnimation(context);
        setAnimation();
        startAnimation();
    }

    private Context context;
    private boolean hasStartedAnimation1;
    private boolean hasStartedAnimation2;
    private boolean hasStartedAnimation3;
    private ScaleAnimation scaleAnimation1;
    private ScaleAnimation scaleAnimation2;
    private ScaleAnimation scaleAnimation3;
    private NotificationSoundManager soundManager;
    private TextView textViewNotification;
    private ThemeManager themeManager;
    private View view;
    private WindowManager windowManager;


/*
    static boolean access$002(OverlayNotification overlaynotification, boolean flag)
    {
        overlaynotification.hasStartedAnimation1 = flag;
        return flag;
    }

*/




/*
    static boolean access$302(OverlayNotification overlaynotification, boolean flag)
    {
        overlaynotification.hasStartedAnimation2 = flag;
        return flag;
    }

*/



/*
    static boolean access$502(OverlayNotification overlaynotification, boolean flag)
    {
        overlaynotification.hasStartedAnimation3 = flag;
        return flag;
    }

*/

}
