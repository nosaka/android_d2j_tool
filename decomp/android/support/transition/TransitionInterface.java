// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionValues

interface TransitionInterface
{

    public abstract void captureEndValues(TransitionValues transitionvalues);

    public abstract void captureStartValues(TransitionValues transitionvalues);

    public abstract Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1);
}
