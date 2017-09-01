// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public abstract class CursorPullToRefreshFragment extends PullToRefreshFragment
{

    public CursorPullToRefreshFragment()
    {
        cursor = -1L;
    }

    public void loadedTimeLine(boolean flag, long l)
    {
        super.loadedTimeLine(flag, l);
        cursor = l;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if(bundle != null)
            cursor = bundle.getLong("cursor");
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putLong("cursor", cursor);
    }

    protected long cursor;
}
