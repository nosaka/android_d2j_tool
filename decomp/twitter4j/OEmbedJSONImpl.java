// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j:
//            TwitterResponseImpl, OEmbed, TwitterException, HttpResponse, 
//            TwitterObjectFactory, JSONException, JSONObject, RateLimitStatus

public class OEmbedJSONImpl extends TwitterResponseImpl
    implements OEmbed, Serializable
{

    OEmbedJSONImpl(HttpResponse httpresponse, Configuration configuration)
        throws TwitterException
    {
        super(httpresponse);
        httpresponse = httpresponse.asJSONObject();
        init(httpresponse);
        if(configuration.isJSONStoreEnabled())
        {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpresponse);
        }
    }

    OEmbedJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        init(jsonobject);
    }

    private void init(JSONObject jsonobject)
        throws TwitterException
    {
        try
        {
            html = jsonobject.getString("html");
            authorName = jsonobject.getString("author_name");
            url = jsonobject.getString("url");
            version = jsonobject.getString("version");
            cacheAge = jsonobject.getLong("cache_age");
            authorURL = jsonobject.getString("author_url");
            width = jsonobject.getInt("width");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (OEmbedJSONImpl)obj;
            if(cacheAge != ((OEmbedJSONImpl) (obj)).cacheAge)
                return false;
            if(width != ((OEmbedJSONImpl) (obj)).width)
                return false;
            if(authorName == null ? ((OEmbedJSONImpl) (obj)).authorName != null : !authorName.equals(((OEmbedJSONImpl) (obj)).authorName))
                return false;
            if(authorURL == null ? ((OEmbedJSONImpl) (obj)).authorURL != null : !authorURL.equals(((OEmbedJSONImpl) (obj)).authorURL))
                return false;
            if(html == null ? ((OEmbedJSONImpl) (obj)).html != null : !html.equals(((OEmbedJSONImpl) (obj)).html))
                return false;
            if(url == null ? ((OEmbedJSONImpl) (obj)).url != null : !url.equals(((OEmbedJSONImpl) (obj)).url))
                return false;
            if(version == null ? ((OEmbedJSONImpl) (obj)).version != null : !version.equals(((OEmbedJSONImpl) (obj)).version))
                return false;
        }
        return true;
    }

    public volatile int getAccessLevel()
    {
        return super.getAccessLevel();
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public String getAuthorURL()
    {
        return authorURL;
    }

    public long getCacheAge()
    {
        return cacheAge;
    }

    public String getHtml()
    {
        return html;
    }

    public volatile RateLimitStatus getRateLimitStatus()
    {
        return super.getRateLimitStatus();
    }

    public String getURL()
    {
        return url;
    }

    public String getVersion()
    {
        return version;
    }

    public int getWidth()
    {
        return width;
    }

    public int hashCode()
    {
        int i1 = 0;
        int i;
        int j;
        int k;
        int l;
        int j1;
        if(html != null)
            i = html.hashCode();
        else
            i = 0;
        if(authorName != null)
            j = authorName.hashCode();
        else
            j = 0;
        if(url != null)
            k = url.hashCode();
        else
            k = 0;
        if(version != null)
            l = version.hashCode();
        else
            l = 0;
        j1 = (int)(cacheAge ^ cacheAge >>> 32);
        if(authorURL != null)
            i1 = authorURL.hashCode();
        return (((((i * 31 + j) * 31 + k) * 31 + l) * 31 + j1) * 31 + i1) * 31 + width;
    }

    public String toString()
    {
        return (new StringBuilder()).append("OEmbedJSONImpl{html='").append(html).append('\'').append(", authorName='").append(authorName).append('\'').append(", url='").append(url).append('\'').append(", version='").append(version).append('\'').append(", cacheAge=").append(cacheAge).append(", authorURL='").append(authorURL).append('\'').append(", width=").append(width).append('}').toString();
    }

    private static final long serialVersionUID = 0x4b03e685L;
    private String authorName;
    private String authorURL;
    private long cacheAge;
    private String html;
    private String url;
    private String version;
    private int width;
}
