// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Map;

// Referenced classes of package twitter4j:
//            URLEntity

public interface MediaEntity
    extends URLEntity
{
    public static interface Size
        extends Serializable
    {

        public abstract int getHeight();

        public abstract int getResize();

        public abstract int getWidth();

        public static final int CROP = 101;
        public static final int FIT = 100;
        public static final Integer LARGE = Integer.valueOf(3);
        public static final Integer MEDIUM = Integer.valueOf(2);
        public static final Integer SMALL = Integer.valueOf(1);
        public static final Integer THUMB = Integer.valueOf(0);

    }


    public abstract long getId();

    public abstract String getMediaURL();

    public abstract String getMediaURLHttps();

    public abstract Map getSizes();

    public abstract String getType();
}
