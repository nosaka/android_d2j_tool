// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageView;

public class StatusItemViewHolder
{

    public StatusItemViewHolder()
    {
    }

    ImageView imageViewFavoritedCount;
    RecyclableImageView imageViewIcon;
    ImageView imageViewProtectedIcon;
    ImageView imageViewReadMore;
    RecyclableImageView imageViewRetweetedBy;
    ImageView imageViewRetweetedCount;
    ImageView imageViewVerifiedIcon;
    View listSpacer;
    View lockedAndVerifiedParent;
    View parent;
    View playButtonParent;
    ViewGroup quoteParent;
    View quotePlayButtonParent;
    ViewGroup quoteThumbnailParent;
    View retweetedByParent;
    View retweetedCountParent;
    TextView textViewClient;
    TextView textViewFavoritedCount;
    TextView textViewQuoteScreenName;
    TextView textViewQuoteText;
    TextView textViewQuoteTime;
    TextView textViewQuoteUserName;
    TextView textViewRetweetedBy;
    TextView textViewRetweetedCount;
    TextView textViewScreenName;
    TextView textViewText;
    TextView textViewTime;
    TextView textViewUserName;
    ViewGroup thumbnailParent;
}
