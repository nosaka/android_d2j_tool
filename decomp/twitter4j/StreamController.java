// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            Logger, HttpClientFactory, TwitterException, HttpParameter, 
//            StringUtil, HttpClient, HttpResponse, ControlStreamInfo, 
//            JSONObject, CursorSupport, JSONException, JSONArray, 
//            ParseUtil

public class StreamController
{
    public final class FriendsIDs
        implements CursorSupport, Serializable
    {

        private void init(JSONObject jsonobject)
            throws TwitterException
        {
            Object obj;
            int i;
            int j;
            JSONArray jsonarray;
            try
            {
                obj = jsonobject.getJSONObject("follow");
                jsonarray = ((JSONObject) (obj)).getJSONArray("friends");
                ids = new long[jsonarray.length()];
            }
            // Misplaced declaration of an exception variable
            catch(JSONObject jsonobject)
            {
                throw new TwitterException(jsonobject);
            }
            i = 0;
            j = jsonarray.length();
            if(i >= j)
                break MISSING_BLOCK_LABEL_104;
            ids[i] = Long.parseLong(jsonarray.getString(i));
            i++;
            break MISSING_BLOCK_LABEL_30;
            obj;
            throw new TwitterException((new StringBuilder()).append("Twitter API returned malformed response: ").append(jsonobject).toString(), ((Throwable) (obj)));
            user = new User(((JSONObject) (obj)).getJSONObject("user"));
            previousCursor = ParseUtil.getLong("previous_cursor", jsonobject);
            nextCursor = ParseUtil.getLong("next_cursor", jsonobject);
            return;
        }

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (FriendsIDs)obj;
                if(nextCursor != ((FriendsIDs) (obj)).nextCursor)
                    return false;
                if(previousCursor != ((FriendsIDs) (obj)).previousCursor)
                    return false;
                if(!Arrays.equals(ids, ((FriendsIDs) (obj)).ids))
                    return false;
                if(user == null ? ((FriendsIDs) (obj)).user != null : !user.equals(((FriendsIDs) (obj)).user))
                    return false;
            }
            return true;
        }

        public long[] getIds()
        {
            return ids;
        }

        public long getNextCursor()
        {
            return nextCursor;
        }

        public long getPreviousCursor()
        {
            return previousCursor;
        }

        public User getUser()
        {
            return user;
        }

        public boolean hasNext()
        {
            return 0L != nextCursor;
        }

        public boolean hasPrevious()
        {
            return 0L != previousCursor;
        }

        public int hashCode()
        {
            int j = 0;
            int i;
            int k;
            int l;
            if(ids != null)
                i = Arrays.hashCode(ids);
            else
                i = 0;
            k = (int)(previousCursor ^ previousCursor >>> 32);
            l = (int)(nextCursor ^ nextCursor >>> 32);
            if(user != null)
                j = user.hashCode();
            return ((i * 31 + k) * 31 + l) * 31 + j;
        }

        public String toString()
        {
            return (new StringBuilder()).append("FriendsIDs{ids=").append(Arrays.toString(ids)).append(", previousCursor=").append(previousCursor).append(", nextCursor=").append(nextCursor).append(", user=").append(user).append('}').toString();
        }

        private static final long serialVersionUID = 0xf02fdbeL;
        private long ids[];
        private long nextCursor;
        private long previousCursor;
        final StreamController this$0;
        private User user;

        FriendsIDs(HttpResponse httpresponse)
            throws TwitterException
        {
            this$0 = StreamController.this;
            super();
            previousCursor = -1L;
            nextCursor = -1L;
            init(httpresponse.asJSONObject());
        }
    }

    public final class User
        implements Serializable
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (User)obj;
                if(dm != ((User) (obj)).dm)
                    return false;
                if(id != ((User) (obj)).id)
                    return false;
                if(name == null ? ((User) (obj)).name != null : !name.equals(((User) (obj)).name))
                    return false;
            }
            return true;
        }

        public long getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public int hashCode()
        {
            int j = 0;
            int k = (int)(id ^ id >>> 32);
            int i;
            if(name != null)
                i = name.hashCode();
            else
                i = 0;
            if(dm)
                j = 1;
            return (k * 31 + i) * 31 + j;
        }

        public boolean isDMAccessible()
        {
            return dm;
        }

        public String toString()
        {
            return (new StringBuilder()).append("User{id=").append(id).append(", name='").append(name).append('\'').append(", dm=").append(dm).append('}').toString();
        }

        private static final long serialVersionUID = 0x80a8cf96L;
        private final boolean dm;
        private final long id;
        private final String name;
        final StreamController this$0;

        User(JSONObject jsonobject)
        {
            this$0 = StreamController.this;
            super();
            id = ParseUtil.getLong("id", jsonobject);
            name = ParseUtil.getRawString("name", jsonobject);
            dm = ParseUtil.getBoolean("dm", jsonobject);
        }
    }


    StreamController(HttpClient httpclient, Authorization authorization)
    {
        controlURI = null;
        lock = new Object();
        http = httpclient;
        AUTH = authorization;
    }

    StreamController(Configuration configuration)
    {
        controlURI = null;
        lock = new Object();
        http = HttpClientFactory.getInstance(configuration.getHttpClientConfiguration());
        AUTH = AuthorizationFactory.getInstance(configuration);
    }

    public transient String addUsers(long al[])
        throws TwitterException
    {
        ensureControlURISet();
        al = new HttpParameter("user_id", StringUtil.join(al));
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(controlURI).append("/add_user.json").toString();
        Authorization authorization = AUTH;
        return httpclient.post(s, new HttpParameter[] {
            al
        }, authorization, null).asString();
    }

    User createUser(JSONObject jsonobject)
    {
        return new User(jsonobject);
    }

    void ensureControlURISet()
        throws TwitterException
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        if(controlURI != null)
            break MISSING_BLOCK_LABEL_50;
        lock.wait(1000L);
        int j;
        j = i + 1;
        i = j;
        if(j <= 29) goto _L2; else goto _L1
_L1:
        try
        {
            throw new TwitterException("timed out for control uri to be ready");
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    String getControlURI()
    {
        return controlURI;
    }

    public FriendsIDs getFriendsIDs(long l, long l1)
        throws TwitterException
    {
        ensureControlURISet();
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(controlURI).append("/friends/ids.json").toString();
        HttpParameter httpparameter = new HttpParameter("user_id", l);
        HttpParameter httpparameter1 = new HttpParameter("cursor", l1);
        Authorization authorization = AUTH;
        return new FriendsIDs(httpclient.post(s, new HttpParameter[] {
            httpparameter, httpparameter1
        }, authorization, null));
    }

    public ControlStreamInfo getInfo()
        throws TwitterException
    {
        ensureControlURISet();
        return new ControlStreamInfo(this, http.get((new StringBuilder()).append(controlURI).append("/info.json").toString(), null, AUTH, null).asJSONObject());
    }

    public transient String removeUsers(long al[])
        throws TwitterException
    {
        ensureControlURISet();
        al = new HttpParameter("user_id", StringUtil.join(al));
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(controlURI).append("/remove_user.json").toString();
        Authorization authorization = AUTH;
        return httpclient.post(s, new HttpParameter[] {
            al
        }, authorization, null).asString();
    }

    void setControlURI(String s)
    {
        if(s != null)
            s = s.replace("/1.1//1.1/", "/1.1/");
        else
            s = null;
        controlURI = s;
        synchronized(lock)
        {
            lock.notifyAll();
        }
        return;
        exception;
        s;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static final Logger logger = Logger.getLogger(twitter4j/StreamController);
    private final Authorization AUTH;
    private String controlURI;
    private final HttpClient http;
    private final Object lock;

}
