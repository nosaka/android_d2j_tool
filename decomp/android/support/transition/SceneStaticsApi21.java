// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.content.Context;
import android.transition.Scene;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneStaticsImpl, SceneApi21, SceneImpl

class SceneStaticsApi21 extends SceneStaticsImpl
{

    SceneStaticsApi21()
    {
    }

    public SceneImpl getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        SceneApi21 sceneapi21 = new SceneApi21();
        sceneapi21.mScene = Scene.getSceneForLayout(viewgroup, i, context);
        return sceneapi21;
    }
}
