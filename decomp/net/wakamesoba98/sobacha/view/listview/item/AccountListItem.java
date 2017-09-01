// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.item;


public class AccountListItem
{

    public AccountListItem(long l, String s, String s1, String s2)
    {
        userId = l;
        screenName = s;
        userName = s1;
        iconUrl = s2;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public long getUserId()
    {
        return userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public boolean isAddButton()
    {
        return isAddButton;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setAddButton(boolean flag)
    {
        isAddButton = flag;
    }

    public void setSelected(boolean flag)
    {
        isSelected = flag;
    }

    private String iconUrl;
    private boolean isAddButton;
    private boolean isSelected;
    private String screenName;
    private long userId;
    private String userName;
}
