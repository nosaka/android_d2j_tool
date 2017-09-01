// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.transition:
//            SceneStaticsApi21, SceneStaticsKitKat, SceneStaticsIcs, SceneImpl, 
//            SceneApi21, SceneKitKat, SceneIcs, SceneStaticsImpl

public class Scene
{

    private Scene(SceneImpl sceneimpl)
    {
        mImpl = sceneimpl;
    }

    public Scene(ViewGroup viewgroup)
    {
        mImpl = createSceneImpl();
        mImpl.init(viewgroup);
    }

    public Scene(ViewGroup viewgroup, View view)
    {
        mImpl = createSceneImpl();
        mImpl.init(viewgroup, view);
    }

    private SceneImpl createSceneImpl()
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new SceneApi21();
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return new SceneKitKat();
        else
            return new SceneIcs();
    }

    public static Scene getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        Object obj = (SparseArray)viewgroup.getTag(R.id.transition_scene_layoutid_cache);
        SparseArray sparsearray = ((SparseArray) (obj));
        if(obj == null)
        {
            sparsearray = new SparseArray();
            viewgroup.setTag(R.id.transition_scene_layoutid_cache, sparsearray);
        }
        obj = (Scene)sparsearray.get(i);
        if(obj != null)
        {
            return ((Scene) (obj));
        } else
        {
            viewgroup = new Scene(sImpl.getSceneForLayout(viewgroup, i, context));
            sparsearray.put(i, viewgroup);
            return viewgroup;
        }
    }

    public void enter()
    {
        mImpl.enter();
    }

    public void exit()
    {
        mImpl.exit();
    }

    public ViewGroup getSceneRoot()
    {
        return mImpl.getSceneRoot();
    }

    public void setEnterAction(Runnable runnable)
    {
        mImpl.setEnterAction(runnable);
    }

    public void setExitAction(Runnable runnable)
    {
        mImpl.setExitAction(runnable);
    }

    private static SceneStaticsImpl sImpl;
    SceneImpl mImpl;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            sImpl = new SceneStaticsApi21();
        else
        if(android.os.Build.VERSION.SDK_INT >= 19)
            sImpl = new SceneStaticsKitKat();
        else
            sImpl = new SceneStaticsIcs();
    }
}
