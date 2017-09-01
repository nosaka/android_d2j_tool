// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

// Referenced classes of package twitter4j:
//            MediaEntity

public interface ExtendedMediaEntity
    extends MediaEntity
{
    public static interface Variant
        extends Serializable
    {

        public abstract int getBitrate();

        public abstract String getContentType();

        public abstract String getUrl();
    }


    public abstract String getExtAltText();

    public abstract int getVideoAspectRatioHeight();

    public abstract int getVideoAspectRatioWidth();

    public abstract long getVideoDurationMillis();

    public abstract Variant[] getVideoVariants();
}
