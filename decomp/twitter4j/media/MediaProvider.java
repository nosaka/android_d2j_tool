// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;


public final class MediaProvider extends Enum
{

    private MediaProvider(String s, int i)
    {
        super(s, i);
    }

    public static MediaProvider valueOf(String s)
    {
        return (MediaProvider)Enum.valueOf(twitter4j/media/MediaProvider, s);
    }

    public static MediaProvider[] values()
    {
        return (MediaProvider[])$VALUES.clone();
    }

    private static final MediaProvider $VALUES[];
    public static final MediaProvider IMG_LY;
    public static final MediaProvider MOBYPICTURE;
    public static final MediaProvider TWIPPLE;
    public static final MediaProvider TWITTER;

    static 
    {
        TWITTER = new MediaProvider("TWITTER", 0);
        IMG_LY = new MediaProvider("IMG_LY", 1);
        TWIPPLE = new MediaProvider("TWIPPLE", 2);
        MOBYPICTURE = new MediaProvider("MOBYPICTURE", 3);
        $VALUES = (new MediaProvider[] {
            TWITTER, IMG_LY, TWIPPLE, MOBYPICTURE
        });
    }
}
