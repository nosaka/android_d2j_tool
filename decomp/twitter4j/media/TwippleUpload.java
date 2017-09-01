// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import twitter4j.*;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j.media:
//            AbstractImageUploadImpl

class TwippleUpload extends AbstractImageUploadImpl
{

    public TwippleUpload(Configuration configuration, OAuthAuthorization oauthauthorization)
    {
        super(configuration, oauthauthorization);
    }

    protected String postUpload()
        throws TwitterException
    {
        if(httpResponse.getStatusCode() != 200)
            throw new TwitterException("Twipple image upload returned invalid status code", httpResponse);
        String s = httpResponse.asString();
        if(s.contains("<rsp stat=\"fail\">"))
        {
            s = s.substring(s.indexOf("msg") + 5, s.lastIndexOf("\""));
            throw new TwitterException((new StringBuilder()).append("Twipple image upload failed with this error message: ").append(s).toString(), httpResponse);
        }
        if(s.contains("<rsp stat=\"ok\">"))
            return s.substring(s.indexOf("<mediaurl>") + "<mediaurl>".length(), s.indexOf("</mediaurl>"));
        else
            throw new TwitterException("Unknown Twipple response", httpResponse);
    }

    protected void preUpload()
        throws TwitterException
    {
        uploadUrl = "http://p.twipple.jp/api/upload";
        postParameter = (new HttpParameter[] {
            new HttpParameter("verify_url", generateVerifyCredentialsAuthorizationURL("https://api.twitter.com/1.1/account/verify_credentials.json")), image
        });
    }
}
