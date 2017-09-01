// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import java.io.Serializable;

// Referenced classes of package android.databinding:
//            BaseObservable

public class ObservableField extends BaseObservable
    implements Serializable
{

    public ObservableField()
    {
    }

    public ObservableField(Object obj)
    {
        mValue = obj;
    }

    public Object get()
    {
        return mValue;
    }

    public void set(Object obj)
    {
        if(obj != mValue)
        {
            mValue = obj;
            notifyChange();
        }
    }

    static final long serialVersionUID = 1L;
    private Object mValue;
}
