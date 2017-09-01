// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;

import android.view.View;
import java.util.ArrayList;
import net.wakamesoba98.sobacha.compatible.PermissionUtil;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;

// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            MenuBase, MenuItemWithIcon, EnumMenuAction

public class ImageSelectMenu extends MenuBase
{

    public ImageSelectMenu(ViewPagerActivity viewpageractivity)
    {
        activity = viewpageractivity;
    }

    protected ArrayList createMenuItem()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.TAKE_A_PHOTO, "", 0x7f02006b, ResourceHelper.getString(activity, 0x7f07011b)));
        arraylist.add(new MenuItemWithIcon(EnumMenuAction.PICK_AN_IMAGE, "", 0x7f0200db, ResourceHelper.getString(activity, 0x7f0700d8)));
        return arraylist;
    }

    protected void onMenuItemSelected(MenuItemWithIcon menuitemwithicon)
    {
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction = new int[EnumMenuAction.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.TAKE_A_PHOTO.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$menu$EnumMenuAction[EnumMenuAction.PICK_AN_IMAGE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.menu.EnumMenuAction[menuitemwithicon.getAction().ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            if(PermissionUtil.checkSelfPermissions(activity, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
            {
                activity.launchCamera();
                return;
            } else
            {
                activity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 12);
                return;
            }

        case 2: // '\002'
            break;
        }
        if(PermissionUtil.checkSelfPermissions(activity, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
        {
            activity.pickImage();
            return;
        } else
        {
            activity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 11);
            return;
        }
    }

    public void show(View view)
    {
        show(((android.content.Context) (activity)), view);
    }

    private ViewPagerActivity activity;
}
