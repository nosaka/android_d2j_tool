// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.util;

import android.content.Context;
import net.wakamesoba98.sobacha.consumer.Consumer;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;

public class TwitterUtils
{

    public TwitterUtils()
    {
    }

    private Configuration buildConfiguration(Context context, AccessToken accesstoken)
    {
        ConfigurationBuilder configurationbuilder = new ConfigurationBuilder();
        String s = PreferenceUtil.getStringPreference(context, EnumPrefs.UPLOAD_PROVIDER);
        configurationbuilder.setOAuthConsumerKey(Consumer.getConsumer(context)).setOAuthConsumerSecret(Consumer.getSecret(context)).setOAuthAccessToken(accesstoken.getToken()).setOAuthAccessTokenSecret(accesstoken.getTokenSecret()).setMediaProvider(s).setMediaProviderAPIKey(Consumer.getConsumer(context)).setJSONStoreEnabled(true).setTweetModeExtended(true);
        return configurationbuilder.build();
    }

    public AsyncTwitter getAsyncTwitterInstance(Context context, AccessToken accesstoken)
    {
        return (new AsyncTwitterFactory(buildConfiguration(context, accesstoken))).getInstance();
    }

    public ImageUpload getImageUploadInstance(Context context, AccessToken accesstoken)
    {
        return (new ImageUploadFactory(buildConfiguration(context, accesstoken))).getInstance();
    }

    public Twitter getTwitterInstance(Context context, AccessToken accesstoken)
    {
        return (new TwitterFactory(buildConfiguration(context, accesstoken))).getInstance();
    }

    public TwitterStream getTwitterStreamInstance(Context context, AccessToken accesstoken)
    {
        return (new TwitterStreamFactory(buildConfiguration(context, accesstoken))).getInstance();
    }
}
