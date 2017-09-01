// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.pulltorefresh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ImageButton;
import android.widget.TextView;
import net.wakamesoba98.sobacha.twitter.api.timeline.TimeLineApi;
import net.wakamesoba98.sobacha.view.edittext.ImeDetectableEditText;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusAdapter;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.listview.value.EnumPullDirection;
import net.wakamesoba98.sobacha.view.tab.EnumTab;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.User;

// Referenced classes of package net.wakamesoba98.sobacha.view.fragment.pulltorefresh:
//            CursorPullToRefreshFragment

public class UserSearchFragment extends CursorPullToRefreshFragment
{

    public UserSearchFragment()
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

    private void search(String s)
    {
        if("".equals(s.trim()))
            return;
        queryString = s;
        if(!s.equals(editText.getText().toString()))
            editText.setText(s);
        adapter.clear();
        setHasNext(true);
        showRefreshing();
        (new TimeLineApi(getActivity(), adapter, userAccount)).searchUsers(this, queryString, 0, 1, -1L);
        hideIme();
    }

    private void setHeaderProperties()
    {
        ThemeManager thememanager = new ThemeManager(getActivity());
        ImageButton imagebutton = (ImageButton)getView().findViewById(0x7f0e00f0);
        ImageButton imagebutton1 = (ImageButton)getView().findViewById(0x7f0e00f2);
        imagebutton.setVisibility(8);
        imagebutton1.setImageResource(thememanager.getThemeDrawableId(0x7f0200f8));
        imagebutton1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                adapter.clear();
                search(editText.getText().toString());
            }

            final UserSearchFragment this$0;

            
            {
                this$0 = UserSearchFragment.this;
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

            final UserSearchFragment this$0;

            
            {
                this$0 = UserSearchFragment.this;
                super();
            }
        }
);
    }

    protected void initTimeLine()
    {
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        adapter = ((AdapterHolder)getParentFragment()).getAdapter(EnumTab.USER_SEARCH);
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
        if(enumpulldirection == EnumPullDirection.UP && adapter.getCount() > 0)
        {
            int i = adapter.getCount() - 1;
            if(adapter.getItem(i) == null)
            {
                hideRefreshing();
                return;
            }
            enumpulldirection = (StatusItem)adapter.getItem(i);
            if(enumpulldirection == null)
            {
                hideRefreshing();
                return;
            }
            enumpulldirection = enumpulldirection.getUser();
            if(enumpulldirection == null)
            {
                hideRefreshing();
                return;
            } else
            {
                (new TimeLineApi(getActivity(), adapter, userAccount)).searchUsers(this, queryString, 0, (int)cursor, enumpulldirection.getId());
                return;
            }
        } else
        {
            hideRefreshing();
            return;
        }
    }

    public void readMore(int i)
    {
    }

    private ImeDetectableEditText editText;
    private String queryString;


}
