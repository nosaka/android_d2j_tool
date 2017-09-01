// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.util.StateSet;
import java.util.ArrayList;

// Referenced classes of package android.support.design.widget:
//            ValueAnimatorCompat

final class StateListAnimator
{
    static class Tuple
    {

        final ValueAnimatorCompat mAnimator;
        final int mSpecs[];

        Tuple(int ai[], ValueAnimatorCompat valueanimatorcompat)
        {
            mSpecs = ai;
            mAnimator = valueanimatorcompat;
        }
    }


    StateListAnimator()
    {
        mLastMatch = null;
        mRunningAnimator = null;
    }

    private void cancel()
    {
        if(mRunningAnimator != null)
        {
            mRunningAnimator.cancel();
            mRunningAnimator = null;
        }
    }

    private void start(Tuple tuple)
    {
        mRunningAnimator = tuple.mAnimator;
        mRunningAnimator.start();
    }

    public void addState(int ai[], ValueAnimatorCompat valueanimatorcompat)
    {
        ai = new Tuple(ai, valueanimatorcompat);
        valueanimatorcompat.addListener(mAnimationListener);
        mTuples.add(ai);
    }

    public void jumpToCurrentState()
    {
        if(mRunningAnimator != null)
        {
            mRunningAnimator.end();
            mRunningAnimator = null;
        }
    }

    void setState(int ai[])
    {
        int i;
        int j;
        Object obj;
        obj = null;
        j = mTuples.size();
        i = 0;
_L8:
        Tuple tuple = obj;
        if(i >= j) goto _L2; else goto _L1
_L1:
        tuple = (Tuple)mTuples.get(i);
        if(!StateSet.stateSetMatches(tuple.mSpecs, ai)) goto _L3; else goto _L2
_L2:
        if(tuple != mLastMatch) goto _L5; else goto _L4
_L4:
        return;
_L3:
        i++;
        continue; /* Loop/switch isn't completed */
_L5:
        if(mLastMatch != null)
            cancel();
        mLastMatch = tuple;
        if(tuple == null) goto _L4; else goto _L6
_L6:
        start(tuple);
        return;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private final ValueAnimatorCompat.AnimatorListener mAnimationListener = new ValueAnimatorCompat.AnimatorListenerAdapter() {

        public void onAnimationEnd(ValueAnimatorCompat valueanimatorcompat)
        {
            if(mRunningAnimator == valueanimatorcompat)
                mRunningAnimator = null;
        }

        final StateListAnimator this$0;

            
            {
                this$0 = StateListAnimator.this;
                super();
            }
    }
;
    private Tuple mLastMatch;
    ValueAnimatorCompat mRunningAnimator;
    private final ArrayList mTuples = new ArrayList();
}
