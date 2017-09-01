// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import java.util.*;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;

// Referenced classes of package net.wakamesoba98.sobacha.view.card:
//            UploadCard

public class UploadCardManager
{

    public UploadCardManager(Activity activity1)
    {
        count = 0;
        activity = activity1;
        uploadCardList = new ArrayList();
        parent = (LinearLayout)activity1.findViewById(0x7f0e00b5);
    }

    void add(Uri uri, Bitmap bitmap)
    {
        UploadCard uploadcard = new UploadCard(activity, count, this);
        count = count + 1;
        uploadcard.setUploadImage(uri);
        uploadcard.setBitmap(bitmap);
        uploadCardList.add(uploadcard);
        int i = DisplayMetricsUtil.convertDipToPixel(activity, 72);
        uri = uploadcard.getView();
        uri.setLayoutParams(new android.widget.LinearLayout.LayoutParams(i, i));
        parent.addView(uri);
    }

    int getCount()
    {
        return count;
    }

    Uri[] getUploadImages()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = uploadCardList.iterator(); iterator.hasNext(); arraylist.add(((UploadCard)iterator.next()).getUploadImage()));
        return (Uri[])arraylist.toArray(new Uri[arraylist.size()]);
    }

    void remove(int i)
    {
        if(count > 0)
        {
            ((UploadCard)uploadCardList.get(i)).recycle();
            parent.removeView(((UploadCard)uploadCardList.get(i)).getView());
            uploadCardList.remove(i);
            count = count - 1;
            while(i < count) 
            {
                ((UploadCard)uploadCardList.get(i)).setPosition(i);
                i++;
            }
        }
    }

    void removeAll()
    {
        UploadCard uploadcard;
        for(Iterator iterator = uploadCardList.iterator(); iterator.hasNext(); parent.removeView(uploadcard.getView()))
        {
            uploadcard = (UploadCard)iterator.next();
            uploadcard.recycle();
        }

        uploadCardList.clear();
        count = 0;
    }

    private Activity activity;
    private int count;
    private LinearLayout parent;
    private List uploadCardList;
}
