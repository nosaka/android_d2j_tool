// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.databinding.ViewStubProxy;

public class ViewStubBindingAdapter
{

    public ViewStubBindingAdapter()
    {
    }

    public static void setOnInflateListener(ViewStubProxy viewstubproxy, android.view.ViewStub.OnInflateListener oninflatelistener)
    {
        viewstubproxy.setOnInflateListener(oninflatelistener);
    }
}
