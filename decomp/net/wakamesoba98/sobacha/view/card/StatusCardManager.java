// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.animation.Animator;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.menu.StatusMenu;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusActionFinishedListener;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.listview.adapter.StatusItemView;
import net.wakamesoba98.sobacha.view.listview.item.EnumStatusType;
import net.wakamesoba98.sobacha.view.listview.item.StatusItem;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.Status;
import twitter4j.User;

// Referenced classes of package net.wakamesoba98.sobacha.view.card:
//            PostCardManager

public class StatusCardManager
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener, OnStatusActionFinishedListener
{

    public StatusCardManager(ViewPagerActivity viewpageractivity, UserAccount useraccount)
    {
        activity = viewpageractivity;
        themeManager = new ThemeManager(viewpageractivity);
        statusItemView = new StatusItemView(viewpageractivity);
        statusUrlUtils = new StatusUrlUtils();
        isVisible = false;
        singleLineMode = PreferenceUtil.getBooleanPreference(viewpageractivity, EnumPrefs.SINGLE_LINE_MODE);
        setUserAccount(useraccount);
        viewInitialize();
    }

    private void dismissProgress()
    {
        ((ProgressBar)activity.findViewById(0x7f0e0133)).setVisibility(8);
    }

    private int getButtonResourceId(View view)
    {
        switch(view.getId())
        {
        default:
            return 0;

        case 2131624109: 
            return 0x7f02006d;

        case 2131624110: 
            return item.getStatus() == null ? 0x7f0200e9 : 0x7f0200ea;

        case 2131624111: 
            return !item.isRetweetable() ? 0x7f0200ed : 0x7f0200f0;

        case 2131624112: 
            if(item.getStatus() != null)
                return !item.getStatus().isFavorited() ? 0x7f020097 : 0x7f020109;
            else
                return 0x7f020096;

        case 2131624113: 
            return 0x7f0200bb;
        }
    }

    private void reply()
    {
        setFullTweet(item);
        PostCardManager postcardmanager = activity.getPostCardManager();
        if(item.getStatusType() == EnumStatusType.PROFILE)
        {
            postcardmanager.setInReplyToId(-1L);
            postcardmanager.setReplyToUser(item.getUser().getScreenName());
            return;
        }
        Status status1 = item.getStatus();
        Status status = status1;
        if(status1.isRetweet())
            status = status1.getRetweetedStatus();
        postcardmanager.setInReplyToId(status.getId());
        postcardmanager.setReplyToUser(status.getUser().getScreenName(), status.getUserMentionEntities());
    }

    private void setButtonProperties()
    {
        ViewGroup viewgroup = (ViewGroup)activity.findViewById(0x7f0e00ac);
        for(int i = 0; i < viewgroup.getChildCount(); i++)
        {
            View view = viewgroup.getChildAt(i);
            if(view instanceof ImageButton)
            {
                ImageButton imagebutton = (ImageButton)view;
                imagebutton.setImageResource(themeManager.getThemeDrawableId(getButtonResourceId(view)));
                imagebutton.setOnClickListener(this);
                imagebutton.setOnLongClickListener(this);
            }
        }

    }

    private void setCircularReveal(View view)
    {
        if(Flavor.isMateCha())
        {
            view.setAnimation(AnimationUtils.loadAnimation(activity, 0x7f040013));
            view = ViewAnimationUtils.createCircularReveal(view, (int)view.getX() + view.getWidth() / 2, 0, 0.0F, view.getWidth());
            view.setDuration(250L);
            view.start();
        }
    }

    private void setFullTweet(StatusItem statusitem)
    {
        Object obj = activity.findViewById(0x7f0e00ab);
        obj = statusItemView.findView(((View) (obj)));
        statusItemView.setView(statusitem, ((net.wakamesoba98.sobacha.view.listview.adapter.StatusItemViewHolder) (obj)), false);
    }

    private void setShortenTweet(StatusItem statusitem)
    {
        Object obj = activity.findViewById(0x7f0e00ab);
        obj = statusItemView.findView(((View) (obj)));
        StatusItemView statusitemview = statusItemView;
        boolean flag;
        if(!singleLineMode)
            flag = true;
        else
            flag = false;
        statusitemview.setView(statusitem, ((net.wakamesoba98.sobacha.view.listview.adapter.StatusItemViewHolder) (obj)), flag);
    }

    public void close()
    {
        PostCardManager postcardmanager = activity.getPostCardManager();
        if(item != null && item.getStatus() != null)
        {
            Status status1 = item.getStatus();
            Status status = status1;
            if(status1.isRetweet())
                status = status1.getRetweetedStatus();
            postcardmanager.removeReplyToUser(status.getUser().getScreenName());
        }
        postcardmanager.setQuoteText(null, null, -1L);
        postcardmanager.hideIme();
        if(isVisible)
        {
            isVisible = false;
            View view = activity.findViewById(0x7f0e0088);
            view.setVisibility(4);
            if(Flavor.isMateCha())
                view.setAnimation(AnimationUtils.loadAnimation(activity, 0x7f040011));
        }
    }

    public void finishedStatusAction()
    {
        dismissProgress();
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void onClick(View view)
    {
        ProgressBar progressbar = (ProgressBar)activity.findViewById(0x7f0e0133);
        view.getId();
        JVM INSTR tableswitch 2131624109 2131624113: default 52
    //                   2131624109 53
    //                   2131624110 58
    //                   2131624111 73
    //                   2131624112 101
    //                   2131624113 162;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        close();
        return;
_L3:
        if(item.getStatus() != null)
        {
            reply();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(item.isRetweetable())
        {
            item.retweet(this, activity, userAccount, progressbar);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if(item.getStatus() != null)
            if(item.getStatus().isFavorited())
            {
                item.unFavorite(this, activity, userAccount, progressbar);
                return;
            } else
            {
                item.favorite(this, activity, userAccount, progressbar);
                return;
            }
        if(true) goto _L1; else goto _L6
_L6:
        (new StatusMenu(activity, this, item, userAccount)).show(activity.findViewById(0x7f0e00b1));
        return;
    }

    public boolean onLongClick(View view)
    {
        boolean flag1;
        ProgressBar progressbar;
        flag1 = true;
        progressbar = (ProgressBar)activity.findViewById(0x7f0e0133);
        view.getId();
        JVM INSTR tableswitch 2131624111 2131624112: default 44
    //                   2131624111 48
    //                   2131624112 66;
           goto _L1 _L2 _L3
_L1:
        boolean flag = false;
_L5:
        return flag;
_L2:
        flag = flag1;
        if(item.isRetweetable())
        {
            quote();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        flag = flag1;
        if(item.isRetweetable())
        {
            flag = flag1;
            if(item.getStatus() != null)
            {
                item.favoriteRetweet(this, activity, userAccount, progressbar);
                return true;
            }
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void quote()
    {
        PostCardManager postcardmanager = activity.getPostCardManager();
        Object obj1 = new StatusUrlUtils();
        Object obj = item.getStatus();
        Status status = ((Status) (obj));
        if(((Status) (obj)).isRetweet())
            status = ((Status) (obj)).getRetweetedStatus();
        obj = (new StringBuilder()).append("@").append(status.getUser().getScreenName()).append(" ").append(statusUrlUtils.replaceToDisplayURL(item.getStatus())).toString();
        obj1 = ((StatusUrlUtils) (obj1)).createStatusUrl(status.getUser().getScreenName(), status.getId());
        postcardmanager.setInReplyToId(status.getId());
        postcardmanager.setQuoteText(((String) (obj)), ((String) (obj1)), item.getStatus().getId());
        postcardmanager.showIme();
    }

    public void setUserAccount(UserAccount useraccount)
    {
        userAccount = useraccount;
    }

    public void show(StatusItem statusitem)
    {
        item = statusitem;
        isVisible = true;
        View view = activity.findViewById(0x7f0e0088);
        view.setVisibility(0);
        setButtonProperties();
        setShortenTweet(statusitem);
        if(SystemVersion.isLollipopOrLater())
            setCircularReveal(view);
    }

    public void viewInitialize()
    {
        if(isVisible && item != null)
            show(item);
    }

    private ViewPagerActivity activity;
    private boolean isVisible;
    private StatusItem item;
    private boolean singleLineMode;
    private StatusItemView statusItemView;
    private StatusUrlUtils statusUrlUtils;
    private ThemeManager themeManager;
    private UserAccount userAccount;
}
