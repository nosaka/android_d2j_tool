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
//            TransitionInterface, TransitionApi23, TransitionImpl, TransitionKitKat, 
//            TransitionIcs, TransitionValues, TransitionInterfaceListener

public abstract class Transition
    implements TransitionInterface
{
    public static interface TransitionListener
        extends TransitionInterfaceListener
    {

        public abstract void onTransitionCancel(Transition transition);

        public abstract void onTransitionEnd(Transition transition);

        public abstract void onTransitionPause(Transition transition);

        public abstract void onTransitionResume(Transition transition);

        public abstract void onTransitionStart(Transition transition);
    }


    public Transition()
    {
        this(false);
    }

    Transition(boolean flag)
    {
        if(!flag)
        {
            if(android.os.Build.VERSION.SDK_INT >= 23)
                mImpl = new TransitionApi23();
            else
            if(android.os.Build.VERSION.SDK_INT >= 19)
                mImpl = new TransitionKitKat();
            else
                mImpl = new TransitionIcs();
            mImpl.init(this);
        }
    }

    public Transition addListener(TransitionListener transitionlistener)
    {
        mImpl.addListener(transitionlistener);
        return this;
    }

    public Transition addTarget(int i)
    {
        mImpl.addTarget(i);
        return this;
    }

    public Transition addTarget(View view)
    {
        mImpl.addTarget(view);
        return this;
    }

    public abstract void captureEndValues(TransitionValues transitionvalues);

    public abstract void captureStartValues(TransitionValues transitionvalues);

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    public Transition excludeChildren(int i, boolean flag)
    {
        mImpl.excludeChildren(i, flag);
        return this;
    }

    public Transition excludeChildren(View view, boolean flag)
    {
        mImpl.excludeChildren(view, flag);
        return this;
    }

    public Transition excludeChildren(Class class1, boolean flag)
    {
        mImpl.excludeChildren(class1, flag);
        return this;
    }

    public Transition excludeTarget(int i, boolean flag)
    {
        mImpl.excludeTarget(i, flag);
        return this;
    }

    public Transition excludeTarget(View view, boolean flag)
    {
        mImpl.excludeTarget(view, flag);
        return this;
    }

    public Transition excludeTarget(Class class1, boolean flag)
    {
        mImpl.excludeTarget(class1, flag);
        return this;
    }

    public long getDuration()
    {
        return mImpl.getDuration();
    }

    public TimeInterpolator getInterpolator()
    {
        return mImpl.getInterpolator();
    }

    public String getName()
    {
        return mImpl.getName();
    }

    public long getStartDelay()
    {
        return mImpl.getStartDelay();
    }

    public List getTargetIds()
    {
        return mImpl.getTargetIds();
    }

    public List getTargets()
    {
        return mImpl.getTargets();
    }

    public String[] getTransitionProperties()
    {
        return mImpl.getTransitionProperties();
    }

    public TransitionValues getTransitionValues(View view, boolean flag)
    {
        return mImpl.getTransitionValues(view, flag);
    }

    public Transition removeListener(TransitionListener transitionlistener)
    {
        mImpl.removeListener(transitionlistener);
        return this;
    }

    public Transition removeTarget(int i)
    {
        mImpl.removeTarget(i);
        return this;
    }

    public Transition removeTarget(View view)
    {
        mImpl.removeTarget(view);
        return this;
    }

    public Transition setDuration(long l)
    {
        mImpl.setDuration(l);
        return this;
    }

    public Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        mImpl.setInterpolator(timeinterpolator);
        return this;
    }

    public Transition setStartDelay(long l)
    {
        mImpl.setStartDelay(l);
        return this;
    }

    public String toString()
    {
        return mImpl.toString();
    }

    TransitionImpl mImpl;
}
