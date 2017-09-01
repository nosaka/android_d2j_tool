// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.Map;

// Referenced classes of package twitter4j:
//            TwitterResponse

public interface TwitterAPIConfiguration
    extends TwitterResponse, Serializable
{

    public abstract int getCharactersReservedPerMedia();

    public abstract int getDmTextCharacterLimit();

    public abstract int getMaxMediaPerUpload();

    public abstract String[] getNonUsernamePaths();

    public abstract int getPhotoSizeLimit();

    public abstract Map getPhotoSizes();

    public abstract int getShortURLLength();

    public abstract int getShortURLLengthHttps();
}
