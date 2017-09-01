// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.media;

import twitter4j.auth.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

// Referenced classes of package twitter4j.media:
//            MediaProvider, TwitterUpload, ImgLyUpload, TwippleUpload, 
//            MobypictureUpload, ImageUpload

public class ImageUploadFactory
{

    public ImageUploadFactory()
    {
        this(ConfigurationContext.getInstance());
    }

    public ImageUploadFactory(Configuration configuration)
    {
        String s = configuration.getMediaProvider().toLowerCase();
        if("twitter".equals(s))
            defaultMediaProvider = MediaProvider.TWITTER;
        else
        if("imgly".equals(s) || "img_ly".equals(s))
            defaultMediaProvider = MediaProvider.IMG_LY;
        else
        if("twipple".equals(s))
            defaultMediaProvider = MediaProvider.TWIPPLE;
        else
        if("mobypicture".equals(s))
            defaultMediaProvider = MediaProvider.MOBYPICTURE;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unsupported media provider:").append(s).toString());
        conf = configuration;
        apiKey = configuration.getMediaProviderAPIKey();
    }

    public ImageUpload getInstance()
    {
        return getInstance(defaultMediaProvider);
    }

    public ImageUpload getInstance(Authorization authorization)
    {
        return getInstance(defaultMediaProvider, authorization);
    }

    public ImageUpload getInstance(MediaProvider mediaprovider)
    {
        return getInstance(mediaprovider, AuthorizationFactory.getInstance(conf));
    }

    public ImageUpload getInstance(MediaProvider mediaprovider, Authorization authorization)
    {
        if(!(authorization instanceof OAuthAuthorization))
            throw new IllegalArgumentException("OAuth authorization is required.");
        authorization = (OAuthAuthorization)authorization;
        if(mediaprovider == MediaProvider.TWITTER)
            return new TwitterUpload(conf, authorization);
        if(mediaprovider == MediaProvider.IMG_LY)
            return new ImgLyUpload(conf, authorization);
        if(mediaprovider == MediaProvider.TWIPPLE)
            return new TwippleUpload(conf, authorization);
        if(mediaprovider == MediaProvider.MOBYPICTURE)
            return new MobypictureUpload(conf, apiKey, authorization);
        else
            throw new AssertionError("Unknown provider");
    }

    private final String apiKey;
    private final Configuration conf;
    private final MediaProvider defaultMediaProvider;
}
