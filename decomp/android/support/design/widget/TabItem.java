// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

public final class TabItem extends View
{

    public TabItem(Context context)
    {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.design.R.styleable.TabItem);
        mText = context.getText(android.support.design.R.styleable.TabItem_android_text);
        mIcon = context.getDrawable(android.support.design.R.styleable.TabItem_android_icon);
        mCustomLayout = context.getResourceId(android.support.design.R.styleable.TabItem_android_layout, 0);
        context.recycle();
    }

    final int mCustomLayout;
    final Drawable mIcon;
    final CharSequence mText;
}
