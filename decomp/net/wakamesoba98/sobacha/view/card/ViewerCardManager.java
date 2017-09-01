// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable;
import java.io.File;
import java.util.HashMap;
import net.wakamesoba98.sobacha.compatible.PermissionUtil;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.image.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.twitter.listener.OnDownloadedListener;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.view.activity.ImageViewerActivity;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public class ViewerCardManager
    implements android.view.View.OnClickListener, OnDownloadedListener
{

    public ViewerCardManager(ImageViewerActivity imagevieweractivity)
    {
        activity = imagevieweractivity;
        imageViewMap = new HashMap();
        viewGroupCard = (ViewGroup)imagevieweractivity.findViewById(0x7f0e00bb);
        setButtonProperties();
        isBackground = PreferenceUtil.getBooleanPreference(imagevieweractivity, EnumPrefs.BACKGROUND_DOWNLOAD);
    }

    private void dismissProgress()
    {
        View view = activity.findViewById(0x7f0e0133);
        if(view != null && !isBackground)
            view.setVisibility(8);
    }

    private int getButtonResourceId(View view)
    {
        switch(view.getId())
        {
        default:
            return 0;

        case 2131624124: 
            return 0x7f02006d;

        case 2131624125: 
            return 0x7f0200f4;

        case 2131624126: 
            return 0x7f0200d3;

        case 2131624127: 
            return 0x7f0200f6;

        case 2131624128: 
            return 0x7f020091;
        }
    }

    private void rotate(int i)
    {
        ImageView imageview = (ImageView)imageViewMap.get(Integer.valueOf(position));
        if(imageview != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
        if(!(imageview instanceof ImageViewTouch))
            break; /* Loop/switch isn't completed */
        if((obj = (FastBitmapDrawable)imageview.getDrawable()) == null)
            continue; /* Loop/switch isn't completed */
        obj = ((FastBitmapDrawable) (obj)).getBitmap();
_L5:
        if(obj != null)
        {
            imageview.setImageBitmap((new ImageEditor()).rotateBitmap(((android.graphics.Bitmap) (obj)), i));
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
        obj = (BitmapDrawable)imageview.getDrawable();
        if(obj == null) goto _L1; else goto _L4
_L4:
        obj = ((BitmapDrawable) (obj)).getBitmap();
          goto _L5
    }

    private void setButtonProperties()
    {
        ThemeManager thememanager = new ThemeManager(activity);
        for(int i = 0; i < viewGroupCard.getChildCount(); i++)
        {
            View view = viewGroupCard.getChildAt(i);
            if(view instanceof ImageButton)
            {
                ImageButton imagebutton = (ImageButton)view;
                imagebutton.setImageResource(thememanager.getThemeDrawableId(getButtonResourceId(view)));
                imagebutton.setOnClickListener(this);
            }
        }

    }

    private void showProgress()
    {
        View view = activity.findViewById(0x7f0e0133);
        if(view != null && !isBackground)
            view.setVisibility(0);
    }

    public void downloadImage()
    {
        showProgress();
        MediaURL mediaurl = new MediaURL(urls[position]);
        SobaChaApplication sobachaapplication = (SobaChaApplication)activity.getApplication();
        if(mediaurl.getUrl(false) != null)
            (new ImageDownloadTask(activity, this, mediaurl, screenName, sobachaapplication.getUserAccount())).execute(new Void[0]);
    }

    public void downloaded(File file)
    {
        dismissProgress();
        if(file != null)
        {
            Notificator.toast(activity, 0x7f07004a);
            (new UriResolver()).updateMediaScanner(activity, Uri.fromFile(file));
            return;
        } else
        {
            Notificator.toast(activity, 0x7f07007f);
            return;
        }
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
        default:
            return;

        case 2131624124: 
            activity.finish();
            return;

        case 2131624125: 
            rotate(-90);
            return;

        case 2131624126: 
            view = new Intent("android.intent.action.VIEW", Uri.parse(urls[position]));
            activity.startActivity(view);
            return;

        case 2131624127: 
            rotate(90);
            return;

        case 2131624128: 
            break;
        }
        if(PermissionUtil.checkSelfPermissions(activity, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
        {
            downloadImage();
            return;
        } else
        {
            activity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
            return;
        }
    }

    public void putImageView(int i, ImageView imageview)
    {
        imageViewMap.put(Integer.valueOf(i), imageview);
    }

    public void removeImageView(int i)
    {
        imageViewMap.remove(Integer.valueOf(i));
    }

    public void setPosition(int i)
    {
        position = i;
    }

    public void setScreenName(String s)
    {
        screenName = s;
    }

    public void setURLs(String as[])
    {
        urls = as;
    }

    private ImageViewerActivity activity;
    private HashMap imageViewMap;
    private boolean isBackground;
    private int position;
    private String screenName;
    private String urls[];
    private ViewGroup viewGroupCard;
}
