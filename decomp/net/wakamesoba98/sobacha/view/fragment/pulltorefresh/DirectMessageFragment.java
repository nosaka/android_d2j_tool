// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import net.wakamesoba98.sobacha.twitter.api.timeline.DirectMessageApi;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import twitter4j.DirectMessage;
import twitter4j.Paging;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class DirectMessageFragment extends PullToRefreshFragment
{

    public DirectMessageFragment()
    {
    }

    protected void initTimeLine()
    {
        if(adapter.getCount() == 0 && hasNext() && !isRefreshing())
        {
            DirectMessageApi directmessageapi = new DirectMessageApi(getActivity(), adapter, userAccount);
            Paging paging = directmessageapi.getDefaultPaging();
            showRefreshing();
            directmessageapi.getDirectMessages(this, paging);
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        adapter = ((AdapterHolder)getActivity()).getAdapter(EnumTab.DIRECT_MESSAGE);
        setAdapterToListView(adapter);
        initTimeLine();
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
        if(adapter.getCount() <= 0) goto _L2; else goto _L1
_L1:
        DirectMessageApi directmessageapi;
        Paging paging;
        directmessageapi = new DirectMessageApi(getActivity(), adapter, userAccount);
        paging = directmessageapi.getDefaultPaging();
        if(enumpulldirection != EnumPullDirection.DOWN) goto _L4; else goto _L3
_L3:
        enumpulldirection = (StatusItem)adapter.getItem(0);
        if(enumpulldirection != null)
            paging.setSinceId(enumpulldirection.getDirectMessage().getId());
_L5:
        directmessageapi.getDirectMessages(this, paging);
        return;
_L4:
        enumpulldirection = (StatusItem)adapter.getItem(adapter.getCount() - 1);
        if(enumpulldirection != null)
            paging.setMaxId(enumpulldirection.getDirectMessage().getId() - 1L);
        if(true) goto _L5; else goto _L2
_L2:
        hideRefreshing();
        return;
    }

    public void readMore(int i)
    {
    }
}
