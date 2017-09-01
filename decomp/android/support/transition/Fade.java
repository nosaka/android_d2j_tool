// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            Visibility, FadeKitKat, FadeIcs, TransitionImpl, 
//            TransitionValues

public class Fade extends Visibility
{

    public Fade()
    {
        this(-1);
    }

    public Fade(int i)
    {
        super(true);
        if(android.os.Build.VERSION.SDK_INT >= 19)
            if(i > 0)
            {
                mImpl = new FadeKitKat(this, i);
                return;
            } else
            {
                mImpl = new FadeKitKat(this);
                return;
            }
        if(i > 0)
        {
            mImpl = new FadeIcs(this, i);
            return;
        } else
        {
            mImpl = new FadeIcs(this);
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

    public static final int IN = 1;
    public static final int OUT = 2;
}
