// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import java.util.Map;
import twitter4j.*;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j.media:
//            AbstractImageUploadImpl

class ImgLyUpload extends AbstractImageUploadImpl
{

    public ImgLyUpload(Configuration configuration, OAuthAuthorization oauthauthorization)
    {
        super(configuration, oauthauthorization);
    }

    protected String postUpload()
        throws TwitterException
    {
label0:
        {
            if(httpResponse.getStatusCode() != 200)
                throw new TwitterException("ImgLy image upload returned invalid status code", httpResponse);
            String s = httpResponse.asString();
            Object obj;
            try
            {
                obj = new JSONObject(s);
                if(((JSONObject) (obj)).isNull("url"))
                    break label0;
                obj = ((JSONObject) (obj)).getString("url");
            }
            catch(JSONException jsonexception)
            {
                throw new TwitterException((new StringBuilder()).append("Invalid ImgLy response: ").append(s).toString(), jsonexception);
            }
            return ((String) (obj));
        }
        throw new TwitterException("Unknown ImgLy response", httpResponse);
    }

    protected void preUpload()
        throws TwitterException
    {
        uploadUrl = "http://img.ly/api/2/upload.json";
        String s = generateVerifyCredentialsAuthorizationHeader();
        headers.put("X-Auth-Service-Provider", "https://api.twitter.com/1.1/account/verify_credentials.json");
        headers.put("X-Verify-Credentials-Authorization", s);
        HttpParameter ahttpparameter1[] = new HttpParameter[1];
        ahttpparameter1[0] = image;
        HttpParameter ahttpparameter[] = ahttpparameter1;
        if(message != null)
            ahttpparameter = appendHttpParameters(new HttpParameter[] {
                message
            }, ahttpparameter1);
        postParameter = ahttpparameter;
    }
}
