// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.util.Arrays;

// Referenced classes of package twitter4j:
//            MediaEntityJSONImpl, ExtendedMediaEntity, TwitterException, JSONException, 
//            JSONObject, JSONArray

public class ExtendedMediaEntityJSONImpl extends MediaEntityJSONImpl
    implements ExtendedMediaEntity
{
    static class Variant
        implements ExtendedMediaEntity.Variant
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(!(obj instanceof Variant))
                    return false;
                obj = (Variant)obj;
                if(bitrate != ((Variant) (obj)).bitrate)
                    return false;
                if(!contentType.equals(((Variant) (obj)).contentType))
                    return false;
                if(!url.equals(((Variant) (obj)).url))
                    return false;
            }
            return true;
        }

        public int getBitrate()
        {
            return bitrate;
        }

        public String getContentType()
        {
            return contentType;
        }

        public String getUrl()
        {
            return url;
        }

        public int hashCode()
        {
            int j = 0;
            int k = bitrate;
            int i;
            if(contentType != null)
                i = contentType.hashCode();
            else
                i = 0;
            if(url != null)
                j = url.hashCode();
            return (k * 31 + i) * 31 + j;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Variant{bitrate=").append(bitrate).append(", contentType=").append(contentType).append(", url=").append(url).append('}').toString();
        }

        private static final long serialVersionUID = 0x1936481cL;
        int bitrate;
        String contentType;
        String url;

        Variant()
        {
        }

        Variant(JSONObject jsonobject)
            throws JSONException
        {
            int i;
            if(jsonobject.has("bitrate"))
                i = jsonobject.getInt("bitrate");
            else
                i = 0;
            bitrate = i;
            contentType = jsonobject.getString("content_type");
            url = jsonobject.getString("url");
        }
    }


    ExtendedMediaEntityJSONImpl()
    {
    }

    ExtendedMediaEntityJSONImpl(JSONObject jsonobject)
        throws TwitterException
    {
        super(jsonobject);
        Object obj;
        if(!jsonobject.has("video_info"))
            break MISSING_BLOCK_LABEL_121;
        obj = jsonobject.getJSONObject("video_info");
        JSONArray jsonarray = ((JSONObject) (obj)).getJSONArray("aspect_ratio");
        videoAspectRatioWidth = jsonarray.getInt(0);
        videoAspectRatioHeight = jsonarray.getInt(1);
        if(!((JSONObject) (obj)).isNull("duration_millis"))
            videoDurationMillis = ((JSONObject) (obj)).getLong("duration_millis");
        obj = ((JSONObject) (obj)).getJSONArray("variants");
        videoVariants = new Variant[((JSONArray) (obj)).length()];
        int i = 0;
        do
        {
            try
            {
                if(i >= ((JSONArray) (obj)).length())
                    break;
                videoVariants[i] = new Variant(((JSONArray) (obj)).getJSONObject(i));
            }
            // Misplaced declaration of an exception variable
            catch(JSONObject jsonobject)
            {
                throw new TwitterException(jsonobject);
            }
            i++;
        } while(true);
        break MISSING_BLOCK_LABEL_129;
        videoVariants = new Variant[0];
        if(jsonobject.has("ext_alt_text"))
            extAltText = jsonobject.getString("ext_alt_text");
        return;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(!(obj instanceof ExtendedMediaEntityJSONImpl))
                return false;
            obj = (ExtendedMediaEntityJSONImpl)obj;
            if(id != ((ExtendedMediaEntityJSONImpl) (obj)).id)
                return false;
        }
        return true;
    }

    public String getExtAltText()
    {
        return extAltText;
    }

    public int getVideoAspectRatioHeight()
    {
        return videoAspectRatioHeight;
    }

    public int getVideoAspectRatioWidth()
    {
        return videoAspectRatioWidth;
    }

    public long getVideoDurationMillis()
    {
        return videoDurationMillis;
    }

    public ExtendedMediaEntity.Variant[] getVideoVariants()
    {
        return videoVariants;
    }

    public int hashCode()
    {
        return (int)(id ^ id >>> 32);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ExtendedMediaEntityJSONImpl{id=").append(id).append(", url=").append(url).append(", mediaURL=").append(mediaURL).append(", mediaURLHttps=").append(mediaURLHttps).append(", expandedURL=").append(expandedURL).append(", displayURL='").append(displayURL).append('\'').append(", sizes=").append(sizes).append(", type=").append(type).append(", videoAspectRatioWidth=").append(videoAspectRatioWidth).append(", videoAspectRatioHeight=").append(videoAspectRatioHeight).append(", videoDurationMillis=").append(videoDurationMillis).append(", extAltText=").append(extAltText).append(", videoVariants=").append(Arrays.toString(videoVariants)).append('}').toString();
    }

    private static final long serialVersionUID = 0x1bf96625L;
    private String extAltText;
    private int videoAspectRatioHeight;
    private int videoAspectRatioWidth;
    private long videoDurationMillis;
    private Variant videoVariants[];
}
