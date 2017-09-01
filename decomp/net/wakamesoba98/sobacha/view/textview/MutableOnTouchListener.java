// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.textview;

import android.text.Layout;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package net.wakamesoba98.sobacha.view.textview:
//            MutableLinkMovementMethod

public class MutableOnTouchListener
    implements android.view.View.OnTouchListener
{

    public MutableOnTouchListener()
    {
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        view = (TextView)view;
        Object obj = new MutableLinkMovementMethod();
        view.setMovementMethod(((android.text.method.MovementMethod) (obj)));
        boolean flag = ((MutableLinkMovementMethod) (obj)).onTouchEvent(view, (Spannable)view.getText(), motionevent);
        view.setMovementMethod(null);
        view.setFocusable(false);
        obj = android.text.Spannable.Factory.getInstance().newSpannable(view.getText());
        int i = motionevent.getAction();
        if(i == 1 || i == 0)
        {
            int l = (int)motionevent.getX();
            int j = (int)motionevent.getY();
            int i1 = view.getTotalPaddingLeft();
            int k = view.getTotalPaddingTop();
            l = (l - i1) + view.getScrollX();
            i1 = view.getScrollY();
            view = view.getLayout();
            j = view.getLineForVertical((j - k) + i1);
            k = view.getOffsetForHorizontal(j, l);
            if((float)l < view.getLineMax(j) && ((ClickableSpan[])((Spannable) (obj)).getSpans(0, k, android/text/style/ClickableSpan)).length > 0)
                return flag;
        }
        return false;
    }
}
