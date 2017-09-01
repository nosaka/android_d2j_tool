// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import net.wakamesoba98.sobacha.compatible.SystemVersion;

public interface ImageThresholdConstants
{

    public static final int ICON_THRESHOLD = 320;
    public static final int IMAGE_THRESHOLD = i;
    public static final int UPLOAD_IMAGE_FILE_SIZE_LIMIT = 0x300000;
    public static final int UPLOAD_IMAGE_MAX_SIZE = 1600;
    public static final int UPLOAD_IMAGE_QUALITY = 90;

    
    {
        int i;
        if(SystemVersion.isHoneycombOrLater())
            i = 1600;
        else
            i = 800;
    }
}
