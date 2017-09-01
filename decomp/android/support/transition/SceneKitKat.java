// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.*;

// Referenced classes of package android.support.transition:
//            SceneWrapper

class SceneKitKat extends SceneWrapper
{

    SceneKitKat()
    {
    }

    private void invokeEnterAction()
    {
        Runnable runnable;
        if(sEnterAction == null)
            try
            {
                sEnterAction = android/transition/Scene.getDeclaredField("mEnterAction");
                sEnterAction.setAccessible(true);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                throw new RuntimeException(nosuchfieldexception);
            }
        try
        {
            runnable = (Runnable)sEnterAction.get(mScene);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        if(runnable == null)
            break MISSING_BLOCK_LABEL_47;
        runnable.run();
    }

    private void updateCurrentScene(View view)
    {
        if(sSetCurrentScene == null)
            try
            {
                sSetCurrentScene = android/transition/Scene.getDeclaredMethod("setCurrentScene", new Class[] {
                    android/view/View, android/transition/Scene
                });
                sSetCurrentScene.setAccessible(true);
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new RuntimeException(view);
            }
        try
        {
            sSetCurrentScene.invoke(null, new Object[] {
                view, mScene
            });
            return;
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        throw new RuntimeException(view);
    }

    public void enter()
    {
        if(mLayout != null)
        {
            ViewGroup viewgroup = getSceneRoot();
            viewgroup.removeAllViews();
            viewgroup.addView(mLayout);
            invokeEnterAction();
            updateCurrentScene(viewgroup);
            return;
        } else
        {
            mScene.enter();
            return;
        }
    }

    public void init(ViewGroup viewgroup)
    {
        mScene = new Scene(viewgroup);
    }

    public void init(ViewGroup viewgroup, View view)
    {
        if(view instanceof ViewGroup)
        {
            mScene = new Scene(viewgroup, (ViewGroup)view);
            return;
        } else
        {
            mScene = new Scene(viewgroup);
            mLayout = view;
            return;
        }
    }

    private static Field sEnterAction;
    private static Method sSetCurrentScene;
    private View mLayout;
}
