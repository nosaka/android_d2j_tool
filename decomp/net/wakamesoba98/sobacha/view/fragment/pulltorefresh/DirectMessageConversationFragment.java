// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import net.wakamesoba98.sobacha.twitter.api.timeline.DirectMessageApi;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class DirectMessageConversationFragment extends PullToRefreshFragment
{

    public DirectMessageConversationFragment()
    {
    }

    protected void initTimeLine()
    {
        showRefreshing();
        (new DirectMessageApi(getActivity(), adapter, userAccount)).getDirectMessageConversation(this, targetUserId);
    }

    public void loadedTimeLine(boolean flag, long l)
    {
        super.loadedTimeLine(flag, l);
        if(getView() != null)
        {
            ListView listview = (ListView)getView().findViewById(0x7f0e00dd);
            listview.setSelection(listview.getAdapter().getCount());
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        targetUserId = getArguments().getLong("user_id");
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.DM_CONVERSATION);
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

    private long targetUserId;
}
