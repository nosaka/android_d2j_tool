// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            JSONException, JSONObject, ParseUtil

public final class StallWarning
    implements Serializable
{

    StallWarning(JSONObject jsonobject)
        throws JSONException
    {
        jsonobject = jsonobject.getJSONObject("warning");
        code = ParseUtil.getRawString("code", jsonobject);
        message = ParseUtil.getRawString("message", jsonobject);
        percentFull = ParseUtil.getInt("percent_full", jsonobject);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (StallWarning)obj;
            if(percentFull != ((StallWarning) (obj)).percentFull)
                return false;
            if(code == null ? ((StallWarning) (obj)).code != null : !code.equals(((StallWarning) (obj)).code))
                return false;
            if(message == null ? ((StallWarning) (obj)).message != null : !message.equals(((StallWarning) (obj)).message))
                return false;
        }
        return true;
    }

    public String getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public int getPercentFull()
    {
        return percentFull;
    }

    public int hashCode()
    {
        int j = 0;
        int i;
        if(code != null)
            i = code.hashCode();
        else
            i = 0;
        if(message != null)
            j = message.hashCode();
        return (i * 31 + j) * 31 + percentFull;
    }

    public String toString()
    {
        return (new StringBuilder()).append("StallWarning{code='").append(code).append('\'').append(", message='").append(message).append('\'').append(", percentFull=").append(percentFull).append('}').toString();
    }

    private static final long serialVersionUID = 0x8f6c4f56L;
    private final String code;
    private final String message;
    private final int percentFull;
}
