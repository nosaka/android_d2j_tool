// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.listview.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.net.URL;
import java.util.*;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.image.LoadBitmapManager;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.ListSettings;
import net.wakamesoba98.sobacha.time.TimeUtils;
import net.wakamesoba98.sobacha.twitter.media.EnumMediaProvider;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.*;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.imageview.OnTryToUseRecycledBitmapListener;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageView;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;
import net.wakamesoba98.sobacha.view.textview.MutableOnTouchListener;
import net.wakamesoba98.sobacha.view.textview.TextViewLinkUtils;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.listview.adapter:
//            StatusItemViewHolder

public class StatusItemView
{
    private class IconOnClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            view = new Bundle();
            view.putLong("user_id", userId);
            (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/UserPageActivity, EnumViewPagerFragment.USER_PAGE, view);
        }

        private ViewPagerActivity activity;
        final StatusItemView this$0;
        private long userId;

        IconOnClickListener(ViewPagerActivity viewpageractivity, long l)
        {
            this$0 = StatusItemView.this;
            super();
            activity = viewpageractivity;
            userId = l;
        }
    }

    private class ResourceId
    {

        int favorite;
        int protectedUser;
        int quoteBackground;
        int readMore;
        int retweet;
        final StatusItemView this$0;
        int verifiedUser;

        private ResourceId()
        {
            this$0 = StatusItemView.this;
            super();
        }

    }

    private class StatusViewParams
    {

        User actionBy;
        String client;
        Date createdAt;
        ExtendedMediaEntity extendedEntities[];
        long favoritedCount;
        HashtagEntity hashtagEntities[];
        boolean isThumbMuteTarget;
        MediaEntity mediaEntities[];
        UserMentionEntity mentionEntities[];
        Status quotedStatus;
        long retweetedCount;
        String text;
        final StatusItemView this$0;
        URLEntity urlEntities[];
        User user;

        private StatusViewParams()
        {
            this$0 = StatusItemView.this;
            super();
        }

    }

    private class TextColor
    {

        int favorite;
        int header;
        int mention;
        int retweet;
        int text;
        final StatusItemView this$0;

        private TextColor()
        {
            this$0 = StatusItemView.this;
            super();
        }

    }

    private class ThumbnailOnClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            Bundle bundle = new Bundle();
            if(((MediaURL)mediaURLList.get(0)).getProvider() == EnumMediaProvider.VIDEO_MP4 && SystemVersion.isIcsOrLater())
            {
                view = new Intent(activity, net/wakamesoba98/sobacha/view/activity/VideoViewActivity);
                bundle.putString("lowest_bitrate_video_url", ((MediaURL)mediaURLList.get(0)).getUrl(false).toString());
                bundle.putString("highest_bitrate_video_url", ((MediaURL)mediaURLList.get(0)).getOriginalUrl().toString());
                bundle.putString("screen_name", screenName);
            } else
            {
                view = new ArrayList();
                for(Iterator iterator = mediaURLList.iterator(); iterator.hasNext(); view.add(((MediaURL)iterator.next()).getUrl(false).toString()));
                String as[] = (String[])view.toArray(new String[view.size()]);
                view = new Intent(activity, net/wakamesoba98/sobacha/view/activity/ImageViewerActivity);
                bundle.putInt("position", position);
                bundle.putStringArray("image_urls", as);
                bundle.putString("screen_name", screenName);
            }
            view.putExtras(bundle);
            view.setFlags(0x10000000);
            activity.startActivity(view);
            if(Flavor.isMateCha())
                activity.overridePendingTransition(0x7f040010, 0x7f040012);
        }

        private ViewPagerActivity activity;
        private List mediaURLList;
        private int position;
        private String screenName;
        final StatusItemView this$0;

        ThumbnailOnClickListener(ViewPagerActivity viewpageractivity, List list, String s, int i)
        {
            this$0 = StatusItemView.this;
            super();
            activity = viewpageractivity;
            mediaURLList = list;
            screenName = s;
            position = i;
        }
    }


    public StatusItemView(ViewPagerActivity viewpageractivity)
    {
        activity = viewpageractivity;
        themeManager = new ThemeManager(viewpageractivity);
        textColor = getThemeTextColor();
        resourceId = getAccountTypeResourceId();
        parentBackgroundDrawable = themeManager.getThemeDrawableId(0x7f0200a8);
        normalIconSize = DisplayMetricsUtil.convertDipToPixel(viewpageractivity, 40);
        normalPadding = DisplayMetricsUtil.convertDipToPixel(viewpageractivity, 8);
        singleLineIconSize = DisplayMetricsUtil.convertDipToPixel(viewpageractivity, 16);
        singleLinePadding = DisplayMetricsUtil.convertDipToPixel(viewpageractivity, 2);
        timeUtils = new TimeUtils(viewpageractivity);
        statusUrlUtils = new StatusUrlUtils();
        retweetedByStyle = PreferenceUtil.getIntPreference(viewpageractivity, EnumPrefs.RETWEETED_BY_STYLE);
        fontSize = PreferenceUtil.getIntPreference(viewpageractivity, EnumPrefs.FONT_SIZE);
        if(fontSize < 6)
            fontSize = 6;
        int i = DisplayMetricsUtil.convertDipToPixel(viewpageractivity, fontSize);
        iconParams = new android.widget.RelativeLayout.LayoutParams(i, i);
        viewpageractivity = (SobaChaApplication)viewpageractivity.getApplicationContext();
        loadBitmapManager = viewpageractivity.getLoadBitmapManager();
        listSettings = viewpageractivity.getListSettings();
    }

    private ResourceId getAccountTypeResourceId()
    {
        ResourceId resourceid = new ResourceId();
        resourceid.protectedUser = themeManager.getThemeDrawableId(0x7f0200b4);
        resourceid.verifiedUser = themeManager.getThemeDrawableId(0x7f020113);
        resourceid.retweet = themeManager.getThemeDrawableId(0x7f0200f0);
        resourceid.favorite = themeManager.getThemeDrawableId(0x7f020097);
        resourceid.quoteBackground = themeManager.getThemeDrawableId(0x7f0200e1);
        resourceid.readMore = themeManager.getThemeDrawableId(0x7f0200bb);
        return resourceid;
    }

    private int getSpanColor(EnumStatusType enumstatustype)
    {
        static class _cls2
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType = new int[EnumStatusType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.DIRECT_MESSAGE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.DIRECT_MESSAGE_USER.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.PROFILE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.READ_MORE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.RETWEETED.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.FAVORITED.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$view$listview$item$EnumStatusType[EnumStatusType.MENTION.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()])
        {
        default:
            return textColor.text;

        case 5: // '\005'
            return textColor.retweet;

        case 6: // '\006'
            return textColor.favorite;

        case 7: // '\007'
            return textColor.mention;
        }
    }

    private TextColor getThemeTextColor()
    {
        TextColor textcolor = new TextColor();
        textcolor.text = themeManager.getThemeColor(0x7f0d00ba);
        textcolor.header = themeManager.getThemeColor(0x7f0d00a8);
        textcolor.retweet = themeManager.getThemeColor(0x7f0d00b4);
        textcolor.favorite = themeManager.getThemeColor(0x7f0d00a2);
        textcolor.mention = themeManager.getThemeColor(0x7f0d00ae);
        return textcolor;
    }

    private void setFontSize(StatusItemViewHolder statusitemviewholder, boolean flag)
    {
        int l = fontSize;
        int k = fontSize - 2;
        int j = k;
        int i = l;
        if(flag)
        {
            i = l + 2;
            j = k + 2;
        }
        statusitemviewholder.textViewScreenName.setTextSize(j);
        statusitemviewholder.textViewUserName.setTextSize(j);
        statusitemviewholder.textViewTime.setTextSize(j);
        statusitemviewholder.textViewText.setTextSize(i);
        statusitemviewholder.textViewClient.setTextSize(j);
        statusitemviewholder.textViewRetweetedBy.setTextSize(j);
        statusitemviewholder.textViewRetweetedCount.setTextSize(j);
        statusitemviewholder.textViewFavoritedCount.setTextSize(j);
        statusitemviewholder.textViewQuoteScreenName.setTextSize(j);
        statusitemviewholder.textViewQuoteUserName.setTextSize(j);
        statusitemviewholder.textViewQuoteTime.setTextSize(j);
        statusitemviewholder.textViewQuoteText.setTextSize(i);
        statusitemviewholder.imageViewRetweetedBy.setLayoutParams(iconParams);
    }

    private void setHeaderColor(StatusItemViewHolder statusitemviewholder, int i)
    {
        statusitemviewholder.textViewUserName.setTextColor(i);
        statusitemviewholder.textViewScreenName.setTextColor(i);
        statusitemviewholder.textViewTime.setTextColor(i);
        statusitemviewholder.textViewClient.setTextColor(i);
        statusitemviewholder.textViewRetweetedCount.setTextColor(textColor.header);
        statusitemviewholder.textViewFavoritedCount.setTextColor(textColor.header);
        statusitemviewholder.textViewRetweetedBy.setTextColor(textColor.header);
        statusitemviewholder.textViewQuoteUserName.setTextColor(textColor.header);
        statusitemviewholder.textViewQuoteScreenName.setTextColor(textColor.header);
        statusitemviewholder.textViewQuoteTime.setTextColor(textColor.header);
    }

    private void setImageFromUrl(RecyclableImageView recyclableimageview, final MediaURL mediaURL, final boolean thumbnail, final boolean round)
    {
        if(listSettings.isLowBandwidthMode())
        {
            recyclableimageview.setVisibility(8);
            return;
        } else
        {
            recyclableimageview.setVisibility(0);
            recyclableimageview.setTag(mediaURL.getUrl(thumbnail).toString());
            recyclableimageview.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

                public void onTryToUseRecycledBitmap(ImageView imageview)
                {
                    loadBitmapManager.doDownloadBitmap(imageview, mediaURL, thumbnail, round);
                }

                final StatusItemView this$0;
                final MediaURL val$mediaURL;
                final boolean val$round;
                final boolean val$thumbnail;

            
            {
                this$0 = StatusItemView.this;
                mediaURL = mediaurl;
                thumbnail = flag;
                round = flag1;
                super();
            }
            }
);
            loadBitmapManager.doDownloadBitmap(recyclableimageview, mediaURL, thumbnail, round);
            return;
        }
    }

    private void setItemColor(StatusItemViewHolder statusitemviewholder, EnumStatusType enumstatustype)
    {
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 5 7: default 36
    //                   5 77
    //                   6 106
    //                   7 135;
           goto _L1 _L2 _L3 _L4
_L1:
        setHeaderColor(statusitemviewholder, textColor.header);
        statusitemviewholder.textViewText.setTextColor(textColor.text);
_L6:
        statusitemviewholder.textViewQuoteText.setTextColor(textColor.text);
        return;
_L2:
        setHeaderColor(statusitemviewholder, textColor.retweet);
        statusitemviewholder.textViewText.setTextColor(textColor.retweet);
        continue; /* Loop/switch isn't completed */
_L3:
        setHeaderColor(statusitemviewholder, textColor.favorite);
        statusitemviewholder.textViewText.setTextColor(textColor.favorite);
        continue; /* Loop/switch isn't completed */
_L4:
        setHeaderColor(statusitemviewholder, textColor.header);
        statusitemviewholder.textViewText.setTextColor(textColor.mention);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setThumbnail(ViewGroup viewgroup, View view, MediaEntity amediaentity[], ExtendedMediaEntity aextendedmediaentity[], String s)
    {
        if(amediaentity == null || amediaentity.length == 0)
        {
            viewgroup.setVisibility(8);
            view.setVisibility(8);
        } else
        {
            ArrayList arraylist = new ArrayList();
            if(aextendedmediaentity.length > 1)
            {
                int k = aextendedmediaentity.length;
                for(int i = 0; i < k; i++)
                    arraylist.add(new MediaURL(HttpsMediaURL.getMediaURL(aextendedmediaentity[i])));

            } else
            {
                arraylist.add(new MediaURL(HttpsMediaURL.getMediaURL(amediaentity[0])));
            }
            amediaentity = new MediaURL(amediaentity, aextendedmediaentity);
            if(arraylist.size() > 0)
            {
                viewgroup.setVisibility(0);
                int j = 0;
                while(j < viewgroup.getChildCount()) 
                {
                    aextendedmediaentity = viewgroup.getChildAt(j);
                    if(aextendedmediaentity instanceof RecyclableImageView)
                    {
                        aextendedmediaentity = (RecyclableImageView)aextendedmediaentity;
                        if(j < arraylist.size())
                        {
                            aextendedmediaentity.setVisibility(0);
                            if(amediaentity.getProvider() == EnumMediaProvider.VIDEO_MP4)
                            {
                                arraylist.set(0, amediaentity);
                                view.setVisibility(0);
                            } else
                            {
                                view.setVisibility(8);
                            }
                            aextendedmediaentity.setOnClickListener(new ThumbnailOnClickListener(activity, arraylist, s, j));
                            setImageFromUrl(aextendedmediaentity, (MediaURL)arraylist.get(j), true, false);
                        } else
                        {
                            aextendedmediaentity.setVisibility(8);
                        }
                    }
                    j++;
                }
            } else
            {
                viewgroup.setVisibility(8);
                view.setVisibility(8);
                return;
            }
        }
    }

    private void setView(StatusItemViewHolder statusitemviewholder, EnumStatusType enumstatustype, StatusViewParams statusviewparams, boolean flag)
    {
        statusitemviewholder.imageViewIcon.setVisibility(0);
        statusitemviewholder.textViewText.setVisibility(0);
        statusitemviewholder.imageViewReadMore.setVisibility(8);
        if(statusitemviewholder.parent != null)
            statusitemviewholder.parent.setBackgroundResource(parentBackgroundDrawable);
        if(!flag) goto _L2; else goto _L1
_L1:
        Object obj;
        int i;
        String s;
        if(listSettings.isWordWrapInSingleLineMode())
        {
            statusitemviewholder.textViewText.setSingleLine(false);
        } else
        {
            statusitemviewholder.textViewText.setSingleLine(true);
            statusitemviewholder.textViewText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        }
        statusitemviewholder.imageViewIcon.getLayoutParams().height = singleLineIconSize;
        statusitemviewholder.imageViewIcon.getLayoutParams().width = singleLineIconSize;
        statusitemviewholder.imageViewIcon.requestLayout();
        if(statusitemviewholder.parent != null)
            statusitemviewholder.parent.setPadding(singleLinePadding, singleLinePadding, singleLinePadding, singleLinePadding);
        statusitemviewholder.textViewScreenName.setVisibility(8);
        statusitemviewholder.textViewUserName.setVisibility(8);
        statusitemviewholder.textViewTime.setVisibility(8);
        statusitemviewholder.textViewClient.setVisibility(8);
        statusitemviewholder.thumbnailParent.setVisibility(8);
        statusitemviewholder.playButtonParent.setVisibility(8);
        statusitemviewholder.quoteThumbnailParent.setVisibility(8);
        statusitemviewholder.quotePlayButtonParent.setVisibility(8);
        statusitemviewholder.retweetedCountParent.setVisibility(8);
        statusitemviewholder.quoteParent.setVisibility(8);
        statusitemviewholder.lockedAndVerifiedParent.setVisibility(8);
_L25:
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 5 6: default 260
    //                   5 1689
    //                   6 1697;
           goto _L3 _L4 _L5
_L3:
        obj = "";
_L21:
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 5 6: default 296
    //                   5 1705
    //                   6 1705;
           goto _L6 _L7 _L7
_L6:
        statusitemviewholder.retweetedByParent.setVisibility(8);
_L22:
        listSettings.getIconSize();
        JVM INSTR tableswitch 0 2: default 340
    //                   0 1834
    //                   1 340
    //                   2 1822;
           goto _L8 _L9 _L8 _L10
_L8:
        obj = HttpsMediaURL.getProfileImageURL(statusviewparams.user);
        break; /* Loop/switch isn't completed */
_L2:
        statusitemviewholder.textViewText.setSingleLine(false);
        statusitemviewholder.imageViewIcon.getLayoutParams().height = normalIconSize;
        statusitemviewholder.imageViewIcon.getLayoutParams().width = normalIconSize;
        statusitemviewholder.imageViewIcon.requestLayout();
        if(statusitemviewholder.parent != null)
            statusitemviewholder.parent.setPadding(normalPadding, normalPadding, normalPadding, normalPadding);
        statusitemviewholder.textViewScreenName.setVisibility(0);
        statusitemviewholder.textViewScreenName.setText((new StringBuilder()).append("@").append(statusviewparams.user.getScreenName()).toString());
        statusitemviewholder.textViewUserName.setVisibility(0);
        statusitemviewholder.textViewUserName.setText(statusviewparams.user.getName());
        int k;
        if(statusviewparams.createdAt == null)
        {
            statusitemviewholder.textViewTime.setVisibility(4);
            statusitemviewholder.textViewTime.setText("");
        } else
        {
            statusitemviewholder.textViewTime.setVisibility(0);
            if(listSettings.isUseAbsoluteTime())
                statusitemviewholder.textViewTime.setText(timeUtils.getAbsoluteTime(statusviewparams.createdAt));
            else
                statusitemviewholder.textViewTime.setText(timeUtils.getRelativeTime(statusviewparams.createdAt));
        }
        if(!listSettings.isShowVia() || statusviewparams.client == null) goto _L12; else goto _L11
_L11:
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 1 3: default 756
    //                   1 1177
    //                   2 1177
    //                   3 1177;
           goto _L13 _L14 _L14 _L14
_L13:
        statusitemviewholder.textViewClient.setVisibility(0);
        statusitemviewholder.textViewClient.setText((new StringBuilder()).append("via ").append(statusviewparams.client).toString());
_L19:
        k = listSettings.getThumbMode();
        flag1 = true;
        k;
        JVM INSTR tableswitch 0 1: default 832
    //                   0 1201
    //                   1 1201;
           goto _L15 _L16 _L16
_L15:
        if(!flag1 || statusviewparams.isThumbMuteTarget || listSettings.isLowBandwidthMode())
        {
            statusitemviewholder.thumbnailParent.setVisibility(8);
            statusitemviewholder.playButtonParent.setVisibility(8);
            statusitemviewholder.quoteThumbnailParent.setVisibility(8);
            statusitemviewholder.quotePlayButtonParent.setVisibility(8);
        } else
        {
            setThumbnail(statusitemviewholder.thumbnailParent, statusitemviewholder.playButtonParent, statusviewparams.mediaEntities, statusviewparams.extendedEntities, statusviewparams.user.getScreenName());
            if(statusviewparams.quotedStatus != null)
            {
                obj = statusviewparams.quotedStatus.getMediaEntities();
                ExtendedMediaEntity aextendedmediaentity[] = statusviewparams.quotedStatus.getExtendedMediaEntities();
                User user = statusviewparams.quotedStatus.getUser();
                setThumbnail(statusitemviewholder.quoteThumbnailParent, statusitemviewholder.quotePlayButtonParent, ((MediaEntity []) (obj)), aextendedmediaentity, user.getScreenName());
            }
        }
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[enumstatustype.ordinal()];
        JVM INSTR tableswitch 1 3: default 924
    //                   1 1302
    //                   2 1302
    //                   3 1302;
           goto _L17 _L18 _L18 _L18
_L17:
        statusitemviewholder.retweetedCountParent.setVisibility(0);
        statusitemviewholder.imageViewRetweetedCount.setImageResource(resourceId.retweet);
        statusitemviewholder.imageViewFavoritedCount.setImageResource(resourceId.favorite);
        if(statusviewparams.retweetedCount > 0L)
        {
            statusitemviewholder.textViewRetweetedCount.setText(String.valueOf(statusviewparams.retweetedCount));
            statusitemviewholder.imageViewRetweetedCount.setVisibility(0);
        } else
        {
            statusitemviewholder.textViewRetweetedCount.setText("");
            statusitemviewholder.imageViewRetweetedCount.setVisibility(8);
        }
        if(statusviewparams.favoritedCount > 0L)
        {
            statusitemviewholder.textViewFavoritedCount.setText(String.valueOf(statusviewparams.favoritedCount));
            statusitemviewholder.imageViewFavoritedCount.setVisibility(0);
        } else
        {
            statusitemviewholder.textViewFavoritedCount.setText("");
            statusitemviewholder.imageViewFavoritedCount.setVisibility(8);
        }
_L20:
        if(statusviewparams.quotedStatus == null)
        {
            statusitemviewholder.quoteParent.setVisibility(8);
        } else
        {
            statusitemviewholder.quoteParent.setVisibility(0);
            statusitemviewholder.quoteParent.setBackgroundResource(resourceId.quoteBackground);
            if(listSettings.isUseAbsoluteTime())
                obj = timeUtils.getAbsoluteTime(statusviewparams.quotedStatus.getCreatedAt());
            else
                obj = timeUtils.getRelativeTime(statusviewparams.quotedStatus.getCreatedAt());
            statusitemviewholder.textViewQuoteScreenName.setText((new StringBuilder()).append("@").append(statusviewparams.quotedStatus.getUser().getScreenName()).toString());
            statusitemviewholder.textViewQuoteUserName.setText(statusviewparams.quotedStatus.getUser().getName());
            statusitemviewholder.textViewQuoteTime.setText(((CharSequence) (obj)));
            obj = statusUrlUtils.replaceToDisplayURL(statusviewparams.quotedStatus);
            if(listSettings.isLinkClickable())
            {
                TextViewLinkUtils textviewlinkutils = new TextViewLinkUtils();
                int j = getSpanColor(EnumStatusType.NORMAL);
                statusitemviewholder.textViewQuoteText.setOnTouchListener(new MutableOnTouchListener());
                statusitemviewholder.textViewQuoteText.setText(textviewlinkutils.createClickableString(activity, ((String) (obj)), j, false, statusviewparams.quotedStatus.getUser().getScreenName(), statusviewparams.quotedStatus.getURLEntities(), statusviewparams.quotedStatus.getMediaEntities(), statusviewparams.quotedStatus.getExtendedMediaEntities(), statusviewparams.quotedStatus.getUserMentionEntities(), statusviewparams.quotedStatus.getHashtagEntities()));
            } else
            {
                statusitemviewholder.textViewQuoteText.setText(((CharSequence) (obj)));
            }
        }
        statusitemviewholder.lockedAndVerifiedParent.setVisibility(0);
        if(statusviewparams.user.isProtected())
        {
            statusitemviewholder.imageViewProtectedIcon.setVisibility(0);
            statusitemviewholder.imageViewProtectedIcon.setImageResource(resourceId.protectedUser);
        } else
        {
            statusitemviewholder.imageViewProtectedIcon.setVisibility(8);
            statusitemviewholder.imageViewProtectedIcon.setImageDrawable(null);
        }
        if(statusviewparams.user.isVerified())
        {
            statusitemviewholder.imageViewVerifiedIcon.setVisibility(0);
            statusitemviewholder.imageViewVerifiedIcon.setImageResource(resourceId.verifiedUser);
        } else
        {
            statusitemviewholder.imageViewVerifiedIcon.setVisibility(8);
            statusitemviewholder.imageViewVerifiedIcon.setImageDrawable(null);
        }
        continue; /* Loop/switch isn't completed */
_L14:
        statusitemviewholder.textViewClient.setVisibility(8);
          goto _L19
_L12:
        statusitemviewholder.textViewClient.setVisibility(8);
          goto _L19
_L16:
        flag1 = false;
          goto _L15
_L18:
        statusitemviewholder.retweetedCountParent.setVisibility(8);
          goto _L20
_L4:
        obj = "RT by ";
          goto _L21
_L5:
        obj = "Fav by ";
          goto _L21
_L7:
        statusitemviewholder.retweetedByParent.setVisibility(0);
        String s1;
        MediaURL mediaurl;
        if(retweetedByStyle == 0)
            s1 = (new StringBuilder()).append("@").append(statusviewparams.actionBy.getScreenName()).toString();
        else
            s1 = statusviewparams.actionBy.getName();
        mediaurl = new MediaURL(HttpsMediaURL.getMiniProfileImageURL(statusviewparams.actionBy));
        setImageFromUrl(statusitemviewholder.imageViewRetweetedBy, mediaurl, false, true);
        statusitemviewholder.textViewRetweetedBy.setText((new StringBuilder()).append(((String) (obj))).append(s1).toString());
          goto _L22
_L10:
        obj = HttpsMediaURL.getBiggerProfileImageURL(statusviewparams.user);
          goto _L23
_L9:
        obj = HttpsMediaURL.getMiniProfileImageURL(statusviewparams.user);
_L23:
        obj = new MediaURL(((String) (obj)));
        setImageFromUrl(statusitemviewholder.imageViewIcon, ((MediaURL) (obj)), false, true);
        statusitemviewholder.imageViewIcon.setOnClickListener(new IconOnClickListener(activity, statusviewparams.user.getId()));
        obj = new TextViewLinkUtils();
        if(listSettings.isLinkClickable())
        {
            i = getSpanColor(enumstatustype);
            statusitemviewholder.textViewText.setOnTouchListener(new MutableOnTouchListener());
            statusitemviewholder = statusitemviewholder.textViewText;
            enumstatustype = activity;
            s = statusviewparams.text;
            boolean flag1;
            if(flag && listSettings.isShowScreenNameInSingleLineMode())
                flag = true;
            else
                flag = false;
            statusitemviewholder.setText(((TextViewLinkUtils) (obj)).createClickableString(enumstatustype, s, i, flag, statusviewparams.user.getScreenName(), statusviewparams.urlEntities, statusviewparams.mediaEntities, statusviewparams.extendedEntities, statusviewparams.mentionEntities, statusviewparams.hashtagEntities));
            return;
        }
        statusitemviewholder.textViewText.setOnTouchListener(new MutableOnTouchListener());
        statusitemviewholder = statusitemviewholder.textViewText;
        enumstatustype = activity;
        String s2 = statusviewparams.text;
        if(flag && listSettings.isShowScreenNameInSingleLineMode())
            flag = true;
        else
            flag = false;
        statusitemviewholder.setText(((TextViewLinkUtils) (obj)).createClickableString(enumstatustype, s2, -1, flag, statusviewparams.user.getScreenName(), null, null, null, null, null));
        return;
        if(true) goto _L25; else goto _L24
_L24:
    }

    private void showDirectMessage(StatusItemViewHolder statusitemviewholder, StatusItem statusitem, boolean flag)
    {
        DirectMessage directmessage = statusitem.getDirectMessage();
        StatusViewParams statusviewparams = new StatusViewParams();
        statusviewparams.user = directmessage.getSender();
        statusviewparams.createdAt = directmessage.getCreatedAt();
        statusviewparams.client = "";
        statusviewparams.retweetedCount = 0L;
        statusviewparams.favoritedCount = 0L;
        statusviewparams.urlEntities = directmessage.getURLEntities();
        statusviewparams.mediaEntities = directmessage.getMediaEntities();
        statusviewparams.extendedEntities = directmessage.getExtendedMediaEntities();
        statusviewparams.mentionEntities = directmessage.getUserMentionEntities();
        statusviewparams.hashtagEntities = directmessage.getHashtagEntities();
        statusviewparams.text = statusitem.getText();
        statusviewparams.actionBy = null;
        statusviewparams.quotedStatus = null;
        statusviewparams.isThumbMuteTarget = statusitem.isThumbMuteTarget();
        setView(statusitemviewholder, statusitem.getStatusType(), statusviewparams, flag);
    }

    private void showProfile(StatusItemViewHolder statusitemviewholder, StatusItem statusitem, boolean flag)
    {
        StatusViewParams statusviewparams = new StatusViewParams();
        statusviewparams.user = statusitem.getUser();
        statusviewparams.createdAt = null;
        statusviewparams.client = "";
        statusviewparams.retweetedCount = 0L;
        statusviewparams.favoritedCount = 0L;
        statusviewparams.text = statusitem.getText();
        statusviewparams.urlEntities = null;
        statusviewparams.mediaEntities = null;
        statusviewparams.extendedEntities = null;
        statusviewparams.mentionEntities = null;
        statusviewparams.hashtagEntities = null;
        statusviewparams.actionBy = null;
        statusviewparams.quotedStatus = null;
        statusviewparams.isThumbMuteTarget = statusitem.isThumbMuteTarget();
        setView(statusitemviewholder, statusitem.getStatusType(), statusviewparams, flag);
    }

    private void showReadMore(StatusItemViewHolder statusitemviewholder)
    {
        statusitemviewholder.imageViewIcon.setVisibility(8);
        statusitemviewholder.textViewText.setVisibility(8);
        statusitemviewholder.textViewScreenName.setVisibility(8);
        statusitemviewholder.textViewUserName.setVisibility(8);
        statusitemviewholder.textViewTime.setVisibility(8);
        statusitemviewholder.textViewClient.setVisibility(8);
        statusitemviewholder.thumbnailParent.setVisibility(8);
        statusitemviewholder.playButtonParent.setVisibility(8);
        statusitemviewholder.quoteThumbnailParent.setVisibility(8);
        statusitemviewholder.quotePlayButtonParent.setVisibility(8);
        statusitemviewholder.retweetedCountParent.setVisibility(8);
        statusitemviewholder.retweetedByParent.setVisibility(8);
        statusitemviewholder.quoteParent.setVisibility(8);
        statusitemviewholder.lockedAndVerifiedParent.setVisibility(8);
        statusitemviewholder.imageViewReadMore.setVisibility(0);
        statusitemviewholder.imageViewReadMore.setImageResource(resourceId.readMore);
    }

    private void showStatus(StatusItemViewHolder statusitemviewholder, StatusItem statusitem, boolean flag)
    {
        Object obj;
        obj = statusitem.getStatus();
        Status status = ((Status) (obj));
        if(((Status) (obj)).isRetweet())
            status = statusitem.getStatus().getRetweetedStatus();
        obj = new StatusViewParams();
        obj.user = status.getUser();
        obj.createdAt = status.getCreatedAt();
        obj.client = statusUrlUtils.getClientName(statusitem.getStatus());
        obj.text = statusitem.getText();
        obj.retweetedCount = status.getRetweetCount();
        obj.favoritedCount = status.getFavoriteCount();
        obj.urlEntities = status.getURLEntities();
        obj.mediaEntities = status.getMediaEntities();
        obj.extendedEntities = status.getExtendedMediaEntities();
        obj.mentionEntities = status.getUserMentionEntities();
        obj.hashtagEntities = status.getHashtagEntities();
        obj.quotedStatus = status.getQuotedStatus();
        obj.isThumbMuteTarget = statusitem.isThumbMuteTarget();
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()];
        JVM INSTR tableswitch 5 6: default 232
    //                   5 245
    //                   6 262;
           goto _L1 _L2 _L3
_L1:
        setView(statusitemviewholder, statusitem.getStatusType(), ((StatusViewParams) (obj)), flag);
        return;
_L2:
        obj.actionBy = statusitem.getStatus().getUser();
        continue; /* Loop/switch isn't completed */
_L3:
        obj.actionBy = statusitem.getFavoritedBy();
        if(true) goto _L1; else goto _L4
_L4:
    }

    public StatusItemViewHolder findView(View view)
    {
        StatusItemViewHolder statusitemviewholder = new StatusItemViewHolder();
        statusitemviewholder.parent = view.findViewById(0x7f0e00fc);
        statusitemviewholder.lockedAndVerifiedParent = view.findViewById(0x7f0e00fd);
        statusitemviewholder.retweetedCountParent = view.findViewById(0x7f0e0108);
        statusitemviewholder.listSpacer = view.findViewById(0x7f0e0119);
        statusitemviewholder.playButtonParent = view.findViewById(0x7f0e0118);
        statusitemviewholder.quotePlayButtonParent = view.findViewById(0x7f0e0116);
        statusitemviewholder.retweetedByParent = view.findViewById(0x7f0e010d);
        statusitemviewholder.thumbnailParent = (ViewGroup)view.findViewById(0x7f0e0117);
        statusitemviewholder.quoteThumbnailParent = (ViewGroup)view.findViewById(0x7f0e0115);
        statusitemviewholder.quoteParent = (ViewGroup)view.findViewById(0x7f0e0110);
        statusitemviewholder.imageViewIcon = (RecyclableImageView)view.findViewById(0x7f0e0101);
        statusitemviewholder.imageViewProtectedIcon = (ImageView)view.findViewById(0x7f0e00ff);
        statusitemviewholder.imageViewVerifiedIcon = (ImageView)view.findViewById(0x7f0e0100);
        statusitemviewholder.imageViewRetweetedCount = (ImageView)view.findViewById(0x7f0e0109);
        statusitemviewholder.imageViewFavoritedCount = (ImageView)view.findViewById(0x7f0e010b);
        statusitemviewholder.imageViewRetweetedBy = (RecyclableImageView)view.findViewById(0x7f0e010e);
        statusitemviewholder.imageViewReadMore = (ImageView)view.findViewById(0x7f0e011a);
        statusitemviewholder.textViewScreenName = (TextView)view.findViewById(0x7f0e0104);
        statusitemviewholder.textViewUserName = (TextView)view.findViewById(0x7f0e0103);
        statusitemviewholder.textViewTime = (TextView)view.findViewById(0x7f0e0105);
        statusitemviewholder.textViewText = (TextView)view.findViewById(0x7f0e0106);
        statusitemviewholder.textViewClient = (TextView)view.findViewById(0x7f0e0107);
        statusitemviewholder.textViewRetweetedBy = (TextView)view.findViewById(0x7f0e010f);
        statusitemviewholder.textViewRetweetedCount = (TextView)view.findViewById(0x7f0e010a);
        statusitemviewholder.textViewFavoritedCount = (TextView)view.findViewById(0x7f0e010c);
        statusitemviewholder.textViewQuoteScreenName = (TextView)view.findViewById(0x7f0e0112);
        statusitemviewholder.textViewQuoteUserName = (TextView)view.findViewById(0x7f0e0111);
        statusitemviewholder.textViewQuoteTime = (TextView)view.findViewById(0x7f0e0113);
        statusitemviewholder.textViewQuoteText = (TextView)view.findViewById(0x7f0e0114);
        return statusitemviewholder;
    }

    public void setView(StatusItem statusitem, StatusItemViewHolder statusitemviewholder, boolean flag)
    {
        setFontSize(statusitemviewholder, flag);
        _cls2..SwitchMap.net.wakamesoba98.sobacha.view.listview.item.EnumStatusType[statusitem.getStatusType().ordinal()];
        JVM INSTR tableswitch 1 4: default 48
    //                   1 65
    //                   2 65
    //                   3 75
    //                   4 85;
           goto _L1 _L2 _L2 _L3 _L4
_L1:
        showStatus(statusitemviewholder, statusitem, flag);
_L6:
        setItemColor(statusitemviewholder, statusitem.getStatusType());
        return;
_L2:
        showDirectMessage(statusitemviewholder, statusitem, flag);
        continue; /* Loop/switch isn't completed */
_L3:
        showProfile(statusitemviewholder, statusitem, flag);
        continue; /* Loop/switch isn't completed */
_L4:
        showReadMore(statusitemviewholder);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private ViewPagerActivity activity;
    private int fontSize;
    private android.widget.RelativeLayout.LayoutParams iconParams;
    private ListSettings listSettings;
    private LoadBitmapManager loadBitmapManager;
    private int normalIconSize;
    private int normalPadding;
    private int parentBackgroundDrawable;
    private ResourceId resourceId;
    private int retweetedByStyle;
    private int singleLineIconSize;
    private int singleLinePadding;
    private StatusUrlUtils statusUrlUtils;
    private TextColor textColor;
    private ThemeManager themeManager;
    private TimeUtils timeUtils;

}
