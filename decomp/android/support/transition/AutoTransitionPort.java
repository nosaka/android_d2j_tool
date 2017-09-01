// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionSetPort, FadePort, ChangeBoundsPort

class AutoTransitionPort extends TransitionSetPort
{

    public AutoTransitionPort()
    {
        setOrdering(1);
        addTransition(new FadePort(2)).addTransition(new ChangeBoundsPort()).addTransition(new FadePort(1));
    }
}
