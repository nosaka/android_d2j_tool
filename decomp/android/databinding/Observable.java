// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;


public interface Observable
{
    public static abstract class OnPropertyChangedCallback
    {

        public abstract void onPropertyChanged(Observable observable, int i);

        public OnPropertyChangedCallback()
        {
        }
    }


    public abstract void addOnPropertyChangedCallback(OnPropertyChangedCallback onpropertychangedcallback);

    public abstract void removeOnPropertyChangedCallback(OnPropertyChangedCallback onpropertychangedcallback);
}
