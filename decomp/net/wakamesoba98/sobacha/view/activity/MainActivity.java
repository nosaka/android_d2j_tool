// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.animation.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.*;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.dialog.DontShowAgainDialog;
import net.wakamesoba98.sobacha.dialog.InformationDialog;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.stream.StreamController;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.activity.util.AccountUtil;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.listview.adapter.*;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            ViewPagerActivity, AppPreferenceActivity

public class MainActivity extends ViewPagerActivity
    implements AdapterHolder, StreamController
{

    public MainActivity()
    {
    }

    private boolean canUseSplashAnimation()
    {
        return Flavor.isMateCha() && PreferenceUtil.getBooleanPreference(this, EnumPrefs.SPLASH_SCREEN) && SystemVersion.isLollipopOrLater();
    }

    private void checkOverlayPermission()
    {
        if(SystemVersion.isMarshmallowOrLater())
        {
            boolean flag = Settings.canDrawOverlays(this);
            if(!PreferenceUtil.getBooleanPreference(this, EnumExtraPrefs.SET_OVERLAY_PERMISSION_DEFAULT) && !flag)
                (new DontShowAgainDialog() {

                    public void onPositiveButtonClick(boolean flag1)
                    {
                        if(SystemVersion.isMarshmallowOrLater())
                        {
                            PreferenceUtil.putPreference(MainActivity.this, EnumExtraPrefs.SET_OVERLAY_PERMISSION_DEFAULT, Boolean.valueOf(flag1));
                            Object obj = getPackageName();
                            obj = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse((new StringBuilder()).append("package:").append(((String) (obj))).toString()));
                            startActivity(((Intent) (obj)));
                        }
                    }

                    final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
                }
).build(this, 0x7f070031, 0x7f0700de);
        }
    }

    private void clearBackStack()
    {
        getSupportFragmentManager().popBackStackImmediate(null, 1);
        clearViewPagerStack();
    }

    private boolean loadDualModeConfig()
    {
        boolean flag1 = PreferenceUtil.getBooleanPreference(this, EnumPrefs.DUAL_TIMELINE_MODE);
        boolean flag3 = DualModeUtil.isTabletDevice(this);
        boolean flag2 = DualModeUtil.canUseDualModeLayout(this);
        boolean flag = flag1;
        if(!PreferenceUtil.getBooleanPreference(this, EnumExtraPrefs.SET_DUAL_MODE_DEFAULT))
        {
            flag = flag1;
            if(flag3)
            {
                flag = flag1;
                if(!flag1)
                {
                    PreferenceUtil.putPreference(this, EnumPrefs.DUAL_TIMELINE_MODE, Boolean.valueOf(true));
                    PreferenceUtil.putPreference(this, EnumPrefs.SPLIT_VERTICALLY, Boolean.valueOf(true));
                    PreferenceUtil.putPreference(this, EnumExtraPrefs.SET_DUAL_MODE_DEFAULT, Boolean.valueOf(true));
                    flag = true;
                }
            }
        }
        return flag2 && flag;
    }

    private void onDropAnimationFinished()
    {
        if(isFirstLayoutLoad && !isStopped)
            reloadLayout(loadDualModeConfig());
    }

    private void refreshDualModeOrientation()
    {
        Object obj = (LinearLayout)findViewById(0x7f0e0083);
        FrameLayout framelayout = (FrameLayout)findViewById(0x7f0e0084);
        FrameLayout framelayout1 = (FrameLayout)findViewById(0x7f0e0085);
        int i = getResources().getConfiguration().orientation;
        if(PreferenceUtil.getBooleanPreference(this, EnumPrefs.SPLIT_VERTICALLY) || i == 2)
        {
            ((LinearLayout) (obj)).setOrientation(0);
            obj = new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F);
        } else
        {
            ((LinearLayout) (obj)).setOrientation(1);
            obj = new android.widget.LinearLayout.LayoutParams(-1, 0, 1.0F);
        }
        framelayout.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        framelayout1.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
    }

    private void reloadLayout(boolean flag)
    {
        isFirstLayoutLoad = false;
        isDualMode = flag;
        Object obj = findViewById(0x7f0e0084);
        if(flag)
        {
            ((View) (obj)).setVisibility(0);
            obj = new ViewPagerFragment();
            ViewPagerFragment viewpagerfragment = new ViewPagerFragment();
            Bundle bundle1 = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle1.putInt("activity", EnumViewPagerFragment.MAIN_TABLET_TIMELINE.ordinal());
            bundle2.putInt("activity", EnumViewPagerFragment.MAIN_TABLET_OTHER.ordinal());
            ((ViewPagerFragment) (obj)).setArguments(bundle1);
            viewpagerfragment.setArguments(bundle2);
            setFragment(((android.support.v4.app.Fragment) (obj)), "leftFragment", 0x7f0e0084);
            setFragment(viewpagerfragment, "rightFragment", 0x7f0e0085);
            refreshDualModeOrientation();
            return;
        } else
        {
            ((View) (obj)).setVisibility(8);
            obj = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("activity", EnumViewPagerFragment.MAIN.ordinal());
            ((ViewPagerFragment) (obj)).setArguments(bundle);
            setFragment(((android.support.v4.app.Fragment) (obj)), "rightFragment", 0x7f0e0085);
            return;
        }
    }

    private void removeFragment(String s)
    {
        FragmentManager fragmentmanager = getSupportFragmentManager();
        s = fragmentmanager.findFragmentByTag(s);
        if(s != null)
        {
            FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
            fragmenttransaction.remove(s);
            fragmenttransaction.commit();
            fragmentmanager.executePendingTransactions();
        }
    }

    private void setDialogDefault()
    {
        boolean flag = PreferenceUtil.getBooleanPreference(this, EnumPrefs.CONFIRM);
        if(!PreferenceUtil.getBooleanPreference(this, EnumExtraPrefs.SET_CONFIRM_DEFAULT))
        {
            PreferenceUtil.putPreference(this, EnumPrefs.CONFIRM_POST, Boolean.valueOf(flag));
            PreferenceUtil.putPreference(this, EnumPrefs.CONFIRM_RETWEET, Boolean.valueOf(flag));
            PreferenceUtil.putPreference(this, EnumPrefs.CONFIRM_FAVORITE, Boolean.valueOf(flag));
            PreferenceUtil.putPreference(this, EnumExtraPrefs.SET_CONFIRM_DEFAULT, Boolean.valueOf(true));
        }
    }

    private void showTwitpicDialog()
    {
        if(PreferenceUtil.getStringPreference(this, EnumPrefs.UPLOAD_PROVIDER).equals("TWITPIC"))
            (new InformationDialog() {

                public void onOkButtonClick()
                {
                    Intent intent = new Intent(MainActivity.this, net/wakamesoba98/sobacha/view/activity/AppPreferenceActivity);
                    intent.setData(Uri.parse("sobachapref://2"));
                    startActivity(intent);
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
).build(this, 0x7f07012b, 0x7f0700da);
    }

    private void splashAnimationImpl()
    {
        final View splash = findViewById(0x7f0e0081);
        final View wrapper = findViewById(0x7f0e0082);
        final View drop = findViewById(0x7f0e013b);
        int i = splash.getWidth() / 2;
        int j = splash.getHeight() / 2;
        int k = Math.min(drop.getWidth(), drop.getHeight()) / 2;
        int l = (int)Math.hypot(splash.getWidth(), splash.getHeight()) / 2;
        final Animator circularRevealAnimator = ViewAnimationUtils.createCircularReveal(wrapper, i, j, k, l);
        circularRevealAnimator.setDuration(300L);
        circularRevealAnimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                splash.setVisibility(8);
            }

            final MainActivity this$0;
            final View val$splash;

            
            {
                this$0 = MainActivity.this;
                splash = view;
                super();
            }
        }
);
        splash = ObjectAnimator.ofFloat(drop, "translationY", new float[] {
            0.0F - (float)drop.getHeight(), (float)(splash.getHeight() / 2 - drop.getHeight())
        });
        splash.setDuration(400L);
        splash.setInterpolator(new AccelerateInterpolator());
        splash.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                drop.setVisibility(8);
                wrapper.setVisibility(0);
                onDropAnimationFinished();
                circularRevealAnimator.start();
            }

            final MainActivity this$0;
            final Animator val$circularRevealAnimator;
            final View val$drop;
            final View val$wrapper;

            
            {
                this$0 = MainActivity.this;
                drop = view;
                wrapper = view1;
                circularRevealAnimator = animator;
                super();
            }
        }
);
        drop.setVisibility(0);
        splash.start();
    }

    public StatusAdapter getAdapter(EnumTab enumtab)
    {
        Object obj = (StatusAdapter)adapterMap.get(enumtab);
        if(obj == null)
        {
            if(enumtab == EnumTab.HOME)
                obj = new AnimationStatusAdapter(this, 0x7f03004e, new ArrayList());
            else
                obj = new StatusAdapter(this, 0x7f03004e, new ArrayList());
            ((SobaChaApplication)getApplication()).registerAdapter(((StatusAdapter) (obj)));
            adapterMap.put(enumtab, obj);
            return (StatusAdapter)adapterMap.get(enumtab);
        } else
        {
            return ((StatusAdapter) (obj));
        }
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        super.onAccountChanged(useraccount);
        useraccount = (SobaChaApplication)getApplication();
        UserAccount useraccount1 = useraccount.getUserAccount();
        useraccount1.setStreamManager(null);
        useraccount1.initializeStream(this, this, useraccount.getStreamPreferences());
        isStreamStarted = false;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(!DualModeUtil.canUseDualModeLayout(this)) goto _L2; else goto _L1
_L1:
        if(isDualMode) goto _L4; else goto _L3
_L3:
        if(!isStopped) goto _L6; else goto _L5
_L5:
        PreferenceUtil.putPreference(this, EnumExtraPrefs.LAYOUT_CHANGED, Boolean.valueOf(true));
_L4:
        if(isDualMode)
            refreshDualModeOrientation();
        return;
_L6:
        if(PreferenceUtil.getBooleanPreference(this, EnumPrefs.DUAL_TIMELINE_MODE))
        {
            clearBackStack();
            removeFragment("rightFragment");
            reloadLayout(true);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(isDualMode)
            if(isStopped)
            {
                PreferenceUtil.putPreference(this, EnumExtraPrefs.LAYOUT_CHANGED, Boolean.valueOf(true));
            } else
            {
                clearBackStack();
                removeFragment("leftFragment");
                removeFragment("rightFragment");
                reloadLayout(false);
            }
        if(true) goto _L4; else goto _L7
_L7:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = (SobaChaApplication)getApplication();
        isStopped = false;
        isFirstLayoutLoad = true;
        if(!(new AccountUtil()).accountInitialize(this, bundle.getUserId()))
        {
            adapterMap = new HashMap();
            bundle.getUserAccount().initializeStream(this, this, bundle.getStreamPreferences());
            isStreamStarted = false;
            showTwitpicDialog();
            setDialogDefault();
            checkOverlayPermission();
            if(Flavor.isMateCha() && PreferenceUtil.getBooleanPreference(this, EnumPrefs.SPLASH_SCREEN) && SystemVersion.isLollipopOrLater())
            {
                bundle = findViewById(0x7f0e0081);
                View view = findViewById(0x7f0e0082);
                bundle.setVisibility(0);
                view.setVisibility(4);
                return;
            }
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
        UserAccount useraccount = sobachaapplication.getUserAccount();
        StreamManager streammanager = useraccount.getStreamManager();
        if(streammanager != null && streammanager.isEnabled())
        {
            streammanager.shutdown();
            sobachaapplication.getUserAccount().setStreamManager(null);
        }
        if(adapterMap != null)
        {
            EnumTab enumtab;
            for(Iterator iterator = adapterMap.keySet().iterator(); iterator.hasNext(); sobachaapplication.releaseAdapter((StatusAdapter)adapterMap.get(enumtab)))
                enumtab = (EnumTab)iterator.next();

        }
        useraccount.clear();
    }

    protected void onResume()
    {
        super.onResume();
        isStopped = false;
        if(PreferenceUtil.getBooleanPreference(this, EnumExtraPrefs.LAYOUT_CHANGED))
        {
            PreferenceUtil.putPreference(this, EnumExtraPrefs.LAYOUT_CHANGED, Boolean.valueOf(false));
            clearBackStack();
            if(isDualMode)
            {
                removeFragment("leftFragment");
                removeFragment("rightFragment");
            } else
            {
                removeFragment("rightFragment");
            }
            reloadLayout(loadDualModeConfig());
        }
        if(canUseSplashAnimation())
        {
            startSplashAnimation();
            return;
        } else
        {
            onDropAnimationFinished();
            return;
        }
    }

    protected void onStop()
    {
        super.onStop();
        isStopped = true;
    }

    public void startSplashAnimation()
    {
        View view = findViewById(0x7f0e0081);
        if(view.getVisibility() == 0)
        {
            view.postDelayed(new Runnable() {

                public void run()
                {
                    splashAnimationImpl();
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
, 500L);
            return;
        } else
        {
            onDropAnimationFinished();
            return;
        }
    }

    public void startStream()
    {
        if(!isStreamStarted)
        {
            SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
            sobachaapplication.getUserAccount().startStream(this, this, sobachaapplication.getStreamPreferences());
            isStreamStarted = true;
        }
    }

    private static final long DELAY_MILLIS = 500L;
    private Map adapterMap;
    private boolean isDualMode;
    private boolean isFirstLayoutLoad;
    private boolean isStopped;
    private boolean isStreamStarted;


}
