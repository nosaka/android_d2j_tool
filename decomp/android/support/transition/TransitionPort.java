// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.*;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.*;

// Referenced classes of package android.support.transition:
//            TransitionValuesMaps, TransitionValues, WindowIdPort, TransitionSetPort

abstract class TransitionPort
    implements Cloneable
{
    private static class AnimationInfo
    {

        String name;
        TransitionValues values;
        View view;
        WindowIdPort windowId;

        AnimationInfo(View view1, String s, WindowIdPort windowidport, TransitionValues transitionvalues)
        {
            view = view1;
            name = s;
            values = transitionvalues;
            windowId = windowidport;
        }
    }

    private static class ArrayListManager
    {

        static ArrayList add(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if(arraylist == null)
                arraylist1 = new ArrayList();
            if(!arraylist1.contains(obj))
                arraylist1.add(obj);
            return arraylist1;
        }

        static ArrayList remove(ArrayList arraylist, Object obj)
        {
            ArrayList arraylist1 = arraylist;
            if(arraylist != null)
            {
                arraylist.remove(obj);
                arraylist1 = arraylist;
                if(arraylist.isEmpty())
                    arraylist1 = null;
            }
            return arraylist1;
        }

        private ArrayListManager()
        {
        }
    }

    public static interface TransitionListener
    {

        public abstract void onTransitionCancel(TransitionPort transitionport);

        public abstract void onTransitionEnd(TransitionPort transitionport);

        public abstract void onTransitionPause(TransitionPort transitionport);

        public abstract void onTransitionResume(TransitionPort transitionport);

        public abstract void onTransitionStart(TransitionPort transitionport);
    }

    public static class TransitionListenerAdapter
        implements TransitionListener
    {

        public void onTransitionCancel(TransitionPort transitionport)
        {
        }

        public void onTransitionEnd(TransitionPort transitionport)
        {
        }

        public void onTransitionPause(TransitionPort transitionport)
        {
        }

        public void onTransitionResume(TransitionPort transitionport)
        {
        }

        public void onTransitionStart(TransitionPort transitionport)
        {
        }

        public TransitionListenerAdapter()
        {
        }
    }


    public TransitionPort()
    {
        mStartDelay = -1L;
        mDuration = -1L;
        mInterpolator = null;
        mTargetIds = new ArrayList();
        mTargets = new ArrayList();
        mTargetIdExcludes = null;
        mTargetExcludes = null;
        mTargetTypeExcludes = null;
        mTargetIdChildExcludes = null;
        mTargetChildExcludes = null;
        mTargetTypeChildExcludes = null;
        mParent = null;
        mSceneRoot = null;
        mCanRemoveViews = false;
        mNumInstances = 0;
        mPaused = false;
        mListeners = null;
        mAnimators = new ArrayList();
        mName = getClass().getName();
        mStartValues = new TransitionValuesMaps();
        mEndValues = new TransitionValuesMaps();
        mCurrentAnimators = new ArrayList();
        mEnded = false;
    }

    private void captureHierarchy(View view, boolean flag)
    {
        if(view != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int k;
        long l1;
        TransitionValues transitionvalues;
        i = 0;
        if(view.getParent() instanceof ListView)
            i = 1;
        if(i != 0 && !((ListView)view.getParent()).getAdapter().hasStableIds())
            continue; /* Loop/switch isn't completed */
        k = -1;
        l1 = -1L;
        if(i == 0)
        {
            k = view.getId();
        } else
        {
            ListView listview = (ListView)view.getParent();
            l1 = listview.getItemIdAtPosition(listview.getPositionForView(view));
        }
        if(mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf(k)) || mTargetExcludes != null && mTargetExcludes.contains(view))
            continue; /* Loop/switch isn't completed */
        if(mTargetTypeExcludes != null && view != null)
        {
            int i1 = mTargetTypeExcludes.size();
            for(int l = 0; l < i1; l++)
                if(((Class)mTargetTypeExcludes.get(l)).isInstance(view))
                    continue; /* Loop/switch isn't completed */

        }
        transitionvalues = new TransitionValues();
        transitionvalues.view = view;
        if(flag)
            captureStartValues(transitionvalues);
        else
            captureEndValues(transitionvalues);
        if(!flag) goto _L4; else goto _L3
_L3:
        if(i == 0)
        {
            mStartValues.viewValues.put(view, transitionvalues);
            if(k >= 0)
                mStartValues.idValues.put(k, transitionvalues);
        } else
        {
            mStartValues.itemIdValues.put(l1, transitionvalues);
        }
_L6:
        if(!(view instanceof ViewGroup) || mTargetIdChildExcludes != null && mTargetIdChildExcludes.contains(Integer.valueOf(k)) || mTargetChildExcludes != null && mTargetChildExcludes.contains(view))
            continue; /* Loop/switch isn't completed */
        if(mTargetTypeChildExcludes != null && view != null)
        {
            k = mTargetTypeChildExcludes.size();
            for(i = 0; i < k; i++)
                if(((Class)mTargetTypeChildExcludes.get(i)).isInstance(view))
                    continue; /* Loop/switch isn't completed */

        }
        break; /* Loop/switch isn't completed */
_L4:
        if(i == 0)
        {
            mEndValues.viewValues.put(view, transitionvalues);
            if(k >= 0)
                mEndValues.idValues.put(k, transitionvalues);
        } else
        {
            mEndValues.itemIdValues.put(l1, transitionvalues);
        }
        if(true) goto _L6; else goto _L5
_L5:
        view = (ViewGroup)view;
        int j = 0;
        while(j < view.getChildCount()) 
        {
            captureHierarchy(view.getChildAt(j), flag);
            j++;
        }
        if(true) goto _L1; else goto _L7
_L7:
    }

    private ArrayList excludeId(ArrayList arraylist, int i, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if(i > 0)
            {
                if(!flag)
                    break label0;
                arraylist1 = ArrayListManager.add(arraylist, Integer.valueOf(i));
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, Integer.valueOf(i));
    }

    private ArrayList excludeType(ArrayList arraylist, Class class1, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if(class1 != null)
            {
                if(!flag)
                    break label0;
                arraylist1 = ArrayListManager.add(arraylist, class1);
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, class1);
    }

    private ArrayList excludeView(ArrayList arraylist, View view, boolean flag)
    {
label0:
        {
            ArrayList arraylist1 = arraylist;
            if(view != null)
            {
                if(!flag)
                    break label0;
                arraylist1 = ArrayListManager.add(arraylist, view);
            }
            return arraylist1;
        }
        return ArrayListManager.remove(arraylist, view);
    }

    private static ArrayMap getRunningAnimators()
    {
        ArrayMap arraymap1 = (ArrayMap)sRunningAnimators.get();
        ArrayMap arraymap = arraymap1;
        if(arraymap1 == null)
        {
            arraymap = new ArrayMap();
            sRunningAnimators.set(arraymap);
        }
        return arraymap;
    }

    private void runAnimator(Animator animator, final ArrayMap runningAnimators)
    {
        if(animator != null)
        {
            animator.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator1)
                {
                    runningAnimators.remove(animator1);
                    mCurrentAnimators.remove(animator1);
                }

                public void onAnimationStart(Animator animator1)
                {
                    mCurrentAnimators.add(animator1);
                }

                final TransitionPort this$0;
                final ArrayMap val$runningAnimators;

            
            {
                this$0 = TransitionPort.this;
                runningAnimators = arraymap;
                super();
            }
            }
);
            animate(animator);
        }
    }

    public TransitionPort addListener(TransitionListener transitionlistener)
    {
        if(mListeners == null)
            mListeners = new ArrayList();
        mListeners.add(transitionlistener);
        return this;
    }

    public TransitionPort addTarget(int i)
    {
        if(i > 0)
            mTargetIds.add(Integer.valueOf(i));
        return this;
    }

    public TransitionPort addTarget(View view)
    {
        mTargets.add(view);
        return this;
    }

    protected void animate(Animator animator)
    {
        if(animator == null)
        {
            end();
            return;
        }
        if(getDuration() >= 0L)
            animator.setDuration(getDuration());
        if(getStartDelay() >= 0L)
            animator.setStartDelay(getStartDelay());
        if(getInterpolator() != null)
            animator.setInterpolator(getInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator1)
            {
                end();
                animator1.removeListener(this);
            }

            final TransitionPort this$0;

            
            {
                this$0 = TransitionPort.this;
                super();
            }
        }
);
        animator.start();
    }

    protected void cancel()
    {
        for(int i = mCurrentAnimators.size() - 1; i >= 0; i--)
            ((Animator)mCurrentAnimators.get(i)).cancel();

        if(mListeners != null && mListeners.size() > 0)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int k = arraylist.size();
            for(int j = 0; j < k; j++)
                ((TransitionListener)arraylist.get(j)).onTransitionCancel(this);

        }
    }

    public abstract void captureEndValues(TransitionValues transitionvalues);

    public abstract void captureStartValues(TransitionValues transitionvalues);

    void captureValues(ViewGroup viewgroup, boolean flag)
    {
        clearValues(flag);
        if(mTargetIds.size() > 0 || mTargets.size() > 0)
        {
            if(mTargetIds.size() > 0)
            {
                int i = 0;
                while(i < mTargetIds.size()) 
                {
                    int k = ((Integer)mTargetIds.get(i)).intValue();
                    View view = viewgroup.findViewById(k);
                    if(view != null)
                    {
                        TransitionValues transitionvalues1 = new TransitionValues();
                        transitionvalues1.view = view;
                        if(flag)
                            captureStartValues(transitionvalues1);
                        else
                            captureEndValues(transitionvalues1);
                        if(flag)
                        {
                            mStartValues.viewValues.put(view, transitionvalues1);
                            if(k >= 0)
                                mStartValues.idValues.put(k, transitionvalues1);
                        } else
                        {
                            mEndValues.viewValues.put(view, transitionvalues1);
                            if(k >= 0)
                                mEndValues.idValues.put(k, transitionvalues1);
                        }
                    }
                    i++;
                }
            }
            if(mTargets.size() > 0)
            {
                int j = 0;
                while(j < mTargets.size()) 
                {
                    viewgroup = (View)mTargets.get(j);
                    if(viewgroup != null)
                    {
                        TransitionValues transitionvalues = new TransitionValues();
                        transitionvalues.view = viewgroup;
                        if(flag)
                            captureStartValues(transitionvalues);
                        else
                            captureEndValues(transitionvalues);
                        if(flag)
                            mStartValues.viewValues.put(viewgroup, transitionvalues);
                        else
                            mEndValues.viewValues.put(viewgroup, transitionvalues);
                    }
                    j++;
                }
            }
        } else
        {
            captureHierarchy(viewgroup, flag);
        }
    }

    void clearValues(boolean flag)
    {
        if(flag)
        {
            mStartValues.viewValues.clear();
            mStartValues.idValues.clear();
            mStartValues.itemIdValues.clear();
            return;
        } else
        {
            mEndValues.viewValues.clear();
            mEndValues.idValues.clear();
            mEndValues.itemIdValues.clear();
            return;
        }
    }

    public TransitionPort clone()
    {
        TransitionPort transitionport = null;
        TransitionPort transitionport1;
        try
        {
            transitionport1 = (TransitionPort)super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            return transitionport;
        }
        transitionport = transitionport1;
        transitionport1.mAnimators = new ArrayList();
        transitionport = transitionport1;
        transitionport1.mStartValues = new TransitionValuesMaps();
        transitionport = transitionport1;
        transitionport1.mEndValues = new TransitionValuesMaps();
        return transitionport1;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1)
    {
        Object obj2;
        ArrayList arraylist;
        ArrayList arraylist1;
        Object obj3;
        ArrayMap arraymap;
        Iterator iterator1;
        arraymap = new ArrayMap(transitionvaluesmaps1.viewValues);
        obj3 = new SparseArray(transitionvaluesmaps1.idValues.size());
        for(int i = 0; i < transitionvaluesmaps1.idValues.size(); i++)
            ((SparseArray) (obj3)).put(transitionvaluesmaps1.idValues.keyAt(i), transitionvaluesmaps1.idValues.valueAt(i));

        obj2 = new LongSparseArray(transitionvaluesmaps1.itemIdValues.size());
        for(int j = 0; j < transitionvaluesmaps1.itemIdValues.size(); j++)
            ((LongSparseArray) (obj2)).put(transitionvaluesmaps1.itemIdValues.keyAt(j), transitionvaluesmaps1.itemIdValues.valueAt(j));

        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        iterator1 = transitionvaluesmaps.viewValues.keySet().iterator();
_L7:
        boolean flag;
        Object obj;
        View view3;
        if(!iterator1.hasNext())
            break; /* Loop/switch isn't completed */
        view3 = (View)iterator1.next();
        obj = null;
        flag = false;
        if(view3.getParent() instanceof ListView)
            flag = true;
        if(flag) goto _L2; else goto _L1
_L1:
        int k;
        k = view3.getId();
        TransitionValues transitionvalues;
        if(transitionvaluesmaps.viewValues.get(view3) != null)
            transitionvalues = (TransitionValues)transitionvaluesmaps.viewValues.get(view3);
        else
            transitionvalues = (TransitionValues)transitionvaluesmaps.idValues.get(k);
        if(transitionvaluesmaps1.viewValues.get(view3) == null) goto _L4; else goto _L3
_L3:
        obj = (TransitionValues)transitionvaluesmaps1.viewValues.get(view3);
        arraymap.remove(view3);
_L5:
        ((SparseArray) (obj3)).remove(k);
        if(isValidTarget(view3, k))
        {
            arraylist.add(transitionvalues);
            arraylist1.add(obj);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(k != -1)
        {
            TransitionValues transitionvalues4 = (TransitionValues)transitionvaluesmaps1.idValues.get(k);
            View view1 = null;
            Iterator iterator2 = arraymap.keySet().iterator();
            do
            {
                if(!iterator2.hasNext())
                    break;
                obj = (View)iterator2.next();
                if(((View) (obj)).getId() == k)
                    view1 = ((View) (obj));
            } while(true);
            obj = transitionvalues4;
            if(view1 != null)
            {
                arraymap.remove(view1);
                obj = transitionvalues4;
            }
        }
        if(true) goto _L5; else goto _L2
_L2:
        obj = (ListView)view3.getParent();
        if(((ListView) (obj)).getAdapter().hasStableIds())
        {
            long l2 = ((ListView) (obj)).getItemIdAtPosition(((ListView) (obj)).getPositionForView(view3));
            obj = (TransitionValues)transitionvaluesmaps.itemIdValues.get(l2);
            ((LongSparseArray) (obj2)).remove(l2);
            arraylist.add(obj);
            arraylist1.add(null);
        }
        if(true) goto _L7; else goto _L6
_L6:
        int l = transitionvaluesmaps.itemIdValues.size();
        for(k = 0; k < l; k++)
        {
            long l3 = transitionvaluesmaps.itemIdValues.keyAt(k);
            if(isValidTarget(null, l3))
            {
                obj = (TransitionValues)transitionvaluesmaps.itemIdValues.get(l3);
                TransitionValues transitionvalues1 = (TransitionValues)transitionvaluesmaps1.itemIdValues.get(l3);
                ((LongSparseArray) (obj2)).remove(l3);
                arraylist.add(obj);
                arraylist1.add(transitionvalues1);
            }
        }

        Iterator iterator = arraymap.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Object obj1 = (View)iterator.next();
            k = ((View) (obj1)).getId();
            if(isValidTarget(((View) (obj1)), k))
            {
                if(transitionvaluesmaps.viewValues.get(obj1) != null)
                    obj = (TransitionValues)transitionvaluesmaps.viewValues.get(obj1);
                else
                    obj = (TransitionValues)transitionvaluesmaps.idValues.get(k);
                obj1 = (TransitionValues)arraymap.get(obj1);
                ((SparseArray) (obj3)).remove(k);
                arraylist.add(obj);
                arraylist1.add(obj1);
            }
        } while(true);
        l = ((SparseArray) (obj3)).size();
        for(k = 0; k < l; k++)
        {
            int k1 = ((SparseArray) (obj3)).keyAt(k);
            if(isValidTarget(null, k1))
            {
                obj = (TransitionValues)transitionvaluesmaps.idValues.get(k1);
                TransitionValues transitionvalues2 = (TransitionValues)((SparseArray) (obj3)).get(k1);
                arraylist.add(obj);
                arraylist1.add(transitionvalues2);
            }
        }

        l = ((LongSparseArray) (obj2)).size();
        for(k = 0; k < l; k++)
        {
            long l4 = ((LongSparseArray) (obj2)).keyAt(k);
            obj = (TransitionValues)transitionvaluesmaps.itemIdValues.get(l4);
            TransitionValues transitionvalues3 = (TransitionValues)((LongSparseArray) (obj2)).get(l4);
            arraylist.add(obj);
            arraylist1.add(transitionvalues3);
        }

        obj3 = getRunningAnimators();
        k = 0;
_L20:
        if(k >= arraylist.size())
            break MISSING_BLOCK_LABEL_1332;
        transitionvaluesmaps = (TransitionValues)arraylist.get(k);
        obj = (TransitionValues)arraylist1.get(k);
        if(transitionvaluesmaps == null && obj == null || transitionvaluesmaps != null && transitionvaluesmaps.equals(obj)) goto _L9; else goto _L8
_L8:
        Animator animator = createAnimator(viewgroup, transitionvaluesmaps, ((TransitionValues) (obj)));
        if(animator == null) goto _L9; else goto _L10
_L10:
        View view;
        View view2;
        String as[];
        obj2 = null;
        if(obj == null)
            break MISSING_BLOCK_LABEL_1316;
        view2 = ((TransitionValues) (obj)).view;
        as = getTransitionProperties();
        obj = animator;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
        view = view2;
        if(view2 == null) goto _L12; else goto _L11
_L11:
        obj = animator;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
        view = view2;
        if(as == null) goto _L12; else goto _L13
_L13:
        obj = animator;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
        view = view2;
        if(as.length <= 0) goto _L12; else goto _L14
_L14:
        int j1;
        int l1;
        obj2 = new TransitionValues();
        obj2.view = view2;
        transitionvaluesmaps = (TransitionValues)transitionvaluesmaps1.viewValues.get(view2);
        if(transitionvaluesmaps != null)
        {
            for(int i1 = 0; i1 < as.length; i1++)
                ((TransitionValues) (obj2)).values.put(as[i1], ((TransitionValues) (transitionvaluesmaps)).values.get(as[i1]));

        }
        l1 = ((ArrayMap) (obj3)).size();
        j1 = 0;
_L18:
        obj = animator;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
        view = view2;
        if(j1 >= l1) goto _L12; else goto _L15
_L15:
        transitionvaluesmaps = (AnimationInfo)((ArrayMap) (obj3)).get((Animator)((ArrayMap) (obj3)).keyAt(j1));
        if(((AnimationInfo) (transitionvaluesmaps)).values == null || ((AnimationInfo) (transitionvaluesmaps)).view != view2 || (((AnimationInfo) (transitionvaluesmaps)).name != null || getName() != null) && !((AnimationInfo) (transitionvaluesmaps)).name.equals(getName()) || !((AnimationInfo) (transitionvaluesmaps)).values.equals(obj2)) goto _L17; else goto _L16
_L16:
        obj = null;
        view = view2;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
_L12:
        if(obj != null)
        {
            ((ArrayMap) (obj3)).put(obj, new AnimationInfo(view, getName(), WindowIdPort.getWindowId(viewgroup), transitionvaluesmaps));
            mAnimators.add(obj);
        }
_L9:
        k++;
        continue; /* Loop/switch isn't completed */
_L17:
        j1++;
          goto _L18
        view = ((TransitionValues) (transitionvaluesmaps)).view;
        obj = animator;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
          goto _L12
        return;
        if(true) goto _L20; else goto _L19
_L19:
    }

    protected void end()
    {
        mNumInstances = mNumInstances - 1;
        if(mNumInstances == 0)
        {
            if(mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int l = arraylist.size();
                for(int i = 0; i < l; i++)
                    ((TransitionListener)arraylist.get(i)).onTransitionEnd(this);

            }
            for(int j = 0; j < mStartValues.itemIdValues.size(); j++)
            {
                View view = ((TransitionValues)mStartValues.itemIdValues.valueAt(j)).view;
            }

            for(int k = 0; k < mEndValues.itemIdValues.size(); k++)
            {
                View view1 = ((TransitionValues)mEndValues.itemIdValues.valueAt(k)).view;
            }

            mEnded = true;
        }
    }

    public TransitionPort excludeChildren(int i, boolean flag)
    {
        mTargetIdChildExcludes = excludeId(mTargetIdChildExcludes, i, flag);
        return this;
    }

    public TransitionPort excludeChildren(View view, boolean flag)
    {
        mTargetChildExcludes = excludeView(mTargetChildExcludes, view, flag);
        return this;
    }

    public TransitionPort excludeChildren(Class class1, boolean flag)
    {
        mTargetTypeChildExcludes = excludeType(mTargetTypeChildExcludes, class1, flag);
        return this;
    }

    public TransitionPort excludeTarget(int i, boolean flag)
    {
        mTargetIdExcludes = excludeId(mTargetIdExcludes, i, flag);
        return this;
    }

    public TransitionPort excludeTarget(View view, boolean flag)
    {
        mTargetExcludes = excludeView(mTargetExcludes, view, flag);
        return this;
    }

    public TransitionPort excludeTarget(Class class1, boolean flag)
    {
        mTargetTypeExcludes = excludeType(mTargetTypeExcludes, class1, flag);
        return this;
    }

    public long getDuration()
    {
        return mDuration;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    public String getName()
    {
        return mName;
    }

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public List getTargetIds()
    {
        return mTargetIds;
    }

    public List getTargets()
    {
        return mTargets;
    }

    public String[] getTransitionProperties()
    {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean flag)
    {
        TransitionValues transitionvalues;
        if(mParent != null)
        {
            transitionvalues = mParent.getTransitionValues(view, flag);
        } else
        {
            TransitionValues transitionvalues1;
            TransitionValuesMaps transitionvaluesmaps;
            if(flag)
                transitionvaluesmaps = mStartValues;
            else
                transitionvaluesmaps = mEndValues;
            transitionvalues1 = (TransitionValues)transitionvaluesmaps.viewValues.get(view);
            transitionvalues = transitionvalues1;
            if(transitionvalues1 == null)
            {
                int i = view.getId();
                if(i >= 0)
                    transitionvalues1 = (TransitionValues)transitionvaluesmaps.idValues.get(i);
                transitionvalues = transitionvalues1;
                if(transitionvalues1 == null)
                {
                    transitionvalues = transitionvalues1;
                    if(view.getParent() instanceof ListView)
                    {
                        ListView listview = (ListView)view.getParent();
                        long l = listview.getItemIdAtPosition(listview.getPositionForView(view));
                        return (TransitionValues)transitionvaluesmaps.itemIdValues.get(l);
                    }
                }
            }
        }
        return transitionvalues;
    }

    boolean isValidTarget(View view, long l)
    {
        if(mTargetIdExcludes != null && mTargetIdExcludes.contains(Integer.valueOf((int)l)))
            return false;
        if(mTargetExcludes != null && mTargetExcludes.contains(view))
            return false;
        if(mTargetTypeExcludes != null && view != null)
        {
            int i1 = mTargetTypeExcludes.size();
            for(int i = 0; i < i1; i++)
                if(((Class)mTargetTypeExcludes.get(i)).isInstance(view))
                    return false;

        }
        if(mTargetIds.size() == 0 && mTargets.size() == 0)
            return true;
        if(mTargetIds.size() > 0)
        {
            for(int j = 0; j < mTargetIds.size(); j++)
                if((long)((Integer)mTargetIds.get(j)).intValue() == l)
                    return true;

        }
        if(view != null && mTargets.size() > 0)
        {
            for(int k = 0; k < mTargets.size(); k++)
                if(mTargets.get(k) == view)
                    return true;

        }
        return false;
    }

    public void pause(View view)
    {
        if(!mEnded)
        {
            ArrayMap arraymap = getRunningAnimators();
            int i = arraymap.size();
            view = WindowIdPort.getWindowId(view);
            for(i--; i >= 0; i--)
            {
                AnimationInfo animationinfo = (AnimationInfo)arraymap.valueAt(i);
                if(animationinfo.view != null && view.equals(animationinfo.windowId))
                    ((Animator)arraymap.keyAt(i)).cancel();
            }

            if(mListeners != null && mListeners.size() > 0)
            {
                view = (ArrayList)mListeners.clone();
                int k = view.size();
                for(int j = 0; j < k; j++)
                    ((TransitionListener)view.get(j)).onTransitionPause(this);

            }
            mPaused = true;
        }
    }

    void playTransition(ViewGroup viewgroup)
    {
        ArrayMap arraymap = getRunningAnimators();
        int i = arraymap.size() - 1;
        while(i >= 0) 
        {
            Animator animator;
label0:
            {
                animator = (Animator)arraymap.keyAt(i);
                if(animator == null)
                    continue;
                Object obj = (AnimationInfo)arraymap.get(animator);
                if(obj == null || ((AnimationInfo) (obj)).view == null || ((AnimationInfo) (obj)).view.getContext() != viewgroup.getContext())
                    continue;
                boolean flag1 = false;
                TransitionValues transitionvalues1 = ((AnimationInfo) (obj)).values;
                Object obj1 = ((AnimationInfo) (obj)).view;
                boolean flag;
                TransitionValues transitionvalues;
                Object obj2;
                if(mEndValues.viewValues != null)
                    obj = (TransitionValues)mEndValues.viewValues.get(obj1);
                else
                    obj = null;
                transitionvalues = ((TransitionValues) (obj));
                if(obj == null)
                    transitionvalues = (TransitionValues)mEndValues.idValues.get(((View) (obj1)).getId());
                flag = flag1;
                if(transitionvalues1 == null)
                    break label0;
                flag = flag1;
                if(transitionvalues == null)
                    break label0;
                obj = transitionvalues1.values.keySet().iterator();
                do
                {
                    flag = flag1;
                    if(!((Iterator) (obj)).hasNext())
                        break label0;
                    obj2 = (String)((Iterator) (obj)).next();
                    obj1 = transitionvalues1.values.get(obj2);
                    obj2 = transitionvalues.values.get(obj2);
                } while(obj1 == null || obj2 == null || obj1.equals(obj2));
                flag = true;
            }
            if(flag)
                if(animator.isRunning() || animator.isStarted())
                    animator.cancel();
                else
                    arraymap.remove(animator);
            i--;
        }
        createAnimators(viewgroup, mStartValues, mEndValues);
        runAnimators();
    }

    public TransitionPort removeListener(TransitionListener transitionlistener)
    {
        if(mListeners != null)
        {
            mListeners.remove(transitionlistener);
            if(mListeners.size() == 0)
            {
                mListeners = null;
                return this;
            }
        }
        return this;
    }

    public TransitionPort removeTarget(int i)
    {
        if(i > 0)
            mTargetIds.remove(Integer.valueOf(i));
        return this;
    }

    public TransitionPort removeTarget(View view)
    {
        if(view != null)
            mTargets.remove(view);
        return this;
    }

    public void resume(View view)
    {
        if(mPaused)
        {
            if(!mEnded)
            {
                ArrayMap arraymap = getRunningAnimators();
                int i = arraymap.size();
                view = WindowIdPort.getWindowId(view);
                for(i--; i >= 0; i--)
                {
                    AnimationInfo animationinfo = (AnimationInfo)arraymap.valueAt(i);
                    if(animationinfo.view != null && view.equals(animationinfo.windowId))
                        ((Animator)arraymap.keyAt(i)).end();
                }

                if(mListeners != null && mListeners.size() > 0)
                {
                    view = (ArrayList)mListeners.clone();
                    int k = view.size();
                    for(int j = 0; j < k; j++)
                        ((TransitionListener)view.get(j)).onTransitionResume(this);

                }
            }
            mPaused = false;
        }
    }

    protected void runAnimators()
    {
        start();
        ArrayMap arraymap = getRunningAnimators();
        Iterator iterator = mAnimators.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Animator animator = (Animator)iterator.next();
            if(arraymap.containsKey(animator))
            {
                start();
                runAnimator(animator, arraymap);
            }
        } while(true);
        mAnimators.clear();
        end();
    }

    void setCanRemoveViews(boolean flag)
    {
        mCanRemoveViews = flag;
    }

    public TransitionPort setDuration(long l)
    {
        mDuration = l;
        return this;
    }

    public TransitionPort setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolator = timeinterpolator;
        return this;
    }

    TransitionPort setSceneRoot(ViewGroup viewgroup)
    {
        mSceneRoot = viewgroup;
        return this;
    }

    public TransitionPort setStartDelay(long l)
    {
        mStartDelay = l;
        return this;
    }

    protected void start()
    {
        if(mNumInstances == 0)
        {
            if(mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int j = arraylist.size();
                for(int i = 0; i < j; i++)
                    ((TransitionListener)arraylist.get(i)).onTransitionStart(this);

            }
            mEnded = false;
        }
        mNumInstances = mNumInstances + 1;
    }

    public String toString()
    {
        return toString("");
    }

    String toString(String s)
    {
        String s1;
label0:
        {
            s1 = (new StringBuilder()).append(s).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(": ").toString();
            s = s1;
            if(mDuration != -1L)
                s = (new StringBuilder()).append(s1).append("dur(").append(mDuration).append(") ").toString();
            s1 = s;
            if(mStartDelay != -1L)
                s1 = (new StringBuilder()).append(s).append("dly(").append(mStartDelay).append(") ").toString();
            s = s1;
            if(mInterpolator != null)
                s = (new StringBuilder()).append(s1).append("interp(").append(mInterpolator).append(") ").toString();
            if(mTargetIds.size() <= 0)
            {
                s1 = s;
                if(mTargets.size() <= 0)
                    break label0;
            }
            s1 = (new StringBuilder()).append(s).append("tgts(").toString();
            s = s1;
            if(mTargetIds.size() > 0)
            {
                int i = 0;
                do
                {
                    s = s1;
                    if(i >= mTargetIds.size())
                        break;
                    s = s1;
                    if(i > 0)
                        s = (new StringBuilder()).append(s1).append(", ").toString();
                    s1 = (new StringBuilder()).append(s).append(mTargetIds.get(i)).toString();
                    i++;
                } while(true);
            }
            s1 = s;
            if(mTargets.size() > 0)
            {
                int j = 0;
                do
                {
                    s1 = s;
                    if(j >= mTargets.size())
                        break;
                    s1 = s;
                    if(j > 0)
                        s1 = (new StringBuilder()).append(s).append(", ").toString();
                    s = (new StringBuilder()).append(s1).append(mTargets.get(j)).toString();
                    j++;
                } while(true);
            }
            s1 = (new StringBuilder()).append(s1).append(")").toString();
        }
        return s1;
    }

    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static ThreadLocal sRunningAnimators = new ThreadLocal();
    ArrayList mAnimators;
    boolean mCanRemoveViews;
    ArrayList mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    private boolean mEnded;
    TimeInterpolator mInterpolator;
    ArrayList mListeners;
    private String mName;
    int mNumInstances;
    TransitionSetPort mParent;
    boolean mPaused;
    ViewGroup mSceneRoot;
    long mStartDelay;
    private TransitionValuesMaps mStartValues;
    ArrayList mTargetChildExcludes;
    ArrayList mTargetExcludes;
    ArrayList mTargetIdChildExcludes;
    ArrayList mTargetIdExcludes;
    ArrayList mTargetIds;
    ArrayList mTargetTypeChildExcludes;
    ArrayList mTargetTypeExcludes;
    ArrayList mTargets;

}
