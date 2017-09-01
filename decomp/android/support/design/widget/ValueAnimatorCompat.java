// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.view.animation.Interpolator;

class ValueAnimatorCompat
{
    static interface AnimatorListener
    {

        public abstract void onAnimationCancel(ValueAnimatorCompat valueanimatorcompat);

        public abstract void onAnimationEnd(ValueAnimatorCompat valueanimatorcompat);

        public abstract void onAnimationStart(ValueAnimatorCompat valueanimatorcompat);
    }

    static class AnimatorListenerAdapter
        implements AnimatorListener
    {

        public void onAnimationCancel(ValueAnimatorCompat valueanimatorcompat)
        {
        }

        public void onAnimationEnd(ValueAnimatorCompat valueanimatorcompat)
        {
        }

        public void onAnimationStart(ValueAnimatorCompat valueanimatorcompat)
        {
        }

        AnimatorListenerAdapter()
        {
        }
    }

    static interface AnimatorUpdateListener
    {

        public abstract void onAnimationUpdate(ValueAnimatorCompat valueanimatorcompat);
    }

    static interface Creator
    {

        public abstract ValueAnimatorCompat createAnimator();
    }

    static abstract class Impl
    {

        abstract void addListener(AnimatorListenerProxy animatorlistenerproxy);

        abstract void addUpdateListener(AnimatorUpdateListenerProxy animatorupdatelistenerproxy);

        abstract void cancel();

        abstract void end();

        abstract float getAnimatedFloatValue();

        abstract float getAnimatedFraction();

        abstract int getAnimatedIntValue();

        abstract long getDuration();

        abstract boolean isRunning();

        abstract void setDuration(long l);

        abstract void setFloatValues(float f, float f1);

        abstract void setIntValues(int i, int j);

        abstract void setInterpolator(Interpolator interpolator);

        abstract void start();

        Impl()
        {
        }
    }

    static interface Impl.AnimatorListenerProxy
    {

        public abstract void onAnimationCancel();

        public abstract void onAnimationEnd();

        public abstract void onAnimationStart();
    }

    static interface Impl.AnimatorUpdateListenerProxy
    {

        public abstract void onAnimationUpdate();
    }


    ValueAnimatorCompat(Impl impl)
    {
        mImpl = impl;
    }

    public void addListener(final AnimatorListener listener)
    {
        if(listener != null)
        {
            mImpl.addListener(new Impl.AnimatorListenerProxy() {

                public void onAnimationCancel()
                {
                    listener.onAnimationCancel(ValueAnimatorCompat.this);
                }

                public void onAnimationEnd()
                {
                    listener.onAnimationEnd(ValueAnimatorCompat.this);
                }

                public void onAnimationStart()
                {
                    listener.onAnimationStart(ValueAnimatorCompat.this);
                }

                final ValueAnimatorCompat this$0;
                final AnimatorListener val$listener;

            
            {
                this$0 = ValueAnimatorCompat.this;
                listener = animatorlistener;
                super();
            }
            }
);
            return;
        } else
        {
            mImpl.addListener(null);
            return;
        }
    }

    public void addUpdateListener(final AnimatorUpdateListener updateListener)
    {
        if(updateListener != null)
        {
            mImpl.addUpdateListener(new Impl.AnimatorUpdateListenerProxy() {

                public void onAnimationUpdate()
                {
                    updateListener.onAnimationUpdate(ValueAnimatorCompat.this);
                }

                final ValueAnimatorCompat this$0;
                final AnimatorUpdateListener val$updateListener;

            
            {
                this$0 = ValueAnimatorCompat.this;
                updateListener = animatorupdatelistener;
                super();
            }
            }
);
            return;
        } else
        {
            mImpl.addUpdateListener(null);
            return;
        }
    }

    public void cancel()
    {
        mImpl.cancel();
    }

    public void end()
    {
        mImpl.end();
    }

    public float getAnimatedFloatValue()
    {
        return mImpl.getAnimatedFloatValue();
    }

    public float getAnimatedFraction()
    {
        return mImpl.getAnimatedFraction();
    }

    public int getAnimatedIntValue()
    {
        return mImpl.getAnimatedIntValue();
    }

    public long getDuration()
    {
        return mImpl.getDuration();
    }

    public boolean isRunning()
    {
        return mImpl.isRunning();
    }

    public void setDuration(long l)
    {
        mImpl.setDuration(l);
    }

    public void setFloatValues(float f, float f1)
    {
        mImpl.setFloatValues(f, f1);
    }

    public void setIntValues(int i, int j)
    {
        mImpl.setIntValues(i, j);
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mImpl.setInterpolator(interpolator);
    }

    public void start()
    {
        mImpl.start();
    }

    private final Impl mImpl;
}
