// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view;

import android.view.*;

public abstract class ActionMode
{
    public static interface Callback
    {

        public abstract boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem);

        public abstract boolean onCreateActionMode(ActionMode actionmode, Menu menu);

        public abstract void onDestroyActionMode(ActionMode actionmode);

        public abstract boolean onPrepareActionMode(ActionMode actionmode, Menu menu);
    }


    public ActionMode()
    {
    }

    public abstract void finish();

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag()
    {
        return mTag;
    }

    public abstract CharSequence getTitle();

    public boolean getTitleOptionalHint()
    {
        return mTitleOptionalHint;
    }

    public abstract void invalidate();

    public boolean isTitleOptional()
    {
        return false;
    }

    public boolean isUiFocusable()
    {
        return true;
    }

    public abstract void setCustomView(View view);

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charsequence);

    public void setTag(Object obj)
    {
        mTag = obj;
    }

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charsequence);

    public void setTitleOptionalHint(boolean flag)
    {
        mTitleOptionalHint = flag;
    }

    private Object mTag;
    private boolean mTitleOptionalHint;
}
