// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            Transition, VisibilityInterface, VisibilityKitKat, TransitionImpl, 
//            VisibilityIcs, VisibilityImpl, TransitionValues

public abstract class Visibility extends Transition
    implements VisibilityInterface
{

    public Visibility()
    {
        this(false);
    }

    Visibility(boolean flag)
    {
        super(true);
        if(!flag)
        {
            if(android.os.Build.VERSION.SDK_INT >= 19)
                mImpl = new VisibilityKitKat();
            else
                mImpl = new VisibilityIcs();
            mImpl.init(this);
        }
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        mImpl.captureEndValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        mImpl.captureStartValues(transitionvalues);
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        return ((VisibilityImpl)mImpl).isVisible(transitionvalues);
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((VisibilityImpl)mImpl).onAppear(viewgroup, transitionvalues, i, transitionvalues1, j);
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return ((VisibilityImpl)mImpl).onDisappear(viewgroup, transitionvalues, i, transitionvalues1, j);
    }
}
