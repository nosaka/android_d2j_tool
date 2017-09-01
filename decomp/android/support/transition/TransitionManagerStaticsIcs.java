// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionManagerStaticsImpl, TransitionManagerPort, TransitionIcs, SceneIcs, 
//            TransitionImpl, SceneImpl

class TransitionManagerStaticsIcs extends TransitionManagerStaticsImpl
{

    TransitionManagerStaticsIcs()
    {
    }

    public void beginDelayedTransition(ViewGroup viewgroup)
    {
        TransitionManagerPort.beginDelayedTransition(viewgroup);
    }

    public void beginDelayedTransition(ViewGroup viewgroup, TransitionImpl transitionimpl)
    {
        if(transitionimpl == null)
            transitionimpl = null;
        else
            transitionimpl = ((TransitionIcs)transitionimpl).mTransition;
        TransitionManagerPort.beginDelayedTransition(viewgroup, transitionimpl);
    }

    public void go(SceneImpl sceneimpl)
    {
        TransitionManagerPort.go(((SceneIcs)sceneimpl).mScene);
    }

    public void go(SceneImpl sceneimpl, TransitionImpl transitionimpl)
    {
        ScenePort sceneport = ((SceneIcs)sceneimpl).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionIcs)transitionimpl).mTransition;
        TransitionManagerPort.go(sceneport, sceneimpl);
    }
}
