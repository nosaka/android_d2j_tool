// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.item;


public final class EnumStatusType extends Enum
{

    private EnumStatusType(String s, int i)
    {
        super(s, i);
    }

    public static EnumStatusType valueOf(String s)
    {
        return (EnumStatusType)Enum.valueOf(net/wakamesoba98/sobacha/view/listview/item/EnumStatusType, s);
    }

    public static EnumStatusType[] values()
    {
        return (EnumStatusType[])$VALUES.clone();
    }

    private static final EnumStatusType $VALUES[];
    public static final EnumStatusType DIRECT_MESSAGE;
    public static final EnumStatusType DIRECT_MESSAGE_USER;
    public static final EnumStatusType FAVORITED;
    public static final EnumStatusType MENTION;
    public static final EnumStatusType NORMAL;
    public static final EnumStatusType PROFILE;
    public static final EnumStatusType READ_MORE;
    public static final EnumStatusType RETWEETED;

    static 
    {
        NORMAL = new EnumStatusType("NORMAL", 0);
        MENTION = new EnumStatusType("MENTION", 1);
        RETWEETED = new EnumStatusType("RETWEETED", 2);
        FAVORITED = new EnumStatusType("FAVORITED", 3);
        DIRECT_MESSAGE_USER = new EnumStatusType("DIRECT_MESSAGE_USER", 4);
        DIRECT_MESSAGE = new EnumStatusType("DIRECT_MESSAGE", 5);
        PROFILE = new EnumStatusType("PROFILE", 6);
        READ_MORE = new EnumStatusType("READ_MORE", 7);
        $VALUES = (new EnumStatusType[] {
            NORMAL, MENTION, RETWEETED, FAVORITED, DIRECT_MESSAGE_USER, DIRECT_MESSAGE, PROFILE, READ_MORE
        });
    }
}
