// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuItemWithIcon

class MenuItemWithIconAdapter extends ArrayAdapter
{
    private class ViewHolder
    {

        ImageView imageView;
        TextView textView;
        final MenuItemWithIconAdapter this$0;

        private ViewHolder()
        {
            this$0 = MenuItemWithIconAdapter.this;
            super();
        }

    }


    MenuItemWithIconAdapter(Context context, int i, List list)
    {
        super(context, i, list);
        theme = new ThemeManager(context);
        isHoneycombOrLater = SystemVersion.isHoneycombOrLater();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        MenuItemWithIcon menuitemwithicon;
        if(view == null)
        {
            view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03004d, null);
            viewgroup = new ViewHolder();
            viewgroup.textView = (TextView)view.findViewById(0x7f0e00fb);
            viewgroup.imageView = (ImageView)view.findViewById(0x7f0e00fa);
            view.setTag(viewgroup);
        } else
        {
            viewgroup = (ViewHolder)view.getTag();
        }
        menuitemwithicon = (MenuItemWithIcon)getItem(i);
        if(menuitemwithicon != null)
        {
            ((ViewHolder) (viewgroup)).textView.setText(menuitemwithicon.getCaption());
            ((ViewHolder) (viewgroup)).imageView.setImageResource(theme.getMenuIconDrawableId(menuitemwithicon.getIcon()));
        }
        if(!isHoneycombOrLater)
            ((ViewHolder) (viewgroup)).textView.setTextColor(0xff000000);
        return view;
    }

    private boolean isHoneycombOrLater;
    private ThemeManager theme;
}
