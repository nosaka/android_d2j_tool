// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            SceneImpl, TransitionImpl

abstract class TransitionManagerImpl
{

    TransitionManagerImpl()
    {
    }

    public abstract void setTransition(SceneImpl sceneimpl, SceneImpl sceneimpl1, TransitionImpl transitionimpl);

    public abstract void setTransition(SceneImpl sceneimpl, TransitionImpl transitionimpl);

    public abstract void transitionTo(SceneImpl sceneimpl);
}
