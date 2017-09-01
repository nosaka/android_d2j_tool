// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;


// Referenced classes of package android.databinding:
//            Observable, PropertyChangeRegistry

public class BaseObservable
    implements Observable
{

    public BaseObservable()
    {
    }

    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onpropertychangedcallback)
    {
        this;
        JVM INSTR monitorenter ;
        if(mCallbacks == null)
            mCallbacks = new PropertyChangeRegistry();
        this;
        JVM INSTR monitorexit ;
        mCallbacks.add(onpropertychangedcallback);
        return;
        onpropertychangedcallback;
        this;
        JVM INSTR monitorexit ;
        throw onpropertychangedcallback;
    }

    public void notifyChange()
    {
        this;
        JVM INSTR monitorenter ;
        if(mCallbacks != null)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mCallbacks.notifyCallbacks(this, 0, null);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void notifyPropertyChanged(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if(mCallbacks != null)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mCallbacks.notifyCallbacks(this, i, null);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback onpropertychangedcallback)
    {
        this;
        JVM INSTR monitorenter ;
        if(mCallbacks != null)
            break MISSING_BLOCK_LABEL_12;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mCallbacks.remove(onpropertychangedcallback);
        return;
        onpropertychangedcallback;
        this;
        JVM INSTR monitorexit ;
        throw onpropertychangedcallback;
    }

    private transient PropertyChangeRegistry mCallbacks;
}
