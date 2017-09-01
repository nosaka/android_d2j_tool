// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewBindingAdapter
{
    public static interface FixText
    {

        public abstract CharSequence fixText(CharSequence charsequence);
    }

    public static interface IsValid
    {

        public abstract boolean isValid(CharSequence charsequence);
    }


    public AutoCompleteTextViewBindingAdapter()
    {
    }

    public static void setOnItemSelectedListener(AutoCompleteTextView autocompletetextview, AdapterViewBindingAdapter.OnItemSelected onitemselected, AdapterViewBindingAdapter.OnNothingSelected onnothingselected)
    {
        if(onitemselected == null && onnothingselected == null)
        {
            autocompletetextview.setOnItemSelectedListener(null);
            return;
        } else
        {
            autocompletetextview.setOnItemSelectedListener(new AdapterViewBindingAdapter.OnItemSelectedComponentListener(onitemselected, onnothingselected, null));
            return;
        }
    }

    public static void setValidator(AutoCompleteTextView autocompletetextview, FixText fixtext, IsValid isvalid)
    {
        if(fixtext == null && isvalid == null)
        {
            autocompletetextview.setValidator(null);
            return;
        } else
        {
            autocompletetextview.setValidator(new android.widget.AutoCompleteTextView.Validator(isvalid, fixtext) {

                public CharSequence fixText(CharSequence charsequence)
                {
                    CharSequence charsequence1 = charsequence;
                    if(fixText != null)
                        charsequence1 = fixText.fixText(charsequence);
                    return charsequence1;
                }

                public boolean isValid(CharSequence charsequence)
                {
                    if(isValid != null)
                        return isValid.isValid(charsequence);
                    else
                        return true;
                }

                final FixText val$fixText;
                final IsValid val$isValid;

            
            {
                isValid = isvalid;
                fixText = fixtext;
                super();
            }
            }
);
            return;
        }
    }
}
