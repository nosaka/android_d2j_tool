// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionIcs, ChangeBoundsInterface, ChangeBoundsPort, TransitionInterface

class ChangeBoundsIcs extends TransitionIcs
    implements ChangeBoundsInterface
{

    public ChangeBoundsIcs(TransitionInterface transitioninterface)
    {
        init(transitioninterface, new ChangeBoundsPort());
    }

    public void setResizeClip(boolean flag)
    {
        ((ChangeBoundsPort)mTransition).setResizeClip(flag);
    }
}
