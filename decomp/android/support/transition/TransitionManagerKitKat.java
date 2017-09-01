// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.TransitionManager;

// Referenced classes of package android.support.transition:
//            TransitionManagerImpl, SceneWrapper, TransitionKitKat, SceneImpl, 
//            TransitionImpl

class TransitionManagerKitKat extends TransitionManagerImpl
{

    TransitionManagerKitKat()
    {
    }

    public void setTransition(SceneImpl sceneimpl, SceneImpl sceneimpl1, TransitionImpl transitionimpl)
    {
        TransitionManager transitionmanager = mTransitionManager;
        android.transition.Scene scene = ((SceneWrapper)sceneimpl).mScene;
        sceneimpl1 = ((SceneWrapper)sceneimpl1).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionKitKat)transitionimpl).mTransition;
        transitionmanager.setTransition(scene, sceneimpl1, sceneimpl);
    }

    public void setTransition(SceneImpl sceneimpl, TransitionImpl transitionimpl)
    {
        TransitionManager transitionmanager = mTransitionManager;
        android.transition.Scene scene = ((SceneWrapper)sceneimpl).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionKitKat)transitionimpl).mTransition;
        transitionmanager.setTransition(scene, sceneimpl);
    }

    public void transitionTo(SceneImpl sceneimpl)
    {
        mTransitionManager.transitionTo(((SceneWrapper)sceneimpl).mScene);
    }

    private final TransitionManager mTransitionManager = new TransitionManager();
}
