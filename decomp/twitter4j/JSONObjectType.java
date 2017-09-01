// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            Logger, JSONException, JSONObject

public final class JSONObjectType
{
    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(twitter4j/JSONObjectType$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        private static final Type $VALUES[];
        public static final Type BLOCK;
        public static final Type DELETE;
        public static final Type DIRECT_MESSAGE;
        public static final Type DISCONNECTION;
        public static final Type FAVORITE;
        public static final Type FAVORITED_RETWEET;
        public static final Type FOLLOW;
        public static final Type FRIENDS;
        public static final Type LIMIT;
        public static final Type QUOTED_TWEET;
        public static final Type RETWEETED_RETWEET;
        public static final Type SCRUB_GEO;
        public static final Type SENDER;
        public static final Type STALL_WARNING;
        public static final Type STATUS;
        public static final Type UNBLOCK;
        public static final Type UNFAVORITE;
        public static final Type UNFOLLOW;
        public static final Type UNKNOWN;
        public static final Type USER_DELETE;
        public static final Type USER_LIST_CREATED;
        public static final Type USER_LIST_DESTROYED;
        public static final Type USER_LIST_MEMBER_ADDED;
        public static final Type USER_LIST_MEMBER_DELETED;
        public static final Type USER_LIST_SUBSCRIBED;
        public static final Type USER_LIST_UNSUBSCRIBED;
        public static final Type USER_LIST_UPDATED;
        public static final Type USER_SUSPEND;
        public static final Type USER_UPDATE;

        static 
        {
            SENDER = new Type("SENDER", 0);
            STATUS = new Type("STATUS", 1);
            DIRECT_MESSAGE = new Type("DIRECT_MESSAGE", 2);
            DELETE = new Type("DELETE", 3);
            LIMIT = new Type("LIMIT", 4);
            STALL_WARNING = new Type("STALL_WARNING", 5);
            SCRUB_GEO = new Type("SCRUB_GEO", 6);
            FRIENDS = new Type("FRIENDS", 7);
            FAVORITE = new Type("FAVORITE", 8);
            UNFAVORITE = new Type("UNFAVORITE", 9);
            FOLLOW = new Type("FOLLOW", 10);
            UNFOLLOW = new Type("UNFOLLOW", 11);
            USER_LIST_MEMBER_ADDED = new Type("USER_LIST_MEMBER_ADDED", 12);
            USER_LIST_MEMBER_DELETED = new Type("USER_LIST_MEMBER_DELETED", 13);
            USER_LIST_SUBSCRIBED = new Type("USER_LIST_SUBSCRIBED", 14);
            USER_LIST_UNSUBSCRIBED = new Type("USER_LIST_UNSUBSCRIBED", 15);
            USER_LIST_CREATED = new Type("USER_LIST_CREATED", 16);
            USER_LIST_UPDATED = new Type("USER_LIST_UPDATED", 17);
            USER_LIST_DESTROYED = new Type("USER_LIST_DESTROYED", 18);
            USER_UPDATE = new Type("USER_UPDATE", 19);
            USER_DELETE = new Type("USER_DELETE", 20);
            USER_SUSPEND = new Type("USER_SUSPEND", 21);
            BLOCK = new Type("BLOCK", 22);
            UNBLOCK = new Type("UNBLOCK", 23);
            DISCONNECTION = new Type("DISCONNECTION", 24);
            RETWEETED_RETWEET = new Type("RETWEETED_RETWEET", 25);
            FAVORITED_RETWEET = new Type("FAVORITED_RETWEET", 26);
            QUOTED_TWEET = new Type("QUOTED_TWEET", 27);
            UNKNOWN = new Type("UNKNOWN", 28);
            $VALUES = (new Type[] {
                SENDER, STATUS, DIRECT_MESSAGE, DELETE, LIMIT, STALL_WARNING, SCRUB_GEO, FRIENDS, FAVORITE, UNFAVORITE, 
                FOLLOW, UNFOLLOW, USER_LIST_MEMBER_ADDED, USER_LIST_MEMBER_DELETED, USER_LIST_SUBSCRIBED, USER_LIST_UNSUBSCRIBED, USER_LIST_CREATED, USER_LIST_UPDATED, USER_LIST_DESTROYED, USER_UPDATE, 
                USER_DELETE, USER_SUSPEND, BLOCK, UNBLOCK, DISCONNECTION, RETWEETED_RETWEET, FAVORITED_RETWEET, QUOTED_TWEET, UNKNOWN
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public JSONObjectType()
    {
    }

    public static Type determine(JSONObject jsonobject)
    {
        if(!jsonobject.isNull("sender"))
            return Type.SENDER;
        if(!jsonobject.isNull("text"))
            return Type.STATUS;
        if(!jsonobject.isNull("direct_message"))
            return Type.DIRECT_MESSAGE;
        if(!jsonobject.isNull("delete"))
            return Type.DELETE;
        if(!jsonobject.isNull("limit"))
            return Type.LIMIT;
        if(!jsonobject.isNull("warning"))
            return Type.STALL_WARNING;
        if(!jsonobject.isNull("scrub_geo"))
            return Type.SCRUB_GEO;
        if(!jsonobject.isNull("friends"))
            return Type.FRIENDS;
        if(jsonobject.isNull("event"))
            break MISSING_BLOCK_LABEL_396;
        Object obj;
        obj = jsonobject.getString("event");
        if("favorite".equals(obj))
            return Type.FAVORITE;
        if("unfavorite".equals(obj))
            return Type.UNFAVORITE;
        if("follow".equals(obj))
            return Type.FOLLOW;
        if("unfollow".equals(obj))
            return Type.UNFOLLOW;
        if(((String) (obj)).startsWith("list"))
        {
            if("list_member_added".equals(obj))
                return Type.USER_LIST_MEMBER_ADDED;
            if("list_member_removed".equals(obj))
                return Type.USER_LIST_MEMBER_DELETED;
            if("list_user_subscribed".equals(obj))
                return Type.USER_LIST_SUBSCRIBED;
            if("list_user_unsubscribed".equals(obj))
                return Type.USER_LIST_UNSUBSCRIBED;
            if("list_created".equals(obj))
                return Type.USER_LIST_CREATED;
            if("list_updated".equals(obj))
                return Type.USER_LIST_UPDATED;
            if("list_destroyed".equals(obj))
                return Type.USER_LIST_DESTROYED;
            break MISSING_BLOCK_LABEL_392;
        }
        if("user_update".equals(obj))
            return Type.USER_UPDATE;
        if("user_delete".equals(obj))
            return Type.USER_DELETE;
        if("user_suspend".equals(obj))
            return Type.USER_SUSPEND;
        if("block".equals(obj))
            return Type.BLOCK;
        if("unblock".equals(obj))
            return Type.UNBLOCK;
        if("retweeted_retweet".equals(obj))
            return Type.RETWEETED_RETWEET;
        if("favorited_retweet".equals(obj))
            return Type.FAVORITED_RETWEET;
        if(!"quoted_tweet".equals(obj))
            break MISSING_BLOCK_LABEL_392;
        obj = Type.QUOTED_TWEET;
        return ((Type) (obj));
        JSONException jsonexception;
        jsonexception;
        try
        {
            logger.warn("Failed to get event element: ", jsonobject.toString(2));
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject) { }
        return Type.UNKNOWN;
        if(!jsonobject.isNull("disconnect"))
            return Type.DISCONNECTION;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_392;
_L1:
    }

    private static final Logger logger = Logger.getLogger(twitter4j/JSONObjectType);

}
