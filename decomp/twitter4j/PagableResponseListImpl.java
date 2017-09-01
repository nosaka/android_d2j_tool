// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


// Referenced classes of package twitter4j:
//            ResponseListImpl, PagableResponseList, ParseUtil, JSONObject, 
//            HttpResponse, RateLimitStatus

class PagableResponseListImpl extends ResponseListImpl
    implements PagableResponseList
{

    PagableResponseListImpl(int i, JSONObject jsonobject, HttpResponse httpresponse)
    {
        super(i, httpresponse);
        previousCursor = ParseUtil.getLong("previous_cursor", jsonobject);
        nextCursor = ParseUtil.getLong("next_cursor", jsonobject);
    }

    PagableResponseListImpl(RateLimitStatus ratelimitstatus, int i)
    {
        super(ratelimitstatus, i);
        previousCursor = 0L;
        nextCursor = 0L;
    }

    public long getNextCursor()
    {
        return nextCursor;
    }

    public long getPreviousCursor()
    {
        return previousCursor;
    }

    public boolean hasNext()
    {
        return 0L != nextCursor;
    }

    public boolean hasPrevious()
    {
        return 0L != previousCursor;
    }

    private static final long serialVersionUID = 0x72c3cd95L;
    private final long nextCursor;
    private final long previousCursor;
}
