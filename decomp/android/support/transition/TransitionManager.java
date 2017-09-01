// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            TransitionManagerStaticsIcs, TransitionManagerStaticsKitKat, TransitionManagerIcs, TransitionManagerKitKat, 
//            TransitionManagerStaticsImpl, Transition, Scene, TransitionManagerImpl

public class TransitionManager
{

    public TransitionManager()
    {
        if(android.os.Build.VERSION.SDK_INT < 19)
        {
            mImpl = new TransitionManagerIcs();
            return;
        } else
        {
            mImpl = new TransitionManagerKitKat();
            return;
        }
    }

    public static void beginDelayedTransition(ViewGroup viewgroup)
    {
        sImpl.beginDelayedTransition(viewgroup);
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, Transition transition)
    {
        TransitionManagerStaticsImpl transitionmanagerstaticsimpl = sImpl;
        if(transition == null)
            transition = null;
        else
            transition = transition.mImpl;
        transitionmanagerstaticsimpl.beginDelayedTransition(viewgroup, transition);
    }

    public static void go(Scene scene)
    {
        sImpl.go(scene.mImpl);
    }

    public static void go(Scene scene, Transition transition)
    {
        TransitionManagerStaticsImpl transitionmanagerstaticsimpl = sImpl;
        SceneImpl sceneimpl = scene.mImpl;
        if(transition == null)
            scene = null;
        else
            scene = transition.mImpl;
        transitionmanagerstaticsimpl.go(sceneimpl, scene);
    }

    public void setTransition(Scene scene, Scene scene1, Transition transition)
    {
        TransitionManagerImpl transitionmanagerimpl = mImpl;
        SceneImpl sceneimpl = scene.mImpl;
        scene1 = scene1.mImpl;
        if(transition == null)
            scene = null;
        else
            scene = transition.mImpl;
        transitionmanagerimpl.setTransition(sceneimpl, scene1, scene);
    }

    public void setTransition(Scene scene, Transition transition)
    {
        TransitionManagerImpl transitionmanagerimpl = mImpl;
        SceneImpl sceneimpl = scene.mImpl;
        if(transition == null)
            scene = null;
        else
            scene = transition.mImpl;
        transitionmanagerimpl.setTransition(sceneimpl, scene);
    }

    public void transitionTo(Scene scene)
    {
        mImpl.transitionTo(scene.mImpl);
    }

    private static TransitionManagerStaticsImpl sImpl;
    private TransitionManagerImpl mImpl;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT < 19)
            sImpl = new TransitionManagerStaticsIcs();
        else
            sImpl = new TransitionManagerStaticsKitKat();
    }
}
