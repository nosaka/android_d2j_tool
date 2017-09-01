// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

public class ViewGroupBindingAdapter
{
    public static interface OnAnimationEnd
    {

        public abstract void onAnimationEnd(Animation animation);
    }

    public static interface OnAnimationRepeat
    {

        public abstract void onAnimationRepeat(Animation animation);
    }

    public static interface OnAnimationStart
    {

        public abstract void onAnimationStart(Animation animation);
    }

    public static interface OnChildViewAdded
    {

        public abstract void onChildViewAdded(View view, View view1);
    }

    public static interface OnChildViewRemoved
    {

        public abstract void onChildViewRemoved(View view, View view1);
    }


    public ViewGroupBindingAdapter()
    {
    }

    public static void setAnimateLayoutChanges(ViewGroup viewgroup, boolean flag)
    {
        if(flag)
        {
            viewgroup.setLayoutTransition(new LayoutTransition());
            return;
        } else
        {
            viewgroup.setLayoutTransition(null);
            return;
        }
    }

    public static void setListener(ViewGroup viewgroup, OnAnimationStart onanimationstart, OnAnimationEnd onanimationend, OnAnimationRepeat onanimationrepeat)
    {
        if(onanimationstart == null && onanimationend == null && onanimationrepeat == null)
        {
            viewgroup.setLayoutAnimationListener(null);
            return;
        } else
        {
            viewgroup.setLayoutAnimationListener(new android.view.animation.Animation.AnimationListener(onanimationstart, onanimationend, onanimationrepeat) {

                public void onAnimationEnd(Animation animation)
                {
                    if(end != null)
                        end.onAnimationEnd(animation);
                }

                public void onAnimationRepeat(Animation animation)
                {
                    if(repeat != null)
                        repeat.onAnimationRepeat(animation);
                }

                public void onAnimationStart(Animation animation)
                {
                    if(start != null)
                        start.onAnimationStart(animation);
                }

                final OnAnimationEnd val$end;
                final OnAnimationRepeat val$repeat;
                final OnAnimationStart val$start;

            
            {
                start = onanimationstart;
                end = onanimationend;
                repeat = onanimationrepeat;
                super();
            }
            }
);
            return;
        }
    }

    public static void setListener(ViewGroup viewgroup, OnChildViewAdded onchildviewadded, OnChildViewRemoved onchildviewremoved)
    {
        if(onchildviewadded == null && onchildviewremoved == null)
        {
            viewgroup.setOnHierarchyChangeListener(null);
            return;
        } else
        {
            viewgroup.setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener(onchildviewadded, onchildviewremoved) {

                public void onChildViewAdded(View view, View view1)
                {
                    if(added != null)
                        added.onChildViewAdded(view, view1);
                }

                public void onChildViewRemoved(View view, View view1)
                {
                    if(removed != null)
                        removed.onChildViewRemoved(view, view1);
                }

                final OnChildViewAdded val$added;
                final OnChildViewRemoved val$removed;

            
            {
                added = onchildviewadded;
                removed = onchildviewremoved;
                super();
            }
            }
);
            return;
        }
    }
}
