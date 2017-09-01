// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuItemWithIconAdapter, MenuItemWithIcon

abstract class MenuBase
{

    MenuBase()
    {
    }

    private void showAlertDialog(Context context, final ArrayAdapter adapter)
    {
        context = new android.app.AlertDialog.Builder(context);
        context.setSingleChoiceItems(adapter, -1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                MenuItemWithIcon menuitemwithicon = (MenuItemWithIcon)adapter.getItem(i);
                onMenuItemSelected(menuitemwithicon);
                dialoginterface.dismiss();
            }

            final MenuBase this$0;
            final ArrayAdapter val$adapter;

            
            {
                this$0 = MenuBase.this;
                adapter = arrayadapter;
                super();
            }
        }
);
        context.show();
    }

    private void showListPopupWindow(Context context, final ArrayAdapter adapter, View view)
    {
        if(view != null && Flavor.isMateCha())
        {
            final ListPopupWindow window = new ListPopupWindow(context);
            window.setAnchorView(view);
            window.setAdapter(adapter);
            window.setModal(true);
            window.setWidth(DisplayMetricsUtil.convertDipToPixel(context, 320));
            window.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view1, int i, long l)
                {
                    adapterview = (MenuItemWithIcon)adapter.getItem(i);
                    onMenuItemSelected(adapterview);
                    window.dismiss();
                }

                final MenuBase this$0;
                final ArrayAdapter val$adapter;
                final ListPopupWindow val$window;

            
            {
                this$0 = MenuBase.this;
                adapter = arrayadapter;
                window = listpopupwindow;
                super();
            }
            }
);
            window.show();
            return;
        } else
        {
            showAlertDialog(context, adapter);
            return;
        }
    }

    protected abstract ArrayList createMenuItem();

    protected abstract void onMenuItemSelected(MenuItemWithIcon menuitemwithicon);

    protected void show(Context context, View view)
    {
        MenuItemWithIconAdapter menuitemwithiconadapter = new MenuItemWithIconAdapter(context, 0, createMenuItem());
        if(SystemVersion.isHoneycombOrLater())
        {
            showListPopupWindow(context, menuitemwithiconadapter, view);
            return;
        } else
        {
            showAlertDialog(context, menuitemwithiconadapter);
            return;
        }
    }
}
