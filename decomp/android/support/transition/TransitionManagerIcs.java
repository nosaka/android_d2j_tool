// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionManagerImpl, TransitionManagerPort, SceneIcs, TransitionIcs, 
//            SceneImpl, TransitionImpl

class TransitionManagerIcs extends TransitionManagerImpl
{

    TransitionManagerIcs()
    {
    }

    public void setTransition(SceneImpl sceneimpl, SceneImpl sceneimpl1, TransitionImpl transitionimpl)
    {
        TransitionManagerPort transitionmanagerport = mTransitionManager;
        ScenePort sceneport = ((SceneIcs)sceneimpl).mScene;
        sceneimpl1 = ((SceneIcs)sceneimpl1).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionIcs)transitionimpl).mTransition;
        transitionmanagerport.setTransition(sceneport, sceneimpl1, sceneimpl);
    }

    public void setTransition(SceneImpl sceneimpl, TransitionImpl transitionimpl)
    {
        TransitionManagerPort transitionmanagerport = mTransitionManager;
        ScenePort sceneport = ((SceneIcs)sceneimpl).mScene;
        if(transitionimpl == null)
            sceneimpl = null;
        else
            sceneimpl = ((TransitionIcs)transitionimpl).mTransition;
        transitionmanagerport.setTransition(sceneport, sceneimpl);
    }

    public void transitionTo(SceneImpl sceneimpl)
    {
        mTransitionManager.transitionTo(((SceneIcs)sceneimpl).mScene);
    }

    private final TransitionManagerPort mTransitionManager = new TransitionManagerPort();
}
