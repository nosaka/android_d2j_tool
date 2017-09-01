// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.value;


public final class EnumCameraButton extends Enum
{

    private EnumCameraButton(String s, int i)
    {
        super(s, i);
    }

    public static EnumCameraButton valueOf(String s)
    {
        return (EnumCameraButton)Enum.valueOf(net/wakamesoba98/sobacha/preference/value/EnumCameraButton, s);
    }

    public static EnumCameraButton[] values()
    {
        return (EnumCameraButton[])$VALUES.clone();
    }

    private static final EnumCameraButton $VALUES[];
    public static final EnumCameraButton CAMERA_BUTTON;
    public static final EnumCameraButton NONE;

    static 
    {
        NONE = new EnumCameraButton("NONE", 0);
        CAMERA_BUTTON = new EnumCameraButton("CAMERA_BUTTON", 1);
        $VALUES = (new EnumCameraButton[] {
            NONE, CAMERA_BUTTON
        });
    }
}
