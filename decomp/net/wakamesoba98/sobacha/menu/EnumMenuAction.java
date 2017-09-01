// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.menu;


final class EnumMenuAction extends Enum
{

    private EnumMenuAction(String s, int i)
    {
        super(s, i);
    }

    public static EnumMenuAction valueOf(String s)
    {
        return (EnumMenuAction)Enum.valueOf(net/wakamesoba98/sobacha/menu/EnumMenuAction, s);
    }

    public static EnumMenuAction[] values()
    {
        return (EnumMenuAction[])$VALUES.clone();
    }

    private static final EnumMenuAction $VALUES[];
    public static final EnumMenuAction ACCOUNT_SETTING;
    public static final EnumMenuAction BLOCK;
    public static final EnumMenuAction COPY_TO_CLIPBOARD;
    public static final EnumMenuAction DELETE;
    public static final EnumMenuAction DELETE_THIS_SEARCH;
    public static final EnumMenuAction LIKE_AND_RETWEET;
    public static final EnumMenuAction LOCATION;
    public static final EnumMenuAction MANAGE_USER_LISTS;
    public static final EnumMenuAction MENTION;
    public static final EnumMenuAction MUTE_CLIENT;
    public static final EnumMenuAction MUTE_HASHTAG;
    public static final EnumMenuAction MUTE_RETWEET;
    public static final EnumMenuAction MUTE_THUMB;
    public static final EnumMenuAction MUTE_USER;
    public static final EnumMenuAction OPEN_HASHTAG_MENU;
    public static final EnumMenuAction OPEN_IN_BROWSER;
    public static final EnumMenuAction OPEN_MUTE_MENU;
    public static final EnumMenuAction OPEN_STATUS_URL_MENU;
    public static final EnumMenuAction OPEN_URL;
    public static final EnumMenuAction PICK_AN_IMAGE;
    public static final EnumMenuAction POST_WITH_HASHTAG;
    public static final EnumMenuAction PROFILE;
    public static final EnumMenuAction QUOTE;
    public static final EnumMenuAction RELOAD;
    public static final EnumMenuAction REPORT;
    public static final EnumMenuAction SAVE_THIS_SEARCH;
    public static final EnumMenuAction SEARCH_HASHTAG;
    public static final EnumMenuAction SEARCH_TREND;
    public static final EnumMenuAction SEARCH_USER;
    public static final EnumMenuAction SEARCH_WORD;
    public static final EnumMenuAction SHOW_CONVERSATION;
    public static final EnumMenuAction SHOW_RETWEET;
    public static final EnumMenuAction SHOW_TRENDS;
    public static final EnumMenuAction SHOW_TWEET;
    public static final EnumMenuAction SHOW_USER;
    public static final EnumMenuAction TAKE_A_PHOTO;
    public static final EnumMenuAction TWEET_THIS_URL;
    public static final EnumMenuAction UNBLOCK;

    static 
    {
        MUTE_USER = new EnumMenuAction("MUTE_USER", 0);
        MUTE_THUMB = new EnumMenuAction("MUTE_THUMB", 1);
        MUTE_HASHTAG = new EnumMenuAction("MUTE_HASHTAG", 2);
        MUTE_CLIENT = new EnumMenuAction("MUTE_CLIENT", 3);
        MUTE_RETWEET = new EnumMenuAction("MUTE_RETWEET", 4);
        TAKE_A_PHOTO = new EnumMenuAction("TAKE_A_PHOTO", 5);
        PICK_AN_IMAGE = new EnumMenuAction("PICK_AN_IMAGE", 6);
        SEARCH_HASHTAG = new EnumMenuAction("SEARCH_HASHTAG", 7);
        POST_WITH_HASHTAG = new EnumMenuAction("POST_WITH_HASHTAG", 8);
        PROFILE = new EnumMenuAction("PROFILE", 9);
        ACCOUNT_SETTING = new EnumMenuAction("ACCOUNT_SETTING", 10);
        RELOAD = new EnumMenuAction("RELOAD", 11);
        SAVE_THIS_SEARCH = new EnumMenuAction("SAVE_THIS_SEARCH", 12);
        DELETE_THIS_SEARCH = new EnumMenuAction("DELETE_THIS_SEARCH", 13);
        SHOW_TRENDS = new EnumMenuAction("SHOW_TRENDS", 14);
        SEARCH_USER = new EnumMenuAction("SEARCH_USER", 15);
        SEARCH_WORD = new EnumMenuAction("SEARCH_WORD", 16);
        SHOW_USER = new EnumMenuAction("SHOW_USER", 17);
        OPEN_URL = new EnumMenuAction("OPEN_URL", 18);
        LIKE_AND_RETWEET = new EnumMenuAction("LIKE_AND_RETWEET", 19);
        QUOTE = new EnumMenuAction("QUOTE", 20);
        DELETE = new EnumMenuAction("DELETE", 21);
        SHOW_CONVERSATION = new EnumMenuAction("SHOW_CONVERSATION", 22);
        OPEN_STATUS_URL_MENU = new EnumMenuAction("OPEN_STATUS_URL_MENU", 23);
        OPEN_MUTE_MENU = new EnumMenuAction("OPEN_MUTE_MENU", 24);
        OPEN_HASHTAG_MENU = new EnumMenuAction("OPEN_HASHTAG_MENU", 25);
        SHOW_TWEET = new EnumMenuAction("SHOW_TWEET", 26);
        OPEN_IN_BROWSER = new EnumMenuAction("OPEN_IN_BROWSER", 27);
        COPY_TO_CLIPBOARD = new EnumMenuAction("COPY_TO_CLIPBOARD", 28);
        TWEET_THIS_URL = new EnumMenuAction("TWEET_THIS_URL", 29);
        SEARCH_TREND = new EnumMenuAction("SEARCH_TREND", 30);
        MENTION = new EnumMenuAction("MENTION", 31);
        BLOCK = new EnumMenuAction("BLOCK", 32);
        UNBLOCK = new EnumMenuAction("UNBLOCK", 33);
        REPORT = new EnumMenuAction("REPORT", 34);
        SHOW_RETWEET = new EnumMenuAction("SHOW_RETWEET", 35);
        MANAGE_USER_LISTS = new EnumMenuAction("MANAGE_USER_LISTS", 36);
        LOCATION = new EnumMenuAction("LOCATION", 37);
        $VALUES = (new EnumMenuAction[] {
            MUTE_USER, MUTE_THUMB, MUTE_HASHTAG, MUTE_CLIENT, MUTE_RETWEET, TAKE_A_PHOTO, PICK_AN_IMAGE, SEARCH_HASHTAG, POST_WITH_HASHTAG, PROFILE, 
            ACCOUNT_SETTING, RELOAD, SAVE_THIS_SEARCH, DELETE_THIS_SEARCH, SHOW_TRENDS, SEARCH_USER, SEARCH_WORD, SHOW_USER, OPEN_URL, LIKE_AND_RETWEET, 
            QUOTE, DELETE, SHOW_CONVERSATION, OPEN_STATUS_URL_MENU, OPEN_MUTE_MENU, OPEN_HASHTAG_MENU, SHOW_TWEET, OPEN_IN_BROWSER, COPY_TO_CLIPBOARD, TWEET_THIS_URL, 
            SEARCH_TREND, MENTION, BLOCK, UNBLOCK, REPORT, SHOW_RETWEET, MANAGE_USER_LISTS, LOCATION
        });
    }
}
