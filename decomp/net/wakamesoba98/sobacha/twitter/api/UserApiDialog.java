// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.Context;
import net.wakamesoba98.sobacha.dialog.ConfirmDialog;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.listener.OnRelationshipGotListener;

// Referenced classes of package net.wakamesoba98.sobacha.twitter.api:
//            UserApi

public class UserApiDialog
{

    public UserApiDialog(UserAccount useraccount)
    {
        userAccount = useraccount;
    }

    public void createBlock(final Context context, final long sourceId, final long targetId, final OnRelationshipGotListener listener)
    {
        (new ConfirmDialog() {

            public void onPositiveButtonClick()
            {
                (new UserApi(context, userAccount)).createBlock(context, listener, sourceId, targetId);
            }

            final UserApiDialog this$0;
            final Context val$context;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApiDialog.this;
                context = context1;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                super();
            }
        }
).build(context, 0x7f07002c, 0x7f070021);
    }

    public void destroyBlock(final Context context, final long sourceId, final long targetId, final OnRelationshipGotListener listener)
    {
        (new ConfirmDialog() {

            public void onPositiveButtonClick()
            {
                (new UserApi(context, userAccount)).destroyBlock(context, listener, sourceId, targetId);
            }

            final UserApiDialog this$0;
            final Context val$context;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApiDialog.this;
                context = context1;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                super();
            }
        }
).build(context, 0x7f07012d, 0x7f070027);
    }

    public void destroyFriendship(final Context context, final long sourceId, final long targetId, final OnRelationshipGotListener listener)
    {
        (new ConfirmDialog() {

            public void onPositiveButtonClick()
            {
                (new UserApi(context, userAccount)).destroyFriendship(context, listener, sourceId, targetId);
            }

            final UserApiDialog this$0;
            final Context val$context;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApiDialog.this;
                context = context1;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                super();
            }
        }
).build(context, 0x7f07012f, 0x7f070028);
    }

    public void reportSpam(final Context context, final long sourceId, final long targetId, final OnRelationshipGotListener listener)
    {
        (new ConfirmDialog() {

            public void onPositiveButtonClick()
            {
                (new UserApi(context, userAccount)).reportSpam(context, listener, sourceId, targetId);
            }

            final UserApiDialog this$0;
            final Context val$context;
            final OnRelationshipGotListener val$listener;
            final long val$sourceId;
            final long val$targetId;

            
            {
                this$0 = UserApiDialog.this;
                context = context1;
                listener = onrelationshipgotlistener;
                sourceId = l;
                targetId = l1;
                super();
            }
        }
).build(context, 0x7f0700ed, 0x7f070026);
    }

    private UserAccount userAccount;

}
