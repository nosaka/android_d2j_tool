// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.ListView;
import net.wakamesoba98.sobacha.compatible.DualModeUtil;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.AccountHolder;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnTimeLineLoadedListener;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public abstract class PullToRefreshFragment extends Fragment
    implements OnTimeLineLoadedListener, AccountHolder
{

    public PullToRefreshFragment()
    {
        hasNext = true;
        isRefreshing = false;
    }

    public boolean hasNext()
    {
        return hasNext;
    }

    protected void hideRefreshing()
    {
        isRefreshing = false;
        refreshLayout.post(new Runnable() {

            public void run()
            {
                refreshLayout.setRefreshing(false);
            }

            final PullToRefreshFragment this$0;

            
            {
                this$0 = PullToRefreshFragment.this;
                super();
            }
        }
);
    }

    protected abstract void initTimeLine();

    public boolean isRefreshing()
    {
        return isRefreshing;
    }

    public void loadedTimeLine(boolean flag, long l)
    {
        setHasNext(flag);
        hideRefreshing();
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        userAccount = useraccount;
        setHasNext(true);
        initTimeLine();
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        SobaChaApplication sobachaapplication = (SobaChaApplication)getActivity().getApplication();
        userAccount = sobachaapplication.getUserAccount();
        sobachaapplication.registerAccountHolder(this);
        if(bundle != null)
        {
            hasNext = bundle.getBoolean("has_next");
            isRefreshing = bundle.getBoolean("is_refreshing");
        }
        bundle = (ListView)getView().findViewById(0x7f0e00dd);
        int i = getArguments().getInt("position");
        ((ViewPagerFragment)getParentFragment()).addListView(bundle, i);
        bundle.setFastScrollEnabled(PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.FAST_SCROLL));
        refreshLayout = (SwipeRefreshLayout)getView().findViewById(0x7f0e00dc);
        bundle = new ThemeManager(getActivity());
        i = bundle.getThemeColor(0x7f0d003c);
        int j = bundle.getThemeColor(0x7f0d008e);
        int k = bundle.getThemeColor(0x7f0d0017);
        int l = bundle.getThemeColor(0x7f0d00c6);
        refreshLayout.setColorSchemeColors(new int[] {
            i, j, k, l
        });
        refreshLayout.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {

            public void onRefresh()
            {
                pullToRefresh(EnumPullDirection.DOWN);
            }

            final PullToRefreshFragment this$0;

            
            {
                this$0 = PullToRefreshFragment.this;
                super();
            }
        }
);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        boolean flag = DualModeUtil.canUseDualModeLayout(getActivity());
        boolean flag1 = PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.DUAL_TIMELINE_MODE);
        if(flag && flag1)
            return layoutinflater.inflate(0x7f030046, viewgroup, false);
        else
            return layoutinflater.inflate(0x7f030045, viewgroup, false);
    }

    public void onDetach()
    {
        super.onDetach();
        ((SobaChaApplication)getActivity().getApplication()).releaseAccountHolder(this);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("has_next", hasNext);
        bundle.putBoolean("is_refreshing", isRefreshing);
    }

    public abstract void pullToRefresh(EnumPullDirection enumpulldirection);

    public abstract void readMore(int i);

    protected void setAdapterToListView(StatusAdapter statusadapter)
    {
        View view = getActivity().findViewById(0x7f0e00ad);
        ListView listview = (ListView)getView().findViewById(0x7f0e00dd);
        statusadapter.setFragment(this, listview, view);
        listview.setAdapter(statusadapter);
    }

    protected void setHasNext(boolean flag)
    {
        hasNext = flag;
    }

    protected void setRefreshEnabled(boolean flag)
    {
        refreshLayout.setEnabled(flag);
    }

    public void showRefreshing()
    {
        isRefreshing = true;
        refreshLayout.post(new Runnable() {

            public void run()
            {
                refreshLayout.setRefreshing(true);
            }

            final PullToRefreshFragment this$0;

            
            {
                this$0 = PullToRefreshFragment.this;
                super();
            }
        }
);
    }

    protected StatusAdapter adapter;
    private boolean hasNext;
    private boolean isRefreshing;
    private SwipeRefreshLayout refreshLayout;
    protected UserAccount userAccount;

}
