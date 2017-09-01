// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            TwitterResponseImpl, Status, Logger, TwitterException, 
//            HttpResponse, TwitterObjectFactory, JSONException, JSONObject, 
//            JSONArray, UserMentionEntity, UserMentionEntityJSONImpl, URLEntity, 
//            URLEntityJSONImpl, HashtagEntity, HashtagEntityJSONImpl, SymbolEntity, 
//            MediaEntity, MediaEntityJSONImpl, ExtendedMediaEntity, ExtendedMediaEntityJSONImpl, 
//            ResponseListImpl, ResponseList, ParseUtil, UserJSONImpl, 
//            JSONImplFactory, PlaceJSONImpl, HTMLEntity, ScopesImpl, 
//            GeoLocation, Place, Scopes, User

final class StatusJSONImpl extends TwitterResponseImpl
    implements Status, Serializable
{

    StatusJSONImpl()
    {
        displayTextRangeStart = -1;
        displayTextRangeEnd = -1;
        geoLocation = null;
        place = null;
        currentUserRetweetId = -1L;
        user = null;
        withheldInCountries = null;
        quotedStatusId = -1L;
    }

    StatusJSONImpl(HttpResponse httpresponse, Configuration configuration)
        throws TwitterException
    {
        super(httpresponse);
        displayTextRangeStart = -1;
        displayTextRangeEnd = -1;
        geoLocation = null;
        place = null;
        currentUserRetweetId = -1L;
        user = null;
        withheldInCountries = null;
        quotedStatusId = -1L;
        httpresponse = httpresponse.asJSONObject();
        init(httpresponse);
        if(configuration.isJSONStoreEnabled())
        {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpresponse);
        }
    }

    StatusJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        displayTextRangeStart = -1;
        displayTextRangeEnd = -1;
        geoLocation = null;
        place = null;
        currentUserRetweetId = -1L;
        user = null;
        withheldInCountries = null;
        quotedStatusId = -1L;
        init(jsonobject);
    }

    StatusJSONImpl(JSONObject jsonobject, Configuration configuration)
        throws TwitterException
    {
        displayTextRangeStart = -1;
        displayTextRangeEnd = -1;
        geoLocation = null;
        place = null;
        currentUserRetweetId = -1L;
        user = null;
        withheldInCountries = null;
        quotedStatusId = -1L;
        init(jsonobject);
        if(configuration.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(this, jsonobject);
    }

    private void collectEntities(JSONObject jsonobject)
        throws JSONException, TwitterException
    {
        if(!jsonobject.isNull("entities"))
        {
            jsonobject = jsonobject.getJSONObject("entities");
            if(!jsonobject.isNull("user_mentions"))
            {
                JSONArray jsonarray = jsonobject.getJSONArray("user_mentions");
                int j1 = jsonarray.length();
                userMentionEntities = new UserMentionEntity[j1];
                for(int i = 0; i < j1; i++)
                    userMentionEntities[i] = new UserMentionEntityJSONImpl(jsonarray.getJSONObject(i));

            }
            if(!jsonobject.isNull("urls"))
            {
                JSONArray jsonarray1 = jsonobject.getJSONArray("urls");
                int k1 = jsonarray1.length();
                urlEntities = new URLEntity[k1];
                for(int j = 0; j < k1; j++)
                    urlEntities[j] = new URLEntityJSONImpl(jsonarray1.getJSONObject(j));

            }
            if(!jsonobject.isNull("hashtags"))
            {
                JSONArray jsonarray2 = jsonobject.getJSONArray("hashtags");
                int l1 = jsonarray2.length();
                hashtagEntities = new HashtagEntity[l1];
                for(int k = 0; k < l1; k++)
                    hashtagEntities[k] = new HashtagEntityJSONImpl(jsonarray2.getJSONObject(k));

            }
            if(!jsonobject.isNull("symbols"))
            {
                JSONArray jsonarray3 = jsonobject.getJSONArray("symbols");
                int i2 = jsonarray3.length();
                symbolEntities = new SymbolEntity[i2];
                for(int l = 0; l < i2; l++)
                    symbolEntities[l] = new HashtagEntityJSONImpl(jsonarray3.getJSONObject(l));

            }
            if(!jsonobject.isNull("media"))
            {
                jsonobject = jsonobject.getJSONArray("media");
                int j2 = jsonobject.length();
                mediaEntities = new MediaEntity[j2];
                for(int i1 = 0; i1 < j2; i1++)
                    mediaEntities[i1] = new MediaEntityJSONImpl(jsonobject.getJSONObject(i1));

            }
        }
    }

    private void collectExtendedEntities(JSONObject jsonobject)
        throws JSONException, TwitterException
    {
        if(!jsonobject.isNull("extended_entities"))
        {
            jsonobject = jsonobject.getJSONObject("extended_entities");
            if(!jsonobject.isNull("media"))
            {
                jsonobject = jsonobject.getJSONArray("media");
                int j = jsonobject.length();
                extendedMediaEntities = new ExtendedMediaEntity[j];
                for(int i = 0; i < j; i++)
                    extendedMediaEntities[i] = new ExtendedMediaEntityJSONImpl(jsonobject.getJSONObject(i));

            }
        }
    }

    static ResponseList createStatusList(HttpResponse httpresponse, Configuration configuration)
        throws TwitterException
    {
        int i;
        int j;
        JSONArray jsonarray;
        JSONObject jsonobject;
        StatusJSONImpl statusjsonimpl;
        try
        {
            if(configuration.isJSONStoreEnabled())
                TwitterObjectFactory.clearThreadLocalMap();
            jsonarray = httpresponse.asJSONArray();
            j = jsonarray.length();
            httpresponse = new ResponseListImpl(j, httpresponse);
        }
        // Misplaced declaration of an exception variable
        catch(HttpResponse httpresponse)
        {
            throw new TwitterException(httpresponse);
        }
        i = 0;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        jsonobject = jsonarray.getJSONObject(i);
        statusjsonimpl = new StatusJSONImpl(jsonobject);
        if(configuration.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(statusjsonimpl, jsonobject);
        httpresponse.add(statusjsonimpl);
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_36;
_L1:
        if(configuration.isJSONStoreEnabled())
            TwitterObjectFactory.registerJSONObject(httpresponse, jsonarray);
        return httpresponse;
    }

    private void init(JSONObject jsonobject)
        throws TwitterException
    {
        id = ParseUtil.getLong("id", jsonobject);
        source = ParseUtil.getUnescapedString("source", jsonobject);
        createdAt = ParseUtil.getDate("created_at", jsonobject);
        isTruncated = ParseUtil.getBoolean("truncated", jsonobject);
        inReplyToStatusId = ParseUtil.getLong("in_reply_to_status_id", jsonobject);
        inReplyToUserId = ParseUtil.getLong("in_reply_to_user_id", jsonobject);
        isFavorited = ParseUtil.getBoolean("favorited", jsonobject);
        isRetweeted = ParseUtil.getBoolean("retweeted", jsonobject);
        inReplyToScreenName = ParseUtil.getUnescapedString("in_reply_to_screen_name", jsonobject);
        retweetCount = ParseUtil.getLong("retweet_count", jsonobject);
        favoriteCount = ParseUtil.getInt("favorite_count", jsonobject);
        isPossiblySensitive = ParseUtil.getBoolean("possibly_sensitive", jsonobject);
        Object obj;
        if(!jsonobject.isNull("user"))
            user = new UserJSONImpl(jsonobject.getJSONObject("user"));
        geoLocation = JSONImplFactory.createGeoLocation(jsonobject);
        if(!jsonobject.isNull("place"))
            place = new PlaceJSONImpl(jsonobject.getJSONObject("place"));
        if(!jsonobject.isNull("retweeted_status"))
            retweetedStatus = new StatusJSONImpl(jsonobject.getJSONObject("retweeted_status"));
        if(jsonobject.isNull("contributors"))
            break MISSING_BLOCK_LABEL_284;
        obj = jsonobject.getJSONArray("contributors");
        contributorsIDs = new long[((JSONArray) (obj)).length()];
        int i = 0;
        do
        {
            int j;
            String as[];
            try
            {
                if(i >= ((JSONArray) (obj)).length())
                    break;
                contributorsIDs[i] = Long.parseLong(((JSONArray) (obj)).getString(i));
            }
            // Misplaced declaration of an exception variable
            catch(JSONObject jsonobject)
            {
                throw new TwitterException(jsonobject);
            }
            i++;
        } while(true);
        break MISSING_BLOCK_LABEL_291;
        contributorsIDs = new long[0];
        collectEntities(jsonobject);
        collectExtendedEntities(jsonobject);
        if(!jsonobject.isNull("quoted_status"))
            quotedStatus = new StatusJSONImpl(jsonobject.getJSONObject("quoted_status"));
        if(!jsonobject.isNull("quoted_status_id"))
            quotedStatusId = ParseUtil.getLong("quoted_status_id", jsonobject);
        if(!jsonobject.isNull("display_text_range"))
        {
            obj = jsonobject.getJSONArray("display_text_range");
            displayTextRangeStart = ((JSONArray) (obj)).getInt(0);
            displayTextRangeEnd = ((JSONArray) (obj)).getInt(1);
        }
        if(userMentionEntities != null) goto _L2; else goto _L1
_L1:
        obj = new UserMentionEntity[0];
_L27:
        userMentionEntities = ((UserMentionEntity []) (obj));
        if(urlEntities != null) goto _L4; else goto _L3
_L3:
        obj = new URLEntity[0];
_L18:
        urlEntities = ((URLEntity []) (obj));
        if(hashtagEntities != null) goto _L6; else goto _L5
_L5:
        obj = new HashtagEntity[0];
_L19:
        hashtagEntities = ((HashtagEntity []) (obj));
        if(symbolEntities != null) goto _L8; else goto _L7
_L7:
        obj = new SymbolEntity[0];
_L20:
        symbolEntities = ((SymbolEntity []) (obj));
        if(mediaEntities != null) goto _L10; else goto _L9
_L9:
        obj = new MediaEntity[0];
_L21:
        mediaEntities = ((MediaEntity []) (obj));
        if(extendedMediaEntities != null) goto _L12; else goto _L11
_L11:
        obj = new ExtendedMediaEntity[0];
_L22:
        extendedMediaEntities = ((ExtendedMediaEntity []) (obj));
        if(!jsonobject.isNull("text"))
            text = HTMLEntity.unescapeAndSlideEntityIncdices(jsonobject.getString("text"), userMentionEntities, urlEntities, hashtagEntities, mediaEntities);
        if(!jsonobject.isNull("full_text"))
            text = HTMLEntity.unescapeAndSlideEntityIncdices(jsonobject.getString("full_text"), userMentionEntities, urlEntities, hashtagEntities, mediaEntities);
        if(!jsonobject.isNull("extended_tweet"))
            mergeExtendedTweet(jsonobject.getJSONObject("extended_tweet"));
        if(!jsonobject.isNull("current_user_retweet"))
            currentUserRetweetId = jsonobject.getJSONObject("current_user_retweet").getLong("id");
        if(!jsonobject.isNull("lang"))
            lang = ParseUtil.getUnescapedString("lang", jsonobject);
        if(jsonobject.isNull("scopes")) goto _L14; else goto _L13
_L13:
        obj = jsonobject.getJSONObject("scopes");
        if(((JSONObject) (obj)).isNull("place_ids")) goto _L14; else goto _L15
_L15:
        obj = ((JSONObject) (obj)).getJSONArray("place_ids");
        j = ((JSONArray) (obj)).length();
        as = new String[j];
        i = 0;
_L17:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        as[i] = ((JSONArray) (obj)).getString(i);
        i++;
        if(true) goto _L17; else goto _L16
_L2:
        obj = userMentionEntities;
        continue; /* Loop/switch isn't completed */
_L4:
        obj = urlEntities;
          goto _L18
_L6:
        obj = hashtagEntities;
          goto _L19
_L8:
        obj = symbolEntities;
          goto _L20
_L10:
        obj = mediaEntities;
          goto _L21
_L12:
        obj = extendedMediaEntities;
          goto _L22
_L16:
        scopes = new ScopesImpl(as);
_L14:
        if(jsonobject.isNull("withheld_in_countries")) goto _L24; else goto _L23
_L23:
        jsonobject = jsonobject.getJSONArray("withheld_in_countries");
        j = jsonobject.length();
        withheldInCountries = new String[j];
        i = 0;
_L25:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        withheldInCountries[i] = jsonobject.getString(i);
        i++;
        if(true) goto _L25; else goto _L24
_L24:
        return;
        if(true) goto _L27; else goto _L26
_L26:
    }

    private void mergeExtendedTweet(JSONObject jsonobject)
        throws TwitterException
    {
        JSONArray jsonarray = jsonobject.getJSONArray("display_text_range");
        displayTextRangeStart = jsonarray.getInt(0);
        displayTextRangeEnd = jsonarray.getInt(1);
        collectEntities(jsonobject);
        collectExtendedEntities(jsonobject);
        if(userMentionEntities != null) goto _L2; else goto _L1
_L1:
        Object aobj[] = new UserMentionEntity[0];
_L11:
        userMentionEntities = ((UserMentionEntity []) (aobj));
        if(urlEntities != null) goto _L4; else goto _L3
_L3:
        aobj = new URLEntity[0];
_L12:
        urlEntities = ((URLEntity []) (aobj));
        if(hashtagEntities != null) goto _L6; else goto _L5
_L5:
        aobj = new HashtagEntity[0];
_L13:
        hashtagEntities = ((HashtagEntity []) (aobj));
        if(symbolEntities != null) goto _L8; else goto _L7
_L7:
        aobj = new SymbolEntity[0];
_L14:
        symbolEntities = ((SymbolEntity []) (aobj));
        if(mediaEntities != null) goto _L10; else goto _L9
_L9:
        aobj = new MediaEntity[0];
_L15:
        mediaEntities = ((MediaEntity []) (aobj));
        if(extendedMediaEntities != null)
            break MISSING_BLOCK_LABEL_209;
        aobj = new ExtendedMediaEntity[0];
_L16:
        extendedMediaEntities = ((ExtendedMediaEntity []) (aobj));
        text = HTMLEntity.unescapeAndSlideEntityIncdices(jsonobject.getString("full_text"), userMentionEntities, urlEntities, hashtagEntities, mediaEntities);
        return;
_L2:
        try
        {
            aobj = userMentionEntities;
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
          goto _L11
_L4:
        aobj = urlEntities;
          goto _L12
_L6:
        aobj = hashtagEntities;
          goto _L13
_L8:
        aobj = symbolEntities;
          goto _L14
_L10:
        aobj = mediaEntities;
          goto _L15
        aobj = extendedMediaEntities;
          goto _L16
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Status)obj);
    }

    public int compareTo(Status status)
    {
        long l = id - status.getId();
        if(l < 0x80000000L)
            return 0x80000000;
        if(l > 0x7fffffffL)
            return 0x7fffffff;
        else
            return (int)l;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = true;
        if(obj != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        flag = flag1;
        if(this == obj) goto _L4; else goto _L3
_L3:
        if(!(obj instanceof Status))
            break; /* Loop/switch isn't completed */
        flag = flag1;
        if(((Status)obj).getId() == id) goto _L4; else goto _L5
_L5:
        return false;
    }

    public long[] getContributors()
    {
        return contributorsIDs;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public long getCurrentUserRetweetId()
    {
        return currentUserRetweetId;
    }

    public int getDisplayTextRangeEnd()
    {
        return displayTextRangeEnd;
    }

    public int getDisplayTextRangeStart()
    {
        return displayTextRangeStart;
    }

    public ExtendedMediaEntity[] getExtendedMediaEntities()
    {
        return extendedMediaEntities;
    }

    public int getFavoriteCount()
    {
        return favoriteCount;
    }

    public GeoLocation getGeoLocation()
    {
        return geoLocation;
    }

    public HashtagEntity[] getHashtagEntities()
    {
        return hashtagEntities;
    }

    public long getId()
    {
        return id;
    }

    public String getInReplyToScreenName()
    {
        return inReplyToScreenName;
    }

    public long getInReplyToStatusId()
    {
        return inReplyToStatusId;
    }

    public long getInReplyToUserId()
    {
        return inReplyToUserId;
    }

    public String getLang()
    {
        return lang;
    }

    public MediaEntity[] getMediaEntities()
    {
        return mediaEntities;
    }

    public Place getPlace()
    {
        return place;
    }

    public Status getQuotedStatus()
    {
        return quotedStatus;
    }

    public long getQuotedStatusId()
    {
        return quotedStatusId;
    }

    public int getRetweetCount()
    {
        return (int)retweetCount;
    }

    public Status getRetweetedStatus()
    {
        return retweetedStatus;
    }

    public Scopes getScopes()
    {
        return scopes;
    }

    public String getSource()
    {
        return source;
    }

    public SymbolEntity[] getSymbolEntities()
    {
        return symbolEntities;
    }

    public String getText()
    {
        return text;
    }

    public URLEntity[] getURLEntities()
    {
        return urlEntities;
    }

    public User getUser()
    {
        return user;
    }

    public UserMentionEntity[] getUserMentionEntities()
    {
        return userMentionEntities;
    }

    public String[] getWithheldInCountries()
    {
        return withheldInCountries;
    }

    public int hashCode()
    {
        return (int)id;
    }

    public boolean isFavorited()
    {
        return isFavorited;
    }

    public boolean isPossiblySensitive()
    {
        return isPossiblySensitive;
    }

    public boolean isRetweet()
    {
        return retweetedStatus != null;
    }

    public boolean isRetweeted()
    {
        return isRetweeted;
    }

    public boolean isRetweetedByMe()
    {
        return currentUserRetweetId != -1L;
    }

    public boolean isTruncated()
    {
        return isTruncated;
    }

    public String toString()
    {
        return (new StringBuilder()).append("StatusJSONImpl{createdAt=").append(createdAt).append(", id=").append(id).append(", text='").append(text).append('\'').append(", source='").append(source).append('\'').append(", isTruncated=").append(isTruncated).append(", inReplyToStatusId=").append(inReplyToStatusId).append(", inReplyToUserId=").append(inReplyToUserId).append(", isFavorited=").append(isFavorited).append(", isRetweeted=").append(isRetweeted).append(", favoriteCount=").append(favoriteCount).append(", inReplyToScreenName='").append(inReplyToScreenName).append('\'').append(", geoLocation=").append(geoLocation).append(", place=").append(place).append(", retweetCount=").append(retweetCount).append(", isPossiblySensitive=").append(isPossiblySensitive).append(", lang='").append(lang).append('\'').append(", contributorsIDs=").append(Arrays.toString(contributorsIDs)).append(", retweetedStatus=").append(retweetedStatus).append(", userMentionEntities=").append(Arrays.toString(userMentionEntities)).append(", urlEntities=").append(Arrays.toString(urlEntities)).append(", hashtagEntities=").append(Arrays.toString(hashtagEntities)).append(", mediaEntities=").append(Arrays.toString(mediaEntities)).append(", symbolEntities=").append(Arrays.toString(symbolEntities)).append(", currentUserRetweetId=").append(currentUserRetweetId).append(", user=").append(user).append(", withHeldInCountries=").append(Arrays.toString(withheldInCountries)).append(", quotedStatusId=").append(quotedStatusId).append(", quotedStatus=").append(quotedStatus).append('}').toString();
    }

    private static final Logger logger = Logger.getLogger(twitter4j/StatusJSONImpl);
    private static final long serialVersionUID = 0x5c21f60fL;
    private long contributorsIDs[];
    private Date createdAt;
    private long currentUserRetweetId;
    private int displayTextRangeEnd;
    private int displayTextRangeStart;
    private ExtendedMediaEntity extendedMediaEntities[];
    private int favoriteCount;
    private GeoLocation geoLocation;
    private HashtagEntity hashtagEntities[];
    private long id;
    private String inReplyToScreenName;
    private long inReplyToStatusId;
    private long inReplyToUserId;
    private boolean isFavorited;
    private boolean isPossiblySensitive;
    private boolean isRetweeted;
    private boolean isTruncated;
    private String lang;
    private MediaEntity mediaEntities[];
    private Place place;
    private Status quotedStatus;
    private long quotedStatusId;
    private long retweetCount;
    private Status retweetedStatus;
    private Scopes scopes;
    private String source;
    private SymbolEntity symbolEntities[];
    private String text;
    private URLEntity urlEntities[];
    private User user;
    private UserMentionEntity userMentionEntities[];
    private String withheldInCountries[];

}
