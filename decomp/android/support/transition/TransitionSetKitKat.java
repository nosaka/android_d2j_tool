// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.TransitionSet;

// Referenced classes of package android.support.transition:
//            TransitionKitKat, TransitionSetImpl, TransitionInterface, TransitionImpl

class TransitionSetKitKat extends TransitionKitKat
    implements TransitionSetImpl
{

    public TransitionSetKitKat(TransitionInterface transitioninterface)
    {
        mTransitionSet = new TransitionSet();
        init(transitioninterface, mTransitionSet);
    }

    public volatile TransitionSetImpl addTransition(TransitionImpl transitionimpl)
    {
        return addTransition(transitionimpl);
    }

    public TransitionSetKitKat addTransition(TransitionImpl transitionimpl)
    {
        mTransitionSet.addTransition(((TransitionKitKat)transitionimpl).mTransition);
        return this;
    }

    public int getOrdering()
    {
        return mTransitionSet.getOrdering();
    }

    public volatile TransitionSetImpl removeTransition(TransitionImpl transitionimpl)
    {
        return removeTransition(transitionimpl);
    }

    public TransitionSetKitKat removeTransition(TransitionImpl transitionimpl)
    {
        mTransitionSet.removeTransition(((TransitionKitKat)transitionimpl).mTransition);
        return this;
    }

    public volatile TransitionSetImpl setOrdering(int i)
    {
        return setOrdering(i);
    }

    public TransitionSetKitKat setOrdering(int i)
    {
        mTransitionSet.setOrdering(i);
        return this;
    }

    private TransitionSet mTransitionSet;
}
