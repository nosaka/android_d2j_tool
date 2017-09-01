// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.ListView;
import java.util.*;
import net.wakamesoba98.sobacha.database.LastPositionDatabase;
import net.wakamesoba98.sobacha.database.StatusDatabase;
import net.wakamesoba98.sobacha.database.data.ListViewPosition;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.twitter.stream.StreamController;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.AnimationStatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import twitter4j.Paging;
import twitter4j.Status;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class HomeFragment extends PullToRefreshFragment
{
    private class AsyncLoadFromDatabase extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient List doInBackground(Void avoid[])
        {
            avoid = new StatusDatabase(context, userAccount.getId(), EnumTab.HOME);
            avoid.openDatabase();
            List list = avoid.getStatusItemList(userAccount);
            avoid.closeDatabase();
            return list;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((List)obj);
        }

        protected void onPostExecute(List list)
        {
            StatusItem statusitem;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); animationAdapter.add(statusitem))
                statusitem = (StatusItem)iterator.next();

            itemCount = list.size();
            if(keepTimelinePosition)
                listView.setSelectionFromTop(0, position.getOffset());
            loadTimeline();
        }

        private Context context;
        final HomeFragment this$0;

        AsyncLoadFromDatabase(Context context1)
        {
            this$0 = HomeFragment.this;
            super();
            context = context1;
        }
    }

    private class AsyncStoreToDatabase extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((StatusItem[])aobj);
        }

        protected transient Void doInBackground(StatusItem astatusitem[])
        {
            StatusDatabase statusdatabase = new StatusDatabase(context, userAccount.getId(), EnumTab.HOME);
            statusdatabase.openDatabase();
            statusdatabase.putStatuses(astatusitem);
            statusdatabase.closeDatabase();
            return null;
        }

        private Context context;
        final HomeFragment this$0;

        AsyncStoreToDatabase(Context context1)
        {
            this$0 = HomeFragment.this;
            super();
            context = context1;
        }
    }


    public HomeFragment()
    {
    }

    private void loadTimeline()
    {
        if(getActivity() == null)
            return;
        TimeLineApi timelineapi = new TimeLineApi(getActivity(), animationAdapter, userAccount);
        Paging paging = timelineapi.getDefaultPaging();
        if(animationAdapter.getCount() > 0)
        {
            StatusItem statusitem = (StatusItem)animationAdapter.getItem(0);
            if(statusitem != null && statusitem.getStatus() != null)
                paging.setSinceId(statusitem.getStatus().getId());
        }
        timelineapi.getHomeTimeLine(this, paging, 0);
    }

    protected void initTimeLine()
    {
label0:
        {
            if(animationAdapter.getCount() == 0 && hasNext() && !isRefreshing())
            {
                first = true;
                keepTimelinePosition = PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.KEEP_POSITION);
                if(keepTimelinePosition)
                {
                    LastPositionDatabase lastpositiondatabase = new LastPositionDatabase(getActivity(), userAccount.getId(), EnumTab.HOME);
                    lastpositiondatabase.openDatabase();
                    position = lastpositiondatabase.getLastVisiblePosition();
                    lastpositiondatabase.closeDatabase();
                }
                showRefreshing();
                if(!PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.STORE_TIMELINE))
                    break label0;
                if(getActivity() != null)
                    (new AsyncLoadFromDatabase(getActivity())).execute(new Void[0]);
            }
            return;
        }
        loadTimeline();
    }

    public void loadedTimeLine(boolean flag, long l)
    {
        super.loadedTimeLine(flag, l);
        if(first)
        {
            if(keepTimelinePosition)
            {
                int i = listView.getCount() - itemCount;
                if(i > 0)
                    listView.setSelectionFromTop(i, position.getOffset());
            }
            streamController.startStream();
        }
        first = false;
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        super.onAccountChanged(useraccount);
        boolean flag;
        if(!PreferenceUtil.getBooleanPreference(getActivity(), EnumExtraPrefs.USER_STREAM))
            flag = true;
        else
            flag = false;
        setRefreshEnabled(flag);
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        animationAdapter = (AnimationStatusAdapter)((AdapterHolder)getActivity()).getAdapter(EnumTab.HOME);
        animationAdapter.setAnimationMode(PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.USE_ANIMATION));
        bundle = userAccount.getStreamManager();
        if(bundle != null)
            bundle.setRefreshLayout((SwipeRefreshLayout)getView().findViewById(0x7f0e00dc));
        boolean flag;
        if(!PreferenceUtil.getBooleanPreference(getActivity(), EnumExtraPrefs.USER_STREAM))
            flag = true;
        else
            flag = false;
        setRefreshEnabled(flag);
        listView = (ListView)getView().findViewById(0x7f0e00dd);
        bundle = getActivity().findViewById(0x7f0e00ad);
        animationAdapter.setFragment(this, listView, bundle);
        listView.setAdapter(animationAdapter);
        listView.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag1)
            {
                if(!flag1)
                    animationAdapter.releaseQueue();
            }

            final HomeFragment this$0;

            
            {
                this$0 = HomeFragment.this;
                super();
            }
        }
);
        streamController = (StreamController)getActivity();
        initTimeLine();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030045, viewgroup, false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        int k = listView.getFirstVisiblePosition();
        int i = 0;
        if(listView.getChildCount() > 0)
            i = listView.getChildAt(0).getTop();
        LastPositionDatabase lastpositiondatabase = new LastPositionDatabase(getActivity(), userAccount.getId(), EnumTab.HOME);
        lastpositiondatabase.openDatabase();
        lastpositiondatabase.putLastVisiblePosition(k, i);
        lastpositiondatabase.closeDatabase();
        if(animationAdapter != null)
        {
            ArrayList arraylist = new ArrayList();
            int l = listView.getFirstVisiblePosition();
            int i1 = Math.min(animationAdapter.getCount() - l, 100);
            for(int j = l; j < l + i1; j++)
                arraylist.add(animationAdapter.getItem(j));

            (new AsyncStoreToDatabase(getActivity().getApplicationContext())).execute(arraylist.toArray(new StatusItem[arraylist.size()]));
        }
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
        if(animationAdapter.getCount() <= 0) goto _L2; else goto _L1
_L1:
        TimeLineApi timelineapi;
        Paging paging;
        timelineapi = new TimeLineApi(getActivity(), animationAdapter, userAccount);
        paging = timelineapi.getDefaultPaging();
        if(enumpulldirection != EnumPullDirection.DOWN) goto _L4; else goto _L3
_L3:
        enumpulldirection = (StatusItem)animationAdapter.getItem(0);
        if(enumpulldirection != null)
        {
            enumpulldirection = enumpulldirection.getStatus();
            if(enumpulldirection != null)
                paging.setSinceId(enumpulldirection.getId());
        }
_L5:
        timelineapi.getHomeTimeLine(this, paging, 0);
        return;
_L4:
        enumpulldirection = (StatusItem)animationAdapter.getItem(animationAdapter.getCount() - 1);
        if(enumpulldirection != null)
            paging.setMaxId(enumpulldirection.getStatus().getId() - 1L);
        if(true) goto _L5; else goto _L2
_L2:
        hideRefreshing();
        return;
    }

    public void readMore(int i)
    {
        if(animationAdapter.getCount() > 0)
        {
            TimeLineApi timelineapi = new TimeLineApi(getActivity(), animationAdapter, userAccount);
            Paging paging = timelineapi.getDefaultPaging();
            if(i > 0)
            {
                Object obj = (StatusItem)animationAdapter.getItem(i - 1);
                if(obj != null)
                {
                    obj = ((StatusItem) (obj)).getStatus();
                    if(obj != null)
                        paging.setMaxId(((Status) (obj)).getId() - 1L);
                }
            }
            if(animationAdapter.getCount() > i + 1)
            {
                Object obj1 = (StatusItem)animationAdapter.getItem(i + 1);
                if(obj1 != null)
                {
                    obj1 = ((StatusItem) (obj1)).getStatus();
                    if(obj1 != null)
                        paging.sinceId(((Status) (obj1)).getId());
                }
            }
            timelineapi.getHomeTimeLine(this, paging, i);
        }
    }

    private static final int MAX_SAVE_STATUSES = 100;
    private AnimationStatusAdapter animationAdapter;
    private boolean first;
    private int itemCount;
    private boolean keepTimelinePosition;
    private ListView listView;
    private ListViewPosition position;
    private StreamController streamController;



/*
    static int access$102(HomeFragment homefragment, int i)
    {
        homefragment.itemCount = i;
        return i;
    }

*/




}
