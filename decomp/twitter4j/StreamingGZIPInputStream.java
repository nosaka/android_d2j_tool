// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

final class StreamingGZIPInputStream extends GZIPInputStream
{

    public StreamingGZIPInputStream(InputStream inputstream)
        throws IOException
    {
        super(inputstream);
        wrapped = inputstream;
    }

    public int available()
        throws IOException
    {
        return wrapped.available();
    }

    private final InputStream wrapped;
}
