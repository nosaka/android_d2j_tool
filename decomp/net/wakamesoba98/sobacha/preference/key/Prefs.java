// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.preference.key;


// Referenced classes of package net.wakamesoba98.sobacha.preference.key:
//            EnumValueType

public interface Prefs
{

    public abstract Object getDefaultValue();

    public abstract String getKey();

    public abstract EnumValueType getType();
}
