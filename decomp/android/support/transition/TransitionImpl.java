// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

// Referenced classes of package android.support.transition:
//            TransitionInterfaceListener, TransitionValues, TransitionInterface

abstract class TransitionImpl
{

    TransitionImpl()
    {
    }

    public abstract TransitionImpl addListener(TransitionInterfaceListener transitioninterfacelistener);

    public abstract TransitionImpl addTarget(int i);

    public abstract TransitionImpl addTarget(View view);

    public abstract void captureEndValues(TransitionValues transitionvalues);

    public abstract void captureStartValues(TransitionValues transitionvalues);

    public abstract Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1);

    public abstract TransitionImpl excludeChildren(int i, boolean flag);

    public abstract TransitionImpl excludeChildren(View view, boolean flag);

    public abstract TransitionImpl excludeChildren(Class class1, boolean flag);

    public abstract TransitionImpl excludeTarget(int i, boolean flag);

    public abstract TransitionImpl excludeTarget(View view, boolean flag);

    public abstract TransitionImpl excludeTarget(Class class1, boolean flag);

    public abstract long getDuration();

    public abstract TimeInterpolator getInterpolator();

    public abstract String getName();

    public abstract long getStartDelay();

    public abstract List getTargetIds();

    public abstract List getTargets();

    public abstract String[] getTransitionProperties();

    public abstract TransitionValues getTransitionValues(View view, boolean flag);

    public void init(TransitionInterface transitioninterface)
    {
        init(transitioninterface, null);
    }

    public abstract void init(TransitionInterface transitioninterface, Object obj);

    public abstract TransitionImpl removeListener(TransitionInterfaceListener transitioninterfacelistener);

    public abstract TransitionImpl removeTarget(int i);

    public abstract TransitionImpl removeTarget(View view);

    public abstract TransitionImpl setDuration(long l);

    public abstract TransitionImpl setInterpolator(TimeInterpolator timeinterpolator);

    public abstract TransitionImpl setStartDelay(long l);
}
