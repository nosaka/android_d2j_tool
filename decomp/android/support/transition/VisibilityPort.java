// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            TransitionPort, TransitionValues

abstract class VisibilityPort extends TransitionPort
{
    private static class VisibilityInfo
    {

        ViewGroup endParent;
        int endVisibility;
        boolean fadeIn;
        ViewGroup startParent;
        int startVisibility;
        boolean visibilityChange;

        VisibilityInfo()
        {
        }
    }


    VisibilityPort()
    {
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getVisibility();
        transitionvalues.values.put("android:visibility:visibility", Integer.valueOf(i));
        transitionvalues.values.put("android:visibility:parent", transitionvalues.view.getParent());
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
        visibilityinfo = new VisibilityInfo();
        visibilityinfo.visibilityChange = false;
        visibilityinfo.fadeIn = false;
        if(transitionvalues != null)
        {
            visibilityinfo.startVisibility = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.startParent = (ViewGroup)transitionvalues.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.startVisibility = -1;
            visibilityinfo.startParent = null;
        }
        if(transitionvalues1 != null)
        {
            visibilityinfo.endVisibility = ((Integer)transitionvalues1.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.endParent = (ViewGroup)transitionvalues1.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.endVisibility = -1;
            visibilityinfo.endParent = null;
        }
        if(transitionvalues == null || transitionvalues1 == null) goto _L2; else goto _L1
_L1:
        if(visibilityinfo.startVisibility != visibilityinfo.endVisibility || visibilityinfo.startParent != visibilityinfo.endParent) goto _L4; else goto _L3
_L3:
        return visibilityinfo;
_L4:
        if(visibilityinfo.startVisibility == visibilityinfo.endVisibility) goto _L6; else goto _L5
_L5:
        if(visibilityinfo.startVisibility != 0) goto _L8; else goto _L7
_L7:
        visibilityinfo.fadeIn = false;
        visibilityinfo.visibilityChange = true;
_L2:
        if(transitionvalues == null)
        {
            visibilityinfo.fadeIn = true;
            visibilityinfo.visibilityChange = true;
            return visibilityinfo;
        }
        break; /* Loop/switch isn't completed */
_L8:
        if(visibilityinfo.endVisibility == 0)
        {
            visibilityinfo.fadeIn = true;
            visibilityinfo.visibilityChange = true;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(visibilityinfo.startParent != visibilityinfo.endParent)
            if(visibilityinfo.endParent == null)
            {
                visibilityinfo.fadeIn = false;
                visibilityinfo.visibilityChange = true;
            } else
            if(visibilityinfo.startParent == null)
            {
                visibilityinfo.fadeIn = true;
                visibilityinfo.visibilityChange = true;
            }
        if(true) goto _L2; else goto _L9
_L9:
        if(transitionvalues1 != null) goto _L3; else goto _L10
_L10:
        visibilityinfo.fadeIn = false;
        visibilityinfo.visibilityChange = true;
        return visibilityinfo;
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
label0:
        {
label1:
            {
                int j = -1;
                Object obj1 = null;
                visibilityinfo = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
                Object obj = obj1;
                if(!visibilityinfo.visibilityChange)
                    break label1;
                int i = 0;
                if(mTargets.size() > 0 || mTargetIds.size() > 0)
                {
                    View view;
                    if(transitionvalues != null)
                        obj = transitionvalues.view;
                    else
                        obj = null;
                    if(transitionvalues1 != null)
                        view = transitionvalues1.view;
                    else
                        view = null;
                    if(obj != null)
                        i = ((View) (obj)).getId();
                    else
                        i = -1;
                    if(view != null)
                        j = view.getId();
                    if(isValidTarget(((View) (obj)), i) || isValidTarget(view, j))
                        i = 1;
                    else
                        i = 0;
                }
                if(i == 0 && visibilityinfo.startParent == null)
                {
                    obj = obj1;
                    if(visibilityinfo.endParent == null)
                        break label1;
                }
                if(!visibilityinfo.fadeIn)
                    break label0;
                obj = onAppear(viewgroup, transitionvalues, visibilityinfo.startVisibility, transitionvalues1, visibilityinfo.endVisibility);
            }
            return ((Animator) (obj));
        }
        return onDisappear(viewgroup, transitionvalues, visibilityinfo.startVisibility, transitionvalues1, visibilityinfo.endVisibility);
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        if(transitionvalues == null)
            return false;
        int i = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
        transitionvalues = (View)transitionvalues.values.get("android:visibility:parent");
        boolean flag;
        if(i == 0 && transitionvalues != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return null;
    }

    public Animator onDisappear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        return null;
    }

    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String sTransitionProperties[] = {
        "android:visibility:visibility", "android:visibility:parent"
    };

}
