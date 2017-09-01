// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.*;
import android.support.v4.view.*;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils, AnimationUtils, SnackbarManager, SwipeDismissBehavior, 
//            CoordinatorLayout

public abstract class BaseTransientBottomBar
{
    public static abstract class BaseCallback
    {

        public void onDismissed(Object obj, int i)
        {
        }

        public void onShown(Object obj)
        {
        }

        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public BaseCallback()
        {
        }
    }

    public static interface BaseCallback.DismissEvent
        extends Annotation
    {
    }

    final class Behavior extends SwipeDismissBehavior
    {

        public boolean canSwipeDismissView(View view)
        {
            return view instanceof SnackbarBaseLayout;
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, SnackbarBaseLayout snackbarbaselayout, MotionEvent motionevent)
        {
            motionevent.getActionMasked();
            JVM INSTR tableswitch 0 3: default 36
        //                       0 44
        //                       1 78
        //                       2 36
        //                       3 78;
               goto _L1 _L2 _L3 _L1 _L3
_L1:
            return super.onInterceptTouchEvent(coordinatorlayout, snackbarbaselayout, motionevent);
_L2:
            if(coordinatorlayout.isPointInChildBounds(snackbarbaselayout, (int)motionevent.getX(), (int)motionevent.getY()))
                SnackbarManager.getInstance().pauseTimeout(mManagerCallback);
            continue; /* Loop/switch isn't completed */
_L3:
            SnackbarManager.getInstance().restoreTimeoutIfPaused(mManagerCallback);
            if(true) goto _L1; else goto _L4
_L4:
        }

        public volatile boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return onInterceptTouchEvent(coordinatorlayout, (SnackbarBaseLayout)view, motionevent);
        }

        final BaseTransientBottomBar this$0;

        Behavior()
        {
            this$0 = BaseTransientBottomBar.this;
            super();
        }
    }

    public static interface ContentViewCallback
    {

        public abstract void animateContentIn(int i, int j);

        public abstract void animateContentOut(int i, int j);
    }

    public static interface Duration
        extends Annotation
    {
    }

    static interface OnAttachStateChangeListener
    {

        public abstract void onViewAttachedToWindow(View view);

        public abstract void onViewDetachedFromWindow(View view);
    }

    static interface OnLayoutChangeListener
    {

        public abstract void onLayoutChange(View view, int i, int j, int k, int l);
    }

    static class SnackbarBaseLayout extends FrameLayout
    {

        protected void onAttachedToWindow()
        {
            super.onAttachedToWindow();
            if(mOnAttachStateChangeListener != null)
                mOnAttachStateChangeListener.onViewAttachedToWindow(this);
            ViewCompat.requestApplyInsets(this);
        }

        protected void onDetachedFromWindow()
        {
            super.onDetachedFromWindow();
            if(mOnAttachStateChangeListener != null)
                mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
            super.onLayout(flag, i, j, k, l);
            if(mOnLayoutChangeListener != null)
                mOnLayoutChangeListener.onLayoutChange(this, i, j, k, l);
        }

        void setOnAttachStateChangeListener(OnAttachStateChangeListener onattachstatechangelistener)
        {
            mOnAttachStateChangeListener = onattachstatechangelistener;
        }

        void setOnLayoutChangeListener(OnLayoutChangeListener onlayoutchangelistener)
        {
            mOnLayoutChangeListener = onlayoutchangelistener;
        }

        private OnAttachStateChangeListener mOnAttachStateChangeListener;
        private OnLayoutChangeListener mOnLayoutChangeListener;

        SnackbarBaseLayout(Context context)
        {
            this(context, null);
        }

        SnackbarBaseLayout(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.SnackbarLayout);
            if(context.hasValue(android.support.design.R.styleable.SnackbarLayout_elevation))
                ViewCompat.setElevation(this, context.getDimensionPixelSize(android.support.design.R.styleable.SnackbarLayout_elevation, 0));
            context.recycle();
            setClickable(true);
        }
    }


    protected BaseTransientBottomBar(ViewGroup viewgroup, View view, ContentViewCallback contentviewcallback)
    {
        if(viewgroup == null)
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        if(view == null)
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        if(contentviewcallback == null)
        {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        } else
        {
            mTargetParent = viewgroup;
            mContentViewCallback = contentviewcallback;
            mContext = viewgroup.getContext();
            ThemeUtils.checkAppCompatTheme(mContext);
            mView = (SnackbarBaseLayout)LayoutInflater.from(mContext).inflate(android.support.design.R.layout.design_layout_snackbar, mTargetParent, false);
            mView.addView(view);
            ViewCompat.setAccessibilityLiveRegion(mView, 1);
            ViewCompat.setImportantForAccessibility(mView, 1);
            ViewCompat.setFitsSystemWindows(mView, true);
            ViewCompat.setOnApplyWindowInsetsListener(mView, new OnApplyWindowInsetsListener() {

                public WindowInsetsCompat onApplyWindowInsets(View view1, WindowInsetsCompat windowinsetscompat)
                {
                    view1.setPadding(view1.getPaddingLeft(), view1.getPaddingTop(), view1.getPaddingRight(), windowinsetscompat.getSystemWindowInsetBottom());
                    return windowinsetscompat;
                }

                final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
            }
);
            mAccessibilityManager = (AccessibilityManager)mContext.getSystemService("accessibility");
            return;
        }
    }

    private void animateViewOut(final int event)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
        {
            ViewCompat.animate(mView).translationY(mView.getHeight()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new ViewPropertyAnimatorListenerAdapter() {

                public void onAnimationEnd(View view)
                {
                    onViewHidden(event);
                }

                public void onAnimationStart(View view)
                {
                    mContentViewCallback.animateContentOut(0, 180);
                }

                final BaseTransientBottomBar this$0;
                final int val$event;

            
            {
                this$0 = BaseTransientBottomBar.this;
                event = i;
                super();
            }
            }
).start();
            return;
        } else
        {
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_snackbar_out);
            animation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            animation.setDuration(250L);
            animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                public void onAnimationEnd(Animation animation1)
                {
                    onViewHidden(event);
                }

                public void onAnimationRepeat(Animation animation1)
                {
                }

                public void onAnimationStart(Animation animation1)
                {
                }

                final BaseTransientBottomBar this$0;
                final int val$event;

            
            {
                this$0 = BaseTransientBottomBar.this;
                event = i;
                super();
            }
            }
);
            mView.startAnimation(animation);
            return;
        }
    }

    public BaseTransientBottomBar addCallback(BaseCallback basecallback)
    {
        if(basecallback == null)
            return this;
        if(mCallbacks == null)
            mCallbacks = new ArrayList();
        mCallbacks.add(basecallback);
        return this;
    }

    void animateViewIn()
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
        {
            ViewCompat.setTranslationY(mView, mView.getHeight());
            ViewCompat.animate(mView).translationY(0.0F).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new ViewPropertyAnimatorListenerAdapter() {

                public void onAnimationEnd(View view)
                {
                    onViewShown();
                }

                public void onAnimationStart(View view)
                {
                    mContentViewCallback.animateContentIn(70, 180);
                }

                final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
            }
).start();
            return;
        } else
        {
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), android.support.design.R.anim.design_snackbar_in);
            animation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            animation.setDuration(250L);
            animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                public void onAnimationEnd(Animation animation1)
                {
                    onViewShown();
                }

                public void onAnimationRepeat(Animation animation1)
                {
                }

                public void onAnimationStart(Animation animation1)
                {
                }

                final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
            }
);
            mView.startAnimation(animation);
            return;
        }
    }

    public void dismiss()
    {
        dispatchDismiss(3);
    }

    void dispatchDismiss(int i)
    {
        SnackbarManager.getInstance().dismiss(mManagerCallback, i);
    }

    public Context getContext()
    {
        return mContext;
    }

    public int getDuration()
    {
        return mDuration;
    }

    public View getView()
    {
        return mView;
    }

    final void hideView(int i)
    {
        if(shouldAnimate() && mView.getVisibility() == 0)
        {
            animateViewOut(i);
            return;
        } else
        {
            onViewHidden(i);
            return;
        }
    }

    public boolean isShown()
    {
        return SnackbarManager.getInstance().isCurrent(mManagerCallback);
    }

    public boolean isShownOrQueued()
    {
        return SnackbarManager.getInstance().isCurrentOrNext(mManagerCallback);
    }

    void onViewHidden(int i)
    {
        SnackbarManager.getInstance().onDismissed(mManagerCallback);
        if(mCallbacks != null)
        {
            for(int j = mCallbacks.size() - 1; j >= 0; j--)
                ((BaseCallback)mCallbacks.get(j)).onDismissed(this, i);

        }
        if(android.os.Build.VERSION.SDK_INT < 11)
            mView.setVisibility(8);
        android.view.ViewParent viewparent = mView.getParent();
        if(viewparent instanceof ViewGroup)
            ((ViewGroup)viewparent).removeView(mView);
    }

    void onViewShown()
    {
        SnackbarManager.getInstance().onShown(mManagerCallback);
        if(mCallbacks != null)
        {
            for(int i = mCallbacks.size() - 1; i >= 0; i--)
                ((BaseCallback)mCallbacks.get(i)).onShown(this);

        }
    }

    public BaseTransientBottomBar removeCallback(BaseCallback basecallback)
    {
        while(basecallback == null || mCallbacks == null) 
            return this;
        mCallbacks.remove(basecallback);
        return this;
    }

    public BaseTransientBottomBar setDuration(int i)
    {
        mDuration = i;
        return this;
    }

    boolean shouldAnimate()
    {
        return !mAccessibilityManager.isEnabled();
    }

    public void show()
    {
        SnackbarManager.getInstance().show(mDuration, mManagerCallback);
    }

    final void showView()
    {
        if(mView.getParent() == null)
        {
            Object obj = mView.getLayoutParams();
            if(obj instanceof CoordinatorLayout.LayoutParams)
            {
                obj = (CoordinatorLayout.LayoutParams)obj;
                Behavior behavior = new Behavior();
                behavior.setStartAlphaSwipeDistance(0.1F);
                behavior.setEndAlphaSwipeDistance(0.6F);
                behavior.setSwipeDirection(0);
                behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {

                    public void onDismiss(View view)
                    {
                        view.setVisibility(8);
                        dispatchDismiss(0);
                    }

                    public void onDragStateChanged(int i)
                    {
                        switch(i)
                        {
                        default:
                            return;

                        case 1: // '\001'
                        case 2: // '\002'
                            SnackbarManager.getInstance().pauseTimeout(mManagerCallback);
                            return;

                        case 0: // '\0'
                            SnackbarManager.getInstance().restoreTimeoutIfPaused(mManagerCallback);
                            return;
                        }
                    }

                    final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
                }
);
                ((CoordinatorLayout.LayoutParams) (obj)).setBehavior(behavior);
                obj.insetEdge = 80;
            }
            mTargetParent.addView(mView);
        }
        mView.setOnAttachStateChangeListener(new OnAttachStateChangeListener() {

            public void onViewAttachedToWindow(View view)
            {
            }

            public void onViewDetachedFromWindow(View view)
            {
                if(isShownOrQueued())
                    BaseTransientBottomBar.sHandler.post(new Runnable() {

                        public void run()
                        {
                            onViewHidden(3);
                        }

                        final _cls5 this$1;

            
            {
                this$1 = _cls5.this;
                super();
            }
                    }
);
            }

            final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
        }
);
        if(ViewCompat.isLaidOut(mView))
        {
            if(shouldAnimate())
            {
                animateViewIn();
                return;
            } else
            {
                onViewShown();
                return;
            }
        } else
        {
            mView.setOnLayoutChangeListener(new OnLayoutChangeListener() {

                public void onLayoutChange(View view, int i, int j, int k, int l)
                {
                    mView.setOnLayoutChangeListener(null);
                    if(shouldAnimate())
                    {
                        animateViewIn();
                        return;
                    } else
                    {
                        onViewShown();
                        return;
                    }
                }

                final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
            }
);
            return;
        }
    }

    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    static final Handler sHandler = new Handler(Looper.getMainLooper(), new android.os.Handler.Callback() {

        public boolean handleMessage(Message message)
        {
            switch(message.what)
            {
            default:
                return false;

            case 0: // '\0'
                ((BaseTransientBottomBar)message.obj).showView();
                return true;

            case 1: // '\001'
                ((BaseTransientBottomBar)message.obj).hideView(message.arg1);
                break;
            }
            return true;
        }

    }
);
    private final AccessibilityManager mAccessibilityManager;
    private List mCallbacks;
    private final ContentViewCallback mContentViewCallback;
    private final Context mContext;
    private int mDuration;
    final SnackbarManager.Callback mManagerCallback = new SnackbarManager.Callback() {

        public void dismiss(int i)
        {
            BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }

        public void show()
        {
            BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(0, BaseTransientBottomBar.this));
        }

        final BaseTransientBottomBar this$0;

            
            {
                this$0 = BaseTransientBottomBar.this;
                super();
            }
    }
;
    private final ViewGroup mTargetParent;
    final SnackbarBaseLayout mView;


}
