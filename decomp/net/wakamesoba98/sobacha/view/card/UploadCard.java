// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

// Referenced classes of package net.wakamesoba98.sobacha.view.card:
//            UploadCardManager

class UploadCard
    implements android.view.View.OnClickListener
{

    UploadCard(Activity activity1, int i, UploadCardManager uploadcardmanager)
    {
        activity = activity1;
        view = activity1.getLayoutInflater().inflate(0x7f03002d, null);
        position = i;
        uploadCardManager = uploadcardmanager;
        setButtonProperties();
    }

    private void setButtonProperties()
    {
        ThemeManager thememanager = new ThemeManager(activity);
        ImageButton imagebutton = (ImageButton)view.findViewById(0x7f0e00b4);
        imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f02006f));
        imagebutton.setOnClickListener(this);
    }

    Uri getUploadImage()
    {
        return uploadImage;
    }

    View getView()
    {
        return view;
    }

    public void onClick(View view1)
    {
        switch(view1.getId())
        {
        default:
            return;

        case 2131624116: 
            uploadImage = null;
            break;
        }
        uploadCardManager.remove(position);
    }

    void recycle()
    {
        ((ImageView)view.findViewById(0x7f0e00b3)).setImageDrawable(null);
        bitmap.recycle();
    }

    void setBitmap(Bitmap bitmap1)
    {
        bitmap = bitmap1;
        ((ImageView)view.findViewById(0x7f0e00b3)).setImageBitmap(bitmap);
    }

    void setPosition(int i)
    {
        position = i;
    }

    void setUploadImage(Uri uri)
    {
        uploadImage = uri;
    }

    private Activity activity;
    private Bitmap bitmap;
    private int position;
    private UploadCardManager uploadCardManager;
    private Uri uploadImage;
    private View view;
}
