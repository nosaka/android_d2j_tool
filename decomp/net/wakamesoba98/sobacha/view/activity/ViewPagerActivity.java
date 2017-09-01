// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.ProgressBar;
import java.util.Deque;
import java.util.LinkedList;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.LoadMode;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.image.UriResolver;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.AccountHolder;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.preference.value.EnumCameraButton;
import net.wakamesoba98.sobacha.view.activity.base.ThemeFragmentActivity;
import net.wakamesoba98.sobacha.view.card.*;
import net.wakamesoba98.sobacha.view.fullscreen.FullScreen;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            MainActivity

public abstract class ViewPagerActivity extends ThemeFragmentActivity
    implements AccountHolder
{

    public ViewPagerActivity()
    {
    }

    private void removeLastViewPager()
    {
        viewPagerStack.pollLast();
    }

    private void setCardMargin()
    {
        int i;
        int j;
        int k;
        int l;
        j = getResources().getConfiguration().orientation;
        i = j;
        if(SystemVersion.isNougatOrLater())
        {
            i = j;
            if(isInMultiWindowMode())
                i = 1;
        }
        j = DisplayMetricsUtil.convertDipToPixel(this, 8);
        k = DisplayMetricsUtil.convertDipToPixel(this, 4);
        l = DisplayMetricsUtil.convertDipToPixel(this, 64) + j;
        if(!SystemVersion.isHoneycombOrLater()) goto _L2; else goto _L1
_L1:
        View view;
        View view2;
        android.widget.FrameLayout.LayoutParams layoutparams;
        android.widget.FrameLayout.LayoutParams layoutparams1;
        view = findViewById(0x7f0e00aa);
        view2 = findViewById(0x7f0e0098);
        layoutparams = new android.widget.FrameLayout.LayoutParams(-1, -1);
        layoutparams1 = new android.widget.FrameLayout.LayoutParams(-1, -1);
        i;
        JVM INSTR tableswitch 2 2: default 116
    //                   2 149;
           goto _L3 _L4
_L3:
        layoutparams.setMargins(j, j, j, k);
        layoutparams1.setMargins(j, k, j, j);
_L5:
        view.setLayoutParams(layoutparams);
        view2.setLayoutParams(layoutparams1);
        return;
_L4:
        layoutparams.setMargins(l, j, l, k);
        layoutparams1.setMargins(l, k, l, j);
        if(true) goto _L5; else goto _L2
_L2:
        View view1 = findViewById(0x7f0e0088);
        View view3 = findViewById(0x7f0e0087);
        switch(i)
        {
        default:
            view1.setPadding(j, 0, j, j);
            view3.setPadding(j, 0, j, j);
            return;

        case 2: // '\002'
            view1.setPadding(l, 0, l, j);
            break;
        }
        view3.setPadding(l, 0, l, j);
        return;
    }

    private void setFullScreen()
    {
        FullScreen fullscreen = new FullScreen();
        fullscreen.hideNotificationBar(this);
        fullscreen.hideNavigationBar(this);
        fullscreen.hideSoftGuide();
    }

    private void viewInitialize(Bundle bundle)
    {
        getWindow().setSoftInputMode(2);
        Object obj = (SobaChaApplication)getApplication();
        if(uploadCardManager == null)
            uploadCardManager = new UploadCardManager(this);
        if(postCardManager == null)
        {
            postCardManager = new PostCardManager(this, ((SobaChaApplication) (obj)).getUserAccount(), uploadCardManager);
            ((SobaChaApplication) (obj)).registerPostCard(postCardManager);
        } else
        {
            postCardManager.viewInitialize();
        }
        if(bundle != null)
            postCardManager.setText(bundle.getString("postcard_text"));
        if(statusCardManager == null)
            statusCardManager = new StatusCardManager(this, ((SobaChaApplication) (obj)).getUserAccount());
        else
            statusCardManager.viewInitialize();
        bundle = findViewById(0x7f0e0088);
        obj = findViewById(0x7f0e0087);
        bundle.setOnTouchListener(new TouchCanceller());
        ((View) (obj)).setOnTouchListener(new TouchCanceller());
        ((ProgressBar)findViewById(0x7f0e0133)).setVisibility(8);
        setCardMargin();
    }

    public void addViewPagerToLast(ViewPager viewpager)
    {
        viewPagerStack.addLast(viewpager);
    }

    protected void clearViewPagerStack()
    {
        viewPagerStack.clear();
    }

    public PostCardManager getPostCardManager()
    {
        return postCardManager;
    }

    public StatusCardManager getStatusCard()
    {
        return statusCardManager;
    }

    public void launchCamera()
    {
        Object obj = new UriResolver();
        try
        {
            cameraUri = ((UriResolver) (obj)).getCameraUri(this);
            obj = new Intent("android.media.action.IMAGE_CAPTURE");
            ((Intent) (obj)).putExtra("output", cameraUri);
            startActivityForResult(((Intent) (obj)), 2);
            return;
        }
        catch(UnsupportedOperationException unsupportedoperationexception)
        {
            Notificator.toast(this, 0x7f070083);
            unsupportedoperationexception.printStackTrace();
            return;
        }
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        postCardManager.setUserAccount(useraccount);
        statusCardManager.setUserAccount(useraccount);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 25
    //                   2 44;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(j == -1)
        {
            intent = intent.getData();
            postCardManager.setUploadImage(intent);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(j == -1 && cameraUri != null)
        {
            (new UriResolver()).registerCameraImage(this, cameraUri, new android.media.MediaScannerConnection.OnScanCompletedListener() {

                public void onScanCompleted(String s, Uri uri)
                {
                    if(uri != null)
                        (new Handler(Looper.getMainLooper())).post(uri. new Runnable() {

                            public void run()
                            {
                                postCardManager.setUploadImage(uri);
                            }

                            final _cls2 this$1;
                            final Uri val$uri;

            
            {
                this$1 = final__pcls2;
                uri = Uri.this;
                super();
            }
                        }
);
                }

                final ViewPagerActivity this$0;

            
            {
                this$0 = ViewPagerActivity.this;
                super();
            }
            }
);
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        setCardMargin();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        viewPagerStack = new LinkedList();
        SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
        sobachaapplication.loadPreferences(LoadMode.LOAD_ONCE);
        sobachaapplication.registerAccountHolder(this);
        setContentView(0x7f030022);
        viewInitialize(bundle);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
        sobachaapplication.releaseAccountHolder(this);
        sobachaapplication.releasePostCard(postCardManager);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = true;
        i;
        JVM INSTR lookupswitch 4: default 48
    //                   4: 92
    //                   27: 260
    //                   82: 225
    //                   131: 225;
           goto _L1 _L2 _L3 _L4 _L4
_L1:
        if(7 <= i && i <= 16 || 29 <= i && i <= 54)
            findViewById(0x7f0e009c).requestFocusFromTouch();
        flag = super.onKeyDown(i, keyevent);
_L6:
        return flag;
_L2:
        if(statusCardManager.isVisible())
        {
            statusCardManager.close();
            return true;
        }
        if(postCardManager.isMenuVisible())
        {
            postCardManager.setMenuVisibility(false);
            return true;
        }
        ViewPager viewpager = (ViewPager)viewPagerStack.peekLast();
        if(viewpager != null && viewpager.getCurrentItem() != 0)
        {
            viewpager.setCurrentItem(0, true);
            return true;
        }
        if((this instanceof MainActivity) && getSupportFragmentManager().getBackStackEntryCount() == 0 && PreferenceUtil.getBooleanPreference(this, EnumPrefs.CONFIRM_QUIT))
        {
            (new ConfirmDialog() {

                public void onPositiveButtonClick()
                {
                    finish();
                }

                final ViewPagerActivity this$0;

            
            {
                this$0 = ViewPagerActivity.this;
                super();
            }
            }
).build(this, 0x7f070036, 0x7f0700e7);
            return true;
        } else
        {
            removeLastViewPager();
            return super.onKeyDown(4, keyevent);
        }
_L4:
        keyevent = postCardManager;
        if(!postCardManager.isMenuVisible())
            flag = true;
        keyevent.setMenuVisibility(flag);
        findViewById(0x7f0e009b).requestFocus();
        return true;
_L3:
        flag = flag1;
        if(PreferenceUtil.getIntPreference(this, EnumPrefs.CAMERA_BUTTON) == EnumCameraButton.CAMERA_BUTTON.ordinal())
            if(PermissionUtil.checkSelfPermissions(this, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
            {
                launchCamera();
                return true;
            } else
            {
                requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 12);
                return true;
            }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
label0:
        {
label1:
            {
                super.onRequestPermissionsResult(i, as, ai);
                if((i == 12 || i == 11) && ai.length > 0)
                {
                    if(!PermissionUtil.verifyPermissions(ai))
                        break label0;
                    if(i != 12)
                        break label1;
                    launchCamera();
                }
                return;
            }
            pickImage();
            return;
        }
        Notificator.toast(this, 0x7f070082);
    }

    protected void onResume()
    {
        super.onResume();
        setFullScreen();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if(postCardManager != null)
            bundle.putString("postcard_text", postCardManager.getText());
    }

    public void pickImage()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    public void requestPermission(String s, int i)
    {
        if(SystemVersion.isMarshmallowOrLater())
            (new RequestPermission()).request(this, s, i);
    }

    protected void setFragment(Fragment fragment, String s, int i)
    {
        Object obj = getSupportFragmentManager();
        Fragment fragment1 = ((FragmentManager) (obj)).findFragmentByTag(s);
        obj = ((FragmentManager) (obj)).beginTransaction();
        if(fragment1 == null)
            ((FragmentTransaction) (obj)).replace(i, Fragment.instantiate(this, fragment.getClass().getName(), fragment.getArguments()), s);
        else
            ((FragmentTransaction) (obj)).attach(fragment1);
        ((FragmentTransaction) (obj)).commit();
    }

    public static final int PERMISSION_REQUEST_CAPTURE = 12;
    public static final int PERMISSION_REQUEST_PICK = 11;
    private static final int REQUEST_CAPTURE = 2;
    private static final int REQUEST_PICK = 1;
    protected static final String TAG_CONVERSATION_FRAGMENT = "conversationFragment";
    protected static final String TAG_DIRECT_MESSAGE_CONVERSATION_FRAGMENT = "directMessageConversationFragment";
    protected static final String TAG_FRAGMENT = "rightFragment";
    protected static final String TAG_LEFT_FRAGMENT = "leftFragment";
    protected static final String TAG_SEARCH_FRAGMENT = "searchFragment";
    protected static final String TAG_USER_PAGE_FRAGMENT = "userPageFragment";
    private Uri cameraUri;
    private PostCardManager postCardManager;
    private StatusCardManager statusCardManager;
    private UploadCardManager uploadCardManager;
    private Deque viewPagerStack;

}
