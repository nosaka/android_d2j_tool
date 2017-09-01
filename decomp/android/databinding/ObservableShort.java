// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

// Referenced classes of package android.databinding:
//            BaseObservable

public class ObservableShort extends BaseObservable
    implements Parcelable, Serializable
{

    public ObservableShort()
    {
    }

    public ObservableShort(short word0)
    {
        mValue = word0;
    }

    public int describeContents()
    {
        return 0;
    }

    public short get()
    {
        return mValue;
    }

    public void set(short word0)
    {
        if(word0 != mValue)
        {
            mValue = word0;
            notifyChange();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ObservableShort createFromParcel(Parcel parcel)
        {
            return new ObservableShort((short)parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ObservableShort[] newArray(int i)
        {
            return new ObservableShort[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final long serialVersionUID = 1L;
    private short mValue;

}
