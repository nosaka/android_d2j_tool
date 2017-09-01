// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.view.activity.DirectMessageConversationActivity;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.card.StatusCardManager;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.PullToRefreshFragment;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.adapter:
//            StatusItemView, StatusItemViewHolder

public class StatusAdapter extends ArrayAdapter
    implements android.view.View.OnKeyListener, android.widget.AbsListView.OnScrollListener
{

    public StatusAdapter(ViewPagerActivity viewpageractivity, int i, List list)
    {
        super(viewpageractivity, i, list);
        activity = viewpageractivity;
        idMap = new HashMap();
        statusItemView = new StatusItemView(viewpageractivity);
        singleLineMode = PreferenceUtil.getBooleanPreference(viewpageractivity, EnumPrefs.SINGLE_LINE_MODE);
        statusCardManager = viewpageractivity.getStatusCard();
    }

    private void addIdMap(StatusItem statusitem)
    {
        if(statusitem.getStatus() != null)
            idMap.put(Long.valueOf(statusitem.getStatus().getId()), statusitem);
        else
        if(statusitem.getDirectMessage() != null)
        {
            idMap.put(Long.valueOf(statusitem.getDirectMessage().getId()), statusitem);
            return;
        }
    }

    private void onStatusItemClick(int i, View view, StatusItem statusitem)
    {
        if(statusCardManager != null)
        {
            static class _cls2
            {

                static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

                static 
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                    try
                    {
                        $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.DIRECT_MESSAGE_USER.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.READ_MORE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror)
                    {
                        return;
                    }
                }
            }

            switch(_cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()])
            {
            default:
                statusCardManager.show(statusitem);
                return;

            case 1: // '\001'
                view = new Bundle();
                view.putLong("user_id", statusitem.getDirectMessage().getSender().getId());
                statusitem = new Intent(activity, net/wakamesoba98/sobacha/view/activity/DirectMessageConversationActivity);
                statusitem.putExtras(view);
                statusitem.setFlags(0x10000000);
                activity.startActivity(statusitem);
                return;

            case 2: // '\002'
                break;
            }
            if(fragment != null)
            {
                fragment.showRefreshing();
                fragment.readMore(i);
                return;
            }
        }
    }

    public volatile void add(Object obj)
    {
        add((StatusItem)obj);
    }

    public void add(StatusItem statusitem)
    {
        super.add(statusitem);
        addIdMap(statusitem);
    }

    public View getView(final int position, View view, ViewGroup viewgroup)
    {
        final StatusItem item = (StatusItem)getItem(position);
        if(view == null)
        {
            view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03004e, null);
            viewgroup = statusItemView.findView(view);
            view.setTag(viewgroup);
        } else
        {
            viewgroup = (StatusItemViewHolder)view.getTag();
        }
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                onStatusItemClick(position, view1, item);
            }

            final StatusAdapter this$0;
            final StatusItem val$item;
            final int val$position;

            
            {
                this$0 = StatusAdapter.this;
                position = i;
                item = statusitem;
                super();
            }
        }
);
        statusItemView.setView(item, viewgroup, singleLineMode);
        if(position == getCount() - 1)
        {
            ((StatusItemViewHolder) (viewgroup)).listSpacer.setVisibility(0);
            return view;
        } else
        {
            ((StatusItemViewHolder) (viewgroup)).listSpacer.setVisibility(8);
            return view;
        }
    }

    public volatile void insert(Object obj, int i)
    {
        insert((StatusItem)obj, i);
    }

    public void insert(StatusItem statusitem, int i)
    {
        super.insert(statusitem, i);
        addIdMap(statusitem);
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        switch(i)
        {
        default:
            return false;

        case 23: // '\027'
        case 66: // 'B'
        case 160: 
            i = listView.getSelectedItemPosition();
            break;
        }
        if(i >= 0)
        {
            onStatusItemClick(i, view, (StatusItem)getItem(i));
            focusView.requestFocus();
        }
        return true;
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
        if(k > j && k == i + j && !fragment.isRefreshing() && fragment.hasNext())
        {
            fragment.showRefreshing();
            fragment.pullToRefresh(EnumPullDirection.UP);
        }
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    public void remove(long l)
    {
        StatusItem statusitem = (StatusItem)idMap.get(Long.valueOf(l));
        if(statusitem != null)
        {
            remove(statusitem);
            idMap.remove(Long.valueOf(l));
        }
    }

    public void setFragment(PullToRefreshFragment pulltorefreshfragment, ListView listview, View view)
    {
        fragment = pulltorefreshfragment;
        focusView = view;
        listView = listview;
        listview.setOnScrollListener(this);
        listview.setOnKeyListener(this);
    }

    private ViewPagerActivity activity;
    private View focusView;
    private PullToRefreshFragment fragment;
    private Map idMap;
    ListView listView;
    private boolean singleLineMode;
    private StatusCardManager statusCardManager;
    private StatusItemView statusItemView;

}
