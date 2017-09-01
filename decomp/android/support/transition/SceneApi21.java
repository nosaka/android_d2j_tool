// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneWrapper

class SceneApi21 extends SceneWrapper
{

    SceneApi21()
    {
    }

    public void enter()
    {
        mScene.enter();
    }

    public void init(ViewGroup viewgroup)
    {
        mScene = new Scene(viewgroup);
    }

    public void init(ViewGroup viewgroup, View view)
    {
        mScene = new Scene(viewgroup, view);
    }
}
