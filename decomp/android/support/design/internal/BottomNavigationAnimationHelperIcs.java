// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.internal;

import android.support.transition.*;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.ViewGroup;

// Referenced classes of package android.support.design.internal:
//            BottomNavigationAnimationHelperBase, TextScale

class BottomNavigationAnimationHelperIcs extends BottomNavigationAnimationHelperBase
{

    BottomNavigationAnimationHelperIcs()
    {
        mSet.setOrdering(0);
        mSet.setDuration(115L);
        mSet.setInterpolator(new FastOutSlowInInterpolator());
        TextScale textscale = new TextScale();
        mSet.addTransition(textscale);
    }

    void beginDelayedTransition(ViewGroup viewgroup)
    {
        TransitionManager.beginDelayedTransition(viewgroup, mSet);
    }

    private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
    private final TransitionSet mSet = new AutoTransition();
}
