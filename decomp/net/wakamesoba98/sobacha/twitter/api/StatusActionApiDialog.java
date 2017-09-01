// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.app.Activity;
import android.net.Uri;
import android.widget.Button;
import android.widget.ProgressBar;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.*;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.api:
//            StatusActionApi, DirectMessageSendApi, UpdateApi

public class StatusActionApiDialog
{

    public StatusActionApiDialog(Activity activity1, UserAccount useraccount, ProgressBar progressbar)
    {
        activity = activity1;
        userAccount = useraccount;
        progressBar = progressbar;
        postDialog = PreferenceUtil.getBooleanPreference(activity1, EnumPrefs.CONFIRM_POST);
        retweetDialog = PreferenceUtil.getBooleanPreference(activity1, EnumPrefs.CONFIRM_RETWEET);
        favoriteDialog = PreferenceUtil.getBooleanPreference(activity1, EnumPrefs.CONFIRM_FAVORITE);
    }

    private String shortenText(String s)
    {
        String s1 = s;
        if(s.length() > 20)
        {
            s = s.substring(0, 20);
            s1 = (new StringBuilder()).append(s).append("...").toString();
        }
        return s1;
    }

    private void showProgress()
    {
        if(progressBar != null)
            progressBar.setVisibility(0);
    }

    public void destroy(OnStatusDeletedListener onstatusdeletedlistener, long l, String s)
    {
        (new ConfirmDialog() {

            public void onDialogCancelled()
            {
                listener.deletedStatus(-1L);
            }

            public void onNegativeButtonClick()
            {
                listener.deletedStatus(-1L);
            }

            public void onPositiveButtonClick()
            {
                showProgress();
                api.destroy(listener, statusId);
            }

            final StatusActionApiDialog this$0;
            final StatusActionApi val$api;
            final OnStatusDeletedListener val$listener;
            final long val$statusId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusdeletedlistener;
                statusId = l;
                super();
            }
        }
).build(activity, 0x7f07003d, 0x7f070023, shortenText(s));
    }

    public void destroyMessage(OnStatusDeletedListener onstatusdeletedlistener, long l, String s)
    {
        (new ConfirmDialog() {

            public void onDialogCancelled()
            {
                listener.deletedStatus(-1L);
            }

            public void onNegativeButtonClick()
            {
                listener.deletedStatus(-1L);
            }

            public void onPositiveButtonClick()
            {
                showProgress();
                api.destroyMessage(listener, messageId);
            }

            final StatusActionApiDialog this$0;
            final StatusActionApi val$api;
            final OnStatusDeletedListener val$listener;
            final long val$messageId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusdeletedlistener;
                messageId = l;
                super();
            }
        }
).build(activity, 0x7f07003d, 0x7f070022, shortenText(s));
    }

    public void favorite(final OnStatusActionFinishedListener listener, final long statusId, String s)
    {
        final StatusActionApi api = new StatusActionApi(activity, userAccount);
        if(favoriteDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    listener.finishedStatusAction();
                }

                public void onNegativeButtonClick()
                {
                    listener.finishedStatusAction();
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.favorite(listener, statusId);
                }

                final StatusActionApiDialog this$0;
                final StatusActionApi val$api;
                final OnStatusActionFinishedListener val$listener;
                final long val$statusId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusactionfinishedlistener;
                statusId = l;
                super();
            }
            }
).build(activity, 0x7f0700b5, 0x7f0700b8, shortenText(s));
            return;
        } else
        {
            showProgress();
            api.favorite(listener, statusId);
            return;
        }
    }

    public void favoriteRetweet(final OnStatusActionFinishedListener listener, final long statusId, String s)
    {
        final StatusActionApi api = new StatusActionApi(activity, userAccount);
        if(retweetDialog || favoriteDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    listener.finishedStatusAction();
                }

                public void onNegativeButtonClick()
                {
                    listener.finishedStatusAction();
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.favoriteRetweet(listener, statusId);
                }

                final StatusActionApiDialog this$0;
                final StatusActionApi val$api;
                final OnStatusActionFinishedListener val$listener;
                final long val$statusId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusactionfinishedlistener;
                statusId = l;
                super();
            }
            }
).build(activity, 0x7f0700b6, 0x7f0700b7, shortenText(s));
            return;
        } else
        {
            showProgress();
            api.favoriteRetweet(listener, statusId);
            return;
        }
    }

    public void retweet(final OnStatusActionFinishedListener listener, final long statusId, String s)
    {
        final StatusActionApi api = new StatusActionApi(activity, userAccount);
        if(retweetDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    listener.finishedStatusAction();
                }

                public void onNegativeButtonClick()
                {
                    listener.finishedStatusAction();
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.retweet(listener, statusId);
                }

                final StatusActionApiDialog this$0;
                final StatusActionApi val$api;
                final OnStatusActionFinishedListener val$listener;
                final long val$statusId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusactionfinishedlistener;
                statusId = l;
                super();
            }
            }
).build(activity, 0x7f0700f1, 0x7f0700f3, shortenText(s));
            return;
        } else
        {
            showProgress();
            api.retweet(listener, statusId);
            return;
        }
    }

    public void send(final OnDirectMessageSentListener listener, final long targetUserId, final String message, final Button button)
    {
        final DirectMessageSendApi api = new DirectMessageSendApi(activity, userAccount);
        if(postDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    button.setEnabled(true);
                }

                public void onNegativeButtonClick()
                {
                    button.setEnabled(true);
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.send(listener, targetUserId, message);
                }

                final StatusActionApiDialog this$0;
                final DirectMessageSendApi val$api;
                final Button val$button;
                final OnDirectMessageSentListener val$listener;
                final String val$message;
                final long val$targetUserId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = directmessagesendapi;
                listener = ondirectmessagesentlistener;
                targetUserId = l;
                message = s;
                button = button1;
                super();
            }
            }
).build(activity, 0x7f0700c5, 0x7f070101);
            return;
        } else
        {
            showProgress();
            api.send(listener, targetUserId, message);
            return;
        }
    }

    public void unFavorite(final OnStatusActionFinishedListener listener, final long statusId, String s)
    {
        final StatusActionApi api = new StatusActionApi(activity, userAccount);
        if(favoriteDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    listener.finishedStatusAction();
                }

                public void onNegativeButtonClick()
                {
                    listener.finishedStatusAction();
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.unFavorite(listener, statusId);
                }

                final StatusActionApiDialog this$0;
                final StatusActionApi val$api;
                final OnStatusActionFinishedListener val$listener;
                final long val$statusId;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = statusactionapi;
                listener = onstatusactionfinishedlistener;
                statusId = l;
                super();
            }
            }
).build(activity, 0x7f070131, 0x7f070132, shortenText(s));
            return;
        } else
        {
            showProgress();
            api.unFavorite(listener, statusId);
            return;
        }
    }

    public void update(final OnStatusUpdatedListener listener, final String text, final String attachmentUrl, final long inReplyToId, final Uri images[], final Button button)
    {
        final UpdateApi api = new UpdateApi(activity, userAccount);
        if(postDialog)
        {
            (new ConfirmDialog() {

                public void onDialogCancelled()
                {
                    button.setEnabled(true);
                }

                public void onNegativeButtonClick()
                {
                    button.setEnabled(true);
                }

                public void onPositiveButtonClick()
                {
                    showProgress();
                    api.update(listener, text, attachmentUrl, inReplyToId, images);
                }

                final StatusActionApiDialog this$0;
                final UpdateApi val$api;
                final String val$attachmentUrl;
                final Button val$button;
                final Uri val$images[];
                final long val$inReplyToId;
                final OnStatusUpdatedListener val$listener;
                final String val$text;

            
            {
                this$0 = StatusActionApiDialog.this;
                api = updateapi;
                listener = onstatusupdatedlistener;
                text = s;
                attachmentUrl = s1;
                inReplyToId = l;
                images = auri;
                button = button1;
                super();
            }
            }
).build(activity, 0x7f0700e0, 0x7f070102);
            return;
        } else
        {
            showProgress();
            api.update(listener, text, attachmentUrl, inReplyToId, images);
            return;
        }
    }

    private Activity activity;
    private boolean favoriteDialog;
    private boolean postDialog;
    private ProgressBar progressBar;
    private boolean retweetDialog;
    private UserAccount userAccount;

}
