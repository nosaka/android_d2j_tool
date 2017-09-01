// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.tab;

import net.wakamesoba98.sobacha.view.fragment.other.UserDetailFragment;
import net.wakamesoba98.sobacha.view.fragment.pulltorefresh.*;

public final class EnumTab extends Enum
{

    private EnumTab(String s, int i, int j, Class class1)
    {
        super(s, i);
        resId = j;
        fragment = class1;
    }

    public static EnumTab valueOf(String s)
    {
        return (EnumTab)Enum.valueOf(net/wakamesoba98/sobacha/view/tab/EnumTab, s);
    }

    public static EnumTab[] values()
    {
        return (EnumTab[])$VALUES.clone();
    }

    public Class getFragment()
    {
        return fragment;
    }

    public int getResId()
    {
        return resId;
    }

    private static final EnumTab $VALUES[];
    public static final EnumTab CONVERSATION;
    public static final EnumTab DETAIL;
    public static final EnumTab DIRECT_MESSAGE;
    public static final EnumTab DM_CONVERSATION;
    public static final EnumTab FAVORITES;
    public static final EnumTab FOLLOWERS;
    public static final EnumTab FRIENDS;
    public static final EnumTab HOME;
    public static final EnumTab MENTION;
    public static final EnumTab SEARCH;
    public static final EnumTab USER_LIST;
    public static final EnumTab USER_SEARCH;
    public static final EnumTab USER_TIMELINE;
    private Class fragment;
    private int resId;

    static 
    {
        HOME = new EnumTab("HOME", 0, 0x7f020064, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/HomeFragment);
        MENTION = new EnumTab("MENTION", 1, 0x7f0200b6, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/MentionFragment);
        USER_LIST = new EnumTab("USER_LIST", 2, 0x7f020110, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserListFragment);
        SEARCH = new EnumTab("SEARCH", 3, 0x7f0200f9, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/SearchFragment);
        USER_SEARCH = new EnumTab("USER_SEARCH", 4, 0x7f0200f9, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserSearchFragment);
        DIRECT_MESSAGE = new EnumTab("DIRECT_MESSAGE", 5, 0x7f02008c, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/DirectMessageFragment);
        DETAIL = new EnumTab("DETAIL", 6, 0x7f0200d8, net/wakamesoba98/sobacha/view/fragment/other/UserDetailFragment);
        USER_TIMELINE = new EnumTab("USER_TIMELINE", 7, 0x7f020064, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserTimeLineFragment);
        FRIENDS = new EnumTab("FRIENDS", 8, 0x7f0200a0, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserFriendsFragment);
        FOLLOWERS = new EnumTab("FOLLOWERS", 9, 0x7f02009a, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserFollowerFragment);
        FAVORITES = new EnumTab("FAVORITES", 10, 0x7f020098, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/UserFavoritesFragment);
        CONVERSATION = new EnumTab("CONVERSATION", 11, 0x7f02007a, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/ConversationFragment);
        DM_CONVERSATION = new EnumTab("DM_CONVERSATION", 12, 0x7f02008c, net/wakamesoba98/sobacha/view/fragment/pulltorefresh/DirectMessageConversationFragment);
        $VALUES = (new EnumTab[] {
            HOME, MENTION, USER_LIST, SEARCH, USER_SEARCH, DIRECT_MESSAGE, DETAIL, USER_TIMELINE, FRIENDS, FOLLOWERS, 
            FAVORITES, CONVERSATION, DM_CONVERSATION
        });
    }
}
