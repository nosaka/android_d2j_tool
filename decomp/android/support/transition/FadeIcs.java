// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionIcs, VisibilityImpl, FadePort, TransitionInterface, 
//            TransitionValues

class FadeIcs extends TransitionIcs
    implements VisibilityImpl
{

    public FadeIcs(TransitionInterface transitioninterface)
    {
        init(transitioninterface, new FadePort());
    }

    public FadeIcs(TransitionInterface transitioninterface, int i)
    {
        init(transitioninterface, new FadePort(i));
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        return ((FadePort)mTransition).isVisible(transitionvalues);
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((FadePort)mTransition).onAppear(viewgroup, transitionvalues, i, transitionvalues1, j);
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((FadePort)mTransition).onDisappear(viewgroup, transitionvalues, i, transitionvalues, i);
    }
}
