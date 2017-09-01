// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.Transition;

// Referenced classes of package android.support.transition:
//            TransitionKitKat, TransitionImpl

class TransitionApi23 extends TransitionKitKat
{

    TransitionApi23()
    {
    }

    public TransitionImpl removeTarget(int i)
    {
        mTransition.removeTarget(i);
        return this;
    }
}
