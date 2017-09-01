// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.transition.Fade;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionKitKat, VisibilityImpl, TransitionInterface, TransitionValues

class FadeKitKat extends TransitionKitKat
    implements VisibilityImpl
{

    public FadeKitKat(TransitionInterface transitioninterface)
    {
        init(transitioninterface, new Fade());
    }

    public FadeKitKat(TransitionInterface transitioninterface, int i)
    {
        init(transitioninterface, new Fade(i));
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        return ((Fade)mTransition).isVisible(convertToPlatform(transitionvalues));
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((Fade)mTransition).onAppear(viewgroup, convertToPlatform(transitionvalues), i, convertToPlatform(transitionvalues1), j);
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((Fade)mTransition).onDisappear(viewgroup, convertToPlatform(transitionvalues), i, convertToPlatform(transitionvalues1), j);
    }
}
