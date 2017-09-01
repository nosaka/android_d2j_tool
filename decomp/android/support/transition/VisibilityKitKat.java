// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionKitKat, VisibilityImpl, VisibilityInterface, TransitionInterface, 
//            TransitionValues

class VisibilityKitKat extends TransitionKitKat
    implements VisibilityImpl
{
    private static class VisibilityWrapper extends Visibility
    {

        public void captureEndValues(TransitionValues transitionvalues)
        {
            TransitionKitKat.wrapCaptureEndValues(mVisibility, transitionvalues);
        }

        public void captureStartValues(TransitionValues transitionvalues)
        {
            TransitionKitKat.wrapCaptureStartValues(mVisibility, transitionvalues);
        }

        public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            return mVisibility.createAnimator(viewgroup, TransitionKitKat.convertToSupport(transitionvalues), TransitionKitKat.convertToSupport(transitionvalues1));
        }

        public boolean isVisible(TransitionValues transitionvalues)
        {
            if(transitionvalues == null)
            {
                return false;
            } else
            {
                android.support.transition.TransitionValues transitionvalues1 = new android.support.transition.TransitionValues();
                TransitionKitKat.copyValues(transitionvalues, transitionvalues1);
                return mVisibility.isVisible(transitionvalues1);
            }
        }

        public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            return mVisibility.onAppear(viewgroup, TransitionKitKat.convertToSupport(transitionvalues), i, TransitionKitKat.convertToSupport(transitionvalues1), j);
        }

        public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
        {
            return mVisibility.onDisappear(viewgroup, TransitionKitKat.convertToSupport(transitionvalues), i, TransitionKitKat.convertToSupport(transitionvalues1), j);
        }

        private final VisibilityInterface mVisibility;

        VisibilityWrapper(VisibilityInterface visibilityinterface)
        {
            mVisibility = visibilityinterface;
        }
    }


    VisibilityKitKat()
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
            mTransition = (Visibility)obj;
            return;
        }
    }

    public boolean isVisible(android.support.transition.TransitionValues transitionvalues)
    {
        return ((Visibility)mTransition).isVisible(convertToPlatform(transitionvalues));
    }

    public Animator onAppear(ViewGroup viewgroup, android.support.transition.TransitionValues transitionvalues, int i, android.support.transition.TransitionValues transitionvalues1, int j)
    {
        return ((Visibility)mTransition).onAppear(viewgroup, convertToPlatform(transitionvalues), i, convertToPlatform(transitionvalues1), j);
    }

    public Animator onDisappear(ViewGroup viewgroup, android.support.transition.TransitionValues transitionvalues, int i, android.support.transition.TransitionValues transitionvalues1, int j)
    {
        return ((Visibility)mTransition).onDisappear(viewgroup, convertToPlatform(transitionvalues), i, convertToPlatform(transitionvalues1), j);
    }
}
