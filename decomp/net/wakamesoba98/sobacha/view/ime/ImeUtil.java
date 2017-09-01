// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.ime;

import android.app.Activity;
import android.text.Editable;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.fullscreen.FullScreen;

public class ImeUtil
{

    public ImeUtil()
    {
    }

    public void hideIme(Activity activity, EditText edittext)
    {
        FullScreen fullscreen = new FullScreen();
        ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        if(activity instanceof ViewPagerActivity)
        {
            fullscreen.hideNotificationBar(activity);
            fullscreen.hideNavigationBar(activity);
        }
    }

    public void showIme(Activity activity, EditText edittext)
    {
        FullScreen fullscreen = new FullScreen();
        edittext.requestFocus();
        edittext.setSelection(edittext.getText().length());
        ((InputMethodManager)activity.getSystemService("input_method")).showSoftInput(edittext, 2);
        if(activity instanceof ViewPagerActivity)
            fullscreen.showNotificationBar(activity);
    }
}
