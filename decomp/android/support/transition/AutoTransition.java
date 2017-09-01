// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionSet, Fade, ChangeBounds

public class AutoTransition extends TransitionSet
{

    public AutoTransition()
    {
        setOrdering(1);
        addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}
