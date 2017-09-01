// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.textview;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.wakamesoba98.sobacha.twitter.media.MediaIntentUtils;
import net.wakamesoba98.sobacha.view.activity.*;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.textview:
//            ColoredURLSpan

public class TextViewLinkUtils
{

    public TextViewLinkUtils()
    {
    }

    public SpannableString createClickableString(ViewPagerActivity viewpageractivity, String s, int i, boolean flag, String s1, URLEntity aurlentity[])
    {
        return createClickableString(viewpageractivity, s, i, flag, s1, aurlentity, null, null, null, null);
    }

    public SpannableString createClickableString(ViewPagerActivity viewpageractivity, String s, final int final_i, boolean flag, final String final_s, final URLEntity final_s[], MediaEntity amediaentity[], 
            ExtendedMediaEntity aextendedmediaentity[], UserMentionEntity ausermentionentity[], HashtagEntity ahashtagentity[])
    {
        String s1 = s;
        if(flag)
            s1 = (new StringBuilder()).append(final_s).append(" ").append(s).toString();
        s = new SpannableString(s1);
        if(flag)
            s.setSpan(new StyleSpan(1), 0, final_s.length(), 33);
        if(final_s != null)
        {
            HashMap hashmap = new HashMap();
            int i1 = final_s.length;
label0:
            for(int i = 0; i < i1; i++)
            {
                URLEntity urlentity = final_s[i];
                Matcher matcher1 = Pattern.compile(Pattern.quote(urlentity.getDisplayURL()), 2).matcher(s1);
                do
                    if(!matcher1.find())
                        continue label0;
                while(hashmap.get(Integer.valueOf(matcher1.start())) != null);
                s.setSpan(new ColoredURLSpan(amediaentity, aextendedmediaentity) {

                    public void onClick(View view)
                    {
                        (new MediaIntentUtils()).startActivityByMediaStatusURL(activity, entity.getExpandedURL(), screenName, mediaEntities, extendedEntities, null);
                    }

                    final TextViewLinkUtils this$0;
                    final ViewPagerActivity val$activity;
                    final URLEntity val$entity;
                    final ExtendedMediaEntity val$extendedEntities[];
                    final MediaEntity val$mediaEntities[];
                    final String val$screenName;

            
            {
                this$0 = TextViewLinkUtils.this;
                activity = viewpageractivity;
                entity = urlentity;
                screenName = s1;
                mediaEntities = amediaentity;
                extendedEntities = aextendedmediaentity;
                super(final_s, final_i);
            }
                }
, matcher1.start(), matcher1.end(), 33);
                hashmap.put(Integer.valueOf(matcher1.start()), Boolean.valueOf(true));
            }

        }
        if(amediaentity != null)
        {
            int j1 = amediaentity.length;
            for(int j = 0; j < j1; j++)
            {
                final_s = amediaentity[j];
                for(Matcher matcher = Pattern.compile(Pattern.quote(final_s.getDisplayURL()), 2).matcher(s1); matcher.find(); s.setSpan(new ColoredURLSpan(amediaentity, aextendedmediaentity) {

        public void onClick(View view)
        {
            (new MediaIntentUtils()).startActivityByMediaStatusURL(activity, entity.getExpandedURL(), screenName, mediaEntities, extendedEntities, null);
        }

        final TextViewLinkUtils this$0;
        final ViewPagerActivity val$activity;
        final MediaEntity val$entity;
        final ExtendedMediaEntity val$extendedEntities[];
        final MediaEntity val$mediaEntities[];
        final String val$screenName;

            
            {
                this$0 = TextViewLinkUtils.this;
                activity = viewpageractivity;
                entity = mediaentity;
                screenName = s1;
                mediaEntities = amediaentity;
                extendedEntities = aextendedmediaentity;
                super(final_s, final_i);
            }
    }
, matcher.start(), matcher.end(), 33));
            }

        }
        if(ausermentionentity != null)
        {
            int k1 = ausermentionentity.length;
            for(int k = 0; k < k1; k++)
            {
                final_s = ausermentionentity[k];
                final_s = (new StringBuilder()).append("@").append(final_s.getScreenName()).toString();
                for(amediaentity = Pattern.compile(Pattern.quote(final_s), 2).matcher(s1); amediaentity.find(); s.setSpan(new ColoredURLSpan(final_s, viewpageractivity) {

        public void onClick(View view)
        {
            view = new Bundle();
            view.putLong("user_id", entity.getId());
            (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/UserPageActivity, EnumViewPagerFragment.USER_PAGE, view);
        }

        final TextViewLinkUtils this$0;
        final ViewPagerActivity val$activity;
        final UserMentionEntity val$entity;

            
            {
                this$0 = TextViewLinkUtils.this;
                entity = usermentionentity;
                activity = viewpageractivity;
                super(final_s, final_i);
            }
    }
, amediaentity.start(), amediaentity.end(), 33));
            }

        }
        if(ahashtagentity != null)
        {
            int l1 = ahashtagentity.length;
            for(int l = 0; l < l1; l++)
            {
                final_s = ahashtagentity[l];
                final_s = (new StringBuilder()).append("#").append(final_s.getText()).toString();
                for(final_s = Pattern.compile(Pattern.quote(final_s), 2).matcher(s1); final_s.find(); s.setSpan(new ColoredURLSpan(final_s, viewpageractivity) {

        public void onClick(View view)
        {
            view = new Bundle();
            view.putString("query", hashtag);
            (new IntentUtil()).startActivityOrAddFragment(activity, net/wakamesoba98/sobacha/view/activity/SearchActivity, EnumViewPagerFragment.SEARCH, view);
        }

        final TextViewLinkUtils this$0;
        final ViewPagerActivity val$activity;
        final String val$hashtag;

            
            {
                this$0 = TextViewLinkUtils.this;
                hashtag = s1;
                activity = viewpageractivity;
                super(final_s, final_i);
            }
    }
, final_s.start(), final_s.end(), 33));
            }

        }
        return s;
    }
}
