// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            Transition, TransitionSetIcs, TransitionSetKitKat, TransitionSetImpl, 
//            TransitionImpl, TransitionValues

public class TransitionSet extends Transition
{

    public TransitionSet()
    {
        super(true);
        if(android.os.Build.VERSION.SDK_INT < 19)
        {
            mImpl = new TransitionSetIcs(this);
            return;
        } else
        {
            mImpl = new TransitionSetKitKat(this);
            return;
        }
    }

    public TransitionSet addTransition(Transition transition)
    {
        ((TransitionSetImpl)mImpl).addTransition(transition.mImpl);
        return this;
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        mImpl.captureEndValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        mImpl.captureStartValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return mImpl.createAnimator(viewgroup, transitionvalues, transitionvalues1);
    }

    public int getOrdering()
    {
        return ((TransitionSetImpl)mImpl).getOrdering();
    }

    public TransitionSet removeTransition(Transition transition)
    {
        ((TransitionSetImpl)mImpl).removeTransition(transition.mImpl);
        return this;
    }

    public TransitionSet setOrdering(int i)
    {
        ((TransitionSetImpl)mImpl).setOrdering(i);
        return this;
    }

    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
}
