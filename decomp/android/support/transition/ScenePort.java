// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.content.Context;
import android.view.*;

final class ScenePort
{

    public ScenePort(ViewGroup viewgroup)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
    }

    private ScenePort(ViewGroup viewgroup, int i, Context context)
    {
        mLayoutId = -1;
        mContext = context;
        mSceneRoot = viewgroup;
        mLayoutId = i;
    }

    public ScenePort(ViewGroup viewgroup, View view)
    {
        mLayoutId = -1;
        mSceneRoot = viewgroup;
        mLayout = view;
    }

    static ScenePort getCurrentScene(View view)
    {
        return (ScenePort)view.getTag(R.id.transition_current_scene);
    }

    public static ScenePort getSceneForLayout(ViewGroup viewgroup, int i, Context context)
    {
        return new ScenePort(viewgroup, i, context);
    }

    static void setCurrentScene(View view, ScenePort sceneport)
    {
        view.setTag(R.id.transition_current_scene, sceneport);
    }

    public void enter()
    {
        if(mLayoutId > 0 || mLayout != null)
        {
            getSceneRoot().removeAllViews();
            if(mLayoutId > 0)
                LayoutInflater.from(mContext).inflate(mLayoutId, mSceneRoot);
            else
                mSceneRoot.addView(mLayout);
        }
        if(mEnterAction != null)
            mEnterAction.run();
        setCurrentScene(mSceneRoot, this);
    }

    public void exit()
    {
        if(getCurrentScene(mSceneRoot) == this && mExitAction != null)
            mExitAction.run();
    }

    public ViewGroup getSceneRoot()
    {
        return mSceneRoot;
    }

    boolean isCreatedFromLayoutResource()
    {
        return mLayoutId > 0;
    }

    public void setEnterAction(Runnable runnable)
    {
        mEnterAction = runnable;
    }

    public void setExitAction(Runnable runnable)
    {
        mExitAction = runnable;
    }

    private Context mContext;
    Runnable mEnterAction;
    Runnable mExitAction;
    private View mLayout;
    private int mLayoutId;
    private ViewGroup mSceneRoot;
}
