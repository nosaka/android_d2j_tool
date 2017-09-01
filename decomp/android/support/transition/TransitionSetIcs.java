// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionIcs, TransitionSetImpl, TransitionSetPort, TransitionInterface, 
//            TransitionImpl

class TransitionSetIcs extends TransitionIcs
    implements TransitionSetImpl
{

    public TransitionSetIcs(TransitionInterface transitioninterface)
    {
        mTransitionSet = new TransitionSetPort();
        init(transitioninterface, mTransitionSet);
    }

    public TransitionSetIcs addTransition(TransitionImpl transitionimpl)
    {
        mTransitionSet.addTransition(((TransitionIcs)transitionimpl).mTransition);
        return this;
    }

    public volatile TransitionSetImpl addTransition(TransitionImpl transitionimpl)
    {
        return addTransition(transitionimpl);
    }

    public int getOrdering()
    {
        return mTransitionSet.getOrdering();
    }

    public TransitionSetIcs removeTransition(TransitionImpl transitionimpl)
    {
        mTransitionSet.removeTransition(((TransitionIcs)transitionimpl).mTransition);
        return this;
    }

    public volatile TransitionSetImpl removeTransition(TransitionImpl transitionimpl)
    {
        return removeTransition(transitionimpl);
    }

    public TransitionSetIcs setOrdering(int i)
    {
        mTransitionSet.setOrdering(i);
        return this;
    }

    public volatile TransitionSetImpl setOrdering(int i)
    {
        return setOrdering(i);
    }

    private TransitionSetPort mTransitionSet;
}
