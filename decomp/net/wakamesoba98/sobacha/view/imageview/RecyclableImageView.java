// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package net.wakamesoba98.sobacha.view.imageview:
//            OnTryToUseRecycledBitmapListener

public class RecyclableImageView extends ImageView
{

    public RecyclableImageView(Context context)
    {
        super(context);
    }

    public RecyclableImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public RecyclableImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public boolean isOpaque()
    {
        if(getDrawable() instanceof BitmapDrawable)
        {
            Object obj = (BitmapDrawable)getDrawable();
            if(obj != null)
            {
                obj = ((BitmapDrawable) (obj)).getBitmap();
                if(obj != null && !((Bitmap) (obj)).isRecycled())
                    return super.isOpaque();
                else
                    return false;
            }
        }
        return super.isOpaque();
    }

    protected void onDraw(Canvas canvas)
    {
        if(getDrawable() instanceof BitmapDrawable)
        {
            Object obj = (BitmapDrawable)getDrawable();
            if(obj != null)
            {
                obj = ((BitmapDrawable) (obj)).getBitmap();
                if(obj != null && !((Bitmap) (obj)).isRecycled())
                {
                    super.onDraw(canvas);
                } else
                {
                    setImageDrawable(null);
                    if(listener != null)
                    {
                        listener.onTryToUseRecycledBitmap(this);
                        return;
                    }
                }
            }
        }
    }

    public void setOnTryToUseRecycledBitmapListener(OnTryToUseRecycledBitmapListener ontrytouserecycledbitmaplistener)
    {
        listener = ontrytouserecycledbitmaplistener;
    }

    private OnTryToUseRecycledBitmapListener listener;
}
