// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.tab;


public final class EnumViewPagerFragment extends Enum
{

    private EnumViewPagerFragment(String s, int i)
    {
        super(s, i);
    }

    public static EnumViewPagerFragment valueOf(String s)
    {
        return (EnumViewPagerFragment)Enum.valueOf(net/wakamesoba98/sobacha/view/tab/EnumViewPagerFragment, s);
    }

    public static EnumViewPagerFragment[] values()
    {
        return (EnumViewPagerFragment[])$VALUES.clone();
    }

    private static final EnumViewPagerFragment $VALUES[];
    public static final EnumViewPagerFragment CONVERSATION;
    public static final EnumViewPagerFragment DIRECT_MESSAGE_CONVERSATION;
    public static final EnumViewPagerFragment MAIN;
    public static final EnumViewPagerFragment MAIN_TABLET_OTHER;
    public static final EnumViewPagerFragment MAIN_TABLET_TIMELINE;
    public static final EnumViewPagerFragment SEARCH;
    public static final EnumViewPagerFragment USER_PAGE;
    public static final EnumViewPagerFragment USER_SEARCH;

    static 
    {
        MAIN = new EnumViewPagerFragment("MAIN", 0);
        MAIN_TABLET_TIMELINE = new EnumViewPagerFragment("MAIN_TABLET_TIMELINE", 1);
        MAIN_TABLET_OTHER = new EnumViewPagerFragment("MAIN_TABLET_OTHER", 2);
        USER_PAGE = new EnumViewPagerFragment("USER_PAGE", 3);
        CONVERSATION = new EnumViewPagerFragment("CONVERSATION", 4);
        SEARCH = new EnumViewPagerFragment("SEARCH", 5);
        USER_SEARCH = new EnumViewPagerFragment("USER_SEARCH", 6);
        DIRECT_MESSAGE_CONVERSATION = new EnumViewPagerFragment("DIRECT_MESSAGE_CONVERSATION", 7);
        $VALUES = (new EnumViewPagerFragment[] {
            MAIN, MAIN_TABLET_TIMELINE, MAIN_TABLET_OTHER, USER_PAGE, CONVERSATION, SEARCH, USER_SEARCH, DIRECT_MESSAGE_CONVERSATION
        });
    }
}
