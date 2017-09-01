// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.animation.*;
import android.view.animation.Interpolator;

class ValueAnimatorCompatImplHoneycombMr1 extends ValueAnimatorCompat.Impl
{

    ValueAnimatorCompatImplHoneycombMr1()
    {
    }

    public void addListener(final ValueAnimatorCompat.Impl.AnimatorListenerProxy listener)
    {
        mValueAnimator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationCancel(Animator animator)
            {
                listener.onAnimationCancel();
            }

            public void onAnimationEnd(Animator animator)
            {
                listener.onAnimationEnd();
            }

            public void onAnimationStart(Animator animator)
            {
                listener.onAnimationStart();
            }

            final ValueAnimatorCompatImplHoneycombMr1 this$0;
            final ValueAnimatorCompat.Impl.AnimatorListenerProxy val$listener;

            
            {
                this$0 = ValueAnimatorCompatImplHoneycombMr1.this;
                listener = animatorlistenerproxy;
                super();
            }
        }
);
    }

    public void addUpdateListener(final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy updateListener)
    {
        mValueAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                updateListener.onAnimationUpdate();
            }

            final ValueAnimatorCompatImplHoneycombMr1 this$0;
            final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy val$updateListener;

            
            {
                this$0 = ValueAnimatorCompatImplHoneycombMr1.this;
                updateListener = animatorupdatelistenerproxy;
                super();
            }
        }
);
    }

    public void cancel()
    {
        mValueAnimator.cancel();
    }

    public void end()
    {
        mValueAnimator.end();
    }

    public float getAnimatedFloatValue()
    {
        return ((Float)mValueAnimator.getAnimatedValue()).floatValue();
    }

    public float getAnimatedFraction()
    {
        return mValueAnimator.getAnimatedFraction();
    }

    public int getAnimatedIntValue()
    {
        return ((Integer)mValueAnimator.getAnimatedValue()).intValue();
    }

    public long getDuration()
    {
        return mValueAnimator.getDuration();
    }

    public boolean isRunning()
    {
        return mValueAnimator.isRunning();
    }

    public void setDuration(long l)
    {
        mValueAnimator.setDuration(l);
    }

    public void setFloatValues(float f, float f1)
    {
        mValueAnimator.setFloatValues(new float[] {
            f, f1
        });
    }

    public void setIntValues(int i, int j)
    {
        mValueAnimator.setIntValues(new int[] {
            i, j
        });
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mValueAnimator.setInterpolator(interpolator);
    }

    public void start()
    {
        mValueAnimator.start();
    }

    private final ValueAnimator mValueAnimator = new ValueAnimator();
}
