// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.*;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.support.transition:
//            AutoTransitionPort, TransitionPort, ScenePort

class TransitionManagerPort
{
    private static class MultiListener
        implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
    {

        private void removeListeners()
        {
            mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public boolean onPreDraw()
        {
            ArrayList arraylist;
            ArrayList arraylist1;
            ArrayMap arraymap;
            removeListeners();
            TransitionManagerPort.sPendingTransitions.remove(mSceneRoot);
            arraymap = TransitionManagerPort.getRunningTransitions();
            arraylist1 = (ArrayList)arraymap.get(mSceneRoot);
            arraylist = null;
            if(arraylist1 != null) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = new ArrayList();
            arraymap.put(mSceneRoot, obj);
_L4:
            ((ArrayList) (obj)).add(mTransition);
            mTransition.addListener(arraymap. new TransitionPort.TransitionListenerAdapter() {

                public void onTransitionEnd(TransitionPort transitionport)
                {
                    ((ArrayList)runningTransitions.get(mSceneRoot)).remove(transitionport);
                }

                final MultiListener this$0;
                final ArrayMap val$runningTransitions;

            
            {
                this$0 = final_multilistener;
                runningTransitions = ArrayMap.this;
                super();
            }
            }
);
            mTransition.captureValues(mSceneRoot, false);
            if(arraylist != null)
                for(obj = arraylist.iterator(); ((Iterator) (obj)).hasNext(); ((TransitionPort)((Iterator) (obj)).next()).resume(mSceneRoot));
            break; /* Loop/switch isn't completed */
_L2:
            obj = arraylist1;
            if(arraylist1.size() > 0)
            {
                arraylist = new ArrayList(arraylist1);
                obj = arraylist1;
            }
            if(true) goto _L4; else goto _L3
_L3:
            mTransition.playTransition(mSceneRoot);
            return true;
        }

        public void onViewAttachedToWindow(View view)
        {
        }

        public void onViewDetachedFromWindow(View view)
        {
            removeListeners();
            TransitionManagerPort.sPendingTransitions.remove(mSceneRoot);
            view = (ArrayList)TransitionManagerPort.getRunningTransitions().get(mSceneRoot);
            if(view != null && view.size() > 0)
                for(view = view.iterator(); view.hasNext(); ((TransitionPort)view.next()).resume(mSceneRoot));
            mTransition.clearValues(true);
        }

        ViewGroup mSceneRoot;
        TransitionPort mTransition;

        MultiListener(TransitionPort transitionport, ViewGroup viewgroup)
        {
            mTransition = transitionport;
            mSceneRoot = viewgroup;
        }
    }


    TransitionManagerPort()
    {
        mSceneTransitions = new ArrayMap();
        mScenePairTransitions = new ArrayMap();
        mSceneNameTransitions = new ArrayMap();
        mNameSceneTransitions = new ArrayMap();
    }

    public static void beginDelayedTransition(ViewGroup viewgroup)
    {
        beginDelayedTransition(viewgroup, null);
    }

    public static void beginDelayedTransition(ViewGroup viewgroup, TransitionPort transitionport)
    {
        if(!sPendingTransitions.contains(viewgroup) && ViewCompat.isLaidOut(viewgroup))
        {
            sPendingTransitions.add(viewgroup);
            TransitionPort transitionport1 = transitionport;
            if(transitionport == null)
                transitionport1 = sDefaultTransition;
            transitionport = transitionport1.clone();
            sceneChangeSetup(viewgroup, transitionport);
            ScenePort.setCurrentScene(viewgroup, null);
            sceneChangeRunTransition(viewgroup, transitionport);
        }
    }

    private static void changeScene(ScenePort sceneport, TransitionPort transitionport)
    {
        ViewGroup viewgroup = sceneport.getSceneRoot();
        TransitionPort transitionport1 = null;
        if(transitionport != null)
        {
            transitionport1 = transitionport.clone();
            transitionport1.setSceneRoot(viewgroup);
        }
        transitionport = ScenePort.getCurrentScene(viewgroup);
        if(transitionport != null && transitionport.isCreatedFromLayoutResource())
            transitionport1.setCanRemoveViews(true);
        sceneChangeSetup(viewgroup, transitionport1);
        sceneport.enter();
        sceneChangeRunTransition(viewgroup, transitionport1);
    }

    public static TransitionPort getDefaultTransition()
    {
        return sDefaultTransition;
    }

    static ArrayMap getRunningTransitions()
    {
        WeakReference weakreference;
label0:
        {
            WeakReference weakreference1 = (WeakReference)sRunningTransitions.get();
            if(weakreference1 != null)
            {
                weakreference = weakreference1;
                if(weakreference1.get() != null)
                    break label0;
            }
            weakreference = new WeakReference(new ArrayMap());
            sRunningTransitions.set(weakreference);
        }
        return (ArrayMap)weakreference.get();
    }

    private TransitionPort getTransition(ScenePort sceneport)
    {
        Object obj = sceneport.getSceneRoot();
        if(obj != null)
        {
            obj = ScenePort.getCurrentScene(((View) (obj)));
            if(obj != null)
            {
                ArrayMap arraymap = (ArrayMap)mScenePairTransitions.get(sceneport);
                if(arraymap != null)
                {
                    obj = (TransitionPort)arraymap.get(obj);
                    if(obj != null)
                        return ((TransitionPort) (obj));
                }
            }
        }
        sceneport = (TransitionPort)mSceneTransitions.get(sceneport);
        if(sceneport != null)
            return sceneport;
        else
            return sDefaultTransition;
    }

    public static void go(ScenePort sceneport)
    {
        changeScene(sceneport, sDefaultTransition);
    }

    public static void go(ScenePort sceneport, TransitionPort transitionport)
    {
        changeScene(sceneport, transitionport);
    }

    private static void sceneChangeRunTransition(ViewGroup viewgroup, TransitionPort transitionport)
    {
        if(transitionport != null && viewgroup != null)
        {
            transitionport = new MultiListener(transitionport, viewgroup);
            viewgroup.addOnAttachStateChangeListener(transitionport);
            viewgroup.getViewTreeObserver().addOnPreDrawListener(transitionport);
        }
    }

    private static void sceneChangeSetup(ViewGroup viewgroup, TransitionPort transitionport)
    {
        Object obj = (ArrayList)getRunningTransitions().get(viewgroup);
        if(obj != null && ((ArrayList) (obj)).size() > 0)
            for(obj = ((ArrayList) (obj)).iterator(); ((Iterator) (obj)).hasNext(); ((TransitionPort)((Iterator) (obj)).next()).pause(viewgroup));
        if(transitionport != null)
            transitionport.captureValues(viewgroup, true);
        viewgroup = ScenePort.getCurrentScene(viewgroup);
        if(viewgroup != null)
            viewgroup.exit();
    }

    public TransitionPort getNamedTransition(ScenePort sceneport, String s)
    {
        sceneport = (ArrayMap)mSceneNameTransitions.get(sceneport);
        if(sceneport != null)
            return (TransitionPort)sceneport.get(s);
        else
            return null;
    }

    public TransitionPort getNamedTransition(String s, ScenePort sceneport)
    {
        s = (ArrayMap)mNameSceneTransitions.get(s);
        if(s != null)
            return (TransitionPort)s.get(sceneport);
        else
            return null;
    }

    public String[] getTargetSceneNames(ScenePort sceneport)
    {
        ArrayMap arraymap = (ArrayMap)mSceneNameTransitions.get(sceneport);
        if(arraymap != null) goto _L2; else goto _L1
_L1:
        sceneport = EMPTY_STRINGS;
_L4:
        return sceneport;
_L2:
        int j = arraymap.size();
        String as[] = new String[j];
        int i = 0;
        do
        {
            sceneport = as;
            if(i >= j)
                continue;
            as[i] = (String)arraymap.keyAt(i);
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setDefaultTransition(TransitionPort transitionport)
    {
        sDefaultTransition = transitionport;
    }

    public void setTransition(ScenePort sceneport, ScenePort sceneport1, TransitionPort transitionport)
    {
        ArrayMap arraymap1 = (ArrayMap)mScenePairTransitions.get(sceneport1);
        ArrayMap arraymap = arraymap1;
        if(arraymap1 == null)
        {
            arraymap = new ArrayMap();
            mScenePairTransitions.put(sceneport1, arraymap);
        }
        arraymap.put(sceneport, transitionport);
    }

    public void setTransition(ScenePort sceneport, TransitionPort transitionport)
    {
        mSceneTransitions.put(sceneport, transitionport);
    }

    public void setTransition(ScenePort sceneport, String s, TransitionPort transitionport)
    {
        ArrayMap arraymap1 = (ArrayMap)mSceneNameTransitions.get(sceneport);
        ArrayMap arraymap = arraymap1;
        if(arraymap1 == null)
        {
            arraymap = new ArrayMap();
            mSceneNameTransitions.put(sceneport, arraymap);
        }
        arraymap.put(s, transitionport);
    }

    public void setTransition(String s, ScenePort sceneport, TransitionPort transitionport)
    {
        ArrayMap arraymap1 = (ArrayMap)mNameSceneTransitions.get(s);
        ArrayMap arraymap = arraymap1;
        if(arraymap1 == null)
        {
            arraymap = new ArrayMap();
            mNameSceneTransitions.put(s, arraymap);
        }
        arraymap.put(sceneport, transitionport);
    }

    public void transitionTo(ScenePort sceneport)
    {
        changeScene(sceneport, getTransition(sceneport));
    }

    private static final String EMPTY_STRINGS[] = new String[0];
    private static String LOG_TAG = "TransitionManager";
    private static TransitionPort sDefaultTransition = new AutoTransitionPort();
    static ArrayList sPendingTransitions = new ArrayList();
    private static ThreadLocal sRunningTransitions = new ThreadLocal();
    ArrayMap mNameSceneTransitions;
    ArrayMap mSceneNameTransitions;
    ArrayMap mScenePairTransitions;
    ArrayMap mSceneTransitions;

}
