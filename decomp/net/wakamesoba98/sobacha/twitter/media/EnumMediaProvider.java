// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.media;


public final class EnumMediaProvider extends Enum
{

    private EnumMediaProvider(String s, int i)
    {
        super(s, i);
    }

    public static EnumMediaProvider valueOf(String s)
    {
        return (EnumMediaProvider)Enum.valueOf(net/wakamesoba98/sobacha/twitter/media/EnumMediaProvider, s);
    }

    public static EnumMediaProvider[] values()
    {
        return (EnumMediaProvider[])$VALUES.clone();
    }

    private static final EnumMediaProvider $VALUES[];
    public static final EnumMediaProvider FLICKR;
    public static final EnumMediaProvider IMGUR;
    public static final EnumMediaProvider IMG_LY;
    public static final EnumMediaProvider INSTAGRAM;
    public static final EnumMediaProvider OTHER;
    public static final EnumMediaProvider TWIPPLE;
    public static final EnumMediaProvider TWITPIC;
    public static final EnumMediaProvider TWITTER;
    public static final EnumMediaProvider TWITTER_DM;
    public static final EnumMediaProvider VIA_ME;
    public static final EnumMediaProvider VIDEO_MP4;
    public static final EnumMediaProvider YFROG;

    static 
    {
        TWITTER = new EnumMediaProvider("TWITTER", 0);
        TWITTER_DM = new EnumMediaProvider("TWITTER_DM", 1);
        TWITPIC = new EnumMediaProvider("TWITPIC", 2);
        YFROG = new EnumMediaProvider("YFROG", 3);
        INSTAGRAM = new EnumMediaProvider("INSTAGRAM", 4);
        TWIPPLE = new EnumMediaProvider("TWIPPLE", 5);
        IMGUR = new EnumMediaProvider("IMGUR", 6);
        IMG_LY = new EnumMediaProvider("IMG_LY", 7);
        VIA_ME = new EnumMediaProvider("VIA_ME", 8);
        FLICKR = new EnumMediaProvider("FLICKR", 9);
        VIDEO_MP4 = new EnumMediaProvider("VIDEO_MP4", 10);
        OTHER = new EnumMediaProvider("OTHER", 11);
        $VALUES = (new EnumMediaProvider[] {
            TWITTER, TWITTER_DM, TWITPIC, YFROG, INSTAGRAM, TWIPPLE, IMGUR, IMG_LY, VIA_ME, FLICKR, 
            VIDEO_MP4, OTHER
        });
    }
}
