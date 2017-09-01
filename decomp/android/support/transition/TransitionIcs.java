// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

// Referenced classes of package android.support.transition:
//            TransitionImpl, TransitionPort, TransitionInterface, TransitionInterfaceListener, 
//            TransitionValues

class TransitionIcs extends TransitionImpl
{
    private class CompatListener
        implements TransitionPort.TransitionListener
    {

        public void addListener(TransitionInterfaceListener transitioninterfacelistener)
        {
            mListeners.add(transitioninterfacelistener);
        }

        public boolean isEmpty()
        {
            return mListeners.isEmpty();
        }

        public void onTransitionCancel(TransitionPort transitionport)
        {
            for(transitionport = mListeners.iterator(); transitionport.hasNext(); ((TransitionInterfaceListener)transitionport.next()).onTransitionCancel(mExternalTransition));
        }

        public void onTransitionEnd(TransitionPort transitionport)
        {
            for(transitionport = mListeners.iterator(); transitionport.hasNext(); ((TransitionInterfaceListener)transitionport.next()).onTransitionEnd(mExternalTransition));
        }

        public void onTransitionPause(TransitionPort transitionport)
        {
            for(transitionport = mListeners.iterator(); transitionport.hasNext(); ((TransitionInterfaceListener)transitionport.next()).onTransitionPause(mExternalTransition));
        }

        public void onTransitionResume(TransitionPort transitionport)
        {
            for(transitionport = mListeners.iterator(); transitionport.hasNext(); ((TransitionInterfaceListener)transitionport.next()).onTransitionResume(mExternalTransition));
        }

        public void onTransitionStart(TransitionPort transitionport)
        {
            for(transitionport = mListeners.iterator(); transitionport.hasNext(); ((TransitionInterfaceListener)transitionport.next()).onTransitionStart(mExternalTransition));
        }

        public void removeListener(TransitionInterfaceListener transitioninterfacelistener)
        {
            mListeners.remove(transitioninterfacelistener);
        }

        private final ArrayList mListeners = new ArrayList();
        final TransitionIcs this$0;

        CompatListener()
        {
            this$0 = TransitionIcs.this;
            super();
        }
    }

    private static class TransitionWrapper extends TransitionPort
    {

        public void captureEndValues(TransitionValues transitionvalues)
        {
            mTransition.captureEndValues(transitionvalues);
        }

        public void captureStartValues(TransitionValues transitionvalues)
        {
            mTransition.captureStartValues(transitionvalues);
        }

        public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            return mTransition.createAnimator(viewgroup, transitionvalues, transitionvalues1);
        }

        private TransitionInterface mTransition;

        public TransitionWrapper(TransitionInterface transitioninterface)
        {
            mTransition = transitioninterface;
        }
    }


    TransitionIcs()
    {
    }

    public TransitionImpl addListener(TransitionInterfaceListener transitioninterfacelistener)
    {
        if(mCompatListener == null)
        {
            mCompatListener = new CompatListener();
            mTransition.addListener(mCompatListener);
        }
        mCompatListener.addListener(transitioninterfacelistener);
        return this;
    }

    public TransitionImpl addTarget(int i)
    {
        mTransition.addTarget(i);
        return this;
    }

    public TransitionImpl addTarget(View view)
    {
        mTransition.addTarget(view);
        return this;
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        mTransition.captureEndValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        mTransition.captureStartValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return mTransition.createAnimator(viewgroup, transitionvalues, transitionvalues1);
    }

    public TransitionImpl excludeChildren(int i, boolean flag)
    {
        mTransition.excludeChildren(i, flag);
        return this;
    }

    public TransitionImpl excludeChildren(View view, boolean flag)
    {
        mTransition.excludeChildren(view, flag);
        return this;
    }

    public TransitionImpl excludeChildren(Class class1, boolean flag)
    {
        mTransition.excludeChildren(class1, flag);
        return this;
    }

    public TransitionImpl excludeTarget(int i, boolean flag)
    {
        mTransition.excludeTarget(i, flag);
        return this;
    }

    public TransitionImpl excludeTarget(View view, boolean flag)
    {
        mTransition.excludeTarget(view, flag);
        return this;
    }

    public TransitionImpl excludeTarget(Class class1, boolean flag)
    {
        mTransition.excludeTarget(class1, flag);
        return this;
    }

    public long getDuration()
    {
        return mTransition.getDuration();
    }

    public TimeInterpolator getInterpolator()
    {
        return mTransition.getInterpolator();
    }

    public String getName()
    {
        return mTransition.getName();
    }

    public long getStartDelay()
    {
        return mTransition.getStartDelay();
    }

    public List getTargetIds()
    {
        return mTransition.getTargetIds();
    }

    public List getTargets()
    {
        return mTransition.getTargets();
    }

    public String[] getTransitionProperties()
    {
        return mTransition.getTransitionProperties();
    }

    public TransitionValues getTransitionValues(View view, boolean flag)
    {
        return mTransition.getTransitionValues(view, flag);
    }

    public void init(TransitionInterface transitioninterface, Object obj)
    {
        mExternalTransition = transitioninterface;
        if(obj == null)
        {
            mTransition = new TransitionWrapper(transitioninterface);
            return;
        } else
        {
            mTransition = (TransitionPort)obj;
            return;
        }
    }

    public TransitionImpl removeListener(TransitionInterfaceListener transitioninterfacelistener)
    {
        if(mCompatListener != null)
        {
            mCompatListener.removeListener(transitioninterfacelistener);
            if(mCompatListener.isEmpty())
            {
                mTransition.removeListener(mCompatListener);
                mCompatListener = null;
                return this;
            }
        }
        return this;
    }

    public TransitionImpl removeTarget(int i)
    {
        mTransition.removeTarget(i);
        return this;
    }

    public TransitionImpl removeTarget(View view)
    {
        mTransition.removeTarget(view);
        return this;
    }

    public TransitionImpl setDuration(long l)
    {
        mTransition.setDuration(l);
        return this;
    }

    public TransitionImpl setInterpolator(TimeInterpolator timeinterpolator)
    {
        mTransition.setInterpolator(timeinterpolator);
        return this;
    }

    public TransitionImpl setStartDelay(long l)
    {
        mTransition.setStartDelay(l);
        return this;
    }

    public String toString()
    {
        return mTransition.toString();
    }

    private CompatListener mCompatListener;
    TransitionInterface mExternalTransition;
    TransitionPort mTransition;
}
