// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.*;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.widget:
//            CardViewApi21, CardViewImpl, CardViewJellybeanMr1, CardViewGingerbread, 
//            CardViewDelegate

public class CardView extends FrameLayout
{

    public CardView(Context context)
    {
        super(context);
        mContentPadding = new Rect();
        mShadowBounds = new Rect();
        mCardViewDelegate = new CardViewDelegate() {

            public Drawable getCardBackground()
            {
                return mCardBackground;
            }

            public View getCardView()
            {
                return CardView.this;
            }

            public boolean getPreventCornerOverlap()
            {
                return CardView.this.getPreventCornerOverlap();
            }

            public boolean getUseCompatPadding()
            {
                return CardView.this.getUseCompatPadding();
            }

            public void setCardBackground(Drawable drawable)
            {
                mCardBackground = drawable;
                setBackgroundDrawable(drawable);
            }

            public void setMinWidthHeightInternal(int i, int j)
            {
                if(i > mUserSetMinWidth)
                    setMinimumWidth(i);
                if(j > mUserSetMinHeight)
                    setMinimumHeight(j);
            }

            public void setShadowPadding(int i, int j, int k, int l)
            {
                mShadowBounds.set(i, j, k, l);
                setPadding(mContentPadding.left + i, mContentPadding.top + j, mContentPadding.right + k, mContentPadding.bottom + l);
            }

            private Drawable mCardBackground;
            final CardView this$0;

            
            {
                this$0 = CardView.this;
                super();
            }
        }
;
        initialize(context, null, 0);
    }

    public CardView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContentPadding = new Rect();
        mShadowBounds = new Rect();
        mCardViewDelegate = new _cls1();
        initialize(context, attributeset, 0);
    }

    public CardView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContentPadding = new Rect();
        mShadowBounds = new Rect();
        mCardViewDelegate = new _cls1();
        initialize(context, attributeset, i);
    }

    private void initialize(Context context, AttributeSet attributeset, int i)
    {
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.cardview.R.styleable.CardView, i, android.support.v7.cardview.R.style.CardView);
        float f;
        float f1;
        float f2;
        float f3;
        if(typedarray.hasValue(android.support.v7.cardview.R.styleable.CardView_cardBackgroundColor))
        {
            attributeset = typedarray.getColorStateList(android.support.v7.cardview.R.styleable.CardView_cardBackgroundColor);
        } else
        {
            attributeset = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            i = attributeset.getColor(0, 0);
            attributeset.recycle();
            attributeset = new float[3];
            Color.colorToHSV(i, attributeset);
            if(attributeset[2] > 0.5F)
                i = getResources().getColor(android.support.v7.cardview.R.color.cardview_light_background);
            else
                i = getResources().getColor(android.support.v7.cardview.R.color.cardview_dark_background);
            attributeset = ColorStateList.valueOf(i);
        }
        f3 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardCornerRadius, 0.0F);
        f1 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardElevation, 0.0F);
        f2 = typedarray.getDimension(android.support.v7.cardview.R.styleable.CardView_cardMaxElevation, 0.0F);
        mCompatPadding = typedarray.getBoolean(android.support.v7.cardview.R.styleable.CardView_cardUseCompatPadding, false);
        mPreventCornerOverlap = typedarray.getBoolean(android.support.v7.cardview.R.styleable.CardView_cardPreventCornerOverlap, true);
        i = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPadding, 0);
        mContentPadding.left = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingLeft, i);
        mContentPadding.top = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingTop, i);
        mContentPadding.right = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingRight, i);
        mContentPadding.bottom = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_contentPaddingBottom, i);
        f = f2;
        if(f1 > f2)
            f = f1;
        mUserSetMinWidth = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_android_minWidth, 0);
        mUserSetMinHeight = typedarray.getDimensionPixelSize(android.support.v7.cardview.R.styleable.CardView_android_minHeight, 0);
        typedarray.recycle();
        IMPL.initialize(mCardViewDelegate, context, attributeset, f3, f1, f);
    }

    public ColorStateList getCardBackgroundColor()
    {
        return IMPL.getBackgroundColor(mCardViewDelegate);
    }

    public float getCardElevation()
    {
        return IMPL.getElevation(mCardViewDelegate);
    }

    public int getContentPaddingBottom()
    {
        return mContentPadding.bottom;
    }

    public int getContentPaddingLeft()
    {
        return mContentPadding.left;
    }

    public int getContentPaddingRight()
    {
        return mContentPadding.right;
    }

    public int getContentPaddingTop()
    {
        return mContentPadding.top;
    }

    public float getMaxCardElevation()
    {
        return IMPL.getMaxElevation(mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap()
    {
        return mPreventCornerOverlap;
    }

    public float getRadius()
    {
        return IMPL.getRadius(mCardViewDelegate);
    }

    public boolean getUseCompatPadding()
    {
        return mCompatPadding;
    }

    protected void onMeasure(int i, int j)
    {
        if(IMPL instanceof CardViewApi21) goto _L2; else goto _L1
_L1:
        int k = android.view.View.MeasureSpec.getMode(i);
        k;
        JVM INSTR lookupswitch 2: default 40
    //                   -2147483648: 79
    //                   1073741824: 79;
           goto _L3 _L4 _L4
_L3:
        k = android.view.View.MeasureSpec.getMode(j);
        k;
        JVM INSTR lookupswitch 2: default 72
    //                   -2147483648: 111
    //                   1073741824: 111;
           goto _L5 _L6 _L6
_L5:
        super.onMeasure(i, j);
        return;
_L4:
        i = android.view.View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinWidth(mCardViewDelegate)), android.view.View.MeasureSpec.getSize(i)), k);
        continue; /* Loop/switch isn't completed */
_L6:
        j = android.view.View.MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil(IMPL.getMinHeight(mCardViewDelegate)), android.view.View.MeasureSpec.getSize(j)), k);
        if(true) goto _L5; else goto _L2
_L2:
        super.onMeasure(i, j);
        return;
        if(true) goto _L3; else goto _L7
_L7:
    }

    public void setCardBackgroundColor(int i)
    {
        IMPL.setBackgroundColor(mCardViewDelegate, ColorStateList.valueOf(i));
    }

    public void setCardBackgroundColor(ColorStateList colorstatelist)
    {
        IMPL.setBackgroundColor(mCardViewDelegate, colorstatelist);
    }

    public void setCardElevation(float f)
    {
        IMPL.setElevation(mCardViewDelegate, f);
    }

    public void setContentPadding(int i, int j, int k, int l)
    {
        mContentPadding.set(i, j, k, l);
        IMPL.updatePadding(mCardViewDelegate);
    }

    public void setMaxCardElevation(float f)
    {
        IMPL.setMaxElevation(mCardViewDelegate, f);
    }

    public void setMinimumHeight(int i)
    {
        mUserSetMinHeight = i;
        super.setMinimumHeight(i);
    }

    public void setMinimumWidth(int i)
    {
        mUserSetMinWidth = i;
        super.setMinimumWidth(i);
    }

    public void setPadding(int i, int j, int k, int l)
    {
    }

    public void setPaddingRelative(int i, int j, int k, int l)
    {
    }

    public void setPreventCornerOverlap(boolean flag)
    {
        if(flag != mPreventCornerOverlap)
        {
            mPreventCornerOverlap = flag;
            IMPL.onPreventCornerOverlapChanged(mCardViewDelegate);
        }
    }

    public void setRadius(float f)
    {
        IMPL.setRadius(mCardViewDelegate, f);
    }

    public void setUseCompatPadding(boolean flag)
    {
        if(mCompatPadding != flag)
        {
            mCompatPadding = flag;
            IMPL.onCompatPaddingChanged(mCardViewDelegate);
        }
    }

    private static final int COLOR_BACKGROUND_ATTR[] = {
        0x1010031
    };
    private static final CardViewImpl IMPL;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            IMPL = new CardViewApi21();
        else
        if(android.os.Build.VERSION.SDK_INT >= 17)
            IMPL = new CardViewJellybeanMr1();
        else
            IMPL = new CardViewGingerbread();
        IMPL.initStatic();
    }



}
