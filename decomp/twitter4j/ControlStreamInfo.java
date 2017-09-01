// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package twitter4j:
//            TwitterException, JSONException, JSONObject, ParseUtil, 
//            JSONArray, StreamController

public final class ControlStreamInfo
    implements Serializable
{

    ControlStreamInfo(StreamController streamcontroller, JSONObject jsonobject)
        throws TwitterException
    {
        int i;
        try
        {
            jsonobject = jsonobject.getJSONObject("info");
            includeFollowingsActivity = ParseUtil.getBoolean("include_followings_activity", jsonobject);
            includeUserChanges = ParseUtil.getBoolean("include_user_changes", jsonobject);
            replies = ParseUtil.getRawString("replies", jsonobject);
            with = ParseUtil.getRawString("with", jsonobject);
            jsonobject = jsonobject.getJSONArray("users");
            users = new StreamController.User[jsonobject.length()];
        }
        // Misplaced declaration of an exception variable
        catch(StreamController streamcontroller)
        {
            throw new TwitterException(streamcontroller);
        }
        i = 0;
        if(i >= jsonobject.length())
            break; /* Loop/switch isn't completed */
        users[i] = streamcontroller.createUser(jsonobject.getJSONObject(i));
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_71;
_L1:
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (ControlStreamInfo)obj;
            if(includeFollowingsActivity != ((ControlStreamInfo) (obj)).includeFollowingsActivity)
                return false;
            if(includeUserChanges != ((ControlStreamInfo) (obj)).includeUserChanges)
                return false;
            if(replies == null ? ((ControlStreamInfo) (obj)).replies != null : !replies.equals(((ControlStreamInfo) (obj)).replies))
                return false;
            if(!Arrays.equals(users, ((ControlStreamInfo) (obj)).users))
                return false;
            if(with == null ? ((ControlStreamInfo) (obj)).with != null : !with.equals(((ControlStreamInfo) (obj)).with))
                return false;
        }
        return true;
    }

    public StreamController.User[] getUsers()
    {
        return users;
    }

    public int hashCode()
    {
        int k = 1;
        int i1 = 0;
        int i;
        int j;
        int l;
        if(users != null)
            i = Arrays.hashCode(users);
        else
            i = 0;
        if(includeFollowingsActivity)
            j = 1;
        else
            j = 0;
        if(!includeUserChanges)
            k = 0;
        if(replies != null)
            l = replies.hashCode();
        else
            l = 0;
        if(with != null)
            i1 = with.hashCode();
        return (((i * 31 + j) * 31 + k) * 31 + l) * 31 + i1;
    }

    public boolean isIncludeFollowingsActivity()
    {
        return includeFollowingsActivity;
    }

    public boolean isIncludeUserChanges()
    {
        return includeUserChanges;
    }

    public String isReplies()
    {
        return replies;
    }

    public String isWith()
    {
        return with;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ControlStreamInfo{users=");
        java.util.List list;
        if(users == null)
            list = null;
        else
            list = Arrays.asList(users);
        return stringbuilder.append(list).append(", includeFollowingsActivity=").append(includeFollowingsActivity).append(", includeUserChanges=").append(includeUserChanges).append(", replies='").append(replies).append('\'').append(", with='").append(with).append('\'').append('}').toString();
    }

    private static final long serialVersionUID = 0xc2ab499bL;
    private final boolean includeFollowingsActivity;
    private final boolean includeUserChanges;
    private final String replies;
    private final StreamController.User users[];
    private final String with;
}
