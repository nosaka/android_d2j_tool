// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            ExtendedMediaEntity, HashtagEntity, MediaEntity, SymbolEntity, 
//            URLEntity, UserMentionEntity

public interface EntitySupport
{

    public abstract ExtendedMediaEntity[] getExtendedMediaEntities();

    public abstract HashtagEntity[] getHashtagEntities();

    public abstract MediaEntity[] getMediaEntities();

    public abstract SymbolEntity[] getSymbolEntities();

    public abstract URLEntity[] getURLEntities();

    public abstract UserMentionEntity[] getUserMentionEntities();
}
