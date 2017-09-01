// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.*;

// Referenced classes of package android.support.v7.widget:
//            CardViewImpl, RoundRectDrawableWithShadow, CardViewDelegate

class CardViewGingerbread
    implements CardViewImpl
{

    CardViewGingerbread()
    {
    }

    private RoundRectDrawableWithShadow createBackground(Context context, ColorStateList colorstatelist, float f, float f1, float f2)
    {
        return new RoundRectDrawableWithShadow(context.getResources(), colorstatelist, f, f1, f2);
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardviewdelegate)
    {
        return (RoundRectDrawableWithShadow)cardviewdelegate.getCardBackground();
    }

    public ColorStateList getBackgroundColor(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getColor();
    }

    public float getElevation(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getShadowSize();
    }

    public float getMaxElevation(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getMaxShadowSize();
    }

    public float getMinHeight(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getMinHeight();
    }

    public float getMinWidth(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getMinWidth();
    }

    public float getRadius(CardViewDelegate cardviewdelegate)
    {
        return getShadowBackground(cardviewdelegate).getCornerRadius();
    }

    public void initStatic()
    {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {

            public void drawRoundRect(Canvas canvas, RectF rectf, float f, Paint paint)
            {
                float f1 = f * 2.0F;
                float f2 = rectf.width() - f1 - 1.0F;
                float f3 = rectf.height();
                if(f >= 1.0F)
                {
                    float f4 = f + 0.5F;
                    sCornerRect.set(-f4, -f4, f4, f4);
                    int i = canvas.save();
                    canvas.translate(rectf.left + f4, rectf.top + f4);
                    canvas.drawArc(sCornerRect, 180F, 90F, true, paint);
                    canvas.translate(f2, 0.0F);
                    canvas.rotate(90F);
                    canvas.drawArc(sCornerRect, 180F, 90F, true, paint);
                    canvas.translate(f3 - f1 - 1.0F, 0.0F);
                    canvas.rotate(90F);
                    canvas.drawArc(sCornerRect, 180F, 90F, true, paint);
                    canvas.translate(f2, 0.0F);
                    canvas.rotate(90F);
                    canvas.drawArc(sCornerRect, 180F, 90F, true, paint);
                    canvas.restoreToCount(i);
                    canvas.drawRect((rectf.left + f4) - 1.0F, rectf.top, 1.0F + (rectf.right - f4), rectf.top + f4, paint);
                    canvas.drawRect((rectf.left + f4) - 1.0F, rectf.bottom - f4, 1.0F + (rectf.right - f4), rectf.bottom, paint);
                }
                canvas.drawRect(rectf.left, rectf.top + f, rectf.right, rectf.bottom - f, paint);
            }

            final CardViewGingerbread this$0;

            
            {
                this$0 = CardViewGingerbread.this;
                super();
            }
        }
;
    }

    public void initialize(CardViewDelegate cardviewdelegate, Context context, ColorStateList colorstatelist, float f, float f1, float f2)
    {
        context = createBackground(context, colorstatelist, f, f1, f2);
        context.setAddPaddingForCorners(cardviewdelegate.getPreventCornerOverlap());
        cardviewdelegate.setCardBackground(context);
        updatePadding(cardviewdelegate);
    }

    public void onCompatPaddingChanged(CardViewDelegate cardviewdelegate)
    {
    }

    public void onPreventCornerOverlapChanged(CardViewDelegate cardviewdelegate)
    {
        getShadowBackground(cardviewdelegate).setAddPaddingForCorners(cardviewdelegate.getPreventCornerOverlap());
        updatePadding(cardviewdelegate);
    }

    public void setBackgroundColor(CardViewDelegate cardviewdelegate, ColorStateList colorstatelist)
    {
        getShadowBackground(cardviewdelegate).setColor(colorstatelist);
    }

    public void setElevation(CardViewDelegate cardviewdelegate, float f)
    {
        getShadowBackground(cardviewdelegate).setShadowSize(f);
    }

    public void setMaxElevation(CardViewDelegate cardviewdelegate, float f)
    {
        getShadowBackground(cardviewdelegate).setMaxShadowSize(f);
        updatePadding(cardviewdelegate);
    }

    public void setRadius(CardViewDelegate cardviewdelegate, float f)
    {
        getShadowBackground(cardviewdelegate).setCornerRadius(f);
        updatePadding(cardviewdelegate);
    }

    public void updatePadding(CardViewDelegate cardviewdelegate)
    {
        Rect rect = new Rect();
        getShadowBackground(cardviewdelegate).getMaxShadowAndCornerPadding(rect);
        cardviewdelegate.setMinWidthHeightInternal((int)Math.ceil(getMinWidth(cardviewdelegate)), (int)Math.ceil(getMinHeight(cardviewdelegate)));
        cardviewdelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    final RectF sCornerRect = new RectF();
}
