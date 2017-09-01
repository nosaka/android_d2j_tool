// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.edittext;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.*;
import android.widget.EditText;
import net.wakamesoba98.sobacha.view.fullscreen.FullScreen;

public class ImeDetectableEditText extends EditText
    implements android.view.View.OnTouchListener
{

    public ImeDetectableEditText(Context context1)
    {
        super(context1);
        context = context1;
        setOnTouchListener(this);
    }

    public ImeDetectableEditText(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        context = context1;
        setOnTouchListener(this);
    }

    public ImeDetectableEditText(Context context1, AttributeSet attributeset, int i)
    {
        super(context1, attributeset, i);
        context = context1;
        setOnTouchListener(this);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 4 && keyevent.getAction() == 1 && (context instanceof Activity))
        {
            Activity activity = (Activity)context;
            FullScreen fullscreen = new FullScreen();
            fullscreen.hideNotificationBar(activity);
            fullscreen.hideNavigationBar(activity);
        }
        return super.onKeyPreIme(i, keyevent);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0 && (context instanceof Activity))
        {
            view = (Activity)context;
            (new FullScreen()).showNotificationBar(view);
        }
        return false;
    }

    private Context context;
}
