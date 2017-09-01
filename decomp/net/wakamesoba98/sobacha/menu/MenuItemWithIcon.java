// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;


// Referenced classes of package net.wakamesoba98.sobacha.menu:
//            EnumMenuAction

class MenuItemWithIcon
{

    MenuItemWithIcon(EnumMenuAction enummenuaction, String s, int i, String s1)
    {
        action = enummenuaction;
        value = s;
        icon = i;
        caption = s1;
    }

    EnumMenuAction getAction()
    {
        return action;
    }

    String getCaption()
    {
        return caption;
    }

    int getIcon()
    {
        return icon;
    }

    String getValue()
    {
        return value;
    }

    private EnumMenuAction action;
    private String caption;
    private int icon;
    private String value;
}
