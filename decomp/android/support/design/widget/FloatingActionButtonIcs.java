// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.ViewPropertyAnimator;

// Referenced classes of package android.support.design.widget:
//            FloatingActionButtonGingerbread, VisibilityAwareImageButton, ShadowDrawableWrapper, CircularBorderDrawable, 
//            AnimationUtils, ShadowViewDelegate

class FloatingActionButtonIcs extends FloatingActionButtonGingerbread
{

    FloatingActionButtonIcs(VisibilityAwareImageButton visibilityawareimagebutton, ShadowViewDelegate shadowviewdelegate, ValueAnimatorCompat.Creator creator)
    {
        super(visibilityawareimagebutton, shadowviewdelegate, creator);
        mRotation = mView.getRotation();
    }

    private boolean shouldAnimateVisibilityChange()
    {
        return ViewCompat.isLaidOut(mView) && !mView.isInEditMode();
    }

    private void updateFromViewRotation()
    {
        if(android.os.Build.VERSION.SDK_INT != 19) goto _L2; else goto _L1
_L1:
        if(mRotation % 90F == 0.0F) goto _L4; else goto _L3
_L3:
        if(mView.getLayerType() != 1)
            mView.setLayerType(1, null);
_L2:
        if(mShadowDrawable != null)
            mShadowDrawable.setRotation(-mRotation);
        if(mBorderDrawable != null)
            mBorderDrawable.setRotation(-mRotation);
        return;
_L4:
        if(mView.getLayerType() != 0)
            mView.setLayerType(0, null);
        if(true) goto _L2; else goto _L5
_L5:
    }

    void hide(final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, final boolean fromUser)
    {
        if(!isOrWillBeHidden())
        {
            mView.animate().cancel();
            if(shouldAnimateVisibilityChange())
            {
                mAnimState = 1;
                mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                    public void onAnimationCancel(Animator animator)
                    {
                        mCancelled = true;
                    }

                    public void onAnimationEnd(Animator animator)
                    {
                        mAnimState = 0;
                        if(!mCancelled)
                        {
                            animator = mView;
                            byte byte1;
                            if(fromUser)
                                byte1 = 8;
                            else
                                byte1 = 4;
                            animator.internalSetVisibility(byte1, fromUser);
                            if(listener != null)
                                listener.onHidden();
                        }
                    }

                    public void onAnimationStart(Animator animator)
                    {
                        mView.internalSetVisibility(0, fromUser);
                        mCancelled = false;
                    }

                    private boolean mCancelled;
                    final FloatingActionButtonIcs this$0;
                    final boolean val$fromUser;
                    final FloatingActionButtonImpl.InternalVisibilityChangedListener val$listener;

            
            {
                this$0 = FloatingActionButtonIcs.this;
                fromUser = flag;
                listener = internalvisibilitychangedlistener;
                super();
            }
                }
);
                return;
            }
            VisibilityAwareImageButton visibilityawareimagebutton = mView;
            byte byte0;
            if(fromUser)
                byte0 = 8;
            else
                byte0 = 4;
            visibilityawareimagebutton.internalSetVisibility(byte0, fromUser);
            if(listener != null)
            {
                listener.onHidden();
                return;
            }
        }
    }

    void onPreDraw()
    {
        float f = mView.getRotation();
        if(mRotation != f)
        {
            mRotation = f;
            updateFromViewRotation();
        }
    }

    boolean requirePreDrawListener()
    {
        return true;
    }

    void show(final FloatingActionButtonImpl.InternalVisibilityChangedListener listener, final boolean fromUser)
    {
        if(!isOrWillBeShown())
        {
            mView.animate().cancel();
            if(shouldAnimateVisibilityChange())
            {
                mAnimState = 2;
                if(mView.getVisibility() != 0)
                {
                    mView.setAlpha(0.0F);
                    mView.setScaleY(0.0F);
                    mView.setScaleX(0.0F);
                }
                mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        mAnimState = 0;
                        if(listener != null)
                            listener.onShown();
                    }

                    public void onAnimationStart(Animator animator)
                    {
                        mView.internalSetVisibility(0, fromUser);
                    }

                    final FloatingActionButtonIcs this$0;
                    final boolean val$fromUser;
                    final FloatingActionButtonImpl.InternalVisibilityChangedListener val$listener;

            
            {
                this$0 = FloatingActionButtonIcs.this;
                fromUser = flag;
                listener = internalvisibilitychangedlistener;
                super();
            }
                }
);
                return;
            }
            mView.internalSetVisibility(0, fromUser);
            mView.setAlpha(1.0F);
            mView.setScaleY(1.0F);
            mView.setScaleX(1.0F);
            if(listener != null)
            {
                listener.onShown();
                return;
            }
        }
    }

    private float mRotation;
}
