// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;

// Referenced classes of package android.support.design.widget:
//            AnimationUtils, VisibilityAwareImageButton, CircularBorderDrawable, ShadowViewDelegate

abstract class FloatingActionButtonImpl
{
    static interface InternalVisibilityChangedListener
    {

        public abstract void onHidden();

        public abstract void onShown();
    }


    FloatingActionButtonImpl(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate, ValueAnimatorCompat.Creator creator)
    {
        mAnimState = 0;
        mView = visibilityawareimagebutton;
        mShadowViewDelegate = shadowviewdelegate;
        mAnimatorCreator = creator;
    }

    private void ensurePreDrawListener()
    {
        if(mPreDrawListener == null)
            mPreDrawListener = new android.view.ViewTreeObserver.OnPreDrawListener() {

                public boolean onPreDraw()
                {
                    FloatingActionButtonImpl.this.onPreDraw();
                    return true;
                }

                final FloatingActionButtonImpl this$0;

            
            {
                this$0 = FloatingActionButtonImpl.this;
                super();
            }
            }
;
    }

    CircularBorderDrawable createBorderDrawable(int i, ColorStateList colorstatelist)
    {
        android.content.Context context = mView.getContext();
        CircularBorderDrawable circularborderdrawable = newCircularDrawable();
        circularborderdrawable.setGradientColors(ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, android.support.design.R.color.design_fab_stroke_end_outer_color));
        circularborderdrawable.setBorderWidth(i);
        circularborderdrawable.setBorderTint(colorstatelist);
        return circularborderdrawable;
    }

    GradientDrawable createShapeDrawable()
    {
        GradientDrawable gradientdrawable = newGradientDrawableForShape();
        gradientdrawable.setShape(1);
        gradientdrawable.setColor(-1);
        return gradientdrawable;
    }

    final Drawable getContentBackground()
    {
        return mContentBackground;
    }

    abstract float getElevation();

    abstract void getPadding(Rect rect);

    abstract void hide(InternalVisibilityChangedListener internalvisibilitychangedlistener, boolean flag);

    boolean isOrWillBeHidden()
    {
        if(mView.getVisibility() != 0) goto _L2; else goto _L1
_L1:
        if(mAnimState != 1) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if(mAnimState == 2)
            return false;
        if(true) goto _L3; else goto _L5
_L5:
    }

    boolean isOrWillBeShown()
    {
        if(mView.getVisibility() == 0) goto _L2; else goto _L1
_L1:
        if(mAnimState != 2) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if(mAnimState == 1)
            return false;
        if(true) goto _L3; else goto _L5
_L5:
    }

    abstract void jumpDrawableToCurrentState();

    CircularBorderDrawable newCircularDrawable()
    {
        return new CircularBorderDrawable();
    }

    GradientDrawable newGradientDrawableForShape()
    {
        return new GradientDrawable();
    }

    void onAttachedToWindow()
    {
        if(requirePreDrawListener())
        {
            ensurePreDrawListener();
            mView.getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);
        }
    }

    abstract void onCompatShadowChanged();

    void onDetachedFromWindow()
    {
        if(mPreDrawListener != null)
        {
            mView.getViewTreeObserver().removeOnPreDrawListener(mPreDrawListener);
            mPreDrawListener = null;
        }
    }

    abstract void onDrawableStateChanged(int ai[]);

    abstract void onElevationsChanged(float f, float f1);

    void onPaddingUpdated(Rect rect)
    {
    }

    void onPreDraw()
    {
    }

    boolean requirePreDrawListener()
    {
        return false;
    }

    abstract void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int i, int j);

    abstract void setBackgroundTintList(ColorStateList colorstatelist);

    abstract void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode);

    final void setElevation(float f)
    {
        if(mElevation != f)
        {
            mElevation = f;
            onElevationsChanged(f, mPressedTranslationZ);
        }
    }

    final void setPressedTranslationZ(float f)
    {
        if(mPressedTranslationZ != f)
        {
            mPressedTranslationZ = f;
            onElevationsChanged(mElevation, f);
        }
    }

    abstract void setRippleColor(int i);

    abstract void show(InternalVisibilityChangedListener internalvisibilitychangedlistener, boolean flag);

    final void updatePadding()
    {
        Rect rect = mTmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    static final Interpolator ANIM_INTERPOLATOR;
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final int EMPTY_STATE_SET[] = new int[0];
    static final int ENABLED_STATE_SET[] = {
        0x101009e
    };
    static final int FOCUSED_ENABLED_STATE_SET[] = {
        0x101009c, 0x101009e
    };
    static final long PRESSED_ANIM_DELAY = 100L;
    static final long PRESSED_ANIM_DURATION = 100L;
    static final int PRESSED_ENABLED_STATE_SET[] = {
        0x10100a7, 0x101009e
    };
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    int mAnimState;
    final ValueAnimatorCompat.Creator mAnimatorCreator;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private android.view.ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final Rect mTmpRect = new Rect();
    final VisibilityAwareImageButton mView;

    static 
    {
        ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    }
}
