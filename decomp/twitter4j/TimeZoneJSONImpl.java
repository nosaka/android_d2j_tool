// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            TimeZone, TwitterException, JSONException, ParseUtil, 
//            JSONObject

public class TimeZoneJSONImpl
    implements TimeZone
{

    TimeZoneJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        try
        {
            UTC_OFFSET = ParseUtil.getInt("utc_offset", jsonobject);
            NAME = jsonobject.getString("name");
            TZINFO_NAME = jsonobject.getString("tzinfo_name");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
    }

    public String getName()
    {
        return NAME;
    }

    public String tzinfoName()
    {
        return TZINFO_NAME;
    }

    public int utcOffset()
    {
        return UTC_OFFSET;
    }

    private static final long serialVersionUID = 0xaed98fb0L;
    private final String NAME;
    private final String TZINFO_NAME;
    private final int UTC_OFFSET;
}
