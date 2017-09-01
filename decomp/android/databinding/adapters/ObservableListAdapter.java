// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.databinding.adapters;

import android.content.Context;
import android.databinding.ObservableList;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class ObservableListAdapter extends BaseAdapter
{

    public ObservableListAdapter(Context context, List list, int i, int j, int k)
    {
        mContext = context;
        mResourceId = i;
        mDropDownResourceId = j;
        mTextViewResourceId = k;
        if(i == 0)
            context = null;
        else
            context = (LayoutInflater)context.getSystemService("layout_inflater");
        mLayoutInflater = context;
        setList(list);
    }

    public int getCount()
    {
        return mList.size();
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return getViewForResource(mDropDownResourceId, i, view, viewgroup);
    }

    public Object getItem(int i)
    {
        return mList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return getViewForResource(mResourceId, i, view, viewgroup);
    }

    public View getViewForResource(int i, int j, View view, ViewGroup viewgroup)
    {
        Object obj = view;
        if(view == null)
            if(i == 0)
                obj = new TextView(mContext);
            else
                obj = mLayoutInflater.inflate(i, viewgroup, false);
        if(mTextViewResourceId == 0)
            view = ((View) (obj));
        else
            view = ((View) (obj)).findViewById(mTextViewResourceId);
        viewgroup = (TextView)(TextView)view;
        view = ((View) (mList.get(j)));
        if(view instanceof CharSequence)
            view = (CharSequence)view;
        else
            view = String.valueOf(view);
        viewgroup.setText(view);
        return ((View) (obj));
    }

    public void setList(List list)
    {
        if(mList == list)
            return;
        if(mList instanceof ObservableList)
            ((ObservableList)mList).removeOnListChangedCallback(mListChangedCallback);
        mList = list;
        if(mList instanceof ObservableList)
        {
            if(mListChangedCallback == null)
                mListChangedCallback = new android.databinding.ObservableList.OnListChangedCallback() {

                    public void onChanged(ObservableList observablelist)
                    {
                        notifyDataSetChanged();
                    }

                    public void onItemRangeChanged(ObservableList observablelist, int i, int j)
                    {
                        notifyDataSetChanged();
                    }

                    public void onItemRangeInserted(ObservableList observablelist, int i, int j)
                    {
                        notifyDataSetChanged();
                    }

                    public void onItemRangeMoved(ObservableList observablelist, int i, int j, int k)
                    {
                        notifyDataSetChanged();
                    }

                    public void onItemRangeRemoved(ObservableList observablelist, int i, int j)
                    {
                        notifyDataSetChanged();
                    }

                    final ObservableListAdapter this$0;

            
            {
                this$0 = ObservableListAdapter.this;
                super();
            }
                }
;
            ((ObservableList)mList).addOnListChangedCallback(mListChangedCallback);
        }
        notifyDataSetChanged();
    }

    private final Context mContext;
    private final int mDropDownResourceId;
    private final LayoutInflater mLayoutInflater;
    private List mList;
    private android.databinding.ObservableList.OnListChangedCallback mListChangedCallback;
    private final int mResourceId;
    private final int mTextViewResourceId;
}
