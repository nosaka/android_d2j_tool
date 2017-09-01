// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            ViewOverlay

class ViewGroupOverlay extends ViewOverlay
{

    ViewGroupOverlay(Context context, ViewGroup viewgroup, View view)
    {
        super(context, viewgroup, view);
    }

    public static ViewGroupOverlay createFrom(ViewGroup viewgroup)
    {
        return (ViewGroupOverlay)ViewOverlay.createFrom(viewgroup);
    }

    public void add(View view)
    {
        mOverlayViewGroup.add(view);
    }

    public void remove(View view)
    {
        mOverlayViewGroup.remove(view);
    }
}
