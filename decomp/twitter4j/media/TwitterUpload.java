// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import java.io.File;
import java.io.InputStream;
import twitter4j.*;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;

// Referenced classes of package twitter4j.media:
//            ImageUpload

class TwitterUpload
    implements ImageUpload
{

    public TwitterUpload(Configuration configuration, OAuthAuthorization oauthauthorization)
    {
        twitter = (new TwitterFactory(configuration)).getInstance(oauthauthorization);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (TwitterUpload)obj;
            if(twitter == null ? ((TwitterUpload) (obj)).twitter != null : !twitter.equals(((TwitterUpload) (obj)).twitter))
                return false;
        }
        return true;
    }

    public int hashCode()
    {
        if(twitter != null)
            return twitter.hashCode();
        else
            return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("TwitterUpload{twitter=").append(twitter).append('}').toString();
    }

    public String upload(File file)
        throws TwitterException
    {
        return twitter.updateStatus((new StatusUpdate("")).media(file)).getText();
    }

    public String upload(File file, String s)
        throws TwitterException
    {
        return twitter.updateStatus((new StatusUpdate(s)).media(file)).getText();
    }

    public String upload(String s, InputStream inputstream)
        throws TwitterException
    {
        return twitter.updateStatus((new StatusUpdate("")).media(s, inputstream)).getText();
    }

    public String upload(String s, InputStream inputstream, String s1)
        throws TwitterException
    {
        return twitter.updateStatus((new StatusUpdate(s1)).media(s, inputstream)).getText();
    }

    private final Twitter twitter;
}
