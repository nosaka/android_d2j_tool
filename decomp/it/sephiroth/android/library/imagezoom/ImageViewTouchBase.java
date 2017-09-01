// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import it.sephiroth.android.library.imagezoom.easing.Cubic;
import it.sephiroth.android.library.imagezoom.easing.Easing;
import it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable;
import it.sephiroth.android.library.imagezoom.utils.IDisposable;

// Referenced classes of package it.sephiroth.android.library.imagezoom:
//            ViewPagerControllable

public abstract class ImageViewTouchBase extends ImageView
    implements IDisposable
{
    public static final class DisplayType extends Enum
    {

        public static DisplayType valueOf(String s)
        {
            return (DisplayType)Enum.valueOf(it/sephiroth/android/library/imagezoom/ImageViewTouchBase$DisplayType, s);
        }

        public static DisplayType[] values()
        {
            return (DisplayType[])$VALUES.clone();
        }

        private static final DisplayType $VALUES[];
        public static final DisplayType FIT_IF_BIGGER;
        public static final DisplayType FIT_TO_SCREEN;
        public static final DisplayType NONE;

        static 
        {
            NONE = new DisplayType("NONE", 0);
            FIT_TO_SCREEN = new DisplayType("FIT_TO_SCREEN", 1);
            FIT_IF_BIGGER = new DisplayType("FIT_IF_BIGGER", 2);
            $VALUES = (new DisplayType[] {
                NONE, FIT_TO_SCREEN, FIT_IF_BIGGER
            });
        }

        private DisplayType(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface OnDrawableChangeListener
    {

        public abstract void onDrawableChanged(Drawable drawable);
    }

    public static interface OnLayoutChangeListener
    {

        public abstract void onLayoutChanged(boolean flag, int i, int j, int k, int l);
    }


    public ImageViewTouchBase(Context context)
    {
        this(context, null);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mEasing = new Cubic();
        mBaseMatrix = new Matrix();
        mSuppMatrix = new Matrix();
        mHandler = new Handler();
        mLayoutRunnable = null;
        mUserScaled = false;
        mMaxZoom = -1F;
        mMinZoom = -1F;
        mDisplayMatrix = new Matrix();
        mMatrixValues = new float[9];
        mThisWidth = -1;
        mThisHeight = -1;
        mCenter = new PointF();
        mScaleType = DisplayType.FIT_IF_BIGGER;
        DEFAULT_ANIMATION_DURATION = 200;
        mBitmapRect = new RectF();
        mCenterRect = new RectF();
        mScrollRect = new RectF();
        init(context, attributeset, i);
    }

    protected void _setImageDrawable(Drawable drawable, Matrix matrix, float f, float f1)
    {
        mBaseMatrix.reset();
        if(drawable != null)
            super.setImageDrawable(drawable);
        else
            super.setImageDrawable(null);
        if(f != -1F && f1 != -1F)
        {
            f = Math.min(f, f1);
            f1 = Math.max(f, f1);
            mMinZoom = f;
            mMaxZoom = f1;
            mMinZoomDefined = true;
            mMaxZoomDefined = true;
            if(mScaleType == DisplayType.FIT_TO_SCREEN || mScaleType == DisplayType.FIT_IF_BIGGER)
            {
                if(mMinZoom >= 1.0F)
                {
                    mMinZoomDefined = false;
                    mMinZoom = -1F;
                }
                if(mMaxZoom <= 1.0F)
                {
                    mMaxZoomDefined = true;
                    mMaxZoom = -1F;
                }
            }
        } else
        {
            mMinZoom = -1F;
            mMaxZoom = -1F;
            mMinZoomDefined = false;
            mMaxZoomDefined = false;
        }
        if(matrix != null)
            mNextMatrix = new Matrix(matrix);
        mBitmapChanged = true;
        requestLayout();
    }

    protected void center(boolean flag, boolean flag1)
    {
        RectF rectf;
        if(getDrawable() != null)
            if((rectf = getCenter(mSuppMatrix, flag, flag1)).left != 0.0F || rectf.top != 0.0F)
            {
                postTranslate(rectf.left, rectf.top);
                return;
            }
    }

    public void clear()
    {
        setImageBitmap(null);
    }

    protected float computeMaxZoom()
    {
        Drawable drawable = getDrawable();
        if(drawable == null)
            return 1.0F;
        else
            return Math.max((float)drawable.getIntrinsicWidth() / (float)mThisWidth, (float)drawable.getIntrinsicHeight() / (float)mThisHeight) * 8F;
    }

    protected float computeMinZoom()
    {
        if(getDrawable() == null)
            return 1.0F;
        else
            return Math.min(1.0F, 1.0F / getScale(mBaseMatrix));
    }

    public void dispose()
    {
        clear();
    }

    protected void fireOnDrawableChangeListener(Drawable drawable)
    {
        if(mDrawableChangeListener != null)
            mDrawableChangeListener.onDrawableChanged(drawable);
    }

    protected void fireOnLayoutChangeListener(int i, int j, int k, int l)
    {
        if(mOnLayoutChangeListener != null)
            mOnLayoutChangeListener.onLayoutChanged(true, i, j, k, l);
    }

    public float getBaseScale()
    {
        return getScale(mBaseMatrix);
    }

    public boolean getBitmapChanged()
    {
        return mBitmapChanged;
    }

    public RectF getBitmapRect()
    {
        return getBitmapRect(mSuppMatrix);
    }

    protected RectF getBitmapRect(Matrix matrix)
    {
        Drawable drawable = getDrawable();
        if(drawable == null)
        {
            return null;
        } else
        {
            matrix = getImageViewMatrix(matrix);
            mBitmapRect.set(0.0F, 0.0F, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            matrix.mapRect(mBitmapRect);
            return mBitmapRect;
        }
    }

    protected PointF getCenter()
    {
        return mCenter;
    }

    protected RectF getCenter(Matrix matrix, boolean flag, boolean flag1)
    {
        if(getDrawable() == null)
            return new RectF(0.0F, 0.0F, 0.0F, 0.0F);
        mCenterRect.set(0.0F, 0.0F, 0.0F, 0.0F);
        matrix = getBitmapRect(matrix);
        float f4 = matrix.height();
        float f3 = matrix.width();
        float f2 = 0.0F;
        float f1 = 0.0F;
        float f = f1;
        int i;
        if(flag1)
        {
            i = mThisHeight;
            if(f4 < (float)i)
                f = ((float)i - f4) / 2.0F - ((RectF) (matrix)).top;
            else
            if(((RectF) (matrix)).top > 0.0F)
            {
                f = -((RectF) (matrix)).top;
            } else
            {
                f = f1;
                if(((RectF) (matrix)).bottom < (float)i)
                    f = (float)mThisHeight - ((RectF) (matrix)).bottom;
            }
        }
        f1 = f2;
        if(flag)
        {
            i = mThisWidth;
            if(f3 < (float)i)
                f1 = ((float)i - f3) / 2.0F - ((RectF) (matrix)).left;
            else
            if(((RectF) (matrix)).left > 0.0F)
            {
                f1 = -((RectF) (matrix)).left;
            } else
            {
                f1 = f2;
                if(((RectF) (matrix)).right < (float)i)
                    f1 = (float)i - ((RectF) (matrix)).right;
            }
        }
        mCenterRect.set(f1, f, 0.0F, 0.0F);
        return mCenterRect;
    }

    protected float getDefaultScale(DisplayType displaytype)
    {
        if(displaytype == DisplayType.FIT_TO_SCREEN)
            return 1.0F;
        if(displaytype == DisplayType.FIT_IF_BIGGER)
            return Math.min(1.0F, 1.0F / getScale(mBaseMatrix));
        else
            return 1.0F / getScale(mBaseMatrix);
    }

    public Matrix getDisplayMatrix()
    {
        return new Matrix(mSuppMatrix);
    }

    public DisplayType getDisplayType()
    {
        return mScaleType;
    }

    public Matrix getImageViewMatrix()
    {
        return getImageViewMatrix(mSuppMatrix);
    }

    public Matrix getImageViewMatrix(Matrix matrix)
    {
        mDisplayMatrix.set(mBaseMatrix);
        mDisplayMatrix.postConcat(matrix);
        return mDisplayMatrix;
    }

    public float getMaxScale()
    {
        if(mMaxZoom == -1F)
            mMaxZoom = computeMaxZoom();
        return mMaxZoom;
    }

    public float getMinScale()
    {
        if(mMinZoom == -1F)
            mMinZoom = computeMinZoom();
        return mMinZoom;
    }

    protected void getProperBaseMatrix(Drawable drawable, Matrix matrix)
    {
        float f = mThisWidth;
        float f1 = mThisHeight;
        float f2 = drawable.getIntrinsicWidth();
        float f3 = drawable.getIntrinsicHeight();
        matrix.reset();
        if(f2 > f || f3 > f1)
        {
            float f4 = Math.min(f / f2, f1 / f3);
            matrix.postScale(f4, f4);
            matrix.postTranslate((f - f2 * f4) / 2.0F, (f1 - f3 * f4) / 2.0F);
            return;
        } else
        {
            float f5 = Math.min(f / f2, f1 / f3);
            matrix.postScale(f5, f5);
            matrix.postTranslate((f - f2 * f5) / 2.0F, (f1 - f3 * f5) / 2.0F);
            return;
        }
    }

    protected void getProperBaseMatrix2(Drawable drawable, Matrix matrix)
    {
        float f = mThisWidth;
        float f1 = mThisHeight;
        float f2 = drawable.getIntrinsicWidth();
        float f3 = drawable.getIntrinsicHeight();
        matrix.reset();
        float f4 = Math.min(f / f2, f1 / f3);
        matrix.postScale(f4, f4);
        matrix.postTranslate((f - f2 * f4) / 2.0F, (f1 - f3 * f4) / 2.0F);
    }

    public float getRotation()
    {
        return 0.0F;
    }

    public float getScale()
    {
        return getScale(mSuppMatrix);
    }

    protected float getScale(Matrix matrix)
    {
        return getValue(matrix, 0);
    }

    protected float getValue(Matrix matrix, int i)
    {
        matrix.getValues(mMatrixValues);
        return mMatrixValues[i];
    }

    protected void init(Context context, AttributeSet attributeset, int i)
    {
        setScaleType(android.widget.ImageView.ScaleType.MATRIX);
    }

    protected void onDrawableChanged(Drawable drawable)
    {
        fireOnDrawableChangeListener(drawable);
    }

    protected void onImageMatrixChanged()
    {
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        Object obj;
        super.onLayout(flag, i, j, k, l);
        i1 = 0;
        j1 = 0;
        if(flag)
        {
            i1 = mThisWidth;
            j1 = mThisHeight;
            mThisWidth = k - i;
            mThisHeight = l - j;
            i1 = mThisWidth - i1;
            j1 = mThisHeight - j1;
            mCenter.x = (float)mThisWidth / 2.0F;
            mCenter.y = (float)mThisHeight / 2.0F;
        }
        obj = mLayoutRunnable;
        if(obj != null)
        {
            mLayoutRunnable = null;
            ((Runnable) (obj)).run();
        }
        obj = getDrawable();
        if(obj == null) goto _L2; else goto _L1
_L1:
        if(!flag && !mScaleTypeChanged && !mBitmapChanged) goto _L4; else goto _L3
_L3:
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        if(mBitmapChanged)
        {
            mBaseMatrix.reset();
            if(!mMinZoomDefined)
                mMinZoom = -1F;
            if(!mMaxZoomDefined)
                mMaxZoom = -1F;
        }
        f1 = 1.0F;
        getDefaultScale(mScaleType);
        f2 = getScale(mBaseMatrix);
        f3 = getScale();
        f4 = Math.min(1.0F, 1.0F / f2);
        getProperBaseMatrix(((Drawable) (obj)), mBaseMatrix);
        f5 = getScale(mBaseMatrix);
        if(!mBitmapChanged && !mScaleTypeChanged) goto _L6; else goto _L5
_L5:
        float f;
        if(mNextMatrix != null)
        {
            mSuppMatrix.set(mNextMatrix);
            mNextMatrix = null;
            f1 = getScale();
        } else
        {
            mSuppMatrix.reset();
            f1 = getDefaultScale(mScaleType);
        }
        setImageMatrix(getImageViewMatrix());
        f = f1;
        if(f1 != getScale())
        {
            zoomTo(f1);
            f = f1;
        }
_L9:
        mUserScaled = false;
        if(f > getMaxScale() || f < getMinScale())
            zoomTo(f);
        center(true, true);
        if(mBitmapChanged)
            onDrawableChanged(((Drawable) (obj)));
        if(flag || mBitmapChanged || mScaleTypeChanged)
            onLayoutChanged(i, j, k, l);
        if(mScaleTypeChanged)
            mScaleTypeChanged = false;
        if(mBitmapChanged)
            mBitmapChanged = false;
_L4:
        return;
_L6:
        f = f1;
        if(flag)
        {
            if(!mMinZoomDefined)
                mMinZoom = -1F;
            if(!mMaxZoomDefined)
                mMaxZoom = -1F;
            setImageMatrix(getImageViewMatrix());
            postTranslate(-i1, -j1);
            if(!mUserScaled)
            {
                f = getDefaultScale(mScaleType);
                zoomTo(f);
            } else
            {
                f = f1;
                if((double)Math.abs(f3 - f4) > 0.001D)
                    f = (f2 / f5) * f3;
                zoomTo(f);
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(mBitmapChanged)
            onDrawableChanged(((Drawable) (obj)));
        if(flag || mBitmapChanged || mScaleTypeChanged)
            onLayoutChanged(i, j, k, l);
        if(mBitmapChanged)
            mBitmapChanged = false;
        if(!mScaleTypeChanged) goto _L4; else goto _L7
_L7:
        mScaleTypeChanged = false;
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    protected void onLayoutChanged(int i, int j, int k, int l)
    {
        fireOnLayoutChangeListener(i, j, k, l);
    }

    protected void onZoom(float f)
    {
    }

    protected void onZoomAnimationCompleted(float f)
    {
    }

    protected void panBy(double d, double d1)
    {
        RectF rectf = getBitmapRect();
        mScrollRect.set((float)d, (float)d1, 0.0F, 0.0F);
        updateRect(rectf, mScrollRect);
        postTranslate(mScrollRect.left, mScrollRect.top);
        center(true, true);
    }

    protected void postScale(float f, float f1, float f2)
    {
        mSuppMatrix.postScale(f, f, f1, f2);
        setImageMatrix(getImageViewMatrix());
    }

    protected void postTranslate(float f, float f1)
    {
        if(f != 0.0F || f1 != 0.0F)
        {
            mSuppMatrix.postTranslate(f, f1);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public void printMatrix(Matrix matrix)
    {
        float f = getValue(matrix, 0);
        float f1 = getValue(matrix, 4);
        float f2 = getValue(matrix, 2);
        float f3 = getValue(matrix, 5);
        Log.d("ImageViewTouchBase", (new StringBuilder()).append("matrix: { x: ").append(f2).append(", y: ").append(f3).append(", scalex: ").append(f).append(", scaley: ").append(f1).append(" }").toString());
    }

    public void resetDisplay()
    {
        mBitmapChanged = true;
        requestLayout();
    }

    public void resetMatrix()
    {
        mSuppMatrix = new Matrix();
        float f = getDefaultScale(mScaleType);
        setImageMatrix(getImageViewMatrix());
        if(f != getScale())
            zoomTo(f);
        postInvalidate();
    }

    public void scrollBy(float f, float f1)
    {
        panBy(f, f1);
    }

    protected void scrollBy(float f, float f1, final double durationMs)
    {
        final double dx = f;
        final double dy = f1;
        final long startTime = System.currentTimeMillis();
        mHandler.post(new Runnable() {

            public void run()
            {
                long l = System.currentTimeMillis();
                double d = Math.min(durationMs, l - startTime);
                double d1 = mEasing.easeOut(d, 0.0D, dx, durationMs);
                double d2 = mEasing.easeOut(d, 0.0D, dy, durationMs);
                panBy(d1 - old_x, d2 - old_y);
                old_x = d1;
                old_y = d2;
                if(d < durationMs)
                {
                    mHandler.post(this);
                } else
                {
                    RectF rectf = getCenter(mSuppMatrix, true, true);
                    if(rectf.left != 0.0F || rectf.top != 0.0F)
                    {
                        scrollBy(rectf.left, rectf.top);
                        return;
                    }
                }
            }

            double old_x;
            double old_y;
            final ImageViewTouchBase this$0;
            final double val$durationMs;
            final double val$dx;
            final double val$dy;
            final long val$startTime;

            
            {
                this$0 = ImageViewTouchBase.this;
                durationMs = d;
                startTime = l;
                dx = d1;
                dy = d2;
                super();
                old_x = 0.0D;
                old_y = 0.0D;
            }
        }
);
    }

    public void setDisplayType(DisplayType displaytype)
    {
        if(displaytype != mScaleType)
        {
            mUserScaled = false;
            mScaleType = displaytype;
            mScaleTypeChanged = true;
            requestLayout();
        }
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        setImageBitmap(bitmap, null, -1F, -1F);
    }

    public void setImageBitmap(Bitmap bitmap, Matrix matrix, float f, float f1)
    {
        if(bitmap != null)
        {
            setImageDrawable(new FastBitmapDrawable(bitmap), matrix, f, f1);
            return;
        } else
        {
            setImageDrawable(null, matrix, f, f1);
            return;
        }
    }

    public void setImageDrawable(Drawable drawable)
    {
        setImageDrawable(drawable, null, -1F, -1F);
    }

    public void setImageDrawable(final Drawable drawable, final Matrix initial_matrix, final float min_zoom, final float max_zoom)
    {
        if(getWidth() <= 0)
        {
            mLayoutRunnable = new Runnable() {

                public void run()
                {
                    setImageDrawable(drawable, initial_matrix, min_zoom, max_zoom);
                }

                final ImageViewTouchBase this$0;
                final Drawable val$drawable;
                final Matrix val$initial_matrix;
                final float val$max_zoom;
                final float val$min_zoom;

            
            {
                this$0 = ImageViewTouchBase.this;
                drawable = drawable1;
                initial_matrix = matrix;
                min_zoom = f;
                max_zoom = f1;
                super();
            }
            }
;
            return;
        } else
        {
            _setImageDrawable(drawable, initial_matrix, min_zoom, max_zoom);
            return;
        }
    }

    public void setImageMatrix(Matrix matrix)
    {
        boolean flag;
label0:
        {
            Matrix matrix1 = getImageMatrix();
            boolean flag1 = false;
            if(matrix != null || matrix1.isIdentity())
            {
                flag = flag1;
                if(matrix == null)
                    break label0;
                flag = flag1;
                if(matrix1.equals(matrix))
                    break label0;
            }
            flag = true;
        }
        super.setImageMatrix(matrix);
        if(flag)
            onImageMatrixChanged();
    }

    public void setImageResource(int i)
    {
        setImageDrawable(getContext().getResources().getDrawable(i));
    }

    protected void setMaxScale(float f)
    {
        mMaxZoom = f;
    }

    protected void setMinScale(float f)
    {
        mMinZoom = f;
    }

    public void setOnDrawableChangedListener(OnDrawableChangeListener ondrawablechangelistener)
    {
        mDrawableChangeListener = ondrawablechangelistener;
    }

    public void setOnLayoutChangeListener(OnLayoutChangeListener onlayoutchangelistener)
    {
        mOnLayoutChangeListener = onlayoutchangelistener;
    }

    public void setScaleType(android.widget.ImageView.ScaleType scaletype)
    {
        if(scaletype == android.widget.ImageView.ScaleType.MATRIX)
        {
            super.setScaleType(scaletype);
            return;
        } else
        {
            Log.w("ImageViewTouchBase", "Unsupported scaletype. Only MATRIX can be used");
            return;
        }
    }

    public void setViewPagerControllable(ViewPagerControllable viewpagercontrollable)
    {
        mViewPagerControllable = viewpagercontrollable;
    }

    protected void switchViewPagerEnable(boolean flag)
    {
        if(mViewPagerControllable != null)
            mViewPagerControllable.switchEnable(flag);
    }

    protected void updateRect(RectF rectf, RectF rectf1)
    {
        if(rectf != null)
        {
            if(rectf.top >= 0.0F && rectf.bottom <= (float)mThisHeight)
                rectf1.top = 0.0F;
            if(rectf.top + rectf1.top >= 0.0F && rectf.bottom > (float)mThisHeight)
                rectf1.top = (int)(0.0F - rectf.top);
            if(rectf.bottom + rectf1.top <= (float)mThisHeight && rectf.top < 0.0F)
                rectf1.top = (int)((float)(mThisHeight + 0) - rectf.bottom);
            if(rectf.left + rectf1.left >= 0.0F)
            {
                rectf1.left = (int)(0.0F - rectf.left);
                switchViewPagerEnable(true);
            }
            if(rectf.right + rectf1.left <= (float)mThisWidth)
            {
                rectf1.left = (int)((float)(mThisWidth + 0) - rectf.right);
                switchViewPagerEnable(true);
                return;
            }
        }
    }

    protected void zoomTo(float f)
    {
        float f1 = f;
        if(f > getMaxScale())
            f1 = getMaxScale();
        f = f1;
        if(f1 < getMinScale())
            f = getMinScale();
        PointF pointf = getCenter();
        zoomTo(f, pointf.x, pointf.y);
    }

    public void zoomTo(float f, float f1)
    {
        PointF pointf = getCenter();
        zoomTo(f, pointf.x, pointf.y, f1);
    }

    protected void zoomTo(float f, float f1, float f2)
    {
        float f3 = f;
        if(f > getMaxScale())
            f3 = getMaxScale();
        postScale(f3 / getScale(), f1, f2);
        onZoom(getScale());
        center(true, true);
    }

    protected void zoomTo(float f, float f1, float f2, final float durationMs)
    {
        float f3 = f;
        if(f > getMaxScale())
            f3 = getMaxScale();
        final long startTime = System.currentTimeMillis();
        f = getScale();
        Object obj = new Matrix(mSuppMatrix);
        ((Matrix) (obj)).postScale(f3, f3, f1, f2);
        obj = getCenter(((Matrix) (obj)), true, true);
        float f4 = ((RectF) (obj)).left;
        float f5 = ((RectF) (obj)).top;
        mHandler.post(new Runnable() {

            public void run()
            {
                long l = System.currentTimeMillis();
                float f6 = Math.min(durationMs, l - startTime);
                float f7 = (float)mEasing.easeInOut(f6, 0.0D, deltaScale, durationMs);
                zoomTo(oldScale + f7, destX, destY);
                if(f6 < durationMs)
                {
                    mHandler.post(this);
                    return;
                } else
                {
                    onZoomAnimationCompleted(getScale());
                    center(true, true);
                    return;
                }
            }

            final ImageViewTouchBase this$0;
            final float val$deltaScale;
            final float val$destX;
            final float val$destY;
            final float val$durationMs;
            final float val$oldScale;
            final long val$startTime;

            
            {
                this$0 = ImageViewTouchBase.this;
                durationMs = f;
                startTime = l;
                deltaScale = f1;
                oldScale = f2;
                destX = f3;
                destY = f4;
                super();
            }
        }
);
    }

    protected static final boolean LOG_ENABLED = false;
    public static final String LOG_TAG = "ImageViewTouchBase";
    public static final String VERSION = "1.0.5-SNAPSHOT";
    public static final float ZOOM_INVALID = -1F;
    protected final int DEFAULT_ANIMATION_DURATION;
    protected Matrix mBaseMatrix;
    private boolean mBitmapChanged;
    protected RectF mBitmapRect;
    private PointF mCenter;
    protected RectF mCenterRect;
    protected final Matrix mDisplayMatrix;
    private OnDrawableChangeListener mDrawableChangeListener;
    protected Easing mEasing;
    protected Handler mHandler;
    protected Runnable mLayoutRunnable;
    protected final float mMatrixValues[];
    private float mMaxZoom;
    private boolean mMaxZoomDefined;
    private float mMinZoom;
    private boolean mMinZoomDefined;
    protected Matrix mNextMatrix;
    private OnLayoutChangeListener mOnLayoutChangeListener;
    protected DisplayType mScaleType;
    private boolean mScaleTypeChanged;
    protected RectF mScrollRect;
    protected Matrix mSuppMatrix;
    private int mThisHeight;
    private int mThisWidth;
    protected boolean mUserScaled;
    private ViewPagerControllable mViewPagerControllable;
}
