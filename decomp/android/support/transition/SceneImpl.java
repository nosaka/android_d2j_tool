// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.view.View;
import android.view.ViewGroup;

abstract class SceneImpl
{

    SceneImpl()
    {
    }

    public abstract void enter();

    public abstract void exit();

    public abstract ViewGroup getSceneRoot();

    public abstract void init(ViewGroup viewgroup);

    public abstract void init(ViewGroup viewgroup, View view);

    public abstract void setEnterAction(Runnable runnable);

    public abstract void setExitAction(Runnable runnable);
}
