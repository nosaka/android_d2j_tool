// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

// Referenced classes of package android.support.transition:
//            TransitionImpl, TransitionValues, TransitionInterface, TransitionInterfaceListener

class TransitionKitKat extends TransitionImpl
{
    private class CompatListener
        implements android.transition.Transition.TransitionListener
    {

        void addListener(TransitionInterfaceListener transitioninterfacelistener)
        {
            mListeners.add(transitioninterfacelistener);
        }

        boolean isEmpty()
        {
            return mListeners.isEmpty();
        }

        public void onTransitionCancel(Transition transition)
        {
            for(transition = mListeners.iterator(); transition.hasNext(); ((TransitionInterfaceListener)transition.next()).onTransitionCancel(mExternalTransition));
        }

        public void onTransitionEnd(Transition transition)
        {
            for(transition = mListeners.iterator(); transition.hasNext(); ((TransitionInterfaceListener)transition.next()).onTransitionEnd(mExternalTransition));
        }

        public void onTransitionPause(Transition transition)
        {
            for(transition = mListeners.iterator(); transition.hasNext(); ((TransitionInterfaceListener)transition.next()).onTransitionPause(mExternalTransition));
        }

        public void onTransitionResume(Transition transition)
        {
            for(transition = mListeners.iterator(); transition.hasNext(); ((TransitionInterfaceListener)transition.next()).onTransitionResume(mExternalTransition));
        }

        public void onTransitionStart(Transition transition)
        {
            for(transition = mListeners.iterator(); transition.hasNext(); ((TransitionInterfaceListener)transition.next()).onTransitionStart(mExternalTransition));
        }

        void removeListener(TransitionInterfaceListener transitioninterfacelistener)
        {
            mListeners.remove(transitioninterfacelistener);
        }

        private final ArrayList mListeners = new ArrayList();
        final TransitionKitKat this$0;

        CompatListener()
        {
            this$0 = TransitionKitKat.this;
            super();
        }
    }

    private static class TransitionWrapper extends Transition
    {

        public void captureEndValues(TransitionValues transitionvalues)
        {
            TransitionKitKat.wrapCaptureEndValues(mTransition, transitionvalues);
        }

        public void captureStartValues(TransitionValues transitionvalues)
        {
            TransitionKitKat.wrapCaptureStartValues(mTransition, transitionvalues);
        }

        public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
        {
            return mTransition.createAnimator(viewgroup, TransitionKitKat.convertToSupport(transitionvalues), TransitionKitKat.convertToSupport(transitionvalues1));
        }

        private TransitionInterface mTransition;

        public TransitionWrapper(TransitionInterface transitioninterface)
        {
            mTransition = transitioninterface;
        }
    }


    TransitionKitKat()
    {
    }

    static TransitionValues convertToPlatform(android.support.transition.TransitionValues transitionvalues)
    {
        if(transitionvalues == null)
        {
            return null;
        } else
        {
            TransitionValues transitionvalues1 = new TransitionValues();
            copyValues(transitionvalues, transitionvalues1);
            return transitionvalues1;
        }
    }

    static android.support.transition.TransitionValues convertToSupport(TransitionValues transitionvalues)
    {
        if(transitionvalues == null)
        {
            return null;
        } else
        {
            android.support.transition.TransitionValues transitionvalues1 = new android.support.transition.TransitionValues();
            copyValues(transitionvalues, transitionvalues1);
            return transitionvalues1;
        }
    }

    static void copyValues(android.support.transition.TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if(transitionvalues != null)
        {
            transitionvalues1.view = transitionvalues.view;
            if(transitionvalues.values.size() > 0)
            {
                transitionvalues1.values.putAll(transitionvalues.values);
                return;
            }
        }
    }

    static void copyValues(TransitionValues transitionvalues, android.support.transition.TransitionValues transitionvalues1)
    {
        if(transitionvalues != null)
        {
            transitionvalues1.view = transitionvalues.view;
            if(transitionvalues.values.size() > 0)
            {
                transitionvalues1.values.putAll(transitionvalues.values);
                return;
            }
        }
    }

    static void wrapCaptureEndValues(TransitionInterface transitioninterface, TransitionValues transitionvalues)
    {
        android.support.transition.TransitionValues transitionvalues1 = new android.support.transition.TransitionValues();
        copyValues(transitionvalues, transitionvalues1);
        transitioninterface.captureEndValues(transitionvalues1);
        copyValues(transitionvalues1, transitionvalues);
    }

    static void wrapCaptureStartValues(TransitionInterface transitioninterface, TransitionValues transitionvalues)
    {
        android.support.transition.TransitionValues transitionvalues1 = new android.support.transition.TransitionValues();
        copyValues(transitionvalues, transitionvalues1);
        transitioninterface.captureStartValues(transitionvalues1);
        copyValues(transitionvalues1, transitionvalues);
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

    public void captureEndValues(android.support.transition.TransitionValues transitionvalues)
    {
        TransitionValues transitionvalues1 = new TransitionValues();
        copyValues(transitionvalues, transitionvalues1);
        mTransition.captureEndValues(transitionvalues1);
        copyValues(transitionvalues1, transitionvalues);
    }

    public void captureStartValues(android.support.transition.TransitionValues transitionvalues)
    {
        TransitionValues transitionvalues1 = new TransitionValues();
        copyValues(transitionvalues, transitionvalues1);
        mTransition.captureStartValues(transitionvalues1);
        copyValues(transitionvalues1, transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, android.support.transition.TransitionValues transitionvalues, android.support.transition.TransitionValues transitionvalues1)
    {
        if(transitionvalues != null)
        {
            TransitionValues transitionvalues2 = new TransitionValues();
            copyValues(transitionvalues, transitionvalues2);
            transitionvalues = transitionvalues2;
        } else
        {
            transitionvalues = null;
        }
        if(transitionvalues1 != null)
        {
            TransitionValues transitionvalues3 = new TransitionValues();
            copyValues(transitionvalues1, transitionvalues3);
            transitionvalues1 = transitionvalues3;
        } else
        {
            transitionvalues1 = null;
        }
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

    public android.support.transition.TransitionValues getTransitionValues(View view, boolean flag)
    {
        android.support.transition.TransitionValues transitionvalues = new android.support.transition.TransitionValues();
        copyValues(mTransition.getTransitionValues(view, flag), transitionvalues);
        return transitionvalues;
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
            mTransition = (Transition)obj;
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
        if(i > 0)
            getTargetIds().remove(Integer.valueOf(i));
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
    Transition mTransition;
}
