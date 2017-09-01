// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;

// Referenced classes of package it.sephiroth.android.library.imagezoom:
//            ImageViewTouchBase

public class ImageViewTouch extends ImageViewTouchBase
{
    public class GestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onDoubleTap(MotionEvent motionevent)
        {
            if(mDoubleTapEnabled)
            {
                mUserScaled = true;
                float f = getScale();
                f = onDoubleTapPost(f, getMaxScale());
                f = Math.min(getMaxScale(), Math.max(f, getMinScale()));
                zoomTo(f, motionevent.getX(), motionevent.getY(), 200F);
                invalidate();
            }
            if(mDoubleTapListener != null)
                mDoubleTapListener.onDoubleTap();
            return super.onDoubleTap(motionevent);
        }

        public boolean onDown(MotionEvent motionevent)
        {
            return ImageViewTouch.this.onDown(motionevent);
        }

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            while(!mScrollEnabled || motionevent.getPointerCount() > 1 || motionevent1.getPointerCount() > 1 || mScaleDetector.isInProgress() || getScale() == 1.0F) 
                return false;
            return ImageViewTouch.this.onFling(motionevent, motionevent1, f, f1);
        }

        public void onLongPress(MotionEvent motionevent)
        {
            if(isLongClickable() && !mScaleDetector.isInProgress())
            {
                setPressed(true);
                performLongClick();
            }
        }

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            while(!mScrollEnabled || motionevent == null || motionevent1 == null || motionevent.getPointerCount() > 1 || motionevent1.getPointerCount() > 1 || mScaleDetector.isInProgress()) 
                return false;
            return ImageViewTouch.this.onScroll(motionevent, motionevent1, f, f1);
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            if(mSingleTapListener != null)
                mSingleTapListener.onSingleTapConfirmed();
            return ImageViewTouch.this.onSingleTapConfirmed(motionevent);
        }

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            return ImageViewTouch.this.onSingleTapUp(motionevent);
        }

        final ImageViewTouch this$0;

        public GestureListener()
        {
            this$0 = ImageViewTouch.this;
            super();
        }
    }

    public static interface OnImageViewTouchDoubleTapListener
    {

        public abstract void onDoubleTap();
    }

    public static interface OnImageViewTouchSingleTapListener
    {

        public abstract void onSingleTapConfirmed();
    }

    public class ScaleListener extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
    {

        public boolean onScale(ScaleGestureDetector scalegesturedetector)
        {
            float f = scalegesturedetector.getCurrentSpan();
            float f2 = scalegesturedetector.getPreviousSpan();
            float f3 = getScale();
            float f4 = scalegesturedetector.getScaleFactor();
            if(mScaleEnabled)
                if(mScaled && f - f2 != 0.0F)
                {
                    mUserScaled = true;
                    float f1 = Math.min(getMaxScale(), Math.max(f3 * f4, getMinScale() - 0.1F));
                    zoomTo(f1, scalegesturedetector.getFocusX(), scalegesturedetector.getFocusY());
                    mDoubleTapDirection = 1;
                    invalidate();
                } else
                if(!mScaled)
                {
                    mScaled = true;
                    return true;
                }
            return true;
        }

        protected boolean mScaled;
        final ImageViewTouch this$0;

        public ScaleListener()
        {
            this$0 = ImageViewTouch.this;
            super();
            mScaled = false;
        }
    }


    public ImageViewTouch(Context context)
    {
        super(context);
        mDoubleTapEnabled = true;
        mScaleEnabled = true;
        mScrollEnabled = true;
    }

    public ImageViewTouch(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ImageViewTouch(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mDoubleTapEnabled = true;
        mScaleEnabled = true;
        mScrollEnabled = true;
    }

    protected void _setImageDrawable(Drawable drawable, Matrix matrix, float f, float f1)
    {
        super._setImageDrawable(drawable, matrix, f, f1);
        mScaleFactor = getMaxScale() / 3F;
    }

    public boolean canScroll(int i)
    {
        boolean flag;
        RectF rectf;
        Rect rect;
        flag = true;
        rectf = getBitmapRect();
        updateRect(rectf, mScrollRect);
        rect = new Rect();
        getGlobalVisibleRect(rect);
        if(rectf != null) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if(rectf.right < (float)rect.right || i >= 0)
            continue; /* Loop/switch isn't completed */
        if(Math.abs(rectf.right - (float)rect.right) > 1.0F) goto _L4; else goto _L3
_L3:
        return false;
        if((double)Math.abs(rectf.left - mScrollRect.left) > 1.0D) goto _L4; else goto _L5
_L5:
        return false;
    }

    public boolean getDoubleTapEnabled()
    {
        return mDoubleTapEnabled;
    }

    protected android.view.GestureDetector.OnGestureListener getGestureListener()
    {
        return new GestureListener();
    }

    protected android.view.ScaleGestureDetector.OnScaleGestureListener getScaleListener()
    {
        return new ScaleListener();
    }

    protected void init(Context context, AttributeSet attributeset, int i)
    {
        super.init(context, attributeset, i);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mGestureListener = getGestureListener();
        mScaleListener = getScaleListener();
        mScaleDetector = new ScaleGestureDetector(getContext(), mScaleListener);
        mGestureDetector = new GestureDetector(getContext(), mGestureListener, null, true);
        mDoubleTapDirection = 1;
    }

    protected float onDoubleTapPost(float f, float f1)
    {
        if(mDoubleTapDirection == 1)
        {
            if(mScaleFactor * 2.0F + f <= f1)
            {
                return f + mScaleFactor;
            } else
            {
                mDoubleTapDirection = -1;
                return f1;
            }
        } else
        {
            mDoubleTapDirection = 1;
            return 1.0F;
        }
    }

    public boolean onDown(MotionEvent motionevent)
    {
        switchViewPagerEnable(false);
        return !getBitmapChanged();
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        float f2 = motionevent1.getX();
        float f3 = motionevent.getX();
        float f4 = motionevent1.getY();
        float f5 = motionevent.getY();
        if(Math.abs(f) > 800F || Math.abs(f1) > 800F)
        {
            mUserScaled = true;
            scrollBy((f2 - f3) / 2.0F, (f4 - f5) / 2.0F, 300D);
            invalidate();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(getScale() == 1.0F)
        {
            switchViewPagerEnable(true);
            return false;
        } else
        {
            mUserScaled = true;
            scrollBy(-f, -f1);
            invalidate();
            return true;
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent motionevent)
    {
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return !getBitmapChanged();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(getBitmapChanged())
            return false;
        mScaleDetector.onTouchEvent(motionevent);
        if(!mScaleDetector.isInProgress())
            mGestureDetector.onTouchEvent(motionevent);
        switch(motionevent.getAction() & 0xff)
        {
        default:
            return true;

        case 1: // '\001'
            return onUp(motionevent);
        }
    }

    public boolean onUp(MotionEvent motionevent)
    {
        boolean flag = true;
        switchViewPagerEnable(true);
        if(getBitmapChanged())
            flag = false;
        else
        if(getScale() < getMinScale())
        {
            zoomTo(getMinScale(), 50F);
            return true;
        }
        return flag;
    }

    protected void onZoomAnimationCompleted(float f)
    {
        if(f < getMinScale())
            zoomTo(getMinScale(), 50F);
    }

    public void setDoubleTapEnabled(boolean flag)
    {
        mDoubleTapEnabled = flag;
    }

    public void setDoubleTapListener(OnImageViewTouchDoubleTapListener onimageviewtouchdoubletaplistener)
    {
        mDoubleTapListener = onimageviewtouchdoubletaplistener;
    }

    public void setScaleEnabled(boolean flag)
    {
        mScaleEnabled = flag;
    }

    public void setScrollEnabled(boolean flag)
    {
        mScrollEnabled = flag;
    }

    public void setSingleTapListener(OnImageViewTouchSingleTapListener onimageviewtouchsingletaplistener)
    {
        mSingleTapListener = onimageviewtouchsingletaplistener;
    }

    static final float SCROLL_DELTA_THRESHOLD = 1F;
    protected int mDoubleTapDirection;
    protected boolean mDoubleTapEnabled;
    private OnImageViewTouchDoubleTapListener mDoubleTapListener;
    protected GestureDetector mGestureDetector;
    protected android.view.GestureDetector.OnGestureListener mGestureListener;
    protected ScaleGestureDetector mScaleDetector;
    protected boolean mScaleEnabled;
    protected float mScaleFactor;
    protected android.view.ScaleGestureDetector.OnScaleGestureListener mScaleListener;
    protected boolean mScrollEnabled;
    private OnImageViewTouchSingleTapListener mSingleTapListener;
    protected int mTouchSlop;


}
