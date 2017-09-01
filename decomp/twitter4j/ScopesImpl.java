// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            Scopes

public class ScopesImpl
    implements Scopes
{

    ScopesImpl()
    {
        placeIds = new String[0];
    }

    public ScopesImpl(String as[])
    {
        placeIds = as;
    }

    public String[] getPlaceIds()
    {
        return placeIds;
    }

    private static final long serialVersionUID = 0x1358b05dL;
    private final String placeIds[];
}
