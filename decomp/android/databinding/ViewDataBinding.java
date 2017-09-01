// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LongSparseArray;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.databinding:
//            BaseObservable, DataBinderMapper, DataBindingUtil, CallbackRegistry, 
//            DataBindingComponent, InverseBindingListener, OnRebindCallback, Observable, 
//            ObservableList, ObservableMap

public abstract class ViewDataBinding extends BaseObservable
{
    private static interface CreateWeakListener
    {

        public abstract WeakListener create(ViewDataBinding viewdatabinding, int i);
    }

    protected static class IncludedLayouts
    {

        public void setIncludes(int i, String as[], int ai[], int ai1[])
        {
            layouts[i] = as;
            indexes[i] = ai;
            layoutIds[i] = ai1;
        }

        public final int indexes[][];
        public final int layoutIds[][];
        public final String layouts[][];

        public IncludedLayouts(int i)
        {
            layouts = new String[i][];
            indexes = new int[i][];
            layoutIds = new int[i][];
        }
    }

    private static interface ObservableReference
    {

        public abstract void addListener(Object obj);

        public abstract WeakListener getListener();

        public abstract void removeListener(Object obj);
    }

    protected static abstract class PropertyChangedInverseListener extends Observable.OnPropertyChangedCallback
        implements InverseBindingListener
    {

        public void onPropertyChanged(Observable observable, int i)
        {
            if(i == mPropertyId || i == 0)
                onChange();
        }

        final int mPropertyId;

        public PropertyChangedInverseListener(int i)
        {
            mPropertyId = i;
        }
    }

    private static class WeakListListener extends ObservableList.OnListChangedCallback
        implements ObservableReference
    {

        public void addListener(ObservableList observablelist)
        {
            observablelist.addOnListChangedCallback(this);
        }

        public volatile void addListener(Object obj)
        {
            addListener((ObservableList)obj);
        }

        public WeakListener getListener()
        {
            return mListener;
        }

        public void onChanged(ObservableList observablelist)
        {
            ViewDataBinding viewdatabinding = mListener.getBinder();
            ObservableList observablelist1;
            if(viewdatabinding != null)
                if((observablelist1 = (ObservableList)mListener.getTarget()) == observablelist)
                {
                    viewdatabinding.handleFieldChange(mListener.mLocalFieldId, observablelist1, 0);
                    return;
                }
        }

        public void onItemRangeChanged(ObservableList observablelist, int i, int j)
        {
            onChanged(observablelist);
        }

        public void onItemRangeInserted(ObservableList observablelist, int i, int j)
        {
            onChanged(observablelist);
        }

        public void onItemRangeMoved(ObservableList observablelist, int i, int j, int k)
        {
            onChanged(observablelist);
        }

        public void onItemRangeRemoved(ObservableList observablelist, int i, int j)
        {
            onChanged(observablelist);
        }

        public void removeListener(ObservableList observablelist)
        {
            observablelist.removeOnListChangedCallback(this);
        }

        public volatile void removeListener(Object obj)
        {
            removeListener((ObservableList)obj);
        }

        final WeakListener mListener;

        public WeakListListener(ViewDataBinding viewdatabinding, int i)
        {
            mListener = new WeakListener(viewdatabinding, i, this);
        }
    }

    private static class WeakListener extends WeakReference
    {

        protected ViewDataBinding getBinder()
        {
            ViewDataBinding viewdatabinding = (ViewDataBinding)get();
            if(viewdatabinding == null)
                unregister();
            return viewdatabinding;
        }

        public Object getTarget()
        {
            return mTarget;
        }

        public void setTarget(Object obj)
        {
            unregister();
            mTarget = obj;
            if(mTarget != null)
                mObservable.addListener(mTarget);
        }

        public boolean unregister()
        {
            boolean flag = false;
            if(mTarget != null)
            {
                mObservable.removeListener(mTarget);
                flag = true;
            }
            mTarget = null;
            return flag;
        }

        protected final int mLocalFieldId;
        private final ObservableReference mObservable;
        private Object mTarget;

        public WeakListener(ViewDataBinding viewdatabinding, int i, ObservableReference observablereference)
        {
            super(viewdatabinding, ViewDataBinding.sReferenceQueue);
            mLocalFieldId = i;
            mObservable = observablereference;
        }
    }

    private static class WeakMapListener extends ObservableMap.OnMapChangedCallback
        implements ObservableReference
    {

        public void addListener(ObservableMap observablemap)
        {
            observablemap.addOnMapChangedCallback(this);
        }

        public volatile void addListener(Object obj)
        {
            addListener((ObservableMap)obj);
        }

        public WeakListener getListener()
        {
            return mListener;
        }

        public void onMapChanged(ObservableMap observablemap, Object obj)
        {
            obj = mListener.getBinder();
            if(obj == null || observablemap != mListener.getTarget())
            {
                return;
            } else
            {
                ((ViewDataBinding) (obj)).handleFieldChange(mListener.mLocalFieldId, observablemap, 0);
                return;
            }
        }

        public void removeListener(ObservableMap observablemap)
        {
            observablemap.removeOnMapChangedCallback(this);
        }

        public volatile void removeListener(Object obj)
        {
            removeListener((ObservableMap)obj);
        }

        final WeakListener mListener;

        public WeakMapListener(ViewDataBinding viewdatabinding, int i)
        {
            mListener = new WeakListener(viewdatabinding, i, this);
        }
    }

    private static class WeakPropertyListener extends Observable.OnPropertyChangedCallback
        implements ObservableReference
    {

        public void addListener(Observable observable)
        {
            observable.addOnPropertyChangedCallback(this);
        }

        public volatile void addListener(Object obj)
        {
            addListener((Observable)obj);
        }

        public WeakListener getListener()
        {
            return mListener;
        }

        public void onPropertyChanged(Observable observable, int i)
        {
            ViewDataBinding viewdatabinding;
            for(viewdatabinding = mListener.getBinder(); viewdatabinding == null || (Observable)mListener.getTarget() != observable;)
                return;

            viewdatabinding.handleFieldChange(mListener.mLocalFieldId, observable, i);
        }

        public void removeListener(Observable observable)
        {
            observable.removeOnPropertyChangedCallback(this);
        }

        public volatile void removeListener(Object obj)
        {
            removeListener((Observable)obj);
        }

        final WeakListener mListener;

        public WeakPropertyListener(ViewDataBinding viewdatabinding, int i)
        {
            mListener = new WeakListener(viewdatabinding, i, this);
        }
    }


    protected ViewDataBinding(DataBindingComponent databindingcomponent, View view, int i)
    {
        mPendingRebind = false;
        mRebindHalted = false;
        mBindingComponent = databindingcomponent;
        mLocalFieldObservers = new WeakListener[i];
        mRoot = view;
        if(Looper.myLooper() == null)
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        if(USE_CHOREOGRAPHER)
        {
            mChoreographer = Choreographer.getInstance();
            mFrameCallback = new android.view.Choreographer.FrameCallback() {

                public void doFrame(long l)
                {
                    mRebindRunnable.run();
                }

                final ViewDataBinding this$0;

            
            {
                this$0 = ViewDataBinding.this;
                super();
            }
            }
;
            return;
        } else
        {
            mFrameCallback = null;
            mUIThreadHandler = new Handler(Looper.myLooper());
            return;
        }
    }

    protected static ViewDataBinding bind(DataBindingComponent databindingcomponent, View view, int i)
    {
        return DataBindingUtil.bind(databindingcomponent, view, i);
    }

    private void executeBindingsInternal()
    {
        if(mIsExecutingPendingBindings)
            requestRebind();
        else
        if(hasPendingBindings())
        {
            mIsExecutingPendingBindings = true;
            mRebindHalted = false;
            if(mRebindCallbacks != null)
            {
                mRebindCallbacks.notifyCallbacks(this, 1, null);
                if(mRebindHalted)
                    mRebindCallbacks.notifyCallbacks(this, 2, null);
            }
            if(!mRebindHalted)
            {
                executeBindings();
                if(mRebindCallbacks != null)
                    mRebindCallbacks.notifyCallbacks(this, 3, null);
            }
            mIsExecutingPendingBindings = false;
            return;
        }
    }

    protected static void executeBindingsOn(ViewDataBinding viewdatabinding)
    {
        viewdatabinding.executeBindingsInternal();
    }

    private static int findIncludeIndex(String s, int i, IncludedLayouts includedlayouts, int j)
    {
        s = s.subSequence(s.indexOf('/') + 1, s.length() - 2);
        includedlayouts = includedlayouts.layouts[j];
        for(j = includedlayouts.length; i < j; i++)
            if(TextUtils.equals(s, includedlayouts[i]))
                return i;

        return -1;
    }

    private static int findLastMatching(ViewGroup viewgroup, int i)
    {
        String s = (String)viewgroup.getChildAt(i).getTag();
        String s1 = s.substring(0, s.length() - 1);
        int l = s1.length();
        int i1 = viewgroup.getChildCount();
        int j = i;
        i++;
        do
        {
            int k;
label0:
            {
                Object obj;
label1:
                {
                    if(i < i1)
                    {
                        obj = viewgroup.getChildAt(i);
                        if(((View) (obj)).getTag() instanceof String)
                            obj = (String)((View) (obj)).getTag();
                        else
                            obj = null;
                        k = j;
                        if(obj == null)
                            break label0;
                        k = j;
                        if(!((String) (obj)).startsWith(s1))
                            break label0;
                        if(((String) (obj)).length() != s.length() || ((String) (obj)).charAt(((String) (obj)).length() - 1) != '0')
                            break label1;
                    }
                    return j;
                }
                k = j;
                if(isNumeric(((String) (obj)), l))
                    k = i;
            }
            i++;
            j = k;
        } while(true);
    }

    static ViewDataBinding getBinding(View view)
    {
        if(view != null)
        {
            if(USE_TAG_ID)
                return (ViewDataBinding)view.getTag(com.android.databinding.library.R.id.dataBinding);
            view = ((View) (view.getTag()));
            if(view instanceof ViewDataBinding)
                return (ViewDataBinding)view;
        }
        return null;
    }

    public static int getBuildSdkInt()
    {
        return SDK_INT;
    }

    protected static int getColorFromResource(View view, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return view.getContext().getColor(i);
        else
            return view.getResources().getColor(i);
    }

    protected static ColorStateList getColorStateListFromResource(View view, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return view.getContext().getColorStateList(i);
        else
            return view.getResources().getColorStateList(i);
    }

    protected static Drawable getDrawableFromResource(View view, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return view.getContext().getDrawable(i);
        else
            return view.getResources().getDrawable(i);
    }

    protected static Object getFrom(Map map, Object obj)
    {
        if(map == null)
            return null;
        else
            return map.get(obj);
    }

    protected static byte getFromArray(byte abyte0[], int i)
    {
        if(abyte0 == null || i < 0 || i >= abyte0.length)
            return 0;
        else
            return abyte0[i];
    }

    protected static char getFromArray(char ac[], int i)
    {
        if(ac == null || i < 0 || i >= ac.length)
            return '\0';
        else
            return ac[i];
    }

    protected static double getFromArray(double ad[], int i)
    {
        if(ad == null || i < 0 || i >= ad.length)
            return 0.0D;
        else
            return ad[i];
    }

    protected static float getFromArray(float af[], int i)
    {
        if(af == null || i < 0 || i >= af.length)
            return 0.0F;
        else
            return af[i];
    }

    protected static int getFromArray(int ai[], int i)
    {
        if(ai == null || i < 0 || i >= ai.length)
            return 0;
        else
            return ai[i];
    }

    protected static long getFromArray(long al[], int i)
    {
        if(al == null || i < 0 || i >= al.length)
            return 0L;
        else
            return al[i];
    }

    protected static Object getFromArray(Object aobj[], int i)
    {
        if(aobj == null || i < 0 || i >= aobj.length)
            return null;
        else
            return aobj[i];
    }

    protected static short getFromArray(short aword0[], int i)
    {
        if(aword0 == null || i < 0 || i >= aword0.length)
            return 0;
        else
            return aword0[i];
    }

    protected static boolean getFromArray(boolean aflag[], int i)
    {
        if(aflag == null || i < 0 || i >= aflag.length)
            return false;
        else
            return aflag[i];
    }

    protected static int getFromList(SparseIntArray sparseintarray, int i)
    {
        if(sparseintarray == null || i < 0)
            return 0;
        else
            return sparseintarray.get(i);
    }

    protected static long getFromList(SparseLongArray sparselongarray, int i)
    {
        if(sparselongarray == null || i < 0)
            return 0L;
        else
            return sparselongarray.get(i);
    }

    protected static Object getFromList(LongSparseArray longsparsearray, int i)
    {
        if(longsparsearray == null || i < 0)
            return null;
        else
            return longsparsearray.get(i);
    }

    protected static Object getFromList(android.util.LongSparseArray longsparsearray, int i)
    {
        if(longsparsearray == null || i < 0)
            return null;
        else
            return longsparsearray.get(i);
    }

    protected static Object getFromList(SparseArray sparsearray, int i)
    {
        if(sparsearray == null || i < 0)
            return null;
        else
            return sparsearray.get(i);
    }

    protected static Object getFromList(List list, int i)
    {
        if(list == null || i < 0 || i >= list.size())
            return null;
        else
            return list.get(i);
    }

    protected static boolean getFromList(SparseBooleanArray sparsebooleanarray, int i)
    {
        if(sparsebooleanarray == null || i < 0)
            return false;
        else
            return sparsebooleanarray.get(i);
    }

    private void handleFieldChange(int i, Object obj, int j)
    {
        if(onFieldChange(i, obj, j))
            requestRebind();
    }

    private static boolean isNumeric(String s, int i)
    {
        int j = s.length();
        if(j != i) goto _L2; else goto _L1
_L1:
        return false;
_L2:
label0:
        do
        {
label1:
            {
                if(i >= j)
                    break label1;
                if(!Character.isDigit(s.charAt(i)))
                    break label0;
                i++;
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        return true;
    }

    private static void mapBindings(DataBindingComponent databindingcomponent, View view, Object aobj[], IncludedLayouts includedlayouts, SparseIntArray sparseintarray, boolean flag)
    {
        if(getBinding(view) == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        Object obj;
        obj = view.getTag();
        int l;
        boolean flag1;
        boolean flag2;
        int k1;
        int l1;
        String s;
        if(obj instanceof String)
            obj = (String)obj;
        else
            obj = null;
        j = 0;
        if(!flag || obj == null || !((String) (obj)).startsWith("layout")) goto _L4; else goto _L3
_L3:
        i = ((String) (obj)).lastIndexOf('_');
        if(i > 0 && isNumeric(((String) (obj)), i + 1))
        {
            i = parseTagInt(((String) (obj)), i + 1);
            if(aobj[i] == null)
                aobj[i] = view;
            if(includedlayouts == null)
                i = -1;
            j = 1;
        } else
        {
            i = -1;
        }
_L5:
        if(j == 0)
        {
            j = view.getId();
            if(j > 0 && sparseintarray != null)
            {
                j = sparseintarray.get(j, -1);
                if(j >= 0 && aobj[j] == null)
                    aobj[j] = view;
            }
        }
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            k1 = view.getChildCount();
            int i1 = 0;
            j = 0;
            while(j < k1) 
            {
                obj = view.getChildAt(j);
                flag2 = false;
                int k = j;
                flag1 = flag2;
                l = i1;
                if(i >= 0)
                {
                    k = j;
                    flag1 = flag2;
                    l = i1;
                    if(((View) (obj)).getTag() instanceof String)
                    {
                        s = (String)((View) (obj)).getTag();
                        k = j;
                        flag1 = flag2;
                        l = i1;
                        if(s.endsWith("_0"))
                        {
                            k = j;
                            flag1 = flag2;
                            l = i1;
                            if(s.startsWith("layout"))
                            {
                                k = j;
                                flag1 = flag2;
                                l = i1;
                                if(s.indexOf('/') > 0)
                                {
                                    l1 = findIncludeIndex(s, i1, includedlayouts, i);
                                    k = j;
                                    flag1 = flag2;
                                    l = i1;
                                    if(l1 >= 0)
                                    {
                                        flag1 = true;
                                        l = l1 + 1;
                                        i1 = includedlayouts.indexes[i][l1];
                                        int j1 = includedlayouts.layoutIds[i][l1];
                                        k = findLastMatching(view, j);
                                        if(k == j)
                                        {
                                            aobj[i1] = DataBindingUtil.bind(databindingcomponent, ((View) (obj)), j1);
                                            k = j;
                                        } else
                                        {
                                            int i2 = (k - j) + 1;
                                            View aview[] = new View[i2];
                                            for(k = 0; k < i2; k++)
                                                aview[k] = view.getChildAt(j + k);

                                            aobj[i1] = DataBindingUtil.bind(databindingcomponent, aview, j1);
                                            k = j + (i2 - 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(!flag1)
                    mapBindings(databindingcomponent, ((View) (obj)), aobj, includedlayouts, sparseintarray, false);
                j = k + 1;
                i1 = l;
            }
        }
        if(true) goto _L1; else goto _L4
_L4:
        if(obj != null && ((String) (obj)).startsWith("binding_"))
        {
            i = parseTagInt(((String) (obj)), BINDING_NUMBER_START);
            if(aobj[i] == null)
                aobj[i] = view;
            j = 1;
            if(includedlayouts == null)
                i = -1;
        } else
        {
            i = -1;
        }
          goto _L5
    }

    protected static Object[] mapBindings(DataBindingComponent databindingcomponent, View view, int i, IncludedLayouts includedlayouts, SparseIntArray sparseintarray)
    {
        Object aobj[] = new Object[i];
        mapBindings(databindingcomponent, view, aobj, includedlayouts, sparseintarray, true);
        return aobj;
    }

    protected static Object[] mapBindings(DataBindingComponent databindingcomponent, View aview[], int i, IncludedLayouts includedlayouts, SparseIntArray sparseintarray)
    {
        Object aobj[] = new Object[i];
        for(i = 0; i < aview.length; i++)
            mapBindings(databindingcomponent, aview[i], aobj, includedlayouts, sparseintarray, true);

        return aobj;
    }

    protected static byte parse(String s, byte byte0)
    {
        byte byte1;
        try
        {
            byte1 = Byte.parseByte(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return byte0;
        }
        return byte1;
    }

    protected static char parse(String s, char c)
    {
        if(s == null || s.isEmpty())
            return c;
        else
            return s.charAt(0);
    }

    protected static double parse(String s, double d)
    {
        double d1;
        try
        {
            d1 = Double.parseDouble(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return d;
        }
        return d1;
    }

    protected static float parse(String s, float f)
    {
        float f1;
        try
        {
            f1 = Float.parseFloat(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return f;
        }
        return f1;
    }

    protected static int parse(String s, int i)
    {
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return i;
        }
        return j;
    }

    protected static long parse(String s, long l)
    {
        long l1;
        try
        {
            l1 = Long.parseLong(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return l;
        }
        return l1;
    }

    protected static short parse(String s, short word0)
    {
        short word1;
        try
        {
            word1 = Short.parseShort(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return word0;
        }
        return word1;
    }

    protected static boolean parse(String s, boolean flag)
    {
        if(s == null)
            return flag;
        else
            return Boolean.parseBoolean(s);
    }

    private static int parseTagInt(String s, int i)
    {
        int k = s.length();
        int j = 0;
        for(; i < k; i++)
            j = j * 10 + (s.charAt(i) - 48);

        return j;
    }

    private static void processReferenceQueue()
    {
        do
        {
            java.lang.ref.Reference reference = sReferenceQueue.poll();
            if(reference == null)
                break;
            if(reference instanceof WeakListener)
                ((WeakListener)reference).unregister();
        } while(true);
    }

    protected static byte safeUnbox(Byte byte1)
    {
        if(byte1 == null)
            return 0;
        else
            return byte1.byteValue();
    }

    protected static char safeUnbox(Character character)
    {
        if(character == null)
            return '\0';
        else
            return character.charValue();
    }

    protected static double safeUnbox(Double double1)
    {
        if(double1 == null)
            return 0.0D;
        else
            return double1.doubleValue();
    }

    protected static float safeUnbox(Float float1)
    {
        if(float1 == null)
            return 0.0F;
        else
            return float1.floatValue();
    }

    protected static int safeUnbox(Integer integer)
    {
        if(integer == null)
            return 0;
        else
            return integer.intValue();
    }

    protected static long safeUnbox(Long long1)
    {
        if(long1 == null)
            return 0L;
        else
            return long1.longValue();
    }

    protected static short safeUnbox(Short short1)
    {
        if(short1 == null)
            return 0;
        else
            return short1.shortValue();
    }

    protected static boolean safeUnbox(Boolean boolean1)
    {
        if(boolean1 == null)
            return false;
        else
            return boolean1.booleanValue();
    }

    protected static void setBindingInverseListener(ViewDataBinding viewdatabinding, InverseBindingListener inversebindinglistener, PropertyChangedInverseListener propertychangedinverselistener)
    {
        if(inversebindinglistener != propertychangedinverselistener)
        {
            if(inversebindinglistener != null)
                viewdatabinding.removeOnPropertyChangedCallback((PropertyChangedInverseListener)inversebindinglistener);
            if(propertychangedinverselistener != null)
                viewdatabinding.addOnPropertyChangedCallback(propertychangedinverselistener);
        }
    }

    protected static void setTo(LongSparseArray longsparsearray, int i, Object obj)
    {
        if(longsparsearray == null || i < 0 || i >= longsparsearray.size())
        {
            return;
        } else
        {
            longsparsearray.put(i, obj);
            return;
        }
    }

    protected static void setTo(android.util.LongSparseArray longsparsearray, int i, Object obj)
    {
        if(longsparsearray == null || i < 0 || i >= longsparsearray.size())
        {
            return;
        } else
        {
            longsparsearray.put(i, obj);
            return;
        }
    }

    protected static void setTo(SparseArray sparsearray, int i, Object obj)
    {
        if(sparsearray == null || i < 0 || i >= sparsearray.size())
        {
            return;
        } else
        {
            sparsearray.put(i, obj);
            return;
        }
    }

    protected static void setTo(SparseBooleanArray sparsebooleanarray, int i, boolean flag)
    {
        if(sparsebooleanarray == null || i < 0 || i >= sparsebooleanarray.size())
        {
            return;
        } else
        {
            sparsebooleanarray.put(i, flag);
            return;
        }
    }

    protected static void setTo(SparseIntArray sparseintarray, int i, int j)
    {
        if(sparseintarray == null || i < 0 || i >= sparseintarray.size())
        {
            return;
        } else
        {
            sparseintarray.put(i, j);
            return;
        }
    }

    protected static void setTo(SparseLongArray sparselongarray, int i, long l)
    {
        if(sparselongarray == null || i < 0 || i >= sparselongarray.size())
        {
            return;
        } else
        {
            sparselongarray.put(i, l);
            return;
        }
    }

    protected static void setTo(List list, int i, Object obj)
    {
        if(list == null || i < 0 || i >= list.size())
        {
            return;
        } else
        {
            list.set(i, obj);
            return;
        }
    }

    protected static void setTo(Map map, Object obj, Object obj1)
    {
        if(map == null)
        {
            return;
        } else
        {
            map.put(obj, obj1);
            return;
        }
    }

    protected static void setTo(byte abyte0[], int i, byte byte0)
    {
        if(abyte0 == null || i < 0 || i >= abyte0.length)
        {
            return;
        } else
        {
            abyte0[i] = byte0;
            return;
        }
    }

    protected static void setTo(char ac[], int i, char c)
    {
        if(ac == null || i < 0 || i >= ac.length)
        {
            return;
        } else
        {
            ac[i] = c;
            return;
        }
    }

    protected static void setTo(double ad[], int i, double d)
    {
        if(ad == null || i < 0 || i >= ad.length)
        {
            return;
        } else
        {
            ad[i] = d;
            return;
        }
    }

    protected static void setTo(float af[], int i, float f)
    {
        if(af == null || i < 0 || i >= af.length)
        {
            return;
        } else
        {
            af[i] = f;
            return;
        }
    }

    protected static void setTo(int ai[], int i, int j)
    {
        if(ai == null || i < 0 || i >= ai.length)
        {
            return;
        } else
        {
            ai[i] = j;
            return;
        }
    }

    protected static void setTo(long al[], int i, long l)
    {
        if(al == null || i < 0 || i >= al.length)
        {
            return;
        } else
        {
            al[i] = l;
            return;
        }
    }

    protected static void setTo(Object aobj[], int i, Object obj)
    {
        if(aobj == null || i < 0 || i >= aobj.length)
        {
            return;
        } else
        {
            aobj[i] = obj;
            return;
        }
    }

    protected static void setTo(short aword0[], int i, short word0)
    {
        if(aword0 == null || i < 0 || i >= aword0.length)
        {
            return;
        } else
        {
            aword0[i] = word0;
            return;
        }
    }

    protected static void setTo(boolean aflag[], int i, boolean flag)
    {
        if(aflag == null || i < 0 || i >= aflag.length)
        {
            return;
        } else
        {
            aflag[i] = flag;
            return;
        }
    }

    private boolean updateRegistration(int i, Object obj, CreateWeakListener createweaklistener)
    {
        if(obj == null)
            return unregisterFrom(i);
        WeakListener weaklistener = mLocalFieldObservers[i];
        if(weaklistener == null)
        {
            registerTo(i, obj, createweaklistener);
            return true;
        }
        if(weaklistener.getTarget() == obj)
        {
            return false;
        } else
        {
            unregisterFrom(i);
            registerTo(i, obj, createweaklistener);
            return true;
        }
    }

    public void addOnRebindCallback(OnRebindCallback onrebindcallback)
    {
        if(mRebindCallbacks == null)
            mRebindCallbacks = new CallbackRegistry(REBIND_NOTIFIER);
        mRebindCallbacks.add(onrebindcallback);
    }

    protected void ensureBindingComponentIsNotNull(Class class1)
    {
        if(mBindingComponent == null)
            throw new IllegalStateException((new StringBuilder()).append("Required DataBindingComponent is null in class ").append(getClass().getSimpleName()).append(". A BindingAdapter in ").append(class1.getCanonicalName()).append(" is not static and requires an object to use, retrieved from the ").append("DataBindingComponent. If you don't use an inflation method taking a ").append("DataBindingComponent, use DataBindingUtil.setDefaultComponent or ").append("make all BindingAdapter methods static.").toString());
        else
            return;
    }

    protected abstract void executeBindings();

    public void executePendingBindings()
    {
        if(mContainingBinding == null)
        {
            executeBindingsInternal();
            return;
        } else
        {
            mContainingBinding.executePendingBindings();
            return;
        }
    }

    void forceExecuteBindings()
    {
        executeBindings();
    }

    protected Object getObservedField(int i)
    {
        WeakListener weaklistener = mLocalFieldObservers[i];
        if(weaklistener == null)
            return null;
        else
            return weaklistener.getTarget();
    }

    public View getRoot()
    {
        return mRoot;
    }

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    protected abstract boolean onFieldChange(int i, Object obj, int j);

    protected void registerTo(int i, Object obj, CreateWeakListener createweaklistener)
    {
        if(obj == null)
            return;
        WeakListener weaklistener1 = mLocalFieldObservers[i];
        WeakListener weaklistener = weaklistener1;
        if(weaklistener1 == null)
        {
            weaklistener = createweaklistener.create(this, i);
            mLocalFieldObservers[i] = weaklistener;
        }
        weaklistener.setTarget(obj);
    }

    public void removeOnRebindCallback(OnRebindCallback onrebindcallback)
    {
        if(mRebindCallbacks != null)
            mRebindCallbacks.remove(onrebindcallback);
    }

    protected void requestRebind()
    {
        if(mContainingBinding != null)
        {
            mContainingBinding.requestRebind();
            return;
        }
        this;
        JVM INSTR monitorenter ;
        if(!mPendingRebind)
            break MISSING_BLOCK_LABEL_32;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mPendingRebind = true;
        this;
        JVM INSTR monitorexit ;
        if(USE_CHOREOGRAPHER)
        {
            mChoreographer.postFrameCallback(mFrameCallback);
            return;
        } else
        {
            mUIThreadHandler.post(mRebindRunnable);
            return;
        }
    }

    protected void setContainedBinding(ViewDataBinding viewdatabinding)
    {
        if(viewdatabinding != null)
            viewdatabinding.mContainingBinding = this;
    }

    protected void setRootTag(View view)
    {
        if(USE_TAG_ID)
        {
            view.setTag(com.android.databinding.library.R.id.dataBinding, this);
            return;
        } else
        {
            view.setTag(this);
            return;
        }
    }

    protected void setRootTag(View aview[])
    {
        int k = 0;
        int i = 0;
        if(USE_TAG_ID)
        {
            for(k = aview.length; i < k; i++)
                aview[i].setTag(com.android.databinding.library.R.id.dataBinding, this);

        } else
        {
            int l = aview.length;
            for(int j = k; j < l; j++)
                aview[j].setTag(this);

        }
    }

    public abstract boolean setVariable(int i, Object obj);

    public void unbind()
    {
        WeakListener aweaklistener[] = mLocalFieldObservers;
        int j = aweaklistener.length;
        for(int i = 0; i < j; i++)
        {
            WeakListener weaklistener = aweaklistener[i];
            if(weaklistener != null)
                weaklistener.unregister();
        }

    }

    protected boolean unregisterFrom(int i)
    {
        WeakListener weaklistener = mLocalFieldObservers[i];
        if(weaklistener != null)
            return weaklistener.unregister();
        else
            return false;
    }

    protected boolean updateRegistration(int i, Observable observable)
    {
        return updateRegistration(i, observable, CREATE_PROPERTY_LISTENER);
    }

    protected boolean updateRegistration(int i, ObservableList observablelist)
    {
        return updateRegistration(i, observablelist, CREATE_LIST_LISTENER);
    }

    protected boolean updateRegistration(int i, ObservableMap observablemap)
    {
        return updateRegistration(i, observablemap, CREATE_MAP_LISTENER);
    }

    private static final int BINDING_NUMBER_START = "binding_".length();
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {

        public WeakListener create(ViewDataBinding viewdatabinding, int i)
        {
            return (new WeakListListener(viewdatabinding, i)).getListener();
        }

    }
;
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {

        public WeakListener create(ViewDataBinding viewdatabinding, int i)
        {
            return (new WeakMapListener(viewdatabinding, i)).getListener();
        }

    }
;
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {

        public WeakListener create(ViewDataBinding viewdatabinding, int i)
        {
            return (new WeakPropertyListener(viewdatabinding, i)).getListener();
        }

    }
;
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final CallbackRegistry.NotifierCallback REBIND_NOTIFIER = new CallbackRegistry.NotifierCallback() {

        public void onNotifyCallback(OnRebindCallback onrebindcallback, ViewDataBinding viewdatabinding, int i, Void void1)
        {
            i;
            JVM INSTR tableswitch 1 3: default 28
        //                       1 29
        //                       2 44
        //                       3 50;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            if(!onrebindcallback.onPreBind(viewdatabinding))
            {
                viewdatabinding.mRebindHalted = true;
                return;
            }
              goto _L1
_L3:
            onrebindcallback.onCanceled(viewdatabinding);
            return;
_L4:
            onrebindcallback.onBound(viewdatabinding);
            return;
        }

        public volatile void onNotifyCallback(Object obj, Object obj1, int i, Object obj2)
        {
            onNotifyCallback((OnRebindCallback)obj, (ViewDataBinding)obj1, i, (Void)obj2);
        }

    }
;
    private static final int REBOUND = 3;
    private static final android.view.View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT;
    private static final boolean USE_CHOREOGRAPHER;
    private static final boolean USE_TAG_ID;
    private static final ReferenceQueue sReferenceQueue = new ReferenceQueue();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final android.view.Choreographer.FrameCallback mFrameCallback;
    private boolean mIsExecutingPendingBindings;
    private WeakListener mLocalFieldObservers[];
    private boolean mPendingRebind;
    private CallbackRegistry mRebindCallbacks;
    private boolean mRebindHalted;
    private final Runnable mRebindRunnable = new Runnable() {

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
            mPendingRebind = false;
            this;
            JVM INSTR monitorexit ;
            ViewDataBinding.processReferenceQueue();
            Exception exception;
            if(android.os.Build.VERSION.SDK_INT >= 19 && !mRoot.isAttachedToWindow())
            {
                mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
                mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
                return;
            } else
            {
                executePendingBindings();
                return;
            }
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final ViewDataBinding this$0;

            
            {
                this$0 = ViewDataBinding.this;
                super();
            }
    }
;
    private final View mRoot;
    private Handler mUIThreadHandler;

    static 
    {
        boolean flag1 = true;
        SDK_INT = android.os.Build.VERSION.SDK_INT;
        boolean flag;
        if(DataBinderMapper.TARGET_MIN_SDK >= 14)
            flag = true;
        else
            flag = false;
        USE_TAG_ID = flag;
        if(SDK_INT >= 16)
            flag = flag1;
        else
            flag = false;
        USE_CHOREOGRAPHER = flag;
        if(android.os.Build.VERSION.SDK_INT < 19)
            ROOT_REATTACHED_LISTENER = null;
        else
            ROOT_REATTACHED_LISTENER = new android.view.View.OnAttachStateChangeListener() {

                public void onViewAttachedToWindow(View view)
                {
                    ViewDataBinding.getBinding(view).mRebindRunnable.run();
                    view.removeOnAttachStateChangeListener(this);
                }

                public void onViewDetachedFromWindow(View view)
                {
                }

            }
;
    }


/*
    static boolean access$002(ViewDataBinding viewdatabinding, boolean flag)
    {
        viewdatabinding.mRebindHalted = flag;
        return flag;
    }

*/



/*
    static boolean access$202(ViewDataBinding viewdatabinding, boolean flag)
    {
        viewdatabinding.mPendingRebind = flag;
        return flag;
    }

*/





}
