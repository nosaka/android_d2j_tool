// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.app.Activity;
import android.view.*;

// Referenced classes of package android.databinding:
//            DataBinderMapper, ViewDataBinding, DataBindingComponent

public class DataBindingUtil
{

    private DataBindingUtil()
    {
    }

    static ViewDataBinding bind(DataBindingComponent databindingcomponent, View view, int i)
    {
        return sMapper.getDataBinder(databindingcomponent, view, i);
    }

    static ViewDataBinding bind(DataBindingComponent databindingcomponent, View aview[], int i)
    {
        return sMapper.getDataBinder(databindingcomponent, aview, i);
    }

    public static ViewDataBinding bind(View view)
    {
        return bind(view, sDefaultComponent);
    }

    public static ViewDataBinding bind(View view, DataBindingComponent databindingcomponent)
    {
        Object obj = getBinding(view);
        if(obj != null)
            return ((ViewDataBinding) (obj));
        obj = view.getTag();
        if(!(obj instanceof String))
            throw new IllegalArgumentException("View is not a binding layout");
        obj = (String)obj;
        int i = sMapper.getLayoutId(((String) (obj)));
        if(i == 0)
            throw new IllegalArgumentException("View is not a binding layout");
        else
            return sMapper.getDataBinder(databindingcomponent, view, i);
    }

    private static ViewDataBinding bindToAddedViews(DataBindingComponent databindingcomponent, ViewGroup viewgroup, int i, int j)
    {
        int k = viewgroup.getChildCount();
        int i1 = k - i;
        if(i1 == 1)
            return bind(databindingcomponent, viewgroup.getChildAt(k - 1), j);
        View aview[] = new View[i1];
        for(int l = 0; l < i1; l++)
            aview[l] = viewgroup.getChildAt(l + i);

        return bind(databindingcomponent, aview, j);
    }

    public static String convertBrIdToString(int i)
    {
        return sMapper.convertBrIdToString(i);
    }

    public static ViewDataBinding findBinding(View view)
    {
_L8:
        Object obj;
        if(view == null)
            break; /* Loop/switch isn't completed */
        obj = ViewDataBinding.getBinding(view);
        if(obj != null)
            return ((ViewDataBinding) (obj));
        obj = view.getTag();
        if(!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        obj = (String)obj;
        if(!((String) (obj)).startsWith("layout") || !((String) (obj)).endsWith("_0")) goto _L2; else goto _L3
_L3:
        boolean flag1;
        char c;
        int i;
        c = ((String) (obj)).charAt(6);
        i = ((String) (obj)).indexOf('/', 7);
        flag1 = false;
        if(c != '/') goto _L5; else goto _L4
_L4:
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
_L6:
        if(flag)
            return null;
        break; /* Loop/switch isn't completed */
_L5:
        flag = flag1;
        if(c == '-')
        {
            flag = flag1;
            if(i != -1)
                if(((String) (obj)).indexOf('/', i + 1) == -1)
                    flag = true;
                else
                    flag = false;
        }
        if(true) goto _L6; else goto _L2
_L2:
        view = view.getParent();
        if(view instanceof View)
            view = (View)view;
        else
            view = null;
        if(true) goto _L8; else goto _L7
_L7:
        return null;
    }

    public static ViewDataBinding getBinding(View view)
    {
        return ViewDataBinding.getBinding(view);
    }

    public static DataBindingComponent getDefaultComponent()
    {
        return sDefaultComponent;
    }

    public static ViewDataBinding inflate(LayoutInflater layoutinflater, int i, ViewGroup viewgroup, boolean flag)
    {
        return inflate(layoutinflater, i, viewgroup, flag, sDefaultComponent);
    }

    public static ViewDataBinding inflate(LayoutInflater layoutinflater, int i, ViewGroup viewgroup, boolean flag, DataBindingComponent databindingcomponent)
    {
        int j = 0;
        boolean flag1;
        if(viewgroup != null && flag)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
            j = viewgroup.getChildCount();
        layoutinflater = layoutinflater.inflate(i, viewgroup, flag);
        if(flag1)
            return bindToAddedViews(databindingcomponent, viewgroup, j, i);
        else
            return bind(databindingcomponent, layoutinflater, i);
    }

    public static ViewDataBinding setContentView(Activity activity, int i)
    {
        return setContentView(activity, i, sDefaultComponent);
    }

    public static ViewDataBinding setContentView(Activity activity, int i, DataBindingComponent databindingcomponent)
    {
        activity.setContentView(i);
        return bindToAddedViews(databindingcomponent, (ViewGroup)activity.getWindow().getDecorView().findViewById(0x1020002), 0, i);
    }

    public static void setDefaultComponent(DataBindingComponent databindingcomponent)
    {
        sDefaultComponent = databindingcomponent;
    }

    private static DataBindingComponent sDefaultComponent = null;
    private static DataBinderMapper sMapper = new DataBinderMapper();

}
