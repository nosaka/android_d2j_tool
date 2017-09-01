// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;


public final class EnumImageFormat extends Enum
{

    private EnumImageFormat(String s, int i)
    {
        super(s, i);
    }

    public static EnumImageFormat valueOf(String s)
    {
        return (EnumImageFormat)Enum.valueOf(net/wakamesoba98/sobacha/image/EnumImageFormat, s);
    }

    public static EnumImageFormat[] values()
    {
        return (EnumImageFormat[])$VALUES.clone();
    }

    private static final EnumImageFormat $VALUES[];
    public static final EnumImageFormat JPEG;
    public static final EnumImageFormat OTHER;
    public static final EnumImageFormat PNG;

    static 
    {
        JPEG = new EnumImageFormat("JPEG", 0);
        PNG = new EnumImageFormat("PNG", 1);
        OTHER = new EnumImageFormat("OTHER", 2);
        $VALUES = (new EnumImageFormat[] {
            JPEG, PNG, OTHER
        });
    }
}
