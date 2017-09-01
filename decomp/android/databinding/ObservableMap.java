// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import java.util.Map;

public interface ObservableMap
    extends Map
{
    public static abstract class OnMapChangedCallback
    {

        public abstract void onMapChanged(ObservableMap observablemap, Object obj);

        public OnMapChangedCallback()
        {
        }
    }


    public abstract void addOnMapChangedCallback(OnMapChangedCallback onmapchangedcallback);

    public abstract void removeOnMapChangedCallback(OnMapChangedCallback onmapchangedcallback);
}
