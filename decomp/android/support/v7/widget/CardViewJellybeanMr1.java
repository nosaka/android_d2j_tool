// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.*;

// Referenced classes of package android.support.v7.widget:
//            CardViewGingerbread, RoundRectDrawableWithShadow

class CardViewJellybeanMr1 extends CardViewGingerbread
{

    CardViewJellybeanMr1()
    {
    }

    public void initStatic()
    {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {

            public void drawRoundRect(Canvas canvas, RectF rectf, float f, Paint paint)
            {
                canvas.drawRoundRect(rectf, f, f, paint);
            }

            final CardViewJellybeanMr1 this$0;

            
            {
                this$0 = CardViewJellybeanMr1.this;
                super();
            }
        }
;
    }
}
