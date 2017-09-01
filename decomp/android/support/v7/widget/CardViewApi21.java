// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            CardViewImpl, CardViewDelegate, RoundRectDrawable, RoundRectDrawableWithShadow

class CardViewApi21
    implements CardViewImpl
{

    CardViewApi21()
    {
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate cardviewdelegate)
    {
        return (RoundRectDrawable)cardviewdelegate.getCardBackground();
    }

    public ColorStateList getBackgroundColor(CardViewDelegate cardviewdelegate)
    {
        return getCardBackground(cardviewdelegate).getColor();
    }

    public float getElevation(CardViewDelegate cardviewdelegate)
    {
        return cardviewdelegate.getCardView().getElevation();
    }

    public float getMaxElevation(CardViewDelegate cardviewdelegate)
    {
        return getCardBackground(cardviewdelegate).getPadding();
    }

    public float getMinHeight(CardViewDelegate cardviewdelegate)
    {
        return getRadius(cardviewdelegate) * 2.0F;
    }

    public float getMinWidth(CardViewDelegate cardviewdelegate)
    {
        return getRadius(cardviewdelegate) * 2.0F;
    }

    public float getRadius(CardViewDelegate cardviewdelegate)
    {
        return getCardBackground(cardviewdelegate).getRadius();
    }

    public void initStatic()
    {
    }

    public void initialize(CardViewDelegate cardviewdelegate, Context context, ColorStateList colorstatelist, float f, float f1, float f2)
    {
        cardviewdelegate.setCardBackground(new RoundRectDrawable(colorstatelist, f));
        context = cardviewdelegate.getCardView();
        context.setClipToOutline(true);
        context.setElevation(f1);
        setMaxElevation(cardviewdelegate, f2);
    }

    public void onCompatPaddingChanged(CardViewDelegate cardviewdelegate)
    {
        setMaxElevation(cardviewdelegate, getMaxElevation(cardviewdelegate));
    }

    public void onPreventCornerOverlapChanged(CardViewDelegate cardviewdelegate)
    {
        setMaxElevation(cardviewdelegate, getMaxElevation(cardviewdelegate));
    }

    public void setBackgroundColor(CardViewDelegate cardviewdelegate, ColorStateList colorstatelist)
    {
        getCardBackground(cardviewdelegate).setColor(colorstatelist);
    }

    public void setElevation(CardViewDelegate cardviewdelegate, float f)
    {
        cardviewdelegate.getCardView().setElevation(f);
    }

    public void setMaxElevation(CardViewDelegate cardviewdelegate, float f)
    {
        getCardBackground(cardviewdelegate).setPadding(f, cardviewdelegate.getUseCompatPadding(), cardviewdelegate.getPreventCornerOverlap());
        updatePadding(cardviewdelegate);
    }

    public void setRadius(CardViewDelegate cardviewdelegate, float f)
    {
        getCardBackground(cardviewdelegate).setRadius(f);
    }

    public void updatePadding(CardViewDelegate cardviewdelegate)
    {
        if(!cardviewdelegate.getUseCompatPadding())
        {
            cardviewdelegate.setShadowPadding(0, 0, 0, 0);
            return;
        } else
        {
            float f = getMaxElevation(cardviewdelegate);
            float f1 = getRadius(cardviewdelegate);
            int i = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            int j = (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(f, f1, cardviewdelegate.getPreventCornerOverlap()));
            cardviewdelegate.setShadowPadding(i, j, i, j);
            return;
        }
    }
}
