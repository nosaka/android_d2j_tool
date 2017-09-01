// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import java.io.File;
import java.io.InputStream;
import twitter4j.TwitterException;

public interface ImageUpload
{

    public abstract String upload(File file)
        throws TwitterException;

    public abstract String upload(File file, String s)
        throws TwitterException;

    public abstract String upload(String s, InputStream inputstream)
        throws TwitterException;

    public abstract String upload(String s, InputStream inputstream, String s1)
        throws TwitterException;
}
