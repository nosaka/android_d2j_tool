// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.view.*;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.*;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.PullToRefreshFragment;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.adapter:
//            StatusAdapter

public class AnimationStatusAdapter extends StatusAdapter
    implements android.view.View.OnTouchListener
{

    public AnimationStatusAdapter(ViewPagerActivity viewpageractivity, int i, List list)
    {
        super(viewpageractivity, i, list);
        isAnimationMode = true;
        stopInsertByKey = false;
        statusQueue = new LinkedList();
    }

    public void insertWithAnimation(StatusItem statusitem, int i)
    {
        if(!stopInsertByKey) goto _L2; else goto _L1
_L1:
        statusQueue.offer(statusitem);
_L4:
        return;
_L2:
        super.insert(statusitem, i);
        if(listView == null || listView.getChildAt(1) == null || listView.getCount() <= 1) goto _L4; else goto _L3
_L3:
        if(!isAnimationMode)
            continue; /* Loop/switch isn't completed */
        if(listView.getFirstVisiblePosition() == 0)
            smoothScrollToTop = true;
        listView.setSelectionFromTop(listView.getFirstVisiblePosition() + 1, listView.getChildAt(0).getTop());
        if(!smoothScrollToTop) goto _L4; else goto _L5
_L5:
        listView.smoothScrollToPosition(0);
        return;
        if(listView.getFirstVisiblePosition() <= 0) goto _L4; else goto _L6
_L6:
        listView.setSelectionFromTop(listView.getFirstVisiblePosition() + 1, listView.getChildAt(0).getTop());
        return;
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        switch(i)
        {
        default:
            return super.onKey(view, i, keyevent);

        case 19: // '\023'
        case 20: // '\024'
            break;
        }
        if(listView.getSelectedItemPosition() < 2)
        {
            releaseQueue();
            return false;
        } else
        {
            smoothScrollToTop = false;
            stopInsertByKey = true;
            return false;
        }
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        i;
        JVM INSTR tableswitch 0 0: default 20
    //                   0 21;
           goto _L1 _L2
_L1:
        return;
_L2:
        if(abslistview.getFirstVisiblePosition() == 0)
        {
            smoothScrollToTop = true;
            releaseQueue();
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        switch(motionevent.getAction())
        {
        default:
            return false;

        case 2: // '\002'
            smoothScrollToTop = false;
            break;
        }
        return false;
    }

    public void releaseQueue()
    {
        stopInsertByKey = false;
        int j = statusQueue.size();
        int k = listView.getFirstVisiblePosition();
        int i = 0;
        if(listView.getChildCount() > 0)
            i = listView.getChildAt(0).getTop();
        if(j > 0)
        {
            for(; statusQueue.peek() != null; insert((StatusItem)statusQueue.poll(), 0));
            listView.setSelectionFromTop(k + j, i);
        }
    }

    public void setAnimationMode(boolean flag)
    {
        isAnimationMode = flag;
    }

    public void setFragment(PullToRefreshFragment pulltorefreshfragment, ListView listview, View view)
    {
        super.setFragment(pulltorefreshfragment, listview, view);
        listview.setOnTouchListener(this);
    }

    private boolean isAnimationMode;
    private boolean smoothScrollToTop;
    private Queue statusQueue;
    private boolean stopInsertByKey;
}
