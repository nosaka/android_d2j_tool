// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.*;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.widget:
//            RoundRectDrawableWithShadow

class RoundRectDrawable extends Drawable
{

    public RoundRectDrawable(ColorStateList colorstatelist, float f)
    {
        mInsetForPadding = false;
        mInsetForRadius = true;
        mTintMode = android.graphics.PorterDuff.Mode.SRC_IN;
        mRadius = f;
        setBackground(colorstatelist);
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode)
    {
        if(colorstatelist == null || mode == null)
            return null;
        else
            return new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), mode);
    }

    private void setBackground(ColorStateList colorstatelist)
    {
        ColorStateList colorstatelist1 = colorstatelist;
        if(colorstatelist == null)
            colorstatelist1 = ColorStateList.valueOf(0);
        mBackground = colorstatelist1;
        mPaint.setColor(mBackground.getColorForState(getState(), mBackground.getDefaultColor()));
    }

    private void updateBounds(Rect rect)
    {
        Rect rect1 = rect;
        if(rect == null)
            rect1 = getBounds();
        mBoundsF.set(rect1.left, rect1.top, rect1.right, rect1.bottom);
        mBoundsI.set(rect1);
        if(mInsetForPadding)
        {
            float f = RoundRectDrawableWithShadow.calculateVerticalPadding(mPadding, mRadius, mInsetForRadius);
            float f1 = RoundRectDrawableWithShadow.calculateHorizontalPadding(mPadding, mRadius, mInsetForRadius);
            mBoundsI.inset((int)Math.ceil(f1), (int)Math.ceil(f));
            mBoundsF.set(mBoundsI);
        }
    }

    public void draw(Canvas canvas)
    {
        Paint paint = mPaint;
        boolean flag;
        if(mTintFilter != null && paint.getColorFilter() == null)
        {
            paint.setColorFilter(mTintFilter);
            flag = true;
        } else
        {
            flag = false;
        }
        canvas.drawRoundRect(mBoundsF, mRadius, mRadius, paint);
        if(flag)
            paint.setColorFilter(null);
    }

    public ColorStateList getColor()
    {
        return mBackground;
    }

    public int getOpacity()
    {
        return -3;
    }

    public void getOutline(Outline outline)
    {
        outline.setRoundRect(mBoundsI, mRadius);
    }

    float getPadding()
    {
        return mPadding;
    }

    public float getRadius()
    {
        return mRadius;
    }

    public boolean isStateful()
    {
        return mTint != null && mTint.isStateful() || mBackground != null && mBackground.isStateful() || super.isStateful();
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        updateBounds(rect);
    }

    protected boolean onStateChange(int ai[])
    {
        int i = mBackground.getColorForState(ai, mBackground.getDefaultColor());
        boolean flag;
        boolean flag1;
        if(i != mPaint.getColor())
            flag = true;
        else
            flag = false;
        if(flag)
            mPaint.setColor(i);
        flag1 = flag;
        if(mTint != null)
        {
            flag1 = flag;
            if(mTintMode != null)
            {
                mTintFilter = createTintFilter(mTint, mTintMode);
                flag1 = true;
            }
        }
        return flag1;
    }

    public void setAlpha(int i)
    {
        mPaint.setAlpha(i);
    }

    public void setColor(ColorStateList colorstatelist)
    {
        setBackground(colorstatelist);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
    }

    void setPadding(float f, boolean flag, boolean flag1)
    {
        if(f == mPadding && mInsetForPadding == flag && mInsetForRadius == flag1)
        {
            return;
        } else
        {
            mPadding = f;
            mInsetForPadding = flag;
            mInsetForRadius = flag1;
            updateBounds(null);
            invalidateSelf();
            return;
        }
    }

    void setRadius(float f)
    {
        if(f == mRadius)
        {
            return;
        } else
        {
            mRadius = f;
            updateBounds(null);
            invalidateSelf();
            return;
        }
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mTint = colorstatelist;
        mTintFilter = createTintFilter(mTint, mTintMode);
        invalidateSelf();
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTintMode = mode;
        mTintFilter = createTintFilter(mTint, mTintMode);
        invalidateSelf();
    }

    private ColorStateList mBackground;
    private final RectF mBoundsF = new RectF();
    private final Rect mBoundsI = new Rect();
    private boolean mInsetForPadding;
    private boolean mInsetForRadius;
    private float mPadding;
    private final Paint mPaint = new Paint(5);
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private android.graphics.PorterDuff.Mode mTintMode;
}
