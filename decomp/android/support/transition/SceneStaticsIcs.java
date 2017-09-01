// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.content.Context;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneStaticsImpl, SceneIcs, ScenePort, SceneImpl

class SceneStaticsIcs extends SceneStaticsImpl
{

    SceneStaticsIcs()
    {
    }

    public SceneImpl getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        SceneIcs sceneics = new SceneIcs();
        sceneics.mScene = ScenePort.getSceneForLayout(viewgroup, i, context);
        return sceneics;
    }
}
