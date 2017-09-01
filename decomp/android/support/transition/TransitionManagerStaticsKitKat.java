// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.TransitionManager;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionManagerStaticsImpl, TransitionKitKat, SceneWrapper, TransitionImpl, 
//            SceneImpl

class TransitionManagerStaticsKitKat extends TransitionManagerStaticsImpl
{

    TransitionManagerStaticsKitKat()
    {
    }

    public void beginDelayedTransition(ViewGroup viewgroup)
    {
        TransitionManager.beginDelayedTransition(viewgroup);
    }

    public void beginDelayedTransition(ViewGroup viewgroup, TransitionImpl transitionimpl)
    {
        if(transitionimpl == null)
            transitionimpl = null;
        else
            transitionimpl = ((TransitionKitKat)transitionimpl).mTransition;
        TransitionManager.beginDelayedTransition(viewgroup, transitionimpl);
    }

    public void go(SceneImpl sceneimpl)
    {
        TransitionManager.go(((SceneWrapper)sceneimpl).mScene);
    }

    public void go(SceneImpl sceneimpl, TransitionImpl transitionimpl)
    {
        android.transition.Scene scene = ((SceneWrapper)sceneimpl).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionKitKat)transitionimpl).mTransition;
        TransitionManager.go(scene, sceneimpl);
    }
}
