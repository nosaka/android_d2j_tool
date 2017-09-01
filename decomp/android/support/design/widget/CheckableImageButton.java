// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

public class CheckableImageButton extends AppCompatImageButton
    implements Checkable
{

    public CheckableImageButton(Context context)
    {
        this(context, null);
    }

    public CheckableImageButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {

            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                super.onInitializeAccessibilityEvent(view, accessibilityevent);
                accessibilityevent.setChecked(isChecked());
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
                accessibilitynodeinfocompat.setCheckable(true);
                accessibilitynodeinfocompat.setChecked(isChecked());
            }

            final CheckableImageButton this$0;

            
            {
                this$0 = CheckableImageButton.this;
                super();
            }
        }
);
    }

    public boolean isChecked()
    {
        return mChecked;
    }

    public int[] onCreateDrawableState(int i)
    {
        if(mChecked)
            return mergeDrawableStates(super.onCreateDrawableState(DRAWABLE_STATE_CHECKED.length + i), DRAWABLE_STATE_CHECKED);
        else
            return super.onCreateDrawableState(i);
    }

    public void setChecked(boolean flag)
    {
        if(mChecked != flag)
        {
            mChecked = flag;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public void toggle()
    {
        boolean flag;
        if(!mChecked)
            flag = true;
        else
            flag = false;
        setChecked(flag);
    }

    private static final int DRAWABLE_STATE_CHECKED[] = {
        0x10100a0
    };
    private boolean mChecked;

}
