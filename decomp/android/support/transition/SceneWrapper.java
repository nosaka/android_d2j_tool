// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.Scene;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneImpl

abstract class SceneWrapper extends SceneImpl
{

    SceneWrapper()
    {
    }

    public void exit()
    {
        mScene.exit();
    }

    public ViewGroup getSceneRoot()
    {
        return mScene.getSceneRoot();
    }

    public void setEnterAction(Runnable runnable)
    {
        mScene.setEnterAction(runnable);
    }

    public void setExitAction(Runnable runnable)
    {
        mScene.setExitAction(runnable);
    }

    Scene mScene;
}
