// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// Referenced classes of package android.support.design.widget:
//            FloatingActionButtonImpl, StateListAnimator, ValueAnimatorCompat, ShadowDrawableWrapper, 
//            VisibilityAwareImageButton, AnimationUtils, ShadowViewDelegate, CircularBorderDrawable

class FloatingActionButtonGingerbread extends FloatingActionButtonImpl
{
    private class DisabledElevationAnimation extends ShadowAnimatorImpl
    {

        protected float getTargetShadowSize()
        {
            return 0.0F;
        }

        final FloatingActionButtonGingerbread this$0;

        DisabledElevationAnimation()
        {
            this$0 = FloatingActionButtonGingerbread.this;
            super();
        }
    }

    private class ElevateToTranslationZAnimation extends ShadowAnimatorImpl
    {

        protected float getTargetShadowSize()
        {
            return mElevation + mPressedTranslationZ;
        }

        final FloatingActionButtonGingerbread this$0;

        ElevateToTranslationZAnimation()
        {
            this$0 = FloatingActionButtonGingerbread.this;
            super();
        }
    }

    private class ResetElevationAnimation extends ShadowAnimatorImpl
    {

        protected float getTargetShadowSize()
        {
            return mElevation;
        }

        final FloatingActionButtonGingerbread this$0;

        ResetElevationAnimation()
        {
            this$0 = FloatingActionButtonGingerbread.this;
            super();
        }
    }

    private abstract class ShadowAnimatorImpl extends ValueAnimatorCompat.AnimatorListenerAdapter
        implements ValueAnimatorCompat.AnimatorUpdateListener
    {

        protected abstract float getTargetShadowSize();

        public void onAnimationEnd(ValueAnimatorCompat valueanimatorcompat)
        {
            mShadowDrawable.setShadowSize(mShadowSizeEnd);
            mValidValues = false;
        }

        public void onAnimationUpdate(ValueAnimatorCompat valueanimatorcompat)
        {
            if(!mValidValues)
            {
                mShadowSizeStart = mShadowDrawable.getShadowSize();
                mShadowSizeEnd = getTargetShadowSize();
                mValidValues = true;
            }
            mShadowDrawable.setShadowSize(mShadowSizeStart + (mShadowSizeEnd - mShadowSizeStart) * valueanimatorcompat.getAnimatedFraction());
        }

        private float mShadowSizeEnd;
        private float mShadowSizeStart;
        private boolean mValidValues;
        final FloatingActionButtonGingerbread this$0;

        private ShadowAnimatorImpl()
        {
            this$0 = FloatingActionButtonGingerbread.this;
            super();
        }

    }


    FloatingActionButtonGingerbread(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate, ValueAnimatorCompat.Creator creator)
    {
        super(visibilityawareimagebutton, shadowviewdelegate, creator);
        mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        mStateListAnimator.addState(ENABLED_STATE_SET, createAnimator(new ResetElevationAnimation()));
        mStateListAnimator.addState(EMPTY_STATE_SET, createAnimator(new DisabledElevationAnimation()));
    }

    private ValueAnimatorCompat createAnimator(ShadowAnimatorImpl shadowanimatorimpl)
    {
        ValueAnimatorCompat valueanimatorcompat = mAnimatorCreator.createAnimator();
        valueanimatorcompat.setInterpolator(ANIM_INTERPOLATOR);
        valueanimatorcompat.setDuration(100L);
        valueanimatorcompat.addListener(shadowanimatorimpl);
        valueanimatorcompat.addUpdateListener(shadowanimatorimpl);
        valueanimatorcompat.setFloatValues(0.0F, 1.0F);
        return valueanimatorcompat;
    }

    private static ColorStateList createColorStateList(int i)
    {
        int ai[][] = new int[3][];
        int ai1[] = new int[3];
        ai[0] = FOCUSED_ENABLED_STATE_SET;
        ai1[0] = i;
        int j = 0 + 1;
        ai[j] = PRESSED_ENABLED_STATE_SET;
        ai1[j] = i;
        i = j + 1;
        ai[i] = new int[0];
        ai1[i] = 0;
        return new ColorStateList(ai, ai1);
    }

    float getElevation()
    {
        return mElevation;
    }

    void getPadding(Rect rect)
    {
        mShadowDrawable.getPadding(rect);
    }

    void hide(final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, final boolean fromUser)
    {
        if(isOrWillBeHidden())
        {
            return;
        } else
        {
            mAnimState = 1;
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_fab_out);
            animation.setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
            animation.setDuration(200L);
            animation.setAnimationListener(new AnimationUtils.AnimationListenerAdapter() {

                public void onAnimationEnd(Animation animation1)
                {
                    mAnimState = 0;
                    animation1 = mView;
                    byte byte0;
                    if(fromUser)
                        byte0 = 8;
                    else
                        byte0 = 4;
                    animation1.internalSetVisibility(byte0, fromUser);
                    if(listener != null)
                        listener.onHidden();
                }

                final FloatingActionButtonGingerbread this$0;
                final boolean val$fromUser;
                final FloatingActionButtonImpl.InternalVisibilityChangedListener val$listener;

            
            {
                this$0 = FloatingActionButtonGingerbread.this;
                fromUser = flag;
                listener = internalvisibilitychangedlistener;
                super();
            }
            }
);
            mView.startAnimation(animation);
            return;
        }
    }

    void jumpDrawableToCurrentState()
    {
        mStateListAnimator.jumpToCurrentState();
    }

    void onCompatShadowChanged()
    {
    }

    void onDrawableStateChanged(int ai[])
    {
        mStateListAnimator.setState(ai);
    }

    void onElevationsChanged(float f, float f1)
    {
        if(mShadowDrawable != null)
        {
            mShadowDrawable.setShadowSize(f, mPressedTranslationZ + f);
            updatePadding();
        }
    }

    void setBackgroundDrawable(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int i, int j)
    {
        mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(mShapeDrawable, colorstatelist);
        if(mode != null)
            DrawableCompat.setTintMode(mShapeDrawable, mode);
        mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(mRippleDrawable, createColorStateList(i));
        if(j > 0)
        {
            mBorderDrawable = createBorderDrawable(j, colorstatelist);
            colorstatelist = new Drawable[3];
            colorstatelist[0] = mBorderDrawable;
            colorstatelist[1] = mShapeDrawable;
            colorstatelist[2] = mRippleDrawable;
        } else
        {
            mBorderDrawable = null;
            colorstatelist = new Drawable[2];
            colorstatelist[0] = mShapeDrawable;
            colorstatelist[1] = mRippleDrawable;
        }
        mContentBackground = new LayerDrawable(colorstatelist);
        mShadowDrawable = new ShadowDrawableWrapper(mView.getContext(), mContentBackground, mShadowViewDelegate.getRadius(), mElevation, mElevation + mPressedTranslationZ);
        mShadowDrawable.setAddPaddingForCorners(false);
        mShadowViewDelegate.setBackgroundDrawable(mShadowDrawable);
    }

    void setBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mShapeDrawable != null)
            DrawableCompat.setTintList(mShapeDrawable, colorstatelist);
        if(mBorderDrawable != null)
            mBorderDrawable.setBorderTint(colorstatelist);
    }

    void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mShapeDrawable != null)
            DrawableCompat.setTintMode(mShapeDrawable, mode);
    }

    void setRippleColor(int i)
    {
        if(mRippleDrawable != null)
            DrawableCompat.setTintList(mRippleDrawable, createColorStateList(i));
    }

    void show(final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, boolean flag)
    {
        if(isOrWillBeShown())
        {
            return;
        } else
        {
            mAnimState = 2;
            mView.internalSetVisibility(0, flag);
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_fab_in);
            animation.setDuration(200L);
            animation.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
            animation.setAnimationListener(new AnimationUtils.AnimationListenerAdapter() {

                public void onAnimationEnd(Animation animation1)
                {
                    mAnimState = 0;
                    if(listener != null)
                        listener.onShown();
                }

                final FloatingActionButtonGingerbread this$0;
                final FloatingActionButtonImpl.InternalVisibilityChangedListener val$listener;

            
            {
                this$0 = FloatingActionButtonGingerbread.this;
                listener = internalvisibilitychangedlistener;
                super();
            }
            }
);
            mView.startAnimation(animation);
            return;
        }
    }

    ShadowDrawableWrapper mShadowDrawable;
    private final StateListAnimator mStateListAnimator = new StateListAnimator();
}
