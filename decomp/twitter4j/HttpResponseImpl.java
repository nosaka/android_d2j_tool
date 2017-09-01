// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

// Referenced classes of package twitter4j:
//            HttpResponse, StreamingGZIPInputStream, HttpClientConfiguration

public class HttpResponseImpl extends HttpResponse
{

    HttpResponseImpl(String s)
    {
        responseAsString = s;
    }

    HttpResponseImpl(HttpURLConnection httpurlconnection, HttpClientConfiguration httpclientconfiguration)
        throws IOException
    {
        super(httpclientconfiguration);
        con = httpurlconnection;
        try
        {
            statusCode = httpurlconnection.getResponseCode();
        }
        // Misplaced declaration of an exception variable
        catch(HttpClientConfiguration httpclientconfiguration)
        {
            if("Received authentication challenge is null".equals(httpclientconfiguration.getMessage()))
                statusCode = httpurlconnection.getResponseCode();
            else
                throw httpclientconfiguration;
        }
        httpclientconfiguration = httpurlconnection.getErrorStream();
        is = httpclientconfiguration;
        if(httpclientconfiguration == null)
            is = httpurlconnection.getInputStream();
        if(is != null && "gzip".equals(httpurlconnection.getContentEncoding()))
            is = new StreamingGZIPInputStream(is);
    }

    public void disconnect()
    {
        con.disconnect();
    }

    public String getResponseHeader(String s)
    {
        return con.getHeaderField(s);
    }

    public Map getResponseHeaderFields()
    {
        return con.getHeaderFields();
    }

    private HttpURLConnection con;
}
