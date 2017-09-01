// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.*;
import net.wakamesoba98.sobacha.database.SavedSearchesDatabase;
import net.wakamesoba98.sobacha.database.data.IdWithNameData;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.menu.SavedSearchesMenu;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.SavedSearchApi;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.edittext.ImeDetectableEditText;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.Query;
import twitter4j.Status;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            PullToRefreshFragment

public class SearchFragment extends PullToRefreshFragment
{

    public SearchFragment()
    {
    }

    private void addHeader()
    {
        View view = getActivity().getLayoutInflater().inflate(0x7f03004a, null);
        ViewGroup viewgroup = (ViewGroup)getView().findViewById(0x7f0e00db);
        if(view != null)
            viewgroup.addView(view);
        editText = (ImeDetectableEditText)getView().findViewById(0x7f0e00f1);
    }

    private void hideIme()
    {
        (new ImeUtil()).hideIme(getActivity(), editText);
    }

    private void setHeaderProperties()
    {
        ThemeManager thememanager = new ThemeManager(getActivity());
        final ImageButton buttonSavedSearches = (ImageButton)getView().findViewById(0x7f0e00f0);
        ImageButton imagebutton = (ImageButton)getView().findViewById(0x7f0e00f2);
        buttonSavedSearches.setImageResource(thememanager.getThemeDrawableId(0x7f0200ba));
        imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f0200f8));
        imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                adapter.clear();
                search(editText.getText().toString());
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
        imagebutton.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                manageSavedSearch();
                return true;
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
        buttonSavedSearches.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view = new SavedSearchesDatabase(getActivity(), myUserId);
                view.openDatabase();
                int i = view.getCount();
                view.closeDatabase();
                if(i > 0)
                {
                    (new SavedSearchesMenu((ViewPagerActivity)getActivity(), userAccount, SearchFragment.this, editText.getText().toString())).show(buttonSavedSearches);
                    return;
                } else
                {
                    (new SavedSearchApi((ViewPagerActivity)getActivity(), userAccount)).getSavedSearches(SearchFragment.this, editText.getText().toString(), buttonSavedSearches);
                    return;
                }
            }

            final SearchFragment this$0;
            final ImageButton val$buttonSavedSearches;

            
            {
                this$0 = SearchFragment.this;
                buttonSavedSearches = imagebutton;
                super();
            }
        }
);
        buttonSavedSearches.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                view = new SavedSearchesDatabase(getActivity(), myUserId);
                view.openDatabase();
                view.deleteAllData();
                view.closeDatabase();
                (new SavedSearchApi((ViewPagerActivity)getActivity(), userAccount)).getSavedSearches(SearchFragment.this, editText.getText().toString(), buttonSavedSearches);
                return true;
            }

            final SearchFragment this$0;
            final ImageButton val$buttonSavedSearches;

            
            {
                this$0 = SearchFragment.this;
                buttonSavedSearches = imagebutton;
                super();
            }
        }
);
        editText.setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
            {
                if(i == 3)
                {
                    search(editText.getText().toString());
                    return true;
                } else
                {
                    return false;
                }
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
    }

    protected void initTimeLine()
    {
    }

    public void manageSavedSearch()
    {
        final HashMap nameIdMap = new SavedSearchesDatabase(getActivity(), myUserId);
        nameIdMap.openDatabase();
        final String name = nameIdMap.getSavedSearches();
        nameIdMap.closeDatabase();
        nameIdMap = new HashMap();
        IdWithNameData idwithnamedata;
        for(name = name.iterator(); name.hasNext(); nameIdMap.put(idwithnamedata.getName(), Long.valueOf(idwithnamedata.getId())))
            idwithnamedata = (IdWithNameData)name.next();

        name = editText.getText().toString();
        if(nameIdMap.containsKey(name))
        {
            (new ConfirmDialog() {

                public void onPositiveButtonClick()
                {
                    (new SavedSearchApi((ViewPagerActivity)getActivity(), userAccount)).destroySavedSearch(((Long)nameIdMap.get(name)).longValue());
                }

                final SearchFragment this$0;
                final String val$name;
                final Map val$nameIdMap;

            
            {
                this$0 = SearchFragment.this;
                nameIdMap = map;
                name = s;
                super();
            }
            }
).build(getActivity(), 0x7f07003d, 0x7f070041);
            return;
        } else
        {
            (new ConfirmDialog() {

                public void onPositiveButtonClick()
                {
                    (new SavedSearchApi((ViewPagerActivity)getActivity(), userAccount)).createSavedSearch(name);
                }

                final SearchFragment this$0;
                final String val$name;

            
            {
                this$0 = SearchFragment.this;
                name = s;
                super();
            }
            }
).build(getActivity(), 0x7f0700f7, 0x7f0700f9);
            return;
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        myUserId = userAccount.getId();
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.SEARCH);
        setAdapterToListView(adapter);
        addHeader();
        setHeaderProperties();
        queryString = getArguments().getString("query");
        if(queryString != null)
            search(queryString);
        if(bundle != null)
            queryString = bundle.getString("search_word");
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("search_word", queryString);
    }

    public void pullToRefresh(EnumPullDirection enumpulldirection)
    {
        if(adapter.getCount() <= 0) goto _L2; else goto _L1
_L1:
        Query query = new Query(queryString);
        if(enumpulldirection != EnumPullDirection.DOWN) goto _L4; else goto _L3
_L3:
        enumpulldirection = (StatusItem)adapter.getItem(0);
        if(enumpulldirection != null)
            query.setSinceId(enumpulldirection.getStatus().getId());
_L5:
        (new TimeLineApi(getActivity(), adapter, userAccount)).search(this, query, 0);
        return;
_L4:
        enumpulldirection = (StatusItem)adapter.getItem(adapter.getCount() - 1);
        if(enumpulldirection != null)
            query.setMaxId(enumpulldirection.getStatus().getId() - 1L);
        if(true) goto _L5; else goto _L2
_L2:
        hideRefreshing();
        return;
    }

    public void readMore(int i)
    {
    }

    public void search(String s)
    {
        if("".equals(s.trim()))
            return;
        queryString = s;
        if(!s.equals(editText.getText().toString()))
            editText.setText(s);
        adapter.clear();
        if(PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.EXCLUDE_RETWEET))
            queryString = (new StringBuilder()).append(queryString).append(" -RT").toString();
        setHasNext(true);
        showRefreshing();
        (new TimeLineApi(getActivity(), adapter, userAccount)).search(this, queryString, 0);
        hideIme();
    }

    private ImeDetectableEditText editText;
    private long myUserId;
    private String queryString;


}
