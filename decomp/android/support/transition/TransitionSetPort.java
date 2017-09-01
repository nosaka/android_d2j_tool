// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.transition:
//            TransitionPort, TransitionValues, TransitionValuesMaps

class TransitionSetPort extends TransitionPort
{
    static class TransitionSetListener extends TransitionPort.TransitionListenerAdapter
    {

        public void onTransitionEnd(TransitionPort transitionport)
        {
            TransitionSetPort transitionsetport = mTransitionSet;
            transitionsetport.mCurrentListeners = transitionsetport.mCurrentListeners - 1;
            if(mTransitionSet.mCurrentListeners == 0)
            {
                mTransitionSet.mStarted = false;
                mTransitionSet.end();
            }
            transitionport.removeListener(this);
        }

        public void onTransitionStart(TransitionPort transitionport)
        {
            if(!mTransitionSet.mStarted)
            {
                mTransitionSet.start();
                mTransitionSet.mStarted = true;
            }
        }

        TransitionSetPort mTransitionSet;

        TransitionSetListener(TransitionSetPort transitionsetport)
        {
            mTransitionSet = transitionsetport;
        }
    }


    public TransitionSetPort()
    {
        mTransitions = new ArrayList();
        mStarted = false;
        mPlayTogether = true;
    }

    private void setupStartEndListeners()
    {
        TransitionSetListener transitionsetlistener = new TransitionSetListener(this);
        for(Iterator iterator = mTransitions.iterator(); iterator.hasNext(); ((TransitionPort)iterator.next()).addListener(transitionsetlistener));
        mCurrentListeners = mTransitions.size();
    }

    public volatile TransitionPort addListener(TransitionPort.TransitionListener transitionlistener)
    {
        return addListener(transitionlistener);
    }

    public TransitionSetPort addListener(TransitionPort.TransitionListener transitionlistener)
    {
        return (TransitionSetPort)super.addListener(transitionlistener);
    }

    public volatile TransitionPort addTarget(int i)
    {
        return addTarget(i);
    }

    public volatile TransitionPort addTarget(View view)
    {
        return addTarget(view);
    }

    public TransitionSetPort addTarget(int i)
    {
        return (TransitionSetPort)super.addTarget(i);
    }

    public TransitionSetPort addTarget(View view)
    {
        return (TransitionSetPort)super.addTarget(view);
    }

    public TransitionSetPort addTransition(TransitionPort transitionport)
    {
        if(transitionport != null)
        {
            mTransitions.add(transitionport);
            transitionport.mParent = this;
            if(mDuration >= 0L)
                transitionport.setDuration(mDuration);
        }
        return this;
    }

    protected void cancel()
    {
        super.cancel();
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            ((TransitionPort)mTransitions.get(i)).cancel();

    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getId();
        if(isValidTarget(transitionvalues.view, i))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                TransitionPort transitionport = (TransitionPort)iterator.next();
                if(transitionport.isValidTarget(transitionvalues.view, i))
                    transitionport.captureEndValues(transitionvalues);
            } while(true);
        }
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getId();
        if(isValidTarget(transitionvalues.view, i))
        {
            Iterator iterator = mTransitions.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                TransitionPort transitionport = (TransitionPort)iterator.next();
                if(transitionport.isValidTarget(transitionvalues.view, i))
                    transitionport.captureStartValues(transitionvalues);
            } while(true);
        }
    }

    public volatile TransitionPort clone()
    {
        return clone();
    }

    public TransitionSetPort clone()
    {
        TransitionSetPort transitionsetport = (TransitionSetPort)super.clone();
        transitionsetport.mTransitions = new ArrayList();
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            transitionsetport.addTransition(((TransitionPort)mTransitions.get(i)).clone());

        return transitionsetport;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1)
    {
        for(Iterator iterator = mTransitions.iterator(); iterator.hasNext(); ((TransitionPort)iterator.next()).createAnimators(viewgroup, transitionvaluesmaps, transitionvaluesmaps1));
    }

    public int getOrdering()
    {
        return !mPlayTogether ? 1 : 0;
    }

    public void pause(View view)
    {
        super.pause(view);
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            ((TransitionPort)mTransitions.get(i)).pause(view);

    }

    public volatile TransitionPort removeListener(TransitionPort.TransitionListener transitionlistener)
    {
        return removeListener(transitionlistener);
    }

    public TransitionSetPort removeListener(TransitionPort.TransitionListener transitionlistener)
    {
        return (TransitionSetPort)super.removeListener(transitionlistener);
    }

    public volatile TransitionPort removeTarget(int i)
    {
        return removeTarget(i);
    }

    public volatile TransitionPort removeTarget(View view)
    {
        return removeTarget(view);
    }

    public TransitionSetPort removeTarget(int i)
    {
        return (TransitionSetPort)super.removeTarget(i);
    }

    public TransitionSetPort removeTarget(View view)
    {
        return (TransitionSetPort)super.removeTarget(view);
    }

    public TransitionSetPort removeTransition(TransitionPort transitionport)
    {
        mTransitions.remove(transitionport);
        transitionport.mParent = null;
        return this;
    }

    public void resume(View view)
    {
        super.resume(view);
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            ((TransitionPort)mTransitions.get(i)).resume(view);

    }

    protected void runAnimators()
    {
        if(!mTransitions.isEmpty()) goto _L2; else goto _L1
_L1:
        start();
        end();
_L4:
        return;
_L2:
        setupStartEndListeners();
        if(!mPlayTogether)
        {
            for(int i = 1; i < mTransitions.size(); i++)
                ((TransitionPort)mTransitions.get(i - 1)).addListener(new TransitionPort.TransitionListenerAdapter() {

                    public void onTransitionEnd(TransitionPort transitionport1)
                    {
                        nextTransition.runAnimators();
                        transitionport1.removeListener(this);
                    }

                    final TransitionSetPort this$0;
                    final TransitionPort val$nextTransition;

            
            {
                this$0 = TransitionSetPort.this;
                nextTransition = transitionport;
                super();
            }
                }
);

            TransitionPort transitionport = (TransitionPort)mTransitions.get(0);
            if(transitionport != null)
            {
                transitionport.runAnimators();
                return;
            }
        } else
        {
            Iterator iterator = mTransitions.iterator();
            while(iterator.hasNext()) 
                ((TransitionPort)iterator.next()).runAnimators();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    void setCanRemoveViews(boolean flag)
    {
        super.setCanRemoveViews(flag);
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            ((TransitionPort)mTransitions.get(i)).setCanRemoveViews(flag);

    }

    public volatile TransitionPort setDuration(long l)
    {
        return setDuration(l);
    }

    public TransitionSetPort setDuration(long l)
    {
        super.setDuration(l);
        if(mDuration >= 0L)
        {
            int j = mTransitions.size();
            for(int i = 0; i < j; i++)
                ((TransitionPort)mTransitions.get(i)).setDuration(l);

        }
        return this;
    }

    public volatile TransitionPort setInterpolator(TimeInterpolator timeinterpolator)
    {
        return setInterpolator(timeinterpolator);
    }

    public TransitionSetPort setInterpolator(TimeInterpolator timeinterpolator)
    {
        return (TransitionSetPort)super.setInterpolator(timeinterpolator);
    }

    public TransitionSetPort setOrdering(int i)
    {
        switch(i)
        {
        default:
            throw new AndroidRuntimeException((new StringBuilder()).append("Invalid parameter for TransitionSet ordering: ").append(i).toString());

        case 1: // '\001'
            mPlayTogether = false;
            return this;

        case 0: // '\0'
            mPlayTogether = true;
            return this;
        }
    }

    volatile TransitionPort setSceneRoot(ViewGroup viewgroup)
    {
        return setSceneRoot(viewgroup);
    }

    TransitionSetPort setSceneRoot(ViewGroup viewgroup)
    {
        super.setSceneRoot(viewgroup);
        int j = mTransitions.size();
        for(int i = 0; i < j; i++)
            ((TransitionPort)mTransitions.get(i)).setSceneRoot(viewgroup);

        return this;
    }

    public volatile TransitionPort setStartDelay(long l)
    {
        return setStartDelay(l);
    }

    public TransitionSetPort setStartDelay(long l)
    {
        return (TransitionSetPort)super.setStartDelay(l);
    }

    String toString(String s)
    {
        String s1 = super.toString(s);
        for(int i = 0; i < mTransitions.size(); i++)
            s1 = (new StringBuilder()).append(s1).append("\n").append(((TransitionPort)mTransitions.get(i)).toString((new StringBuilder()).append(s).append("  ").toString())).toString();

        return s1;
    }

    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList mTransitions;
}
