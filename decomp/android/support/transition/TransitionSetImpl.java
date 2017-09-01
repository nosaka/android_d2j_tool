// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionImpl

interface TransitionSetImpl
{

    public abstract TransitionSetImpl addTransition(TransitionImpl transitionimpl);

    public abstract int getOrdering();

    public abstract TransitionSetImpl removeTransition(TransitionImpl transitionimpl);

    public abstract TransitionSetImpl setOrdering(int i);
}
