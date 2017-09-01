// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.relationship;


public final class EnumRelationshipType extends Enum
{

    private EnumRelationshipType(String s, int i)
    {
        super(s, i);
    }

    public static EnumRelationshipType valueOf(String s)
    {
        return (EnumRelationshipType)Enum.valueOf(net/wakamesoba98/sobacha/twitter/relationship/EnumRelationshipType, s);
    }

    public static EnumRelationshipType[] values()
    {
        return (EnumRelationshipType[])$VALUES.clone();
    }

    private static final EnumRelationshipType $VALUES[];
    public static final EnumRelationshipType BLOCKING;
    public static final EnumRelationshipType FOLLOWING;
    public static final EnumRelationshipType ME;
    public static final EnumRelationshipType NOT_FOLLOWING;

    static 
    {
        ME = new EnumRelationshipType("ME", 0);
        FOLLOWING = new EnumRelationshipType("FOLLOWING", 1);
        NOT_FOLLOWING = new EnumRelationshipType("NOT_FOLLOWING", 2);
        BLOCKING = new EnumRelationshipType("BLOCKING", 3);
        $VALUES = (new EnumRelationshipType[] {
            ME, FOLLOWING, NOT_FOLLOWING, BLOCKING
        });
    }
}
