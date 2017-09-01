// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;


// Referenced classes of package android.support.transition:
//            TransitionInterface

interface TransitionInterfaceListener
{

    public abstract void onTransitionCancel(TransitionInterface transitioninterface);

    public abstract void onTransitionEnd(TransitionInterface transitioninterface);

    public abstract void onTransitionPause(TransitionInterface transitioninterface);

    public abstract void onTransitionResume(TransitionInterface transitioninterface);

    public abstract void onTransitionStart(TransitionInterface transitioninterface);
}
