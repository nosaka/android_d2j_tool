// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.image.LoadBitmapManager;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.view.imageview.OnTryToUseRecycledBitmapListener;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageView;
import net.wakamesoba98.sobacha.view.listview.item.AccountListItem;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public class SingleChoiceAccountAdapter extends ArrayAdapter
{
    private class ViewHolder
    {

        View background;
        RecyclableImageView imageViewIcon;
        ImageView imageViewSelected;
        TextView textViewName;
        final SingleChoiceAccountAdapter this$0;

        private ViewHolder()
        {
            this$0 = SingleChoiceAccountAdapter.this;
            super();
        }

    }


    public SingleChoiceAccountAdapter(Context context, int i, List list)
    {
        super(context, i, list);
        accountList = null;
        accountList = list;
        list = new ThemeManager(context);
        selectedColor = list.getThemeColor(0x7f0d0056);
        selectedResourceId = list.getThemeDrawableId(0x7f020055);
        addAccountResourceId = list.getThemeDrawableId(0x7f020059);
        loadBitmapManager = ((SobaChaApplication)context.getApplicationContext()).getLoadBitmapManager();
    }

    private void setUserIcon(RecyclableImageView recyclableimageview, final String mediaURL)
    {
        recyclableimageview.setTag(mediaURL);
        mediaURL = new MediaURL(mediaURL);
        recyclableimageview.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

            public void onTryToUseRecycledBitmap(ImageView imageview)
            {
                loadBitmapManager.doDownloadBitmap(imageview, mediaURL, false, true);
            }

            final SingleChoiceAccountAdapter this$0;
            final MediaURL val$mediaURL;

            
            {
                this$0 = SingleChoiceAccountAdapter.this;
                mediaURL = mediaurl;
                super();
            }
        }
);
        loadBitmapManager.doDownloadBitmap(recyclableimageview, mediaURL, false, true);
    }

    public int getCount()
    {
        return accountList.size();
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public AccountListItem getItem(int i)
    {
        return (AccountListItem)accountList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        AccountListItem accountlistitem;
        if(view == null)
        {
            view = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(0x7f03004c, null);
            viewgroup = new ViewHolder();
            viewgroup.background = view.findViewById(0x7f0e00f6);
            viewgroup.imageViewIcon = (RecyclableImageView)view.findViewById(0x7f0e00f8);
            viewgroup.imageViewSelected = (ImageView)view.findViewById(0x7f0e00f7);
            viewgroup.textViewName = (TextView)view.findViewById(0x7f0e00f9);
            view.setTag(viewgroup);
        } else
        {
            viewgroup = (ViewHolder)view.getTag();
        }
        accountlistitem = getItem(i);
        if(accountlistitem.isAddButton())
        {
            ((ViewHolder) (viewgroup)).imageViewIcon.setImageResource(addAccountResourceId);
            ((ViewHolder) (viewgroup)).textViewName.setText(ResourceHelper.getString(getContext(), 0x7f07001a));
            ((ViewHolder) (viewgroup)).imageViewSelected.setVisibility(8);
            return view;
        }
        ((ViewHolder) (viewgroup)).textViewName.setText((new StringBuilder()).append("@").append(accountlistitem.getScreenName()).append("\n").append(accountlistitem.getUserName()).toString());
        if(accountlistitem.isSelected())
        {
            ((ViewHolder) (viewgroup)).imageViewSelected.setVisibility(0);
            ((ViewHolder) (viewgroup)).imageViewSelected.setImageResource(selectedResourceId);
            ((ViewHolder) (viewgroup)).background.setBackgroundColor(selectedColor);
        } else
        {
            ((ViewHolder) (viewgroup)).imageViewSelected.setVisibility(4);
            ((ViewHolder) (viewgroup)).background.setBackgroundColor(0);
        }
        setUserIcon(((ViewHolder) (viewgroup)).imageViewIcon, accountlistitem.getIconUrl());
        return view;
    }

    private List accountList;
    private int addAccountResourceId;
    private LoadBitmapManager loadBitmapManager;
    private int selectedColor;
    private int selectedResourceId;

}
