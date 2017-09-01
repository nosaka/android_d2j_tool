// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.util;

import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package android.support.v7.util:
//            BatchingListUpdateCallback, ListUpdateCallback

public class SortedList
{
    public static class BatchedCallback extends Callback
    {

        public boolean areContentsTheSame(Object obj, Object obj1)
        {
            return mWrappedCallback.areContentsTheSame(obj, obj1);
        }

        public boolean areItemsTheSame(Object obj, Object obj1)
        {
            return mWrappedCallback.areItemsTheSame(obj, obj1);
        }

        public int compare(Object obj, Object obj1)
        {
            return mWrappedCallback.compare(obj, obj1);
        }

        public void dispatchLastEvent()
        {
            mBatchingListUpdateCallback.dispatchLastEvent();
        }

        public void onChanged(int i, int j)
        {
            mBatchingListUpdateCallback.onChanged(i, j, null);
        }

        public void onInserted(int i, int j)
        {
            mBatchingListUpdateCallback.onInserted(i, j);
        }

        public void onMoved(int i, int j)
        {
            mBatchingListUpdateCallback.onMoved(i, j);
        }

        public void onRemoved(int i, int j)
        {
            mBatchingListUpdateCallback.onRemoved(i, j);
        }

        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback mWrappedCallback;

        public BatchedCallback(Callback callback)
        {
            mWrappedCallback = callback;
            mBatchingListUpdateCallback = new BatchingListUpdateCallback(mWrappedCallback);
        }
    }

    public static abstract class Callback
        implements Comparator, ListUpdateCallback
    {

        public abstract boolean areContentsTheSame(Object obj, Object obj1);

        public abstract boolean areItemsTheSame(Object obj, Object obj1);

        public abstract int compare(Object obj, Object obj1);

        public abstract void onChanged(int i, int j);

        public void onChanged(int i, int j, Object obj)
        {
            onChanged(i, j);
        }

        public Callback()
        {
        }
    }


    public SortedList(Class class1, Callback callback)
    {
        this(class1, callback, 10);
    }

    public SortedList(Class class1, Callback callback, int i)
    {
        mTClass = class1;
        mData = (Object[])(Object[])Array.newInstance(class1, i);
        mCallback = callback;
        mSize = 0;
    }

    private int add(Object obj, boolean flag)
    {
        int j = findIndexOf(obj, mData, 0, mSize, 1);
        int i;
        if(j == -1)
        {
            i = 0;
        } else
        {
            i = j;
            if(j < mSize)
            {
                Object obj1 = mData[j];
                i = j;
                if(mCallback.areItemsTheSame(obj1, obj))
                    if(mCallback.areContentsTheSame(obj1, obj))
                    {
                        mData[j] = obj;
                        return j;
                    } else
                    {
                        mData[j] = obj;
                        mCallback.onChanged(j, 1);
                        return j;
                    }
            }
        }
        addToData(i, obj);
        if(flag)
            mCallback.onInserted(i, 1);
        return i;
    }

    private void addAllInternal(Object aobj[])
    {
        boolean flag;
        int i;
        if(!(mCallback instanceof BatchedCallback))
            flag = true;
        else
            flag = false;
        if(flag)
            beginBatchedUpdates();
        mOldData = mData;
        mOldDataStart = 0;
        mOldDataSize = mSize;
        Arrays.sort(aobj, mCallback);
        i = deduplicate(aobj);
        if(mSize == 0)
        {
            mData = aobj;
            mSize = i;
            mMergedSize = i;
            mCallback.onInserted(0, i);
        } else
        {
            merge(aobj, i);
        }
        mOldData = null;
        if(flag)
            endBatchedUpdates();
    }

    private void addToData(int i, Object obj)
    {
        if(i > mSize)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("cannot add item to ").append(i).append(" because size is ").append(mSize).toString());
        if(mSize == mData.length)
        {
            Object aobj[] = (Object[])(Object[])Array.newInstance(mTClass, mData.length + 10);
            System.arraycopy(((Object) (mData)), 0, ((Object) (aobj)), 0, i);
            aobj[i] = obj;
            System.arraycopy(((Object) (mData)), i, ((Object) (aobj)), i + 1, mSize - i);
            mData = aobj;
        } else
        {
            System.arraycopy(((Object) (mData)), i, ((Object) (mData)), i + 1, mSize - i);
            mData[i] = obj;
        }
        mSize = mSize + 1;
    }

    private int deduplicate(Object aobj[])
    {
        if(aobj.length == 0)
            throw new IllegalArgumentException("Input array must be non-empty");
        int k = 0;
        int i = 1;
        int j = 1;
        while(j < aobj.length) 
        {
            Object obj = aobj[j];
            int l = mCallback.compare(aobj[k], obj);
            if(l > 0)
                throw new IllegalArgumentException("Input must be sorted in ascending order.");
            if(l == 0)
            {
                int i1 = findSameItem(obj, aobj, k, i);
                if(i1 != -1)
                {
                    aobj[i1] = obj;
                } else
                {
                    if(i != j)
                        aobj[i] = obj;
                    i++;
                }
            } else
            {
                if(i != j)
                    aobj[i] = obj;
                k = i;
                i++;
            }
            j++;
        }
        return i;
    }

    private int findIndexOf(Object obj, Object aobj[], int i, int j, int k)
    {
        do
        {
            if(i >= j)
                break;
            int l = (i + j) / 2;
            Object obj1 = aobj[l];
            int i1 = mCallback.compare(obj1, obj);
            if(i1 < 0)
            {
                i = l + 1;
                continue;
            }
            if(i1 == 0)
            {
                if(!mCallback.areItemsTheSame(obj1, obj))
                {
                    i = linearEqualitySearch(obj, l, i, j);
                    if(k == 1)
                    {
                        if(i != -1)
                            return i;
                    } else
                    {
                        return i;
                    }
                }
                return l;
            }
            j = l;
        } while(true);
        if(k != 1)
            i = -1;
        return i;
    }

    private int findSameItem(Object obj, Object aobj[], int i, int j)
    {
        for(; i < j; i++)
            if(mCallback.areItemsTheSame(aobj[i], obj))
                return i;

        return -1;
    }

    private int linearEqualitySearch(Object obj, int i, int j, int k)
    {
        int l = i - 1;
_L4:
        if(l < j) goto _L2; else goto _L1
_L1:
        Object obj1 = mData[l];
        if(mCallback.compare(obj1, obj) == 0) goto _L3; else goto _L2
_L2:
        i++;
_L5:
        if(i < k)
        {
            obj1 = mData[i];
            if(mCallback.compare(obj1, obj) == 0)
                break MISSING_BLOCK_LABEL_91;
        }
        return -1;
_L3:
        if(mCallback.areItemsTheSame(obj1, obj))
            return l;
        l--;
          goto _L4
        if(mCallback.areItemsTheSame(obj1, obj))
            return i;
        i++;
          goto _L5
    }

    private void merge(Object aobj[], int i)
    {
        int j = mSize;
        mData = (Object[])(Object[])Array.newInstance(mTClass, j + i + 10);
        mMergedSize = 0;
        j = 0;
        do
        {
label0:
            {
                if(mOldDataStart < mOldDataSize || j < i)
                {
                    if(mOldDataStart != mOldDataSize)
                        break label0;
                    i -= j;
                    System.arraycopy(((Object) (aobj)), j, ((Object) (mData)), mMergedSize, i);
                    mMergedSize = mMergedSize + i;
                    mSize = mSize + i;
                    mCallback.onInserted(mMergedSize - i, i);
                }
                return;
            }
            if(j == i)
            {
                i = mOldDataSize - mOldDataStart;
                System.arraycopy(((Object) (mOldData)), mOldDataStart, ((Object) (mData)), mMergedSize, i);
                mMergedSize = mMergedSize + i;
                return;
            }
            Object obj = mOldData[mOldDataStart];
            Object obj1 = aobj[j];
            int k = mCallback.compare(obj, obj1);
            if(k > 0)
            {
                obj = ((Object) (mData));
                k = mMergedSize;
                mMergedSize = k + 1;
                obj[k] = obj1;
                mSize = mSize + 1;
                j++;
                mCallback.onInserted(mMergedSize - 1, 1);
            } else
            if(k == 0 && mCallback.areItemsTheSame(obj, obj1))
            {
                Object aobj2[] = mData;
                int l = mMergedSize;
                mMergedSize = l + 1;
                aobj2[l] = obj1;
                l = j + 1;
                mOldDataStart = mOldDataStart + 1;
                j = l;
                if(!mCallback.areContentsTheSame(obj, obj1))
                {
                    mCallback.onChanged(mMergedSize - 1, 1);
                    j = l;
                }
            } else
            {
                Object aobj1[] = mData;
                int i1 = mMergedSize;
                mMergedSize = i1 + 1;
                aobj1[i1] = obj;
                mOldDataStart = mOldDataStart + 1;
            }
        } while(true);
    }

    private boolean remove(Object obj, boolean flag)
    {
        int i = findIndexOf(obj, mData, 0, mSize, 2);
        if(i == -1)
        {
            return false;
        } else
        {
            removeItemAtIndex(i, flag);
            return true;
        }
    }

    private void removeItemAtIndex(int i, boolean flag)
    {
        System.arraycopy(((Object) (mData)), i + 1, ((Object) (mData)), i, mSize - i - 1);
        mSize = mSize - 1;
        mData[mSize] = null;
        if(flag)
            mCallback.onRemoved(i, 1);
    }

    private void throwIfMerging()
    {
        if(mOldData != null)
            throw new IllegalStateException("Cannot call this method from within addAll");
        else
            return;
    }

    public int add(Object obj)
    {
        throwIfMerging();
        return add(obj, true);
    }

    public void addAll(Collection collection)
    {
        addAll(collection.toArray((Object[])(Object[])Array.newInstance(mTClass, collection.size())), true);
    }

    public transient void addAll(Object aobj[])
    {
        addAll(aobj, false);
    }

    public void addAll(Object aobj[], boolean flag)
    {
        throwIfMerging();
        if(aobj.length == 0)
            return;
        if(flag)
        {
            addAllInternal(aobj);
            return;
        } else
        {
            Object aobj1[] = (Object[])(Object[])Array.newInstance(mTClass, aobj.length);
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, aobj.length);
            addAllInternal(aobj1);
            return;
        }
    }

    public void beginBatchedUpdates()
    {
        throwIfMerging();
        if(mCallback instanceof BatchedCallback)
            return;
        if(mBatchedCallback == null)
            mBatchedCallback = new BatchedCallback(mCallback);
        mCallback = mBatchedCallback;
    }

    public void clear()
    {
        throwIfMerging();
        if(mSize == 0)
        {
            return;
        } else
        {
            int i = mSize;
            Arrays.fill(mData, 0, i, null);
            mSize = 0;
            mCallback.onRemoved(0, i);
            return;
        }
    }

    public void endBatchedUpdates()
    {
        throwIfMerging();
        if(mCallback instanceof BatchedCallback)
            ((BatchedCallback)mCallback).dispatchLastEvent();
        if(mCallback == mBatchedCallback)
            mCallback = mBatchedCallback.mWrappedCallback;
    }

    public Object get(int i)
        throws IndexOutOfBoundsException
    {
        if(i >= mSize || i < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Asked to get item at ").append(i).append(" but size is ").append(mSize).toString());
        if(mOldData != null && i >= mMergedSize)
            return mOldData[(i - mMergedSize) + mOldDataStart];
        else
            return mData[i];
    }

    public int indexOf(Object obj)
    {
        if(mOldData != null)
        {
            int i = findIndexOf(obj, mData, 0, mMergedSize, 4);
            if(i != -1)
                return i;
            i = findIndexOf(obj, mOldData, mOldDataStart, mOldDataSize, 4);
            if(i != -1)
                return (i - mOldDataStart) + mMergedSize;
            else
                return -1;
        } else
        {
            return findIndexOf(obj, mData, 0, mSize, 4);
        }
    }

    public void recalculatePositionOfItemAt(int i)
    {
        throwIfMerging();
        Object obj = get(i);
        removeItemAtIndex(i, false);
        int j = add(obj, false);
        if(i != j)
            mCallback.onMoved(i, j);
    }

    public boolean remove(Object obj)
    {
        throwIfMerging();
        return remove(obj, true);
    }

    public Object removeItemAt(int i)
    {
        throwIfMerging();
        Object obj = get(i);
        removeItemAtIndex(i, true);
        return obj;
    }

    public int size()
    {
        return mSize;
    }

    public void updateItemAt(int i, Object obj)
    {
        throwIfMerging();
        Object obj1 = get(i);
        boolean flag;
        if(obj1 == obj || !mCallback.areContentsTheSame(obj1, obj))
            flag = true;
        else
            flag = false;
        if(obj1 != obj && mCallback.compare(obj1, obj) == 0)
        {
            mData[i] = obj;
            if(flag)
                mCallback.onChanged(i, 1);
        } else
        {
            if(flag)
                mCallback.onChanged(i, 1);
            removeItemAtIndex(i, false);
            int j = add(obj, false);
            if(i != j)
            {
                mCallback.onMoved(i, j);
                return;
            }
        }
    }

    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    Object mData[];
    private int mMergedSize;
    private Object mOldData[];
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class mTClass;
}
