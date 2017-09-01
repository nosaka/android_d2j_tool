// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.key;


// Referenced classes of package net.wakamesoba98.sobacha.preference.key:
//            Prefs, EnumValueType

public final class EnumPrefs extends Enum
    implements Prefs
{

    private EnumPrefs(String s, int i, EnumValueType enumvaluetype, String s1, Object obj)
    {
        super(s, i);
        type = enumvaluetype;
        key = s1;
        defaultValue = obj;
    }

    public static EnumPrefs valueOf(String s)
    {
        return (EnumPrefs)Enum.valueOf(net/wakamesoba98/sobacha/preference/key/EnumPrefs, s);
    }

    public static EnumPrefs[] values()
    {
        return (EnumPrefs[])$VALUES.clone();
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    public String getKey()
    {
        return key;
    }

    public EnumValueType getType()
    {
        return type;
    }

    private static final EnumPrefs $VALUES[];
    public static final EnumPrefs ABSOLUTE_TIME;
    public static final EnumPrefs AUTO_RESIZE;
    public static final EnumPrefs BACKGROUND_DOWNLOAD;
    public static final EnumPrefs CAMERA_BUTTON;
    public static final EnumPrefs CLICKABLE_LINK;
    public static final EnumPrefs COLOR_SCHEME;
    public static final EnumPrefs CONFIRM;
    public static final EnumPrefs CONFIRM_FAVORITE;
    public static final EnumPrefs CONFIRM_POST;
    public static final EnumPrefs CONFIRM_QUIT;
    public static final EnumPrefs CONFIRM_RETWEET;
    public static final EnumPrefs DUAL_TIMELINE_MODE;
    public static final EnumPrefs EXCLUDE_RETWEET;
    public static final EnumPrefs FAST_SCROLL;
    public static final EnumPrefs FONT_SIZE;
    public static final EnumPrefs FULL_SCREEN;
    public static final EnumPrefs HIDE_ACTION_BAR;
    public static final EnumPrefs HIDE_NAVIGATION_BAR;
    public static final EnumPrefs ICON_SIZE;
    public static final EnumPrefs ITEMS_PER_PAGE;
    public static final EnumPrefs KEEP_POSITION;
    public static final EnumPrefs KEEP_SCREEN_ON;
    public static final EnumPrefs LOW_BANDWIDTH_MODE;
    public static final EnumPrefs MAX_LIST_ITEMS;
    public static final EnumPrefs NOISE_CANCEL;
    public static final EnumPrefs NOTIFICATION_SOUND;
    public static final EnumPrefs NOTIFY_FAVORITED;
    public static final EnumPrefs NOTIFY_MENTION;
    public static final EnumPrefs NOTIFY_MESSAGE;
    public static final EnumPrefs NOTIFY_RETWEETED;
    public static final EnumPrefs OFF_TIMER;
    public static final EnumPrefs PADDING;
    public static final EnumPrefs POST_KEY;
    public static final EnumPrefs QUOTE_FORMAT;
    public static final EnumPrefs RETWEETED_BY_STYLE;
    public static final EnumPrefs SHOW_SCREEN_NAME;
    public static final EnumPrefs SHOW_VIA;
    public static final EnumPrefs SINGLE_LINE_MODE;
    public static final EnumPrefs SPLASH_SCREEN;
    public static final EnumPrefs SPLIT_VERTICALLY;
    public static final EnumPrefs STORE_TIMELINE;
    public static final EnumPrefs THEME;
    public static final EnumPrefs THUMBNAIL_MODE;
    public static final EnumPrefs TREND_LOCATION;
    public static final EnumPrefs TWEET_HINT;
    public static final EnumPrefs UPLOAD_PROVIDER;
    public static final EnumPrefs USE_ANIMATION;
    public static final EnumPrefs USE_DOT_AT;
    public static final EnumPrefs USE_LIKE;
    public static final EnumPrefs USE_NOTIFICATION_SOUND;
    public static final EnumPrefs USE_SCREEN_NAME_DIR;
    public static final EnumPrefs WORD_WRAP;
    private final Object defaultValue;
    private final String key;
    private final EnumValueType type;

    static 
    {
        CONFIRM = new EnumPrefs("CONFIRM", 0, EnumValueType.BOOLEAN, "pref_confirm", Boolean.valueOf(true));
        CONFIRM_POST = new EnumPrefs("CONFIRM_POST", 1, EnumValueType.BOOLEAN, "pref_confirm_post", Boolean.valueOf(true));
        CONFIRM_RETWEET = new EnumPrefs("CONFIRM_RETWEET", 2, EnumValueType.BOOLEAN, "pref_confirm_retweet", Boolean.valueOf(true));
        CONFIRM_FAVORITE = new EnumPrefs("CONFIRM_FAVORITE", 3, EnumValueType.BOOLEAN, "pref_confirm_favorite", Boolean.valueOf(true));
        CONFIRM_QUIT = new EnumPrefs("CONFIRM_QUIT", 4, EnumValueType.BOOLEAN, "pref_confirm_quit", Boolean.valueOf(false));
        KEEP_SCREEN_ON = new EnumPrefs("KEEP_SCREEN_ON", 5, EnumValueType.BOOLEAN, "pref_screen", Boolean.valueOf(true));
        LOW_BANDWIDTH_MODE = new EnumPrefs("LOW_BANDWIDTH_MODE", 6, EnumValueType.BOOLEAN, "pref_low_bandwidth_mode", Boolean.valueOf(false));
        OFF_TIMER = new EnumPrefs("OFF_TIMER", 7, EnumValueType.INTEGER, "pref_off_timer", Integer.valueOf(60));
        POST_KEY = new EnumPrefs("POST_KEY", 8, EnumValueType.INTEGER, "pref_enter_post", Integer.valueOf(2));
        CAMERA_BUTTON = new EnumPrefs("CAMERA_BUTTON", 9, EnumValueType.INTEGER, "pref_camera_button", Integer.valueOf(1));
        UPLOAD_PROVIDER = new EnumPrefs("UPLOAD_PROVIDER", 10, EnumValueType.STRING, "pref_upload_to", "TWITTER");
        AUTO_RESIZE = new EnumPrefs("AUTO_RESIZE", 11, EnumValueType.INTEGER, "pref_auto_resize", Integer.valueOf(1600));
        QUOTE_FORMAT = new EnumPrefs("QUOTE_FORMAT", 12, EnumValueType.STRING, "pref_quote_format", "QT");
        USE_DOT_AT = new EnumPrefs("USE_DOT_AT", 13, EnumValueType.BOOLEAN, "pref_use_dot_at", Boolean.valueOf(true));
        THEME = new EnumPrefs("THEME", 14, EnumValueType.INTEGER, "pref_theme", Integer.valueOf(0));
        COLOR_SCHEME = new EnumPrefs("COLOR_SCHEME", 15, EnumValueType.INTEGER, "pref_color_scheme", Integer.valueOf(0));
        TWEET_HINT = new EnumPrefs("TWEET_HINT", 16, EnumValueType.INTEGER, "pref_tweet_hint", Integer.valueOf(0));
        USE_LIKE = new EnumPrefs("USE_LIKE", 17, EnumValueType.BOOLEAN, "pref_use_like", Boolean.valueOf(true));
        FULL_SCREEN = new EnumPrefs("FULL_SCREEN", 18, EnumValueType.BOOLEAN, "pref_full_screen", Boolean.valueOf(false));
        HIDE_ACTION_BAR = new EnumPrefs("HIDE_ACTION_BAR", 19, EnumValueType.BOOLEAN, "pref_hide_action_bar", Boolean.valueOf(true));
        HIDE_NAVIGATION_BAR = new EnumPrefs("HIDE_NAVIGATION_BAR", 20, EnumValueType.BOOLEAN, "pref_hide_navigation_bar", Boolean.valueOf(false));
        SPLASH_SCREEN = new EnumPrefs("SPLASH_SCREEN", 21, EnumValueType.BOOLEAN, "pref_splash_screen", Boolean.valueOf(false));
        USE_ANIMATION = new EnumPrefs("USE_ANIMATION", 22, EnumValueType.BOOLEAN, "pref_use_animation", Boolean.valueOf(true));
        FAST_SCROLL = new EnumPrefs("FAST_SCROLL", 23, EnumValueType.BOOLEAN, "pref_fast_scroll", Boolean.valueOf(false));
        STORE_TIMELINE = new EnumPrefs("STORE_TIMELINE", 24, EnumValueType.BOOLEAN, "pref_store_timeline", Boolean.valueOf(true));
        KEEP_POSITION = new EnumPrefs("KEEP_POSITION", 25, EnumValueType.BOOLEAN, "pref_keep_position", Boolean.valueOf(false));
        ITEMS_PER_PAGE = new EnumPrefs("ITEMS_PER_PAGE", 26, EnumValueType.INTEGER, "pref_items_per_page", Integer.valueOf(20));
        DUAL_TIMELINE_MODE = new EnumPrefs("DUAL_TIMELINE_MODE", 27, EnumValueType.BOOLEAN, "pref_sub_list", Boolean.valueOf(false));
        SPLIT_VERTICALLY = new EnumPrefs("SPLIT_VERTICALLY", 28, EnumValueType.BOOLEAN, "pref_split_vertically", Boolean.valueOf(false));
        SINGLE_LINE_MODE = new EnumPrefs("SINGLE_LINE_MODE", 29, EnumValueType.BOOLEAN, "pref_single_line", Boolean.valueOf(false));
        WORD_WRAP = new EnumPrefs("WORD_WRAP", 30, EnumValueType.BOOLEAN, "pref_word_wrap", Boolean.valueOf(false));
        SHOW_SCREEN_NAME = new EnumPrefs("SHOW_SCREEN_NAME", 31, EnumValueType.BOOLEAN, "pref_show_screen_name", Boolean.valueOf(false));
        FONT_SIZE = new EnumPrefs("FONT_SIZE", 32, EnumValueType.INTEGER, "pref_font_size", Integer.valueOf(14));
        ICON_SIZE = new EnumPrefs("ICON_SIZE", 33, EnumValueType.INTEGER, "pref_icon_size", Integer.valueOf(1));
        THUMBNAIL_MODE = new EnumPrefs("THUMBNAIL_MODE", 34, EnumValueType.INTEGER, "pref_thumbnail", Integer.valueOf(2));
        RETWEETED_BY_STYLE = new EnumPrefs("RETWEETED_BY_STYLE", 35, EnumValueType.INTEGER, "pref_retweeted_by_style", Integer.valueOf(0));
        ABSOLUTE_TIME = new EnumPrefs("ABSOLUTE_TIME", 36, EnumValueType.BOOLEAN, "pref_absolute_time", Boolean.valueOf(false));
        CLICKABLE_LINK = new EnumPrefs("CLICKABLE_LINK", 37, EnumValueType.BOOLEAN, "pref_clickable_link", Boolean.valueOf(true));
        SHOW_VIA = new EnumPrefs("SHOW_VIA", 38, EnumValueType.BOOLEAN, "pref_show_via", Boolean.valueOf(true));
        MAX_LIST_ITEMS = new EnumPrefs("MAX_LIST_ITEMS", 39, EnumValueType.INTEGER, "pref_count", Integer.valueOf(100));
        PADDING = new EnumPrefs("PADDING", 40, EnumValueType.INTEGER, "pref_padding", Integer.valueOf(8));
        EXCLUDE_RETWEET = new EnumPrefs("EXCLUDE_RETWEET", 41, EnumValueType.BOOLEAN, "pref_exclude_retweet", Boolean.valueOf(false));
        TREND_LOCATION = new EnumPrefs("TREND_LOCATION", 42, EnumValueType.INTEGER, "pref_trend_location", Integer.valueOf(-1));
        NOTIFY_MENTION = new EnumPrefs("NOTIFY_MENTION", 43, EnumValueType.BOOLEAN, "pref_notify_mention", Boolean.valueOf(true));
        NOTIFY_RETWEETED = new EnumPrefs("NOTIFY_RETWEETED", 44, EnumValueType.BOOLEAN, "pref_notify_retweeted", Boolean.valueOf(true));
        NOTIFY_FAVORITED = new EnumPrefs("NOTIFY_FAVORITED", 45, EnumValueType.BOOLEAN, "pref_notify_favorited", Boolean.valueOf(true));
        NOTIFY_MESSAGE = new EnumPrefs("NOTIFY_MESSAGE", 46, EnumValueType.BOOLEAN, "pref_notify_message", Boolean.valueOf(true));
        USE_NOTIFICATION_SOUND = new EnumPrefs("USE_NOTIFICATION_SOUND", 47, EnumValueType.BOOLEAN, "pref_use_notification_sound", Boolean.valueOf(false));
        NOTIFICATION_SOUND = new EnumPrefs("NOTIFICATION_SOUND", 48, EnumValueType.STRING, "pref_notification_sound", "");
        NOISE_CANCEL = new EnumPrefs("NOISE_CANCEL", 49, EnumValueType.BOOLEAN, "pref_mute_mention_from_not_following", Boolean.valueOf(true));
        BACKGROUND_DOWNLOAD = new EnumPrefs("BACKGROUND_DOWNLOAD", 50, EnumValueType.BOOLEAN, "pref_background_download", Boolean.valueOf(false));
        USE_SCREEN_NAME_DIR = new EnumPrefs("USE_SCREEN_NAME_DIR", 51, EnumValueType.BOOLEAN, "pref_screen_name_dir", Boolean.valueOf(true));
        $VALUES = (new EnumPrefs[] {
            CONFIRM, CONFIRM_POST, CONFIRM_RETWEET, CONFIRM_FAVORITE, CONFIRM_QUIT, KEEP_SCREEN_ON, LOW_BANDWIDTH_MODE, OFF_TIMER, POST_KEY, CAMERA_BUTTON, 
            UPLOAD_PROVIDER, AUTO_RESIZE, QUOTE_FORMAT, USE_DOT_AT, THEME, COLOR_SCHEME, TWEET_HINT, USE_LIKE, FULL_SCREEN, HIDE_ACTION_BAR, 
            HIDE_NAVIGATION_BAR, SPLASH_SCREEN, USE_ANIMATION, FAST_SCROLL, STORE_TIMELINE, KEEP_POSITION, ITEMS_PER_PAGE, DUAL_TIMELINE_MODE, SPLIT_VERTICALLY, SINGLE_LINE_MODE, 
            WORD_WRAP, SHOW_SCREEN_NAME, FONT_SIZE, ICON_SIZE, THUMBNAIL_MODE, RETWEETED_BY_STYLE, ABSOLUTE_TIME, CLICKABLE_LINK, SHOW_VIA, MAX_LIST_ITEMS, 
            PADDING, EXCLUDE_RETWEET, TREND_LOCATION, NOTIFY_MENTION, NOTIFY_RETWEETED, NOTIFY_FAVORITED, NOTIFY_MESSAGE, USE_NOTIFICATION_SOUND, NOTIFICATION_SOUND, NOISE_CANCEL, 
            BACKGROUND_DOWNLOAD, USE_SCREEN_NAME_DIR
        });
    }
}
