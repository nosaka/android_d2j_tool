// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import java.util.*;
import net.wakamesoba98.sobacha.core.*;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.*;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.util.AccountUtil;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            ShareActivity, UserPageActivity, ConversationActivity

public class IntentManageActivity extends Activity
    implements OnStatusActionFinishedListener, OnUserActionFinishedListener, OnUserDetailGotListener
{

    public IntentManageActivity()
    {
    }

    private void showFollowDialog(final UserAccount account, final User user)
    {
        (new ConfirmDialog() {

            public void onDialogCancelled()
            {
                finish();
            }

            public void onNegativeButtonClick()
            {
                finish();
            }

            public void onPositiveButtonClick()
            {
                (new UserApi(IntentManageActivity.this, account)).createFriendship(IntentManageActivity.this, IntentManageActivity.this, account.getId(), user.getId());
            }

            final IntentManageActivity this$0;
            final UserAccount val$account;
            final User val$user;

            
            {
                this$0 = IntentManageActivity.this;
                account = useraccount;
                user = user1;
                super();
            }
        }
).build(this, 0x7f070099, 0x7f07009b, (new StringBuilder()).append("@").append(user.getScreenName()).toString());
    }

    private void startTweetIntent(String s, String s1, String s2, String s3, long l)
    {
        ArrayList arraylist = new ArrayList();
        if(s != null)
            arraylist.add(s);
        if(s1 != null)
            arraylist.add(s1);
        if(s2 != null)
        {
            s = s2.split(",");
            int k = s.length;
            for(int i = 0; i < k; i++)
            {
                s1 = s[i];
                arraylist.add((new StringBuilder()).append("#").append(s1).toString());
            }

        }
        if(s3 != null)
            arraylist.add(String.format(ResourceHelper.getString(this, 0x7f07013a), new Object[] {
                (new StringBuilder()).append("@").append(s3).toString()
            }));
        s = "";
        for(int j = 0; j < arraylist.size(); j++)
        {
            s1 = (new StringBuilder()).append(s).append((String)arraylist.get(j)).toString();
            s = s1;
            if(j < arraylist.size() - 1)
                s = (new StringBuilder()).append(s1).append(" ").toString();
        }

        s1 = new Intent(this, net/wakamesoba98/sobacha/view/activity/ShareActivity);
        s2 = new Bundle();
        s2.putString("text", s);
        s2.putLong("in_reply_to", l);
        s1.putExtras(s2);
        startActivity(s1);
        finish();
    }

    public void finishedStatusAction()
    {
        finish();
    }

    public void finishedUserAction()
    {
        finish();
    }

    public void gotUserDetail(User user)
    {
        if(user == null)
        {
            Notificator.toast(this, 0x7f07011e);
            finish();
            return;
        }
        long l = user.getId();
        if(l > 0L)
        {
            user = new Bundle();
            user.putLong("user_id", l);
            Intent intent = new Intent(this, net/wakamesoba98/sobacha/view/activity/UserPageActivity);
            intent.putExtras(user);
            startActivity(intent);
        } else
        {
            Notificator.toast(this, 0x7f07011e);
        }
        finish();
    }

    protected void onCreate(final Bundle account)
    {
        super.onCreate(account);
        setContentView(0x7f03001e);
        Object obj = getIntent();
        if(obj == null)
        {
            finish();
            return;
        }
        account = (SobaChaApplication)getApplication();
        if((new AccountUtil()).accountInitialize(this, account.getUserId()))
        {
            finish();
            return;
        }
        account.loadPreferences(LoadMode.ONLY_TOKEN);
        account = account.getUserAccount();
        if("android.intent.action.VIEW".equals(((Intent) (obj)).getAction()))
        {
            obj = ((Intent) (obj)).getData();
            if(obj != null)
            {
                final String text = ((Uri) (obj)).getPath();
                if(text.equals("/intent/retweet"))
                {
                    final long statusId = Long.parseLong(((Uri) (obj)).getQueryParameter("tweet_id"));
                    (new StatusApi(this, account)).showStatus(new OnStatusReceivedListener() {

                        public void receivedStatus(Status status)
                        {
                            if(status == null)
                            {
                                return;
                            } else
                            {
                                StatusUrlUtils statusurlutils = new StatusUrlUtils();
                                ProgressBar progressbar = (ProgressBar)findViewById(0x7f0e007d);
                                (new StatusActionApiDialog(IntentManageActivity.this, account, progressbar)).retweet(IntentManageActivity.this, statusId, statusurlutils.replaceToDisplayURL(status));
                                return;
                            }
                        }

                        final IntentManageActivity this$0;
                        final UserAccount val$account;
                        final long val$statusId;

            
            {
                this$0 = IntentManageActivity.this;
                account = useraccount;
                statusId = l;
                super();
            }
                    }
, statusId);
                    return;
                }
                if(text.equals("/intent/favorite") || text.equals("/intent/like"))
                {
                    final long statusId = Long.parseLong(((Uri) (obj)).getQueryParameter("tweet_id"));
                    (new StatusApi(this, account)).showStatus(new OnStatusReceivedListener() {

                        public void receivedStatus(Status status)
                        {
                            if(status == null)
                            {
                                return;
                            } else
                            {
                                StatusUrlUtils statusurlutils = new StatusUrlUtils();
                                ProgressBar progressbar = (ProgressBar)findViewById(0x7f0e007d);
                                (new StatusActionApiDialog(IntentManageActivity.this, account, progressbar)).favorite(IntentManageActivity.this, statusId, statusurlutils.replaceToDisplayURL(status));
                                return;
                            }
                        }

                        final IntentManageActivity this$0;
                        final UserAccount val$account;
                        final long val$statusId;

            
            {
                this$0 = IntentManageActivity.this;
                account = useraccount;
                statusId = l;
                super();
            }
                    }
, statusId);
                    return;
                }
                if(text.equals("/intent/tweet") || text.equals("/share"))
                {
                    text = ((Uri) (obj)).getQueryParameter("text");
                    final String url = ((Uri) (obj)).getQueryParameter("url");
                    final String hashtags = ((Uri) (obj)).getQueryParameter("hashtags");
                    final String via = ((Uri) (obj)).getQueryParameter("via");
                    obj = ((Uri) (obj)).getQueryParameter("in_reply_to");
                    if(obj != null && ((String) (obj)).matches("[0-9]+"))
                    {
                        final long inReplyTo = Long.parseLong(((String) (obj)));
                        (new StatusApi(this, account)).showStatus(new OnStatusReceivedListener() {

                            public void receivedStatus(Status status)
                            {
                                if(status == null)
                                    return;
                                Object obj1 = new ArrayList();
                                ((List) (obj1)).add(status.getUser().getScreenName());
                                status = status.getUserMentionEntities();
                                int j = status.length;
                                for(int i = 0; i < j; i++)
                                    ((List) (obj1)).add(status[i].getScreenName());

                                status = "";
                                for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext();)
                                {
                                    String s1 = (String)((Iterator) (obj1)).next();
                                    status = (new StringBuilder()).append(status).append("@").append(s1).append(" ").toString();
                                }

                                obj1 = status;
                                if(text != null)
                                    obj1 = (new StringBuilder()).append(status).append(text).toString();
                                startTweetIntent(((String) (obj1)), url, hashtags, via, inReplyTo);
                            }

                            final IntentManageActivity this$0;
                            final String val$hashtags;
                            final long val$inReplyTo;
                            final String val$text;
                            final String val$url;
                            final String val$via;

            
            {
                this$0 = IntentManageActivity.this;
                text = s;
                url = s1;
                hashtags = s2;
                via = s3;
                inReplyTo = l;
                super();
            }
                        }
, inReplyTo);
                        return;
                    } else
                    {
                        startTweetIntent(text, url, hashtags, via, -1L);
                        return;
                    }
                }
                if(text.equals("/intent/follow"))
                {
                    text = ((Uri) (obj)).getQueryParameter("user_id");
                    obj = ((Uri) (obj)).getQueryParameter("screen_name");
                    UserApi userapi = new UserApi(this, account);
                    account = new OnUserDetailGotListener() {

                        public void gotUserDetail(User user)
                        {
                            showFollowDialog(account, user);
                        }

                        final IntentManageActivity this$0;
                        final UserAccount val$account;

            
            {
                this$0 = IntentManageActivity.this;
                account = useraccount;
                super();
            }
                    }
;
                    if(text != null)
                    {
                        userapi.getUserDetail(account, Long.parseLong(text));
                        return;
                    } else
                    {
                        userapi.getUserDetail(account, ((String) (obj)));
                        return;
                    }
                }
                if(text.equals("/intent/user"))
                {
                    text = ((Uri) (obj)).getQueryParameter("user_id");
                    obj = ((Uri) (obj)).getQueryParameter("screen_name");
                    account = new UserApi(this, account);
                    if(text != null)
                    {
                        account.getUserDetail(this, Long.parseLong(text));
                        return;
                    } else
                    {
                        account.getUserDetail(this, ((String) (obj)));
                        return;
                    }
                }
                if(text.matches("/(i/)?[a-zA-Z0-9_]+/status/\\d+(/(photo|video)/\\d+)?$"))
                {
                    account = text.split("/");
                    long l;
                    Bundle bundle;
                    if(text.contains("/i/"))
                        l = Long.parseLong(account[4]);
                    else
                        l = Long.parseLong(account[3]);
                    account = new Intent(this, net/wakamesoba98/sobacha/view/activity/ConversationActivity);
                    bundle = new Bundle();
                    bundle.putLong("status_id", l);
                    account.putExtras(bundle);
                    startActivity(account);
                    finish();
                    return;
                }
                if(text.matches("/[a-zA-Z0-9_]+$"))
                {
                    String s = text.split("/", 0)[1];
                    (new UserApi(this, account)).getUserDetail(this, s);
                    return;
                } else
                {
                    Notificator.toast(this, 0x7f07011e);
                    finish();
                    return;
                }
            } else
            {
                Notificator.toast(this, 0x7f07011d);
                finish();
                return;
            }
        } else
        {
            Notificator.toast(this, 0x7f07011d);
            finish();
            return;
        }
    }

    private static final String INTENT_FAVORITE = "/intent/favorite";
    private static final String INTENT_FOLLOW = "/intent/follow";
    private static final String INTENT_LIKE = "/intent/like";
    private static final String INTENT_RETWEET = "/intent/retweet";
    private static final String INTENT_SHARE = "/share";
    private static final String INTENT_TWEET = "/intent/tweet";
    private static final String INTENT_USER = "/intent/user";
    private static final String QUERY_HASHTAGS = "hashtags";
    private static final String QUERY_IN_REPLY_TO = "in_reply_to";
    private static final String QUERY_SCREEN_NAME = "screen_name";
    private static final String QUERY_TEXT = "text";
    private static final String QUERY_TWEET_ID = "tweet_id";
    private static final String QUERY_URL = "url";
    private static final String QUERY_USER_ID = "user_id";
    private static final String QUERY_VIA = "via";
    private static final String URL_STATUS = "/(i/)?[a-zA-Z0-9_]+/status/\\d+(/(photo|video)/\\d+)?$";
    private static final String URL_USER = "/[a-zA-Z0-9_]+$";


}
