// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            Transition, ChangeBoundsIcs, ChangeBoundsKitKat, TransitionImpl, 
//            ChangeBoundsInterface, TransitionValues

public class ChangeBounds extends Transition
{

    public ChangeBounds()
    {
        super(true);
        if(android.os.Build.VERSION.SDK_INT < 19)
        {
            mImpl = new ChangeBoundsIcs(this);
            return;
        } else
        {
            mImpl = new ChangeBoundsKitKat(this);
            return;
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

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return mImpl.createAnimator(viewgroup, transitionvalues, transitionvalues1);
    }

    public void setResizeClip(boolean flag)
    {
        ((ChangeBoundsInterface)mImpl).setResizeClip(flag);
    }
}
