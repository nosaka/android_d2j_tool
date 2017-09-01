// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.relationship;

import twitter4j.Relationship;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.relationship:
//            EnumRelationshipType

public class RelationshipUtil
{

    public RelationshipUtil()
    {
    }

    public EnumRelationshipType getRelationshipType(Relationship relationship)
    {
        if(relationship.getSourceUserId() == relationship.getTargetUserId())
            return EnumRelationshipType.ME;
        if(relationship.isSourceBlockingTarget())
            return EnumRelationshipType.BLOCKING;
        if(relationship.isSourceFollowingTarget())
            return EnumRelationshipType.FOLLOWING;
        else
            return EnumRelationshipType.NOT_FOLLOWING;
    }
}
