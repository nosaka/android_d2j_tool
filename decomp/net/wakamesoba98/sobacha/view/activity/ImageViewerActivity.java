// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import it.sephiroth.android.library.imagezoom.ViewPagerControllable;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.view.activity.base.TranslucentThemeFragmentActivity;
import net.wakamesoba98.sobacha.view.card.ViewerCardManager;
import net.wakamesoba98.sobacha.view.fragment.parent.ImageViewerPagerAdapter;
import net.wakamesoba98.sobacha.view.fullscreen.FullScreen;
import net.wakamesoba98.sobacha.view.viewpager.TouchControllableViewPager;

public class ImageViewerActivity extends TranslucentThemeFragmentActivity
    implements ViewPagerControllable
{

    public ImageViewerActivity()
    {
    }

    private void cardInitialize(String s, String as[], int i)
    {
        viewerCardManager = new ViewerCardManager(this);
        viewerCardManager.setPosition(i);
        viewerCardManager.setURLs(as);
        viewerCardManager.setScreenName(s);
    }

    private void pageInitialize(int i, int j)
    {
        TextView textview = (TextView)findViewById(0x7f0e007c);
        if(j > 1)
        {
            textview.setVisibility(0);
            textview.setText((new StringBuilder()).append(i + 1).append(" / ").append(j).toString());
            return;
        } else
        {
            textview.setVisibility(8);
            return;
        }
    }

    private void setFullScreen()
    {
        FullScreen fullscreen = new FullScreen();
        fullscreen.hideNotificationBar(this);
        fullscreen.hideSoftGuide();
    }

    private void viewPagerInitialize(Bundle bundle, String as[], int i, final int total)
    {
        bundle = new ImageViewerPagerAdapter(getSupportFragmentManager(), as, bundle);
        viewPager.setAdapter(bundle);
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int j)
            {
            }

            public void onPageScrolled(int j, float f, int k)
            {
            }

            public void onPageSelected(int j)
            {
                viewerCardManager.setPosition(j);
                ((TextView)findViewById(0x7f0e007c)).setText((new StringBuilder()).append(j + 1).append(" / ").append(total).toString());
            }

            final ImageViewerActivity this$0;
            final int val$total;

            
            {
                this$0 = ImageViewerActivity.this;
                total = i;
                super();
            }
        }
);
        viewPager.setCurrentItem(i, false);
    }

    public void finish()
    {
        super.finish();
        if(Flavor.isMateCha())
            overridePendingTransition(0x7f040010, 0x7f040012);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001d);
        viewPager = (TouchControllableViewPager)findViewById(0x7f0e007a);
        bundle = getIntent();
        if(bundle != null)
            if((bundle = bundle.getExtras()) != null)
            {
                String s = bundle.getString("screen_name");
                String as[] = bundle.getStringArray("image_urls");
                int j = bundle.getInt("position");
                int i = 1;
                if(as != null)
                    i = as.length;
                cardInitialize(s, as, j);
                viewPagerInitialize(bundle, as, j, i);
                pageInitialize(j, i);
                return;
            }
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
label0:
        {
            super.onRequestPermissionsResult(i, as, ai);
            if(i == 1 && ai.length > 0)
            {
                if(!PermissionUtil.verifyPermissions(ai))
                    break label0;
                viewerCardManager.downloadImage();
            }
            return;
        }
        Notificator.toast(this, 0x7f070082);
    }

    protected void onResume()
    {
        super.onResume();
        setFullScreen();
    }

    public void putImageView(int i, ImageView imageview)
    {
        viewerCardManager.putImageView(i, imageview);
    }

    public void removeImageView(int i)
    {
        viewerCardManager.removeImageView(i);
    }

    public void requestPermission(String s, int i)
    {
        if(SystemVersion.isMarshmallowOrLater())
            (new RequestPermission()).request(this, s, i);
    }

    public void switchEnable(boolean flag)
    {
        if(isViewPagerEnabled != flag)
        {
            isViewPagerEnabled = flag;
            viewPager.setTouchEnable(flag);
        }
    }

    public static final int PERMISSION_REQUEST_DOWNLOAD = 1;
    private boolean isViewPagerEnabled;
    private TouchControllableViewPager viewPager;
    private ViewerCardManager viewerCardManager;

}
