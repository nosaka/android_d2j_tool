// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

// Referenced classes of package android.databinding:
//            BaseObservable

public class ObservableLong extends BaseObservable
    implements Parcelable, Serializable
{

    public ObservableLong()
    {
    }

    public ObservableLong(long l)
    {
        mValue = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public long get()
    {
        return mValue;
    }

    public void set(long l)
    {
        if(l != mValue)
        {
            mValue = l;
            notifyChange();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ObservableLong createFromParcel(Parcel parcel)
        {
            return new ObservableLong(parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ObservableLong[] newArray(int i)
        {
            return new ObservableLong[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final long serialVersionUID = 1L;
    private long mValue;

}
