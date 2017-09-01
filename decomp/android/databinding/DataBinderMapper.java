// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding;

import android.view.View;
import net.wakamesoba98.sobacha.databinding.ProfileActivityBinding;

// Referenced classes of package android.databinding:
//            DataBindingComponent, ViewDataBinding

class DataBinderMapper
{
    private static class InnerBrLookup
    {

        static String sKeys[] = {
            "_all", "description", "handler", "location", "name", "profile", "url"
        };


        private InnerBrLookup()
        {
        }
    }


    public DataBinderMapper()
    {
    }

    String convertBrIdToString(int i)
    {
        if(i < 0 || i >= InnerBrLookup.sKeys.length)
            return null;
        else
            return InnerBrLookup.sKeys[i];
    }

    public ViewDataBinding getDataBinder(DataBindingComponent databindingcomponent, View view, int i)
    {
        switch(i)
        {
        default:
            return null;

        case 2130903138: 
            return ProfileActivityBinding.bind(view, databindingcomponent);
        }
    }

    ViewDataBinding getDataBinder(DataBindingComponent databindingcomponent, View aview[], int i)
    {
        return null;
    }

    int getLayoutId(String s)
    {
        if(s != null)
        {
            switch(s.hashCode())
            {
            default:
                return 0;

            case -1369697701: 
                break;
            }
            if(s.equals("layout/profile_activity_0"))
                return 0x7f030062;
        }
        return 0;
    }

    static final int TARGET_MIN_SDK = 21;
}
