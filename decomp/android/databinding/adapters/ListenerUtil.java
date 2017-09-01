// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil
{

    public ListenerUtil()
    {
    }

    public static Object getListener(View view, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return view.getTag(i);
        SparseArray sparsearray = sListeners;
        sparsearray;
        JVM INSTR monitorenter ;
        WeakHashMap weakhashmap = (WeakHashMap)sListeners.get(i);
        if(weakhashmap != null)
            break MISSING_BLOCK_LABEL_44;
        sparsearray;
        JVM INSTR monitorexit ;
        return null;
        view;
        sparsearray;
        JVM INSTR monitorexit ;
        throw view;
        view = (WeakReference)weakhashmap.get(view);
        if(view != null)
            break MISSING_BLOCK_LABEL_61;
        sparsearray;
        JVM INSTR monitorexit ;
        return null;
        view = ((View) (view.get()));
        sparsearray;
        JVM INSTR monitorexit ;
        return view;
    }

    public static Object trackListener(View view, Object obj, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
        {
            Object obj1 = view.getTag(i);
            view.setTag(i, obj);
            return obj1;
        }
        SparseArray sparsearray = sListeners;
        sparsearray;
        JVM INSTR monitorenter ;
        WeakHashMap weakhashmap1 = (WeakHashMap)sListeners.get(i);
        WeakHashMap weakhashmap;
        weakhashmap = weakhashmap1;
        if(weakhashmap1 != null)
            break MISSING_BLOCK_LABEL_66;
        weakhashmap = new WeakHashMap();
        sListeners.put(i, weakhashmap);
        if(obj != null)
            break MISSING_BLOCK_LABEL_94;
        view = (WeakReference)weakhashmap.remove(view);
_L1:
        if(view != null)
            break MISSING_BLOCK_LABEL_114;
        sparsearray;
        JVM INSTR monitorexit ;
        return null;
        view;
        sparsearray;
        JVM INSTR monitorexit ;
        throw view;
        view = (WeakReference)weakhashmap.put(view, new WeakReference(obj));
          goto _L1
        view = ((View) (view.get()));
        sparsearray;
        JVM INSTR monitorexit ;
        return view;
    }

    private static SparseArray sListeners = new SparseArray();

}
