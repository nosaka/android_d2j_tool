// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package twitter4j:
//            EntityIndex, MediaEntity, TwitterException, JSONException, 
//            JSONObject, JSONArray, ParseUtil

public class MediaEntityJSONImpl extends EntityIndex
    implements MediaEntity
{
    static class Size
        implements MediaEntity.Size
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(!(obj instanceof Size))
                    return false;
                obj = (Size)obj;
                if(height != ((Size) (obj)).height)
                    return false;
                if(resize != ((Size) (obj)).resize)
                    return false;
                if(width != ((Size) (obj)).width)
                    return false;
            }
            return true;
        }

        public int getHeight()
        {
            return height;
        }

        public int getResize()
        {
            return resize;
        }

        public int getWidth()
        {
            return width;
        }

        public int hashCode()
        {
            return (width * 31 + height) * 31 + resize;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Size{width=").append(width).append(", height=").append(height).append(", resize=").append(resize).append('}').toString();
        }

        private static final long serialVersionUID = 0x2be2f28fL;
        int height;
        int resize;
        int width;

        Size()
        {
        }

        Size(JSONObject jsonobject)
            throws JSONException
        {
            width = jsonobject.getInt("w");
            height = jsonobject.getInt("h");
            int i;
            if("fit".equals(jsonobject.getString("resize")))
                i = 100;
            else
                i = 101;
            resize = i;
        }
    }


    MediaEntityJSONImpl()
    {
    }

    MediaEntityJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        try
        {
            Object obj = jsonobject.getJSONArray("indices");
            setStart(((JSONArray) (obj)).getInt(0));
            setEnd(((JSONArray) (obj)).getInt(1));
            id = ParseUtil.getLong("id", jsonobject);
            url = jsonobject.getString("url");
            expandedURL = jsonobject.getString("expanded_url");
            mediaURL = jsonobject.getString("media_url");
            mediaURLHttps = jsonobject.getString("media_url_https");
            displayURL = jsonobject.getString("display_url");
            obj = jsonobject.getJSONObject("sizes");
            sizes = new HashMap(4);
            addMediaEntitySizeIfNotNull(sizes, ((JSONObject) (obj)), MediaEntity.Size.LARGE, "large");
            addMediaEntitySizeIfNotNull(sizes, ((JSONObject) (obj)), MediaEntity.Size.MEDIUM, "medium");
            addMediaEntitySizeIfNotNull(sizes, ((JSONObject) (obj)), MediaEntity.Size.SMALL, "small");
            addMediaEntitySizeIfNotNull(sizes, ((JSONObject) (obj)), MediaEntity.Size.THUMB, "thumb");
            if(!jsonobject.isNull("type"))
                type = jsonobject.getString("type");
            return;
        }
        // Misplaced declaration of an exception variable
        catch(JSONObject jsonobject)
        {
            throw new TwitterException(jsonobject);
        }
    }

    private void addMediaEntitySizeIfNotNull(Map map, JSONObject jsonobject, Integer integer, String s)
        throws JSONException
    {
        if(!jsonobject.isNull(s))
            map.put(integer, new Size(jsonobject.getJSONObject(s)));
    }

    public volatile int compareTo(EntityIndex entityindex)
    {
        return super.compareTo(entityindex);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(!(obj instanceof MediaEntityJSONImpl))
                return false;
            obj = (MediaEntityJSONImpl)obj;
            if(id != ((MediaEntityJSONImpl) (obj)).id)
                return false;
        }
        return true;
    }

    public String getDisplayURL()
    {
        return displayURL;
    }

    public int getEnd()
    {
        return super.getEnd();
    }

    public String getExpandedURL()
    {
        return expandedURL;
    }

    public long getId()
    {
        return id;
    }

    public String getMediaURL()
    {
        return mediaURL;
    }

    public String getMediaURLHttps()
    {
        return mediaURLHttps;
    }

    public Map getSizes()
    {
        return sizes;
    }

    public int getStart()
    {
        return super.getStart();
    }

    public String getText()
    {
        return url;
    }

    public String getType()
    {
        return type;
    }

    public String getURL()
    {
        return url;
    }

    public int hashCode()
    {
        return (int)(id ^ id >>> 32);
    }

    public String toString()
    {
        return (new StringBuilder()).append("MediaEntityJSONImpl{id=").append(id).append(", url=").append(url).append(", mediaURL=").append(mediaURL).append(", mediaURLHttps=").append(mediaURLHttps).append(", expandedURL=").append(expandedURL).append(", displayURL='").append(displayURL).append('\'').append(", sizes=").append(sizes).append(", type=").append(type).append('}').toString();
    }

    private static final long serialVersionUID = 0xfb3fb272L;
    protected String displayURL;
    protected String expandedURL;
    protected long id;
    protected String mediaURL;
    protected String mediaURLHttps;
    protected Map sizes;
    protected String type;
    protected String url;
}
