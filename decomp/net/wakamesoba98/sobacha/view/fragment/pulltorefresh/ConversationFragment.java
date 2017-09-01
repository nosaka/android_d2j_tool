// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class ConversationFragment extends PullToRefreshFragment
{

    public ConversationFragment()
    {
    }

    protected void initTimeLine()
    {
        showRefreshing();
        (new TimeLineApi(getActivity(), adapter, userAccount)).showConversation(this, statusId);
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        statusId = getArguments().getLong("status_id");
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.CONVERSATION);
        setAdapterToListView(adapter);
        setRefreshEnabled(false);
        setHasNext(false);
        initTimeLine();
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
    }

    public void readMore(int i)
    {
    }

    private long statusId;
}
