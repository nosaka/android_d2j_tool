// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.AccountHolder;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.tab.*;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.Relationship;
import twitter4j.User;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.parent:
//            SectionsPagerAdapter

public class ViewPagerFragment extends Fragment
    implements AdapterHolder, AccountHolder
{

    public ViewPagerFragment()
    {
    }

    private void tabInitialize(List list)
    {
        LinearLayout linearlayout = (LinearLayout)getView().findViewById(0x7f0e00ee);
        tabManager = (new TabListCreator()).createTab(getActivity(), this, list, linearlayout, viewPager);
        ((ViewPagerActivity)getActivity()).addViewPagerToLast(viewPager);
        boolean flag = PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.FULL_SCREEN);
        boolean flag1 = PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.HIDE_ACTION_BAR);
        if(flag && flag1)
            linearlayout.setVisibility(8);
    }

    private void viewPagerInitialize(Bundle bundle)
    {
        bundle = new SectionsPagerAdapter(getChildFragmentManager(), tabList, bundle);
        viewPager.setAdapter(bundle);
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i)
            {
            }

            public void onPageScrolled(int i, float f, int j)
            {
            }

            public void onPageSelected(int i)
            {
                tabManager.setHighlightButton(i);
            }

            final ViewPagerFragment this$0;

            
            {
                this$0 = ViewPagerFragment.this;
                super();
            }
        }
);
        viewPager.setCurrentItem(0, false);
    }

    public void addListView(ListView listview, int i)
    {
        listViewMap.put(Integer.valueOf(i), listview);
    }

    public StatusAdapter getAdapter(EnumTab enumtab)
    {
        StatusAdapter statusadapter = (StatusAdapter)adapterMap.get(enumtab);
        if(statusadapter == null)
        {
            statusadapter = new StatusAdapter((ViewPagerActivity)getActivity(), 0x7f03004e, new ArrayList());
            ((SobaChaApplication)getActivity().getApplication()).registerAdapter(statusadapter);
            adapterMap.put(enumtab, statusadapter);
            return (StatusAdapter)adapterMap.get(enumtab);
        } else
        {
            return statusadapter;
        }
    }

    public ListView getListView(int i)
    {
        return (ListView)listViewMap.get(Integer.valueOf(i));
    }

    public Relationship getRelationship()
    {
        return relationship;
    }

    public User getUser()
    {
        return user;
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        user = null;
        relationship = null;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        viewPager = (ViewPager)getView().findViewById(0x7f0e00ef);
        bundle = new ThemeManager(getActivity());
        getView().findViewById(0x7f0e00ed).setBackgroundColor(bundle.getThemeColor(0x7f0d004a));
        listViewMap = new HashMap();
        adapterMap = new HashMap();
        bundle = getArguments();
        tabList = (new TabListCreator()).getTabList(EnumViewPagerFragment.values()[bundle.getInt("activity")]);
        tabInitialize(tabList);
        viewPagerInitialize(bundle);
        ((SobaChaApplication)getActivity().getApplication()).registerAccountHolder(this);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030049, viewgroup, false);
    }

    public void onDetach()
    {
        super.onDetach();
        if(getActivity() != null && adapterMap != null)
        {
            SobaChaApplication sobachaapplication = (SobaChaApplication)getActivity().getApplication();
            EnumTab enumtab;
            for(Iterator iterator = adapterMap.keySet().iterator(); iterator.hasNext(); sobachaapplication.releaseAdapter((StatusAdapter)adapterMap.get(enumtab)))
                enumtab = (EnumTab)iterator.next();

            sobachaapplication.releaseAccountHolder(this);
        }
    }

    public void setRelationship(Relationship relationship1)
    {
        relationship = relationship1;
    }

    public void setUser(User user1)
    {
        user = user1;
    }

    private Map adapterMap;
    private Map listViewMap;
    private Relationship relationship;
    private List tabList;
    private TabManager tabManager;
    private User user;
    private ViewPager viewPager;

}
