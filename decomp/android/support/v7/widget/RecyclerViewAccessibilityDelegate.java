// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat
{

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerview)
    {
        mRecyclerView = recyclerview;
    }

    public AccessibilityDelegateCompat getItemDelegate()
    {
        return mItemDelegate;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/v7/widget/RecyclerView.getName());
        if((view instanceof RecyclerView) && !shouldIgnore())
        {
            view = (RecyclerView)view;
            if(view.getLayoutManager() != null)
                view.getLayoutManager().onInitializeAccessibilityEvent(accessibilityevent);
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        accessibilitynodeinfocompat.setClassName(android/support/v7/widget/RecyclerView.getName());
        if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
            mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(accessibilitynodeinfocompat);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if(super.performAccessibilityAction(view, i, bundle))
            return true;
        if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
            return mRecyclerView.getLayoutManager().performAccessibilityAction(i, bundle);
        else
            return false;
    }

    boolean shouldIgnore()
    {
        return mRecyclerView.hasPendingAdapterUpdates();
    }

    final AccessibilityDelegateCompat mItemDelegate = new AccessibilityDelegateCompat() {

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
                mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, accessibilitynodeinfocompat);
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if(super.performAccessibilityAction(view, i, bundle))
                return true;
            if(!shouldIgnore() && mRecyclerView.getLayoutManager() != null)
                return mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i, bundle);
            else
                return false;
        }

        final RecyclerViewAccessibilityDelegate this$0;

            
            {
                this$0 = RecyclerViewAccessibilityDelegate.this;
                super();
            }
    }
;
    final RecyclerView mRecyclerView;
}
