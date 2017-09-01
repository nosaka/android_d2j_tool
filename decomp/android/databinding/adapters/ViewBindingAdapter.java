// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.graphics.drawable.Drawable;
import android.view.View;

// Referenced classes of package android.databinding.adapters:
//            ListenerUtil

public class ViewBindingAdapter
{
    public static interface OnViewAttachedToWindow
    {

        public abstract void onViewAttachedToWindow(View view);
    }

    public static interface OnViewDetachedFromWindow
    {

        public abstract void onViewDetachedFromWindow(View view);
    }


    public ViewBindingAdapter()
    {
    }

    private static int pixelsToDimensionPixelSize(float f)
    {
        int i = (int)(0.5F + f);
        if(i != 0)
            return i;
        if(f == 0.0F)
            return 0;
        return f <= 0.0F ? -1 : 1;
    }

    public static void setBackground(View view, Drawable drawable)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
        {
            view.setBackground(drawable);
            return;
        } else
        {
            view.setBackgroundDrawable(drawable);
            return;
        }
    }

    public static void setClickListener(View view, android.view.View.OnClickListener onclicklistener, boolean flag)
    {
        view.setOnClickListener(onclicklistener);
        view.setClickable(flag);
    }

    public static void setOnAttachStateChangeListener(View view, OnViewDetachedFromWindow onviewdetachedfromwindow, OnViewAttachedToWindow onviewattachedtowindow)
    {
        if(android.os.Build.VERSION.SDK_INT >= 12)
        {
            if(onviewdetachedfromwindow == null && onviewattachedtowindow == null)
                onviewdetachedfromwindow = null;
            else
                onviewdetachedfromwindow = new android.view.View.OnAttachStateChangeListener(onviewattachedtowindow, onviewdetachedfromwindow) {

                    public void onViewAttachedToWindow(View view1)
                    {
                        if(attach != null)
                            attach.onViewAttachedToWindow(view1);
                    }

                    public void onViewDetachedFromWindow(View view1)
                    {
                        if(detach != null)
                            detach.onViewDetachedFromWindow(view1);
                    }

                    final OnViewAttachedToWindow val$attach;
                    final OnViewDetachedFromWindow val$detach;

            
            {
                attach = onviewattachedtowindow;
                detach = onviewdetachedfromwindow;
                super();
            }
                }
;
            onviewattachedtowindow = (android.view.View.OnAttachStateChangeListener)ListenerUtil.trackListener(view, onviewdetachedfromwindow, com.android.databinding.library.baseAdapters.R.id.onAttachStateChangeListener);
            if(onviewattachedtowindow != null)
                view.removeOnAttachStateChangeListener(onviewattachedtowindow);
            if(onviewdetachedfromwindow != null)
                view.addOnAttachStateChangeListener(onviewdetachedfromwindow);
        }
    }

    public static void setOnClick(View view, android.view.View.OnClickListener onclicklistener, boolean flag)
    {
        view.setOnClickListener(onclicklistener);
        view.setClickable(flag);
    }

    public static void setOnLayoutChangeListener(View view, android.view.View.OnLayoutChangeListener onlayoutchangelistener, android.view.View.OnLayoutChangeListener onlayoutchangelistener1)
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            if(onlayoutchangelistener != null)
                view.removeOnLayoutChangeListener(onlayoutchangelistener);
            if(onlayoutchangelistener1 != null)
                view.addOnLayoutChangeListener(onlayoutchangelistener1);
        }
    }

    public static void setOnLongClick(View view, android.view.View.OnLongClickListener onlongclicklistener, boolean flag)
    {
        view.setOnLongClickListener(onlongclicklistener);
        view.setLongClickable(flag);
    }

    public static void setOnLongClickListener(View view, android.view.View.OnLongClickListener onlongclicklistener, boolean flag)
    {
        view.setOnLongClickListener(onlongclicklistener);
        view.setLongClickable(flag);
    }

    public static void setPadding(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        view.setPadding(i, i, i, i);
    }

    public static void setPaddingBottom(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
    }

    public static void setPaddingEnd(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), i, view.getPaddingBottom());
            return;
        } else
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), i, view.getPaddingBottom());
            return;
        }
    }

    public static void setPaddingLeft(View view, float f)
    {
        view.setPadding(pixelsToDimensionPixelSize(f), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingRight(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), i, view.getPaddingBottom());
    }

    public static void setPaddingStart(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        if(android.os.Build.VERSION.SDK_INT >= 17)
        {
            view.setPaddingRelative(i, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
            return;
        } else
        {
            view.setPadding(i, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            return;
        }
    }

    public static void setPaddingTop(View view, float f)
    {
        int i = pixelsToDimensionPixelSize(f);
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setRequiresFadingEdge(View view, int i)
    {
        boolean flag;
        boolean flag1;
        if((FADING_EDGE_VERTICAL & i) != 0)
            flag = true;
        else
            flag = false;
        if((FADING_EDGE_HORIZONTAL & i) != 0)
            flag1 = true;
        else
            flag1 = false;
        view.setVerticalFadingEdgeEnabled(flag);
        view.setHorizontalFadingEdgeEnabled(flag1);
    }

    public static int FADING_EDGE_HORIZONTAL = 1;
    public static int FADING_EDGE_NONE = 0;
    public static int FADING_EDGE_VERTICAL = 2;

}
