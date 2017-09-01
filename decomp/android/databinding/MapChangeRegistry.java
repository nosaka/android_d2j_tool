// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;


// Referenced classes of package android.databinding:
//            CallbackRegistry, ObservableMap

public class MapChangeRegistry extends CallbackRegistry
{

    public MapChangeRegistry()
    {
        super(NOTIFIER_CALLBACK);
    }

    public void notifyChange(ObservableMap observablemap, Object obj)
    {
        notifyCallbacks(observablemap, 0, obj);
    }

    private static CallbackRegistry.NotifierCallback NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback() {

        public void onNotifyCallback(ObservableMap.OnMapChangedCallback onmapchangedcallback, ObservableMap observablemap, int i, Object obj)
        {
            onmapchangedcallback.onMapChanged(observablemap, obj);
        }

        public volatile void onNotifyCallback(Object obj, Object obj1, int i, Object obj2)
        {
            onNotifyCallback((ObservableMap.OnMapChangedCallback)obj, (ObservableMap)obj1, i, obj2);
        }

    }
;

}
