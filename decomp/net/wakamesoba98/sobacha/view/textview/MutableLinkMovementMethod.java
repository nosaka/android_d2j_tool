// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.textview;

import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

class MutableLinkMovementMethod extends LinkMovementMethod
{

    MutableLinkMovementMethod()
    {
    }

    public boolean onTouchEvent(TextView textview, Spannable spannable, MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if(i == 1 || i == 0)
        {
            int j = (int)motionevent.getX();
            int k = (int)motionevent.getY();
            int l = textview.getTotalPaddingLeft();
            int i1 = textview.getTotalPaddingTop();
            int j1 = textview.getScrollX();
            int k1 = textview.getScrollY();
            motionevent = textview.getLayout();
            j = motionevent.getOffsetForHorizontal(motionevent.getLineForVertical((k - i1) + k1), (j - l) + j1);
            motionevent = (ClickableSpan[])spannable.getSpans(j, j, android/text/style/ClickableSpan);
            if(motionevent.length != 0)
                if(i == 1)
                {
                    motionevent[0].onClick(textview);
                    return true;
                } else
                {
                    Selection.setSelection(spannable, spannable.getSpanStart(motionevent[0]), spannable.getSpanEnd(motionevent[0]));
                    return true;
                }
            Selection.removeSelection(spannable);
        }
        return false;
    }
}
