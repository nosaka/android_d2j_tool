// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.textview;

import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.URLSpan;

class ColoredURLSpan extends URLSpan
{

    private ColoredURLSpan(Parcel parcel)
    {
        super(parcel);
        color = parcel.readInt();
    }


    ColoredURLSpan(String s, int i)
    {
        super(s);
        color = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        if(color != -1)
            textpaint.setColor(color);
        else
            textpaint.setColor(textpaint.linkColor);
        textpaint.setUnderlineText(true);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(color);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ColoredURLSpan createFromParcel(Parcel parcel)
        {
            return new ColoredURLSpan(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ColoredURLSpan[] newArray(int i)
        {
            return new ColoredURLSpan[i];
        }

    }
;
    private int color;

}
