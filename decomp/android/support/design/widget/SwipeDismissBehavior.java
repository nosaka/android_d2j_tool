// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.*;

// Referenced classes of package android.support.design.widget:
//            CoordinatorLayout

public class SwipeDismissBehavior extends CoordinatorLayout.Behavior
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss(View view);

        public abstract void onDragStateChanged(int i);
    }

    private class SettleRunnable
        implements Runnable
    {

        public void run()
        {
            if(mViewDragHelper != null && mViewDragHelper.continueSettling(true))
                ViewCompat.postOnAnimation(mView, this);
            else
            if(mDismiss && mListener != null)
            {
                mListener.onDismiss(mView);
                return;
            }
        }

        private final boolean mDismiss;
        private final View mView;
        final SwipeDismissBehavior this$0;

        SettleRunnable(View view, boolean flag)
        {
            this$0 = SwipeDismissBehavior.this;
            super();
            mView = view;
            mDismiss = flag;
        }
    }


    public SwipeDismissBehavior()
    {
        mSensitivity = 0.0F;
        mSwipeDirection = 2;
        mDragDismissThreshold = 0.5F;
        mAlphaStartSwipeDistance = 0.0F;
        mAlphaEndSwipeDistance = 0.5F;
    }

    static float clamp(float f, float f1, float f2)
    {
        return Math.min(Math.max(f, f1), f2);
    }

    static int clamp(int i, int j, int k)
    {
        return Math.min(Math.max(i, j), k);
    }

    private void ensureViewDragHelper(ViewGroup viewgroup)
    {
        if(mViewDragHelper == null)
        {
            if(mSensitivitySet)
                viewgroup = ViewDragHelper.create(viewgroup, mSensitivity, mDragCallback);
            else
                viewgroup = ViewDragHelper.create(viewgroup, mDragCallback);
            mViewDragHelper = viewgroup;
        }
    }

    static float fraction(float f, float f1, float f2)
    {
        return (f2 - f) / (f1 - f);
    }

    public boolean canSwipeDismissView(View view)
    {
        return true;
    }

    public int getDragState()
    {
        if(mViewDragHelper != null)
            return mViewDragHelper.getViewDragState();
        else
            return 0;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = mInterceptingEvents;
        flag = flag2;
        MotionEventCompat.getActionMasked(motionevent);
        JVM INSTR tableswitch 0 3: default 48
    //                   0 75
    //                   1 103
    //                   2 52
    //                   3 103;
           goto _L1 _L2 _L3 _L4 _L3
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        flag = flag2;
_L6:
        if(flag)
        {
            ensureViewDragHelper(coordinatorlayout);
            flag1 = mViewDragHelper.shouldInterceptTouchEvent(motionevent);
        }
        return flag1;
_L2:
        mInterceptingEvents = coordinatorlayout.isPointInChildBounds(view, (int)motionevent.getX(), (int)motionevent.getY());
        flag = mInterceptingEvents;
        continue; /* Loop/switch isn't completed */
_L3:
        mInterceptingEvents = false;
        flag = flag2;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if(mViewDragHelper != null)
        {
            mViewDragHelper.processTouchEvent(motionevent);
            return true;
        } else
        {
            return false;
        }
    }

    public void setDragDismissDistance(float f)
    {
        mDragDismissThreshold = clamp(0.0F, f, 1.0F);
    }

    public void setEndAlphaSwipeDistance(float f)
    {
        mAlphaEndSwipeDistance = clamp(0.0F, f, 1.0F);
    }

    public void setListener(OnDismissListener ondismisslistener)
    {
        mListener = ondismisslistener;
    }

    public void setSensitivity(float f)
    {
        mSensitivity = f;
        mSensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f)
    {
        mAlphaStartSwipeDistance = clamp(0.0F, f, 1.0F);
    }

    public void setSwipeDirection(int i)
    {
        mSwipeDirection = i;
    }

    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5F;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0F;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5F;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float mAlphaEndSwipeDistance;
    float mAlphaStartSwipeDistance;
    private final android.support.v4.widget.ViewDragHelper.Callback mDragCallback = new android.support.v4.widget.ViewDragHelper.Callback() {

        private boolean shouldDismiss(View view, float f)
        {
            if(f == 0.0F) goto _L2; else goto _L1
_L1:
            boolean flag;
            if(ViewCompat.getLayoutDirection(view) == 1)
                flag = true;
            else
                flag = false;
            if(mSwipeDirection != 2) goto _L4; else goto _L3
_L3:
            return true;
_L4:
            if(mSwipeDirection == 0)
            {
                if(!flag)
                    continue; /* Loop/switch isn't completed */
                if(f >= 0.0F)
                    return false;
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_64;
            if(f > 0.0F) goto _L3; else goto _L5
_L5:
            return false;
            if(mSwipeDirection != 1)
                break; /* Loop/switch isn't completed */
            if(!flag)
                continue; /* Loop/switch isn't completed */
            if(f <= 0.0F)
                return false;
            continue; /* Loop/switch isn't completed */
            if(f < 0.0F) goto _L3; else goto _L6
_L6:
            return false;
_L2:
            int i = view.getLeft();
            int j = mOriginalCapturedViewLeft;
            int k = Math.round((float)view.getWidth() * mDragDismissThreshold);
            if(Math.abs(i - j) < k)
                return false;
            if(true) goto _L3; else goto _L7
_L7:
            return false;
        }

        public int clampViewPositionHorizontal(View view, int i, int j)
        {
            int k;
            if(ViewCompat.getLayoutDirection(view) == 1)
                j = 1;
            else
                j = 0;
            if(mSwipeDirection == 0)
            {
                if(j != 0)
                {
                    k = mOriginalCapturedViewLeft - view.getWidth();
                    j = mOriginalCapturedViewLeft;
                } else
                {
                    k = mOriginalCapturedViewLeft;
                    j = mOriginalCapturedViewLeft + view.getWidth();
                }
            } else
            if(mSwipeDirection == 1)
            {
                if(j != 0)
                {
                    k = mOriginalCapturedViewLeft;
                    j = mOriginalCapturedViewLeft + view.getWidth();
                } else
                {
                    k = mOriginalCapturedViewLeft - view.getWidth();
                    j = mOriginalCapturedViewLeft;
                }
            } else
            {
                k = mOriginalCapturedViewLeft - view.getWidth();
                j = mOriginalCapturedViewLeft + view.getWidth();
            }
            return SwipeDismissBehavior.clamp(k, i, j);
        }

        public int clampViewPositionVertical(View view, int i, int j)
        {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(View view)
        {
            return view.getWidth();
        }

        public void onViewCaptured(View view, int i)
        {
            mActivePointerId = i;
            mOriginalCapturedViewLeft = view.getLeft();
            view = view.getParent();
            if(view != null)
                view.requestDisallowInterceptTouchEvent(true);
        }

        public void onViewDragStateChanged(int i)
        {
            if(mListener != null)
                mListener.onDragStateChanged(i);
        }

        public void onViewPositionChanged(View view, int i, int j, int k, int l)
        {
            float f = (float)mOriginalCapturedViewLeft + (float)view.getWidth() * mAlphaStartSwipeDistance;
            float f1 = (float)mOriginalCapturedViewLeft + (float)view.getWidth() * mAlphaEndSwipeDistance;
            if((float)i <= f)
            {
                ViewCompat.setAlpha(view, 1.0F);
                return;
            }
            if((float)i >= f1)
            {
                ViewCompat.setAlpha(view, 0.0F);
                return;
            } else
            {
                ViewCompat.setAlpha(view, SwipeDismissBehavior.clamp(0.0F, 1.0F - SwipeDismissBehavior.fraction(f, f1, i), 1.0F));
                return;
            }
        }

        public void onViewReleased(View view, float f, float f1)
        {
            mActivePointerId = -1;
            int i = view.getWidth();
            boolean flag = false;
            if(shouldDismiss(view, f))
            {
                if(view.getLeft() < mOriginalCapturedViewLeft)
                    i = mOriginalCapturedViewLeft - i;
                else
                    i = mOriginalCapturedViewLeft + i;
                flag = true;
            } else
            {
                i = mOriginalCapturedViewLeft;
            }
            if(mViewDragHelper.settleCapturedViewAt(i, view.getTop()))
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, flag));
            else
            if(flag && mListener != null)
            {
                mListener.onDismiss(view);
                return;
            }
        }

        public boolean tryCaptureView(View view, int i)
        {
            return mActivePointerId == -1 && canSwipeDismissView(view);
        }

        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId;
        private int mOriginalCapturedViewLeft;
        final SwipeDismissBehavior this$0;

            
            {
                this$0 = SwipeDismissBehavior.this;
                super();
                mActivePointerId = -1;
            }
    }
;
    float mDragDismissThreshold;
    private boolean mInterceptingEvents;
    OnDismissListener mListener;
    private float mSensitivity;
    private boolean mSensitivitySet;
    int mSwipeDirection;
    ViewDragHelper mViewDragHelper;
}
