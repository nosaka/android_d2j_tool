// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionImpl, SceneImpl

abstract class TransitionManagerStaticsImpl
{

    TransitionManagerStaticsImpl()
    {
    }

    public abstract void beginDelayedTransition(ViewGroup viewgroup);

    public abstract void beginDelayedTransition(ViewGroup viewgroup, TransitionImpl transitionimpl);

    public abstract void go(SceneImpl sceneimpl);

    public abstract void go(SceneImpl sceneimpl, TransitionImpl transitionimpl);
}
