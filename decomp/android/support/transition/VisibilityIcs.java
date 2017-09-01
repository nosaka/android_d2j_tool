// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionIcs, VisibilityImpl, VisibilityInterface, VisibilityPort, 
//            TransitionInterface, TransitionValues

class VisibilityIcs extends TransitionIcs
    implements VisibilityImpl
{
    private static class VisibilityWrapper extends VisibilityPort
    {

        public void captureEndValues(TransitionValues transitionvalues)
        {
            mVisibility.captureEndValues(transitionvalues);
        }

        public void captureStartValues(TransitionValues transitionvalues)
        {
            mVisibility.captureStartValues(transitionvalues);
        }

        public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            return mVisibility.createAnimator(viewgroup, transitionvalues, transitionvalues1);
        }

        public boolean isVisible(TransitionValues transitionvalues)
        {
            return mVisibility.isVisible(transitionvalues);
        }

        public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            return mVisibility.onAppear(viewgroup, transitionvalues, i, transitionvalues1, j);
        }

        public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            return mVisibility.onDisappear(viewgroup, transitionvalues, i, transitionvalues1, j);
        }

        private VisibilityInterface mVisibility;

        VisibilityWrapper(VisibilityInterface visibilityinterface)
        {
            mVisibility = visibilityinterface;
        }
    }


    VisibilityIcs()
    {
    }

    public void init(TransitionInterface transitioninterface, Object obj)
    {
        mExternalTransition = transitioninterface;
        if(obj == null)
        {
            mTransition = new VisibilityWrapper((VisibilityInterface)transitioninterface);
            return;
        } else
        {
            mTransition = (VisibilityPort)obj;
            return;
        }
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        return ((VisibilityPort)mTransition).isVisible(transitionvalues);
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((VisibilityPort)mTransition).onAppear(viewgroup, transitionvalues, i, transitionvalues1, j);
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((VisibilityPort)mTransition).onDisappear(viewgroup, transitionvalues, i, transitionvalues1, j);
    }
}
