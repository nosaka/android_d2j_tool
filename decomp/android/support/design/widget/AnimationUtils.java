// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.support.v4.view.animation.*;
import android.view.animation.*;

class AnimationUtils
{
    static class AnimationListenerAdapter
        implements android.view.animation.Animation.AnimationListener
    {

        public void onAnimationEnd(Animation animation)
        {
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
        }

        AnimationListenerAdapter()
        {
        }
    }


    AnimationUtils()
    {
    }

    static float lerp(float f, float f1, float f2)
    {
        return (f1 - f) * f2 + f;
    }

    static int lerp(int i, int j, float f)
    {
        return Math.round((float)(j - i) * f) + i;
    }

    static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
    static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

}
