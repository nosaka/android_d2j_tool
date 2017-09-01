// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.widget.*;
import java.util.List;

// Referenced classes of package android.databinding.adapters:
//            ObservableListAdapter

public class AbsSpinnerBindingAdapter
{

    public AbsSpinnerBindingAdapter()
    {
    }

    public static void setEntries(AbsSpinner absspinner, List list)
    {
        if(list != null)
        {
            SpinnerAdapter spinneradapter = absspinner.getAdapter();
            if(spinneradapter instanceof ObservableListAdapter)
            {
                ((ObservableListAdapter)spinneradapter).setList(list);
                return;
            } else
            {
                absspinner.setAdapter(new ObservableListAdapter(absspinner.getContext(), list, 0x1090008, 0x1090009, 0));
                return;
            }
        } else
        {
            absspinner.setAdapter(null);
            return;
        }
    }

    public static void setEntries(AbsSpinner absspinner, CharSequence acharsequence[])
    {
        if(acharsequence == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        int i;
        SpinnerAdapter spinneradapter;
        spinneradapter = absspinner.getAdapter();
        i = 1;
        flag = i;
        if(spinneradapter == null) goto _L4; else goto _L3
_L3:
        flag = i;
        if(spinneradapter.getCount() != acharsequence.length) goto _L4; else goto _L5
_L5:
        boolean flag1;
        flag1 = false;
        i = 0;
_L9:
        flag = flag1;
        if(i >= acharsequence.length) goto _L4; else goto _L6
_L6:
        if(acharsequence[i].equals(spinneradapter.getItem(i))) goto _L8; else goto _L7
_L7:
        flag = true;
_L4:
        if(flag)
        {
            acharsequence = new ArrayAdapter(absspinner.getContext(), 0x1090008, acharsequence);
            acharsequence.setDropDownViewResource(0x1090009);
            absspinner.setAdapter(acharsequence);
        }
        return;
_L8:
        i++;
        if(true) goto _L9; else goto _L2
_L2:
        absspinner.setAdapter(null);
        return;
    }
}
