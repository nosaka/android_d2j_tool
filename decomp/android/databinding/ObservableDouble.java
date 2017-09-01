// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

// Referenced classes of package android.databinding:
//            BaseObservable

public class ObservableDouble extends BaseObservable
    implements Parcelable, Serializable
{

    public ObservableDouble()
    {
    }

    public ObservableDouble(double d)
    {
        mValue = d;
    }

    public int describeContents()
    {
        return 0;
    }

    public double get()
    {
        return mValue;
    }

    public void set(double d)
    {
        if(d != mValue)
        {
            mValue = d;
            notifyChange();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeDouble(mValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ObservableDouble createFromParcel(Parcel parcel)
        {
            return new ObservableDouble(parcel.readDouble());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ObservableDouble[] newArray(int i)
        {
            return new ObservableDouble[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final long serialVersionUID = 1L;
    private double mValue;

}
