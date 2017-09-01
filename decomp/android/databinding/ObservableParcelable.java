// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

// Referenced classes of package android.databinding:
//            ObservableField

public class ObservableParcelable extends ObservableField
    implements Parcelable, Serializable
{

    public ObservableParcelable()
    {
    }

    public ObservableParcelable(Parcelable parcelable)
    {
        super(parcelable);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable((Parcelable)get(), 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ObservableParcelable createFromParcel(Parcel parcel)
        {
            return new ObservableParcelable(parcel.readParcelable(getClass().getClassLoader()));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ObservableParcelable[] newArray(int i)
        {
            return new ObservableParcelable[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final long serialVersionUID = 1L;

}
