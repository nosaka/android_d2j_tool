// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.os.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.ArrayList;

// Referenced classes of package android.support.design.widget:
//            AnimationUtils, MathUtils

class ValueAnimatorCompatImplGingerbread extends ValueAnimatorCompat.Impl
{

    ValueAnimatorCompatImplGingerbread()
    {
        mDuration = 200L;
    }

    private void dispatchAnimationCancel()
    {
        if(mListeners != null)
        {
            int i = 0;
            for(int j = mListeners.size(); i < j; i++)
                ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)mListeners.get(i)).onAnimationCancel();

        }
    }

    private void dispatchAnimationEnd()
    {
        if(mListeners != null)
        {
            int i = 0;
            for(int j = mListeners.size(); i < j; i++)
                ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)mListeners.get(i)).onAnimationEnd();

        }
    }

    private void dispatchAnimationStart()
    {
        if(mListeners != null)
        {
            int i = 0;
            for(int j = mListeners.size(); i < j; i++)
                ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)mListeners.get(i)).onAnimationStart();

        }
    }

    private void dispatchAnimationUpdate()
    {
        if(mUpdateListeners != null)
        {
            int i = 0;
            for(int j = mUpdateListeners.size(); i < j; i++)
                ((ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy)mUpdateListeners.get(i)).onAnimationUpdate();

        }
    }

    public void addListener(ValueAnimatorCompat.Impl.AnimatorListenerProxy animatorlistenerproxy)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(animatorlistenerproxy);
    }

    public void addUpdateListener(ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy animatorupdatelistenerproxy)
    {
        if(mUpdateListeners == null)
            mUpdateListeners = new ArrayList();
        mUpdateListeners.add(animatorupdatelistenerproxy);
    }

    public void cancel()
    {
        mIsRunning = false;
        sHandler.removeCallbacks(mRunnable);
        dispatchAnimationCancel();
        dispatchAnimationEnd();
    }

    public void end()
    {
        if(mIsRunning)
        {
            mIsRunning = false;
            sHandler.removeCallbacks(mRunnable);
            mAnimatedFraction = 1.0F;
            dispatchAnimationUpdate();
            dispatchAnimationEnd();
        }
    }

    public float getAnimatedFloatValue()
    {
        return AnimationUtils.lerp(mFloatValues[0], mFloatValues[1], getAnimatedFraction());
    }

    public float getAnimatedFraction()
    {
        return mAnimatedFraction;
    }

    public int getAnimatedIntValue()
    {
        return AnimationUtils.lerp(mIntValues[0], mIntValues[1], getAnimatedFraction());
    }

    public long getDuration()
    {
        return mDuration;
    }

    public boolean isRunning()
    {
        return mIsRunning;
    }

    public void setDuration(long l)
    {
        mDuration = l;
    }

    public void setFloatValues(float f, float f1)
    {
        mFloatValues[0] = f;
        mFloatValues[1] = f1;
    }

    public void setIntValues(int i, int j)
    {
        mIntValues[0] = i;
        mIntValues[1] = j;
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void start()
    {
        if(mIsRunning)
            return;
        if(mInterpolator == null)
            mInterpolator = new AccelerateDecelerateInterpolator();
        mIsRunning = true;
        mAnimatedFraction = 0.0F;
        startInternal();
    }

    final void startInternal()
    {
        mStartTime = SystemClock.uptimeMillis();
        dispatchAnimationUpdate();
        dispatchAnimationStart();
        sHandler.postDelayed(mRunnable, 10L);
    }

    final void update()
    {
        if(mIsRunning)
        {
            float f1 = MathUtils.constrain((float)(SystemClock.uptimeMillis() - mStartTime) / (float)mDuration, 0.0F, 1.0F);
            float f = f1;
            if(mInterpolator != null)
                f = mInterpolator.getInterpolation(f1);
            mAnimatedFraction = f;
            dispatchAnimationUpdate();
            if(SystemClock.uptimeMillis() >= mStartTime + mDuration)
            {
                mIsRunning = false;
                dispatchAnimationEnd();
            }
        }
        if(mIsRunning)
            sHandler.postDelayed(mRunnable, 10L);
    }

    private static final int DEFAULT_DURATION = 200;
    private static final int HANDLER_DELAY = 10;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private float mAnimatedFraction;
    private long mDuration;
    private final float mFloatValues[] = new float[2];
    private final int mIntValues[] = new int[2];
    private Interpolator mInterpolator;
    private boolean mIsRunning;
    private ArrayList mListeners;
    private final Runnable mRunnable = new Runnable() {

        public void run()
        {
            update();
        }

        final ValueAnimatorCompatImplGingerbread this$0;

            
            {
                this$0 = ValueAnimatorCompatImplGingerbread.this;
                super();
            }
    }
;
    private long mStartTime;
    private ArrayList mUpdateListeners;

}
