// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.*;
import java.util.*;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

// Referenced classes of package net.wakamesoba98.sobacha.view.tab:
//            EnumTab

public class TabManager
{

    TabManager(Context context1, ViewPagerFragment viewpagerfragment)
    {
        context = context1;
        fragment = viewpagerfragment;
        themeManager = new ThemeManager(context1);
    }

    private List createImageButtonList(final ViewPager pager)
    {
        ArrayList arraylist = new ArrayList();
        for(final int page = 0; page < tabList.size(); page++)
        {
            ImageButton imagebutton = new ImageButton(context);
            imagebutton.setImageResource(((EnumTab)tabList.get(page)).getResId());
            imagebutton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(pager.getCurrentItem() == page)
                    {
                        view = fragment.getListView(page);
                        if(view != null)
                            view.setSelection(0);
                        return;
                    } else
                    {
                        pager.setCurrentItem(page);
                        return;
                    }
                }

                final TabManager this$0;
                final int val$page;
                final ViewPager val$pager;

            
            {
                this$0 = TabManager.this;
                pager = viewpager;
                page = i;
                super();
            }
            }
);
            arraylist.add(imagebutton);
        }

        return arraylist;
    }

    public void setHighlightButton(int i)
    {
        int j = 0;
        while(j < imageButtonList.size()) 
        {
            int k;
            boolean flag;
            if(j == i)
                flag = true;
            else
                flag = false;
            k = themeManager.getTabDrawableId(((EnumTab)tabList.get(j)).getResId(), flag);
            ((ImageButton)imageButtonList.get(j)).setImageResource(k);
            j++;
        }
    }

    void setTabButton(LinearLayout linearlayout, ViewPager viewpager)
    {
        if(imageButtonList == null)
            imageButtonList = createImageButtonList(viewpager);
        int i = linearlayout.getOrientation();
        linearlayout.setOrientation(i);
        Iterator iterator = imageButtonList.iterator();
        while(iterator.hasNext()) 
        {
            ImageButton imagebutton = (ImageButton)iterator.next();
            if(i == 0)
                viewpager = new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F);
            else
                viewpager = new android.widget.LinearLayout.LayoutParams(-1, 0, 1.0F);
            linearlayout.addView(imagebutton, viewpager);
        }
    }

    void setTabList(List list)
    {
        tabList = list;
    }

    private Context context;
    private ViewPagerFragment fragment;
    private List imageButtonList;
    private List tabList;
    private ThemeManager themeManager;

}
