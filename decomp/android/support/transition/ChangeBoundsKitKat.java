// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.ChangeBounds;

// Referenced classes of package android.support.transition:
//            TransitionKitKat, ChangeBoundsInterface, TransitionInterface

class ChangeBoundsKitKat extends TransitionKitKat
    implements ChangeBoundsInterface
{

    public ChangeBoundsKitKat(TransitionInterface transitioninterface)
    {
        init(transitioninterface, new ChangeBounds());
    }

    public void setResizeClip(boolean flag)
    {
        ((ChangeBounds)mTransition).setResizeClip(flag);
    }
}
