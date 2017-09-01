// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneImpl, ScenePort

class SceneIcs extends SceneImpl
{

    SceneIcs()
    {
    }

    public void enter()
    {
        mScene.enter();
    }

    public void exit()
    {
        mScene.exit();
    }

    public ViewGroup getSceneRoot()
    {
        return mScene.getSceneRoot();
    }

    public void init(ViewGroup viewgroup)
    {
        mScene = new ScenePort(viewgroup);
    }

    public void init(ViewGroup viewgroup, View view)
    {
        mScene = new ScenePort(viewgroup, view);
    }

    public void setEnterAction(Runnable runnable)
    {
        mScene.setEnterAction(runnable);
    }

    public void setExitAction(Runnable runnable)
    {
        mScene.setExitAction(runnable);
    }

    ScenePort mScene;
}
